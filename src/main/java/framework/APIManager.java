package framework;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import entities.ConferenceRooms;
import entities.Location;
import entities.OutOfOrders;
import common.EnumKeys;
import entities.Meeting;
import entities.Resource;
import org.json.JSONArray;
import org.json.simple.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created with IntelliJ IDEA.
 * User: jhasmanyquiroz
 * Date: 12/11/15
 * Time: 5:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class APIManager {
    private String token;
    private static APIManager instance;

    protected APIManager() {
        initialize();
    }

    public static APIManager getInstance() {
        if(instance == null)
            instance = new APIManager();
        return instance;
    }

    private void initialize() {
        RestAssured.baseURI = "https://172.20.208.216:4040";
        RestAssured.useRelaxedHTTPSValidation();
        token = getToken();
    }

    private String getToken() {
        Response response = given()
                .parameters("username", "BrayanRosas", "password",
                            "Client123", "authentication", "local")
                .post("/login");

        String json = response.asString();
        JsonPath jp = new JsonPath(json);
        return jp.get("token");
    }

    public Resource createResourceByName(String name) {
        Resource resource = new Resource();
        Response response = given()
                .header("Authorization", "jwt " + token)
                .parameters(EnumKeys.RESOURCE_KEY.name, name, EnumKeys.RESOURCE_KEY.description, "",
                        EnumKeys.RESOURCE_KEY.customName, name, EnumKeys.RESOURCE_KEY.from, "",
                        EnumKeys.RESOURCE_KEY.icon, "fa fa-filter")
                .post("/resources")
                ;

        String json = response.asString();
        JsonPath jp = new JsonPath(json);
        resource = setResource((String)jp.get("_id"), (String)jp.get("name"), (String)jp.get("description"), (String)jp.get("customName"), (String)jp.get("fontIcon"));

        return resource;
    }

    private void createLocationByName(String name) {
        given()
                .header("Authorization", "jwt " + token)
                .parameters("customName", name, "name", name,
                            "description", "")
                .post("/locations")
                ;
    }

    private void deleteResourceByID(String id) {
        given()
            .header("Authorization", "jwt " + token)
            .parameters("id", id)
            .delete("/resources/"+id)
        ;
    }

    private void deleteLocationByID(String _id) {
        given()
                .header("Authorization", "jwt " + token)
                .parameters("id", _id)
                .delete("/locations/"+_id)
        ;
    }

    private Resource setResource(String _id, String name, String description,
                                 String customName, String fontIcon) {
        Resource resource = new Resource();

        resource.setID(_id);
        resource.setName(name);
        resource.setDescription(description);
        resource.setDisplayName(customName);
        resource.setIcon(fontIcon);

        return resource;
    }

    private Location setLocation(String _id, String name, String description,
                                 String customName, String path) {
        Location location = new Location();

        location.setId(_id);
        location.setName(name);
        location.setDescription(description);
        location.setDisplayName(customName);
        location.setParentLocation(path);

        return location;
    }

    private ConferenceRooms setConferenceRooms(String _id, String name, String displayName, String resourceId, String locationId) {
        ConferenceRooms conferenceRooms = new ConferenceRooms();
        conferenceRooms.setId(_id);
        conferenceRooms.setName(name);
        conferenceRooms.setDisplayName(displayName);
        conferenceRooms.setResource(resourceId);
        conferenceRooms.setLocation(locationId);

        return conferenceRooms;
    }


    public ArrayList<Resource> createResourcesByName(ArrayList<String> resourcesName) {
        ArrayList<Resource> resources = new ArrayList<>();
        for (String name : resourcesName) {
            resources.add(createResourceByName(name));
        }
        return resources;
    }

    public ArrayList<Location> createLocationsByName(ArrayList<String> locationsName) {
        ArrayList<Location> locations = new ArrayList<>();
        for (String name : locationsName) {
            createLocationByName(name);
        }
        return locations;
    }

    public void deleteResourcesById(ArrayList<Resource> resources) {
        for (Resource resource : resources) {
            deleteResourceByID(resource.getID());
        }
    }

    public void deleteLocationByID(ArrayList<Location> locations) {
        for (Location location : locations) {
            deleteLocationByID(location.getId());
        }
    }

    public void deleteOutOfOrder(String serviceId, String roomId, String outOfOrderId){
        given()
                .header("Authorization", "jwt " + token)
                .delete("/services/"+serviceId+"rooms/"+roomId+"/out-of-orders"+outOfOrderId)
                ;

    }

    public void activateConferenceRooms(String roomId){
        given()
                .header("Authorization", "jwt "+ token)
                .parameters("enabled", true)
                .put("/rooms/"+roomId)
                ;
    }

    public Resource getResourceByID(String id) {
        Response response = given().when().get("/resources/"+id);
        String json = response.asString();
        JsonPath jp = new JsonPath(json);

        return setResource((String)jp.get(EnumKeys.RESOURCE_KEY._id), (String)jp.get(EnumKeys.RESOURCE_KEY.name),
                            (String)jp.get(EnumKeys.RESOURCE_KEY.description), (String)jp.get(EnumKeys.RESOURCE_KEY.customName),
                            (String)jp.get("fontIcon"));
    }

    public Location getLocationByID(String _id) {
        Response response = given().when().get("/locations/"+_id);
        String json = response.asString();
        JsonPath jp = new JsonPath(json);

        return setLocation((String)jp.get("_id"), (String)jp.get("name"),
                            (String)jp.get("description"),(String)jp.get("customName"),
                            (String)jp.get("path"));
    }

    public ConferenceRooms getConferenceRoomByID(String _id) {
        Response response = given().when().get("/rooms/"+_id);
        String json = response.asString();
        JsonPath jp = new JsonPath(json);
        return setConferenceRooms((String)jp.get("_id"),(String)jp.get("displayName"),
                (String)jp.get("customDisplayName"),(String)jp.get("resources[0].resourceId"),
                (String)jp.get("locationId"));
    }

    public OutOfOrders getOutOfOrderByTitle(String title, String roomId) {
        OutOfOrders outOfOrders = new OutOfOrders();
        Response response = given().when().get("/out-of-orders");
        JSONArray jsonArray = new JSONArray(response.asString());
        System.out.println("JSON - "+jsonArray);
        for (int indice = 0; indice < jsonArray.length(); indice++) {
            if (jsonArray.getJSONObject(indice).getString("roomId").equalsIgnoreCase(roomId)) {
                outOfOrders.set_Id(jsonArray.getJSONObject(indice).getString("_id"));
                outOfOrders.setTitle(jsonArray.getJSONObject(indice).getString("title"));
                outOfOrders.setRoomID(jsonArray.getJSONObject(indice).getString("roomId"));
            }
        }
        return outOfOrders;

    }

    public ConferenceRooms getConferenceRoomByName(String name) {
        ConferenceRooms conferenceRooms = new ConferenceRooms();

        Response response = given().when().get("/rooms");
        JSONArray jsonArray = new JSONArray(response.asString());
        for (int indice = 0; indice < jsonArray.length(); indice++) {
            if (jsonArray.getJSONObject(indice).getString("displayName").equalsIgnoreCase(name)) {
                conferenceRooms.setName(jsonArray.getJSONObject(indice).getString("displayName"));
                conferenceRooms.setDisplayName(jsonArray.getJSONObject(indice).getString("customDisplayName"));
                conferenceRooms.setId(jsonArray.getJSONObject(indice).getString("_id"));
                conferenceRooms.setEnabled(jsonArray.getJSONObject(indice).getBoolean("enabled"));
            }
        }
        return conferenceRooms;
    }

    public Resource getResourceInConferenceRoomById(String roomId, String resourceId){
        Resource resource = new Resource();
        Response response = given().when().get("/rooms/" + roomId + "/resources");
        JSONArray jsonArray = new JSONArray(response.asString());
        System.out.println("JSON - "+jsonArray);
        for (int indice = 0; indice < jsonArray.length(); indice++) {
            System.out.println(jsonArray.getJSONObject(indice).getString("resourceId"));
            if (jsonArray.getJSONObject(indice).getString("resourceId").equalsIgnoreCase(resourceId)) {
                resource.setID(jsonArray.getJSONObject(indice).getString("resourceId"));
                resource.setQuantity(jsonArray.getJSONObject(indice).getInt("quantity"));
            }
        }
        return resource;
    }

    public Resource getResourceByName(String name){
        Resource resource = new Resource();
        Response response = given().when().get("/resources");
        JSONArray jsonArray = new JSONArray(response.asString());
        System.out.println("JSON - "+jsonArray);
        for (int indice = 0; indice < jsonArray.length(); indice++) {
            if (jsonArray.getJSONObject(indice).getString("customName").equalsIgnoreCase(name)) {
                resource.setName(jsonArray.getJSONObject(indice).getString("name"));
                resource.setDisplayName(jsonArray.getJSONObject(indice).getString("customName"));
                resource.setID(jsonArray.getJSONObject(indice).getString("_id"));
            }
        }
        return resource;
    }

    public ArrayList<Resource> getResources() {
        ArrayList<Resource> resources = new ArrayList<>();

        Response response = given().when().get("/resources");
        JSONArray jsonArray = new JSONArray(response.asString());

        for (int indice = 0; indice < jsonArray.length(); indice++) {
            resources.add(setResource(jsonArray.getJSONObject(indice).getString("_id"),
                            jsonArray.getJSONObject(indice).getString("name"),
                            jsonArray.getJSONObject(indice).getString("description"),
                            jsonArray.getJSONObject(indice).getString("customName"),
                            jsonArray.getJSONObject(indice).getString("fontIcon"))
                          );
        }
        return resources;
    }

    public ArrayList<Location> getLocations() {
        ArrayList<Location> locations = new ArrayList<>();

        Response response = given().when().get("/locations");
        JSONArray jsonArray = new JSONArray(response.asString());

        for (int indice = 0; indice < jsonArray.length(); indice++) {
            locations.add(setLocation(jsonArray.getJSONObject(indice).getString("_id"),
                    jsonArray.getJSONObject(indice).getString("name"),
                    jsonArray.getJSONObject(indice).getString("description"),
                    jsonArray.getJSONObject(indice).getString("customName"),
                    jsonArray.getJSONObject(indice).getString("path"))
            );
        }
        return locations;
    }

    public Meeting createMeeting(Meeting meeting, String roomId ) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("organizer", meeting.getOrganizer());
        jsonObject.put("title", meeting.getTitle());
        jsonObject.put("start", meeting.getFromExchange());
        jsonObject.put("end", meeting.getToExchange());
        jsonObject.put("location", CredentialsManager.getInstance().getRoomName());
        jsonObject.put("roomEmail", CredentialsManager.getInstance().getRoomName()+"@forest1.local");
        jsonObject.put("resources", new JSONArray().put(CredentialsManager.getInstance().getRoomName()+"@forest1.local"));
        jsonObject.put("attendees", new JSONArray().put(meeting.getOrganizer()+"@myexchange.com"));

        String userAuthentication = cifrarBase64(CredentialsManager.getInstance().getExchangeUserName()+
                ":"+CredentialsManager.getInstance().getExchangeUserPassword());

        String createEndPoint = replaceEndPoint(CredentialsManager.getInstance().getRoomName());
        postMeeting(jsonObject, createEndPoint, userAuthentication);

        return meeting;
    }

    public static String postMeeting(JSONObject meeting, String endPoint, String key) {
        Response res = given()
                .contentType("application/json")
                .header("Authorization", "Basic " + key)
                .content(meeting.toString())
                .when()
                .post(endPoint);
        return res.asString();
    }

    public static  String replaceEndPoint(String roomName) {
        String serviceLocations = "/locations";
        String serviceLocationById = "/locations/#id#";
        String serviceResources = "/resources";
        String serviceResourcesById = "/resources/#id#";
        String serviceRooms = "/rooms";
        String serviceRoomById = "/rooms/#id#";
        String serviceMeetingById = "services/#serviceId#/rooms/#roomId#/meetings/#meetingId#";
        String serviceMeetings = "services/#serviceId#/rooms/#roomId#/meetings";

        String remServiceId = "#serviceId#";
        String remRoomId = "#roomId#";

        String servicesId = APIManager.getInstance().getServiceId();
        String roomId = DBQuery.getInstance().getRoomIdByName(roomName);
        DBQuery.getInstance().closeMongoDB();

        return serviceMeetings.replace(remServiceId, servicesId)
                .replace(remRoomId, roomId);
    }

    public static String cifrarBase64(String a){
        Base64.Encoder encoder = Base64.getEncoder();
        String encode = encoder.encodeToString(a.getBytes(StandardCharsets.UTF_8) );
        return encode;
    }

    private Meeting setMeeting(String organizer,String title,String from,String to){

        Meeting meeting = new Meeting();
        meeting.setOrganizer(organizer);
        meeting.setTitle(title);
        meeting.setFrom(from);
        meeting.setTo(to) ;
        return meeting;

    }

    public void deleteMeetingById(String idMeeting, String idRoom) {
        String endPoint = "/services/"+getServiceId()+
                "/rooms/"+idRoom+"/meetings/"+idMeeting;
        given()
            .header("Authorization", "Basic amhhc21hbnkucXVpcm96OkNsaWVudDEyMw==")
            .delete(endPoint)
        ;
    }

    public boolean isMeetingInTheRoom(String idMeeting, String roomName) {
        boolean res = false;
        Meeting meeting = new Meeting();

        String endPoint = "/services/"+getServiceId()+
                "/rooms/"+roomName+"/meetings";
        Response response = given().when()
                                .get(endPoint)
        ;
        JSONArray jsonArray = new JSONArray(response.asString());
        for (int indice = 0; indice < jsonArray.length(); indice++) {
            if (jsonArray.getJSONObject(indice).getString("_id").equalsIgnoreCase(idMeeting)) {
                res = true;
                break;
            }
        }

        return res;
    }

    public String getServiceId(){
        Response response = given().header("Authorization", "jwt " + token).get("/services");
        String json = response.asString();
        JSONArray jsonArray = new JSONArray(json);
        return jsonArray.getJSONObject(0).getString("_id");
    }
}
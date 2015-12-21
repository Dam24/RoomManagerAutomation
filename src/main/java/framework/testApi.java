//package framework;
//
//import api.APIManager;
//import com.jayway.restassured.RestAssured;
//import com.jayway.restassured.path.json.JsonPath;
//import com.jayway.restassured.response.Response;
//import database.DBQuery;
//import org.json.simple.JSONObject;
//
//import java.nio.charset.StandardCharsets;
//import java.util.Base64;
//
//import static com.jayway.restassured.RestAssured.given;
//
//public class testApi {
//    public static void main(String ar[]) {
////        Response response =
////        System.out.println("ROOMS - "+
////                given()
////                        .when()
////                        .get("https://172.20.208.195:4040/rooms")
////        );
//        RestAssured.baseURI = "https://172.20.208.216:4040";
//        RestAssured.useRelaxedHTTPSValidation();
//
//        //###########TOKEN
//        Response response = given()
//                .parameters("username", "BrayanRosas", "password",
//                        "Client123", "authentication", "local")
//                .post("/login");
//
//        String json = response.asString();
//        JsonPath jp = new JsonPath(json);
//        String token = jp.get("token");
//
//        //#############OTHER WAY THE OBTAIN THE KEYS
//        //String respuesta = response.asString();
//        //List<String> categories = with(Object).get("store.book.category");
//
//
//        //################PRUEBA DE AUTENTIFICACION
////        Response res =
////                given()
////                .header("Authorization", "jwt "+token)
////                .auth()
////                .oauth2(token)
////                .get("/services");
////
////        System.out.println("SERVICES - "+res.asString());
//
//        //########################### POST
////        Response res = given()
////                .header("Authorization", "jwt " + token)
////                .parameters("name", "TELEVISION-1", "customName", "television-1")
////                //.auth()
////                //.oauth2(token)
////                .post("/resources")
////                ;
////
////        System.out.println("RECURSO CREADO - "+res.asString());
//
//        //########################### PRUEBA AUTENTICACION
//        //RestAssured.authentication = basic("BrayanRosas", "Client123");
//
////        Response res =
////                given()
////                .header("Authorization", "jwt " + token)
////                .get("/services")
////                ;
////        System.out.println("SERVICES - "+res.asString());
//
//
//        //#################API MANAGER
////        ArrayList<String> resources = new ArrayList<>();
////        resources.add("Datadisplay-1");
////        resources.add("Datadisplay-2");
////        APIManager.getInstance().createResourcesByName(resources);
////        ArrayList<Resource> array = APIManager.getInstance().getResources();
////        for (Resource res : array) {
////            System.out.println("#######################GET");
////            System.out.println("RESOURCE - " + res.getID());
////            System.out.println("RESOURCE - " + res.getDisplayName());
////            System.out.println("RESOURCE - "+res.getName());
////        }
////        ArrayList<String> ids = new ArrayList<>();
////        ids.add("566d98019c27d64812f72b92");
////        ids.add("566d98019c27d64812f72b93");
////        APIManager.getInstance().deleteResourcesById(ids);
//
////        ArrayList<Resource> del = APIManager.getInstance().getResources();
////        for (Resource res : del) {
////            System.out.println("#######################DELETE");
////            System.out.println("RESOURCE - " + res.getID());
////            System.out.println("RESOURCE - " + res.getDisplayName());
////            System.out.println("RESOURCE - "+res.getName());
////        }
//////        Response response =
////                given()
////                .when()
////                .get("/resources/5669a6749c27d64812f72b5f")
////                ;
////        String json = response.asString();
////        JsonPath jp = new JsonPath(json);
////        System.out.println("JSON - "+json);
////        System.out.println("NAME - "+jp.get("name"));
////        System.out.println("ID - "+jp.get("_id"));
//
////        Resource res = APIManager.getInstance().getResourceByID("5669a6749c27d64812f72b5f");
////        System.out.println("name "+res.getName());
////        System.out.println("display name "+res.getDisplayName());
////        System.out.println("id "+res.getID());
////        System.out.println("5669a6749c27d64812f72b5f".equalsIgnoreCase(res.getID()));
//
//
//        //############################### LOCATIONS
//        //GET LOCATIONS
////        Response responsee = given().when().get("/locations");
////        JSONArray jsonArray = new JSONArray(responsee.asString());
////        System.out.println("LOCATIONS - "+responsee.asString());
////
////        ArrayList<Location> locations = APIManager.getInstance().getLocations();
////
////        for (Location loc : locations) {
////            System.out.println("#######################LOCATION - "+(loc.getName()));
////            System.out.println("ID - " + loc.getId());
////            System.out.println("DISPLAY NAME - " + loc.getDisplayName());
////            System.out.println("PATH - " + loc.getParentLocation());
////        }
//
//        //GET LOCATION BY ID
////        Location location = APIManager.getInstance().getLocationByID("566c78ad9c27d64812f72b88");
////        System.out.println("############################LOCATION BY ID - "+location.getId());
////        System.out.println("NAME - "+location.getName());
////        System.out.println("DISPLAY NAME - "+location.getDisplayName());
////        System.out.println("Path - "+location.getParentLocation());
////        System.out.println("EQUALS - "+location.getId().equalsIgnoreCase("566c78ad9c27d64812f72b88"));
//
//        //CREATE LOCATION BY NAME
////        Response response1 = given()
////                .header("Authorization", "jwt " + token)
////                .parameters("customName", "Location-101", "name", "Location-101",
////                        "description", "", "path", null)
////                .post("/locations")
////        ;
////        System.out.println("LOCATION CREATE - "+response1.asString());
//
//        //DELETE LOCATION BY ID
////        ArrayList<String> ids = new ArrayList<>();
////        ids.add("566dac629c27d64812f72b9f");
////        ids.add("566dad529c27d64812f72ba1");
////        APIManager.getInstance().deleteLocationByID(ids);
//
//
//        //######################CREATE MEETING
////        Response response1 = given().header("Authorization", "jwt " + token).get("/services");
////        String json1 = response.asString();
////        JsonPath jp1 = new JsonPath(json1);
//        //APIManager.getInstance().getServiceId();
//
////        System.out.println("##### json"+APIManager.getInstance().getServiceId());
////
////        String endPoint = "/services/"+APIManager.getInstance().getServiceId()+
////                "/rooms/"+DBQuery.getInstance().getRoomIdByName("Floor1Room52")+"/meetings/"+
////                DBQuery.getInstance().getMeetingIdByName("TBBT #0");
////        System.out.println("endopoint - "+endPoint);
////        DBManager.getInstance().closeMongoDB();
////
////        Response resp = given()
////                .header("Authorization", "Basic amhhc21hbnkucXVpcm96OkNsaWVudDEyMw==")
////                .delete(endPoint);
////
////        System.out.println("MEETING DELETE - "+ resp.asString());
//
//        // CREATE MEETING
////        String json = {
////                "organizer": "a.user",
////                "title": "my meeting",
////                "start": "2015-03-05T23:00:00.000Z",
////                "end": "2015-03-05T23:30:00.000Z",
////                "location": "Room 102",
////                "roomEmail": "room102@myexchange.com",
////                "resources": [
////                    "room102@myexchange.com"
////                ],
////                "attendees": [
////                    "a.user@myexchange.com"
////                ]
////            }
////        ;
//
//
//        //################### out order
//        String roomId = DBQuery.getInstance().getIdByKey("rooms", "displayName", "Floor1Room6");
//        boolean bandera = false;
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("enabled", bandera);
//        System.out.println("ROOM ID - "+roomId);
//        Response res = given()
//                        .contentType("application/json")
//                        .header("Authorization", "jwt " + token)
//                        .content(jsonObject.toString())
//                        .put("/rooms/" + roomId)
//        ;
//
//        System.out.println("JSON - "+res.asString());
//
//
////
////        Meeting meeting = new Meeting();
////        Date localTime = new Date();
////        SimpleDateFormat converter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
////        Timestamp times = new Timestamp(localTime.getTime());
//////        converter.setTimeZone(TimeZone.getTimeZone("UTC"));
////        String start = converter.format(new Date());
////        System.out.println("time zone - "+start);
////        String star = converter.format(times);
////        String []change = star.split("T");
////        String hour = change[1].split(":")[0];
////        String min = (Integer.parseInt(change[1].split(":")[1])+10)+"";
////        String seg = change[1].split(":")[2];
////
////        System.out.println("Change - "+change[0]);
////        System.out.println("HOUR "+hour);
////        System.out.println("MIN "+min);
////        System.out.println("Seg "+seg);
////        System.out.println("TIME - "+localTime.getTime());
////        System.out.println("FECHA - "+star);
////        System.out.println("FECHA CONVERTIDA "+change[0]+"T"+hour+":"+min+":"+seg);
////        //String userAuthentication = cifrarBase64("jhasmany.quiroz:Client123");
////
////        JSONObject jsonObject = new JSONObject();
////        jsonObject.put("organizer", "jhasmany.quiroz");
////        jsonObject.put("title", "myMeeting #1");
////        jsonObject.put("start", star);
////        jsonObject.put("end", "2015-12-17T10:30:00.000Z");
////        jsonObject.put("location", "Floor1Room71");
////        jsonObject.put("roomEmail", "Floor1Room71"+"@forest1.local");
////        jsonObject.put("resources", new JSONArray().put("Floor1Room71"+"@forest1.local"));
////        jsonObject.put("attendees", new JSONArray().put("a.user@myexchange.com"));
//
//        //String createEndPoint = replaceEndPoint("Floor1Room71");
//        //System.out.println("MEETING - "+postMeeting(jsonObject, createEndPoint, userAuthentication));
//
//
////        String []resources = {"room102@myexchange.com"};
////        String []attendes = {"a.user@myexchange.com"};
////        JSONObject jsonObject = new JSONObject();
////        jsonObject.put("organizer", "jhasmany.quiroz");
////        jsonObject.put("title", "myMeeting");
////        jsonObject.put("start", "2015-12-14T20:00:00.000Z");
////        jsonObject.put("end", "2015-12-14T20:10:00.000Z");
////        jsonObject.put("location", "565f3f459c27d64812f72b23");
////        jsonObject.put("roomEmail", "room102@myexchange.com");
////        jsonObject.put("resources", new JSONArray(resources));
////        jsonObject.put("attendees", new JSONArray(attendes));
////        String endPoint = "/services/"+APIManager.getInstance().getServiceId()+
////                "/rooms/"+DBQuery.getInstance().getRoomIdByName("Floor1Room52")+"/meetings/";
////
////            Response responseCrated = given()
////                    .contentType("content-type/json")
////                    .header("Authorization", "Basic amhhc21hbnkucXVpcm96OkNsaWVudDEyMw==")
////                    .body(jsonObject.toString())
////                    .post(endPoint);
////
////        DBQuery.getInstance().closeMongoDB();
////        String jsonApi = APIManager.getInstance().createMeeting("jhasmany.quiroz", "TBBT #1",
////                "2015-12-14T20:00:00.000Z", "2015-12-14T20:10:00.000Z", "565f3f459c27d64812f72b23",
////                "room102@myexchange.com", resources.toString(), attendes.toString(), DBQuery.getInstance().getRoomIdByName("Floor1Room52"));
////        DBQuery.getInstance().closeMongoDB();
////        System.out.println("MEETING CREATED - "+responseCrated.asString());
//    }
//
//    public static String postMeeting(JSONObject meeting, String endPoint, String key) {
//        Response res = given()
//                .contentType("application/json")
//                .header("Authorization", "Basic " + key)
//                .content(meeting.toString())
//                .when()
//                .post(endPoint);
//        return res.asString();
//    }
//
//    public static  String replaceEndPoint(String roomName) {
//        String serviceLocations = "/locations";
//        String serviceLocationById = "/locations/#id#";
//        String serviceResources = "/resources";
//        String serviceResourcesById = "/resources/#id#";
//        String serviceRooms = "/rooms";
//        String serviceRoomById = "/rooms/#id#";
//        String serviceMeetingById = "services/#serviceId#/rooms/#roomId#/meetings/#meetingId#";
//        String serviceMeetings = "services/#serviceId#/rooms/#roomId#/meetings";
//
//        String remServiceId = "#serviceId#";
//        String remRoomId = "#roomId#";
//
//        String servicesId = APIManager.getInstance().getServiceId();
//        String roomId = DBQuery.getInstance().getRoomIdByName(roomName);
//        DBQuery.getInstance().closeMongoDB();
//
//        return serviceMeetings.replace(remServiceId, servicesId)
//                .replace(remRoomId, roomId);
//    }
//
//    public static String cifrarBase64(String a){
//        Base64.Encoder encoder = Base64.getEncoder();
//        String b = encoder.encodeToString(a.getBytes(StandardCharsets.UTF_8) );
//        return b;
//    }
//}
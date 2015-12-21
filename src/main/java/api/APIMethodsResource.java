package api;

import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import common.*;
import common.Enum;
import entities.ConferenceRoom;
import entities.Resource;
import org.json.JSONArray;
import java.util.ArrayList;
import static com.jayway.restassured.RestAssured.given;

/**
 * Created with IntelliJ IDEA.
 * User: jhasmanyquiroz
 * Date: 12/19/15
 * Time: 6:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class APIMethodsResource {
    private static APIManager apiManager = APIManager.getInstance();

    /**
     * Create a resource with the information of resource
     * @param resource
     * @return
     */
    public static Resource createResource(Resource resource) {
        Response response = given()
                .header(Constant.AUTHORIZATHION, Constant.JWT +apiManager.getToken() )
                .parameters(Enum.RESOURCE_KEY.name, resource.getName(), Enum.RESOURCE_KEY.description, resource.getDescription(),
                        Enum.RESOURCE_KEY.customName, resource.getDisplayName(), Enum.RESOURCE_KEY.from, "",
                        Enum.RESOURCE_KEY.icon, "fa fa-filter")
                .post("/resources");

        String json = response.asString();
        JsonPath jp = new JsonPath(json);
        resource = setResource(jp.get(common.Enum.RESOURCE_KEY.id), jp.get(Enum.RESOURCE_KEY.name), jp.get(Enum.RESOURCE_KEY.description),
                jp.get(Enum.RESOURCE_KEY.customName), jp.get(Enum.RESOURCE_KEY.icon));

        return resource;
    }

    /**
     * Create a list of resources
     * @param resourcesNews
     * @return
     */
    public static ArrayList<Resource> createResources(ArrayList<Resource> resourcesNews) {
        ArrayList<Resource> resources = new ArrayList<>();
        for (Resource resource : resourcesNews) {
            resources.add(createResource(resource));
        }
        return resources;
    }

    /**
     * Delete a resource by id
     * @param resource
     */
    public static void deleteResourceByID(Resource resource) {
        given()
                .header(Constant.AUTHORIZATHION, Constant.JWT + apiManager.getToken())
                .parameters(Enum.RESOURCE_KEY.id, resource.getID())
                .delete("/resources/"+resource.getID());
    }

    /**
     * Delete a list of resources by id
     * @param resources
     */
    public static void deleteResourcesById(ArrayList<Resource> resources) {
        for (Resource resource : resources) {
            deleteResourceByID(resource);
        }
    }

    /**
     * Obtain a resource by id
     * @param resource
     * @return
     */
    public static Resource getResourceByID(Resource resource) {
        Response response = given().when().get("/resources/"+resource.getID());
        String json = response.asString();
        JsonPath jp = new JsonPath(json);

        return setResource(jp.get(Enum.RESOURCE_KEY.id), jp.get(Enum.RESOURCE_KEY.name),
                jp.get(Enum.RESOURCE_KEY.description), jp.get(Enum.RESOURCE_KEY.customName),
                jp.get(Enum.RESOURCE_KEY.icon));
    }

    /**
     * Obtain a list of resources
     * @return
     */
    public static ArrayList<Resource> getResources() {
        ArrayList<Resource> resources = new ArrayList<>();

        Response response = given().when().get("/resources");
        JSONArray jsonArray = new JSONArray(response.asString());

        for (int indice = 0; indice < jsonArray.length(); indice++) {
            resources.add(setResource(jsonArray.getJSONObject(indice).getString(Enum.RESOURCE_KEY.id),
                    jsonArray.getJSONObject(indice).getString(Enum.RESOURCE_KEY.name),
                    jsonArray.getJSONObject(indice).getString(Enum.RESOURCE_KEY.customName),
                    jsonArray.getJSONObject(indice).getString(Enum.RESOURCE_KEY.description),
                    jsonArray.getJSONObject(indice).getString(Enum.RESOURCE_KEY.icon)));
        }
        return resources;
    }

    /**
     * Verify whether a resource is in a room
     * @param conferenceRoom
     * @param resource
     * @return
     */
    public static int getIsResourceInConferenceRoomById(ConferenceRoom conferenceRoom, Resource resource){
        int res=0;
        Response response = given().when().get("/rooms/" + conferenceRoom.getId() + "/resources");
        JSONArray jsonArray = new JSONArray(response.asString());
        for (int indice = 0; indice < jsonArray.length(); indice++) {
            if (jsonArray.getJSONObject(indice).getString(Constant.RESOURCE_ID).equalsIgnoreCase(resource.getID())) {
                res=jsonArray.getJSONObject(indice).getInt(Constant.QUANTITY);
            }
        }
        return res;
    }

    /**
     * Change a Resource with the information.
     * @param _id
     * @param name
     * @param description
     * @param customName
     * @param fontIcon
     * @return
     */
    private static Resource setResource(String _id, String name, String description,
                                        String customName, String fontIcon) {
        Resource resource = new Resource();

        resource.setID(_id);
        resource.setName(name);
        resource.setDescription(description);
        resource.setDisplayName(customName);
        resource.setIcon(fontIcon);

        return resource;
    }

    /**
     * Obtain a list of resources name
     * @return
     */
    public static ArrayList<String> getResourcesNameByApi(){
        ArrayList<Resource> resources = APIMethodsResource.getResources();
        ArrayList<String> names=new ArrayList<>();
        for (Resource resource : resources){
            names.add(resource.getName());
        }
        return names;
    }

    /**
     * Verify if a resource has the same name
     * @param nameSearch
     * @return
     */
    public static boolean foundResourceSameName(String nameSearch){
        ArrayList<String>resourcesNames = getResourcesNameByApi();
        ArrayList<String> namesFounded=new ArrayList<>();
        for (String resourcesName :resourcesNames){
            if(nameSearch.equals(resourcesName)){
                namesFounded.add(resourcesName);
            }
        }
        if(namesFounded.size() > 1) {
            return true;
        }
        else {
            return false;
        }
    }

    /*
     Verify if the Api founded some resource
     @receive a Resource
     @return boolean
      */
    public static  boolean isFoundedTheResourceByApi(Resource resource){
        if(getResourceByID(resource).getID()==resource.getID()) {
            return true;
        } else {
            return false;
        }
    }
}
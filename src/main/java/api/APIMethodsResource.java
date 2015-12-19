package api;

import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import common.*;
import common.Enum;
import entities.ConferenceRooms;
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

    public static Resource createResource(Resource resource) {
        Response response = given()
                .header("Authorization", "jwt " +apiManager.getToken() )
                .parameters(EnumKeys.RESOURCE_KEY.name, resource.getName(), EnumKeys.RESOURCE_KEY.description, resource.getDescription(),
                        EnumKeys.RESOURCE_KEY.customName, resource.getDisplayName(), EnumKeys.RESOURCE_KEY.from, "",
                        EnumKeys.RESOURCE_KEY.icon, "fa fa-filter")
                .post("/resources");

        String json = response.asString();
        JsonPath jp = new JsonPath(json);
        resource = setResource(jp.get(common.Enum.RESOURCE_KEY.id), jp.get(Enum.RESOURCE_KEY.name), jp.get(Enum.RESOURCE_KEY.description),
                jp.get(Enum.RESOURCE_KEY.customName), jp.get(Enum.RESOURCE_KEY.icon));

        return resource;
    }

    public static ArrayList<Resource> createResources(ArrayList<Resource> resourcesNews) {
        ArrayList<Resource> resources = new ArrayList<>();
        for (Resource resource : resourcesNews) {
            resources.add(createResource(resource));
        }
        return resources;
    }

    public static void deleteResourceByID(Resource resource) {
        given()
                .header("Authorization", "jwt " + apiManager.getToken())
                .parameters(Enum.RESOURCE_KEY.id, resource.getID())
                .delete("/resources/"+resource.getID());
    }

    public static void deleteResourcesById(ArrayList<Resource> resources) {
        for (Resource resource : resources) {
            deleteResourceByID(resource);
        }
    }


    public static Resource getResourceByID(String id) {
        Response response = given().when().get("/resources/"+id);
        String json = response.asString();
        JsonPath jp = new JsonPath(json);

        return setResource(jp.get(EnumKeys.RESOURCE_KEY._id), jp.get(EnumKeys.RESOURCE_KEY.name),
                jp.get(EnumKeys.RESOURCE_KEY.description), jp.get(EnumKeys.RESOURCE_KEY.customName),
                jp.get("fontIcon"));
    }

    public static ArrayList<Resource> getResources() {
        ArrayList<Resource> resources = new ArrayList<>();

        Response response = given().when().get("/resources");
        JSONArray jsonArray = new JSONArray(response.asString());

        for (int indice = 0; indice < jsonArray.length(); indice++) {
            resources.add(setResource(jsonArray.getJSONObject(indice).getString(Enum.RESOURCE_KEY.id),
                    jsonArray.getJSONObject(indice).getString(Enum.RESOURCE_KEY.name),
                    jsonArray.getJSONObject(indice).getString(Enum.RESOURCE_KEY.description),
                    jsonArray.getJSONObject(indice).getString(Enum.RESOURCE_KEY.description),
                    jsonArray.getJSONObject(indice).getString(Enum.RESOURCE_KEY.icon)));
        }
        return resources;
    }

    public static Resource getResourceInConferenceRoomById(ConferenceRooms conferenceRooms, Resource resource){
        Resource res = new Resource();
        Response response = given().when().get("/rooms/" + conferenceRooms.getId() + "/resources");
        JSONArray jsonArray = new JSONArray(response.asString());
        for (int indice = 0; indice < jsonArray.length(); indice++) {
            if (jsonArray.getJSONObject(indice).getString("resourceId").equalsIgnoreCase(resource.getID())) {
                res.setID(jsonArray.getJSONObject(indice).getString("resourceId"));
                res.setQuantity(jsonArray.getJSONObject(indice).getInt("quantity"));
            }
        }
        return res;
    }

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
}

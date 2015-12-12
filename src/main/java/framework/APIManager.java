package framework;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.json.JsonPath;
import entities.Resource;
import java.util.ArrayList;
import com.jayway.restassured.response.Response;
import org.json.JSONArray;
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
                .parameters("username", "BrayanRosas", "password", "Client123", "authentication", "local")
                .post("/login");

        String json = response.asString();
        JsonPath jp = new JsonPath(json);
        return jp.get("token");
    }

    private void createResourceByName(String name) {
        given()
                .header("Authorization", "jwt " + token)
                .parameters("name", name, "description", "", "customName", name, "from", "", "fontIcon", "")
                .post("/resources")
        ;
    }

    private void deleteResourceByID(String id) {
        given()
            .header("Authorization", "jwt " + token)
            .parameters("id", id)
            .delete("/resources/"+id)
        ;
    }

    public void createResourcesByName(ArrayList<String> resourcesName) {
        for (String name : resourcesName)
            createResourceByName(name);
    }

    public void deleteResourcesById(ArrayList<String> resourcesID) {
        for (String id : resourcesID) {
            deleteResourceByID(id);
        }
    }

    public Resource getResourceByID(String id) {
        Resource resource = new Resource();

        Response response = given().when().get("/resources/"+id);
        String json = response.asString();
        JsonPath jp = new JsonPath(json);

        resource.setID((String)jp.get("_id"));
        resource.setName((String)jp.get("name"));
        resource.setDescription((String)jp.get("description"));
        resource.setDisplayName((String)jp.get("customName"));
        resource.setIcon((String)jp.get("fontIcon"));

        return resource;
    }

    public ArrayList<Resource> getResources() {
        ArrayList<Resource> resources = new ArrayList<Resource>();

        Response response = given().when().get("/resources");
        JSONArray jsonArray = new JSONArray(response.asString());

        for (int indice = 0; indice < jsonArray.length(); indice++) {
            Resource resource = new Resource();
            resource.setID(jsonArray.getJSONObject(indice).getString("_id"));
            resource.setName(jsonArray.getJSONObject(indice).getString("name"));
            resource.setDescription(jsonArray.getJSONObject(indice).getString("description"));
            resource.setDisplayName(jsonArray.getJSONObject(indice).getString("customName"));
            resource.setIcon(jsonArray.getJSONObject(indice).getString("fontIcon"));
            resources.add(resource);
        }

        return resources;
    }
}
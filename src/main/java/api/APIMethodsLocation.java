package api;

import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import common.*;
import common.Enum;
import entities.Location;

import java.util.ArrayList;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created with IntelliJ IDEA.
 * User: jhasmanyquiroz
 * Date: 12/19/15
 * Time: 6:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class APIMethodsLocation {
    private static APIManager apiManager = APIManager.getInstance();

    public static void createLocation(Location location) {
        given()
                .header("Authorization", "jwt " + apiManager.getToken())
                .parameters(common.Enum.LOCATION_KEY.customName, location.getDisplayName(), Enum.LOCATION_KEY.name, location.getName(),
                        Enum.LOCATION_KEY.description, location.getDescription())
                .post("/locations");
    }

    public static void deleteLocationByID(Location location) {
        given()
                .header("Authorization", "jwt " + apiManager.getToken())
                .parameters(Enum.LOCATION_KEY.id, location.getId())
                .delete("/locations/"+location.getId());
    }

    public static void deleteLocations(ArrayList<Location> locations) {
        for (Location location : locations) {
            deleteLocationByID(location);
        }
    }

    public static Location setLocation(String _id, String name, String description,
                                String customName, String path) {
        Location location = new Location();

        location.setId(_id);
        location.setName(name);
        location.setDescription(description);
        location.setDisplayName(customName);
        location.setParentLocation(path);

        return location;
    }

    public static Location getLocationByID(Location location) {
        Response response = given().when().get("/locations/"+location.getId());
        String json = response.asString();
        JsonPath jp = new JsonPath(json);

        return setLocation(jp.get(Enum.LOCATION_KEY.id), jp.get(Enum.LOCATION_KEY.name),
                jp.get(Enum.LOCATION_KEY.description),jp.get(Enum.LOCATION_KEY.customName),
                jp.get(Enum.LOCATION_KEY.path));
    }
}

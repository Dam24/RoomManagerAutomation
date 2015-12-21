package api;

import common.*;
import common.Enum;
import entities.Location;
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

    /**
     * Create a location
     * @param location
     */
    public static void createLocation(Location location) {
        given()
                .header(Constant.AUTHORIZATHION, Constant.JWT + apiManager.getToken())
                .parameters(common.Enum.LOCATION_KEY.customName, location.getDisplayName(), Enum.LOCATION_KEY.name, location.getName(),
                        Enum.LOCATION_KEY.description, location.getDescription())
                .post("/locations");
    }

    /**
     * Delete a Location
     * @param location
     */
    public static void deleteLocationByID(Location location) {
        given()
                .header(Constant.AUTHORIZATHION, Constant.JWT + apiManager.getToken())
                .parameters(Enum.LOCATION_KEY.id, location.getId())
                .delete("/locations/"+location.getId());
    }
}
package api;

import com.jayway.restassured.response.Response;
import common.Constant;
import entities.ConferenceRoom;
import entities.OutOfOrder;
import org.json.JSONArray;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created with IntelliJ IDEA.
 * User: jhasmanyquiroz
 * Date: 12/19/15
 * Time: 6:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class APIMethodsOutOfOrder {
    private static APIManager apiManager = APIManager.getInstance();

    /**
     * Obtain a OutOfOrder by name
     * @param conferenceRoom
     * @return
     */
    public static OutOfOrder getOutOfOrderByTitle(ConferenceRoom conferenceRoom) {
        OutOfOrder outOfOrders = new OutOfOrder();
        Response response = given().when().get("/out-of-orders");
        JSONArray jsonArray = new JSONArray(response.asString());
        for (int indice = 0; indice < jsonArray.length(); indice++) {
            if (jsonArray.getJSONObject(indice).getString(Constant.ROOM_ID).equalsIgnoreCase(conferenceRoom.getId())) {
                outOfOrders.set_Id(jsonArray.getJSONObject(indice).getString(Constant.ID));
                outOfOrders.setTitle(jsonArray.getJSONObject(indice).getString(Constant.TITLE));
                outOfOrders.setRoomID(jsonArray.getJSONObject(indice).getString(Constant.ROOM_ID));
            }
        }
        return outOfOrders;
    }
}
package api;

import com.jayway.restassured.response.Response;
import entities.OutOfOrders;
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

    public static OutOfOrders getOutOfOrderByTitle(String roomId) {
        OutOfOrders outOfOrders = new OutOfOrders();
        Response response = given().when().get("/out-of-orders");
        JSONArray jsonArray = new JSONArray(response.asString());
        for (int indice = 0; indice < jsonArray.length(); indice++) {
            if (jsonArray.getJSONObject(indice).getString("roomId").equalsIgnoreCase(roomId)) {
                outOfOrders.set_Id(jsonArray.getJSONObject(indice).getString("_id"));
                outOfOrders.setTitle(jsonArray.getJSONObject(indice).getString("title"));
                outOfOrders.setRoomID(jsonArray.getJSONObject(indice).getString("roomId"));
            }
        }
        return outOfOrders;
    }

    public static void deleteOutOfOrder(String serviceId, String roomId, String outOfOrderId){
        given()
                .header("Authorization", "jwt " + apiManager.getToken())
                .delete("/services/"+serviceId+"rooms/"+roomId+"/out-of-orders"+outOfOrderId);
    }

    public static void deleteOutOfOrder(OutOfOrders outOfOrder){
        given()
                .header("Authorization", "jwt " + apiManager.getToken())
                .delete("/services/"+apiManager.getServiceId()+"rooms/"+outOfOrder.getRoomID()+"/out-of-orders"+
                        outOfOrder.get_Id())
        ;

    }
}

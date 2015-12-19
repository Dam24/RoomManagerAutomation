package api;

import com.jayway.restassured.response.Response;
import common.Enum;
import entities.ConferenceRooms;
import org.json.JSONArray;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created with IntelliJ IDEA.
 * User: jhasmanyquiroz
 * Date: 12/19/15
 * Time: 6:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class APIMethodsRoom {
    private static APIManager apiManager = APIManager.getInstance();

    public static void activateConferenceRooms(String roomId){
        given()
                .header("Authorization", "jwt "+ apiManager.getToken())
                .parameters("enabled", true)
                .put("/rooms/"+roomId);
    }

    //REVISAR
    public static ConferenceRooms getConferenceRoomByName(ConferenceRooms conferenceRooms) {
        ConferenceRooms conferenceRooms1 = new ConferenceRooms();

        Response response = given().when().get("/rooms");
        JSONArray jsonArray = new JSONArray(response.asString());
        for (int indice = 0; indice < jsonArray.length(); indice++) {
            if (jsonArray.getJSONObject(indice).getString(common.Enum.ROOM_KEY.name).equalsIgnoreCase(conferenceRooms.getName())) {
                conferenceRooms1.setName(jsonArray.getJSONObject(indice).getString(Enum.ROOM_KEY.name));
                conferenceRooms1.setDisplayName(jsonArray.getJSONObject(indice).getString(Enum.ROOM_KEY.customName));
                conferenceRooms1.setId(jsonArray.getJSONObject(indice).getString(Enum.ROOM_KEY.id));
                conferenceRooms1.setEnabled(jsonArray.getJSONObject(indice).getBoolean(Enum.ROOM_KEY.enable));
            }
        }
        return conferenceRooms1;
    }
}

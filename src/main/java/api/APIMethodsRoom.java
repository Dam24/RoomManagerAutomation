package api;

import com.jayway.restassured.response.Response;
import common.Constant;
import common.Enum;
import entities.ConferenceRoom;
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

    /**
     * Activate the conference room
     * @param conferenceRoom
     */
    public static void activateConferenceRooms(ConferenceRoom conferenceRoom){
        given()
                .header(Constant.AUTHORIZATHION, Constant.JWT+ apiManager.getToken())
                .parameters(Enum.ROOM_KEY.enable, true)
                .put("/rooms/" + conferenceRoom.getId());
    }

    /**
     * Obtain a conferenceRoom with the same name of parameter
     * @param conferenceRooms
     * @return a Conference Room
     */
    public static ConferenceRoom getConferenceRoomByName(ConferenceRoom conferenceRooms) {
        ConferenceRoom conferenceRooms1 = new ConferenceRoom();

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
package api;

import com.jayway.restassured.response.Response;
import common.*;
import common.Enum;
import entities.ConferenceRoom;
import entities.Meeting;
import framework.CredentialsManager;
import org.json.JSONArray;
import org.json.simple.JSONObject;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created with IntelliJ IDEA.
 * User: jhasmanyquiroz
 * Date: 12/19/15
 * Time: 6:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class APIMethodsMeeting {
    private static APIManager apiManager = APIManager.getInstance();

    public static Meeting createMeeting(Meeting meeting, ConferenceRoom conferenceRoom) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(common.Enum.MEETING_kEY.organizer, meeting.getOrganizer());
        jsonObject.put(Enum.MEETING_kEY.title, meeting.getTitle());
        jsonObject.put(Enum.MEETING_kEY.from, meeting.getFromExchange());
        jsonObject.put(Enum.MEETING_kEY.end, meeting.getToExchange());
        jsonObject.put(Enum.MEETING_kEY.location, CredentialsManager.getInstance().getRoomName());
        jsonObject.put(Enum.MEETING_kEY.roomEmail, CredentialsManager.getInstance().getRoomName()+Constant.DOMAIN);
        jsonObject.put(Enum.MEETING_kEY.resources, new JSONArray().put(CredentialsManager.getInstance().getRoomName()+Constant.DOMAIN));
        jsonObject.put(Enum.MEETING_kEY.attendees, new JSONArray().put(meeting.getOrganizer()+Constant.DOMAIN));

        String userAuthentication = apiManager.getBasicAuthentication();

        String createEndPoint = apiManager.replaceEndPoint(conferenceRoom);
        postMeeting(jsonObject, createEndPoint, userAuthentication);

        return meeting;
    }

    private static String postMeeting(JSONObject meeting, String endPoint, String key) {
        Response res = given()
                .contentType(Constant.CONTENT_TYPE)
                .header(Constant.AUTHORIZATHION, Constant.BASIC + key)
                .content(meeting.toString())
                .when()
                .post(endPoint);
        return res.asString();
    }

    public static void deleteMeetingById(Meeting meeting, String idRoom) {
        String endPoint = "/services/"+apiManager.getServiceId()+
                "/rooms/"+idRoom+"/meetings/"+meeting.getId();
        given()
                .header(Constant.AUTHORIZATHION, Constant.BASIC + APIManager.getInstance().getBasicAuthentication())
                .delete(endPoint)
        ;
    }

    public static boolean isMeetingInTheRoom(Meeting meeting, String roomId) {
        boolean res = false;

        String endPoint = "/services/"+apiManager.getServiceId()+
                "/rooms/"+roomId+"/meetings";
        Response response = given().when().get(endPoint);
        JSONArray jsonArray = new JSONArray(response.asString());
        for (int indice = 0; indice < jsonArray.length(); indice++) {
            if (jsonArray.getJSONObject(indice).getString(Enum.MEETING_kEY.id).equalsIgnoreCase(meeting.getId())) {
                res = true;
                break;
            }
        }

        return res;
    }
}

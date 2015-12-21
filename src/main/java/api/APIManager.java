package api;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import common.*;
import common.Enum;
import entities.ConferenceRooms;
import entities.Location;
import entities.OutOfOrders;
import entities.Meeting;
import entities.Resource;
import framework.CredentialsManager;
import database.DBQuery;
import org.json.JSONArray;
import org.json.simple.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;

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
    private static CredentialsManager credentials;

    protected APIManager() {
        initialize();
    }

    public static APIManager getInstance() {
        if(instance == null)
            instance = new APIManager();
        return instance;
    }

    private void initialize() {
        credentials = CredentialsManager.getInstance();
        RestAssured.baseURI = credentials.getApiServiceURL();
        RestAssured.useRelaxedHTTPSValidation();
        token = getTokenAPI();
    }

    public String getToken() {
        return token;
    }

    private String getTokenAPI() {
        Response response = given()
                .parameters("username", credentials.getAdminUserName(), "password",
                        credentials.getAdminUserPassword(), "authentication", "local")
                .post("/login");

        String json = response.asString();
        JsonPath jp = new JsonPath(json);
        return jp.get("token");
    }

    public static String replaceEndPoint(String roomName) {
        String serviceLocations = "/locations";
        String serviceLocationById = "/locations/#id#";
        String serviceResources = "/resources";
        String serviceResourcesById = "/resources/#id#";
        String serviceRooms = "/rooms";
        String serviceRoomById = "/rooms/#id#";
        String serviceMeetingById = "services/#serviceId#/rooms/#roomId#/meetings/#meetingId#";
        String serviceMeetings = "services/#serviceId#/rooms/#roomId#/meetings";

        String remServiceId = "#serviceId#";
        String remRoomId = "#roomId#";

        String servicesId = APIManager.getInstance().getServiceId();
        String roomId = DBQuery.getInstance().getRoomIdByName(roomName);
        DBQuery.getInstance().closeMongoDB();

        return serviceMeetings.replace(remServiceId, servicesId)
                .replace(remRoomId, roomId);
    }

    public String encodeBase64(String a){
        Base64.Encoder encoder = Base64.getEncoder();
        String encode = encoder.encodeToString(a.getBytes(StandardCharsets.UTF_8) );
        return encode;
    }

    public String getServiceId(){
        Response response = given().header("Authorization", "jwt " + token).get("/services");
        String json = response.asString();
        JSONArray jsonArray = new JSONArray(json);
        return jsonArray.getJSONObject(0).getString(Enum.RESOURCE_KEY.id);
    }

    public String getBasicAuthentication() {
        return encodeBase64(CredentialsManager.getInstance().getExchangeUserName()+
                ":"+CredentialsManager.getInstance().getExchangeUserPassword());
    }
}
package api;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import common.Constant;
import common.Enum;
import entities.ConferenceRoom;
import framework.CredentialsManager;
import database.DBQuery;
import org.json.JSONArray;
import java.nio.charset.StandardCharsets;
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
                .parameters(Constant.USERNAME, credentials.getAdminUserName(), Constant.PASSWORD,
                        credentials.getAdminUserPassword(), Constant.AUTHENTICATION, Constant.LOCAL)
                .post("/login");

        String json = response.asString();
        JsonPath jp = new JsonPath(json);
        return jp.get(Constant.TOKEN);
    }

    public static String replaceEndPoint(ConferenceRoom conferenceRoom) {
        String serviceMeetings = Constant.SERVICEMEETING;
        String remServiceId = "#serviceId#";
        String remRoomId = "#roomId#";

        String servicesId = APIManager.getInstance().getServiceId();
        String roomId = DBQuery.getInstance().getRoomIdByName(conferenceRoom);
        DBQuery.getInstance().closeMongoDB();

        return serviceMeetings.replace(remServiceId, servicesId)
                .replace(remRoomId, roomId);
    }

    private String encodeBase64(String a){
        Base64.Encoder encoder = Base64.getEncoder();
        String encode = encoder.encodeToString(a.getBytes(StandardCharsets.UTF_8) );
        return encode;
    }

    public String getServiceId(){
        Response response = given().header(Constant.AUTHORIZATHION, Constant.JWT + token).get("/services");
        String json = response.asString();
        JSONArray jsonArray = new JSONArray(json);
        return jsonArray.getJSONObject(0).getString(Enum.RESOURCE_KEY.id);
    }

    public String getBasicAuthentication() {
        return encodeBase64(CredentialsManager.getInstance().getExchangeUserName() +
                ":" + CredentialsManager.getInstance().getExchangeUserPassword());
    }
}
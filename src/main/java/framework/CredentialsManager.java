package framework;

import common.JSONReader;

/**
 * Created with IntelliJ IDEA.
 * User: jhasmanyquiroz
 * Date: 12/8/15
 * Time: 3:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class CredentialsManager {
    private JSONReader jsonObjectMain;
    private static  CredentialsManager instance;
    private static String envId;

    private static String adminURL;
    private static String mainURL;
    private static String tabletURL;
    private static String tabletHomeURL;
    private static String resourcesURL;
    private static String conferenceRoomsURL;
    private static String serverURL;
    private static String roomManagerService;
    private static String roomName;
    private static String adminUserName;
    private static String adminUserPassword;
    private static String tabletUserName;
    private static String tabletUserPassword;
    private static String exchangeUserName;
    private static String exchangeUserPassword;
    private static String apiServiceURL;
    private static String mongoServer;
    private static String mongoDataBase;

    protected CredentialsManager() {
        initialize();
    }

    /**
     * Return the instance of CredentialsManager.
    */
    public static CredentialsManager getInstance() {
        if(instance == null) {
            instance = new CredentialsManager();
        }
        return instance;
    }

    /**
     * Initialize the properties of JSON Reader
     * and read the values of environment.json file
    */
    private void initialize() {
        jsonObjectMain = new JSONReader("environment.json");
        String environmentId = System.getProperty("envId");

        if (environmentId == null || environmentId.isEmpty()){
            envId = "RM02";
        } else {
            envId = environmentId;
        }

        adminURL = jsonObjectMain.getKeyValue("Environments", "id", envId, "admin URL");
        mainURL = jsonObjectMain.getKeyValue("Environments", "id", envId, "main URL");
        tabletURL = jsonObjectMain.getKeyValue("Environments", "id", envId, "tablet URL");
        tabletHomeURL = jsonObjectMain.getKeyValue("Environments", "id", envId, "tabletHome URL");
        serverURL = jsonObjectMain.getKeyValue("Environments", "id", envId, "server URL");
        resourcesURL = jsonObjectMain.getKeyValue("Environments", "id", envId, "resources URL");
        conferenceRoomsURL = jsonObjectMain.getKeyValue("Environments", "id", envId, "conferenceRooms URL");
        roomManagerService = jsonObjectMain.getKeyValue("Environments", "id", envId, "room manager service");
        roomName = jsonObjectMain.getKeyValue("Environments", "id", envId, "roomName");
        adminUserName = jsonObjectMain.getKeyValue("Environments", "id", envId, "admin user", "name");
        adminUserPassword = jsonObjectMain.getKeyValue("Environments", "id", envId, "admin user", "password");
        tabletUserName = jsonObjectMain.getKeyValue("Environments", "id", envId, "tablet user", "name");
        tabletUserPassword = jsonObjectMain.getKeyValue("Environments", "id", envId, "tablet user", "password");
        exchangeUserName = jsonObjectMain.getKeyValue("Environments", "id", envId, "exchange user", "name");
        exchangeUserPassword = jsonObjectMain.getKeyValue("Environments", "id", envId, "exchange user", "password");
        apiServiceURL = jsonObjectMain.getKeyValue("Environments", "id", envId, "api", "service");
        mongoServer = jsonObjectMain.getKeyValue("Environments", "id", envId, "MongoDB", "server");
        mongoDataBase = jsonObjectMain.getKeyValue("Environments", "id", envId, "MongoDB", "database");

    }

    /**
     * Return the getters and setters about values.
    */
    public String getBaseAdminURL() {
        return adminURL;
    }

    public String getBaseMainURL() {
        return mainURL;
    }

    public String getBaseTabletURL() {
        return tabletURL;
    }

    public String getRoomManagerService() {
        return roomManagerService;
    }

    public String getBaseServerURL() {
        return serverURL;
    }

    public String getResBaseResourcesURL() {
        return resourcesURL;
    }

    public String getBaseConferenceRooms() {
        return conferenceRoomsURL;
    }

    public String getBaseTabletHomeURL() {
        return tabletHomeURL;
    }

    public String getRoomName() {
        return roomName;
    }

    public String getAdminUserName() {
        return adminUserName;
    }

    public String getAdminUserPassword() {
        return adminUserPassword;
    }

    public String getTabletUserName() {
        return tabletUserName;
    }

    public String getTabletUserPassword() {
        return tabletUserPassword;
    }

    public String getExchangeUserName() {
        return exchangeUserName;
    }

    public String getExchangeUserPassword() {
        return exchangeUserPassword;
    }

    public String getApiServiceURL() {
        return apiServiceURL;
    }

    public String getMongoServer() {
        return mongoServer;
    }

    public String getMongoDataBase() {
        return mongoDataBase;
    }
}
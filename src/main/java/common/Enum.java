package common;

/**
 * Created with IntelliJ IDEA.
 * User: BrayanRosas
 * Date: 12/14/15
 * Time: 8:36 PM
 * To change this template use File | Settings | File Templates.
 */
public enum Enum {

    RESOURCE_KEY ("resourcemodels","name","customName","description","fontIcon","_id","from"),
    ROOM_KEY     ("rooms","displayName","customDisplayName","emailAddress","enabled","_id","locationId","serviceId"),
    LOCATION_KEY  ("locations","name","customName","description","path","_id"),
    MEETING_kEY ("meetings","organizer","title","start","end","location","roomEmail","resources","attendees","_id","serviceId"),
    SERVER_MENU ("Email Servers"),
    IMPERSONATION_MENU ("Impersonation"),
    ROOMS_MENU("Conference Rooms"),
    RESOURCES_MENU("Resources"),
    LOCATIONS_MENU("Locations"),
    TABLETS_MENU("Tablets"),
    SETTINGS_MENU("Settings");

    public String nameCollection;
    public String name;
    public String customName;
    public String icon;
    public String description;
    public String serverId;
    public String from;
    public String enable;
    public String path;
    public String locationId;
    public String start;
    public String end;
    public String location;


    public String organizer;
    public String title;
    public String roomEmail;
    public String resources;
    public String id;
    public String attendees;
    public String option;


    /*
    Enum for RESOURCE_KEY
     */

    private Enum(String nameCollection, String name, String customName, String description, String icon, String id, String from){
        this.nameCollection = nameCollection;
        this.name = name;
        this.customName = customName;
        this.description = description;
        this.icon = icon;
        this.id = id;
        this.from = from;
    }
    /*
    Enum for Room_KEY
     */
    private Enum(String nameCollection, String name, String customName, String roomEmail, String enable, String id,
                 String locationId, String serverId){
        this.nameCollection = nameCollection;
        this.name = name;
        this.customName = customName;
        this.roomEmail = roomEmail;
        this.enable = enable;
        this.id = id;
        this.locationId = locationId;
        this.serverId = serverId;
    }
    /*
    Enum for LOCATION_KEY
     */
    private Enum(String nameCollection, String name, String customName, String description, String path, String id){
        this.nameCollection = nameCollection;
        this.name = name;
        this.customName = customName;
        this.description = description;
        this.path = path;
        this.id = id;
    }

    private Enum(String nameCollection, String organizer, String title, String start, String end, String location,
                 String roomEmail, String resources, String attendees, String id, String serverId){
        this.nameCollection = nameCollection;
        this.organizer = organizer;
        this.title = title;
        this.start = start;
        this.end = end;
        this.location = location;
        this.roomEmail = roomEmail;
        this.resources = resources;
        this.attendees = attendees;
        this.id = id;
        this.serverId = serverId;
    }

    private Enum(String option){
        this.option = option;
    }

}
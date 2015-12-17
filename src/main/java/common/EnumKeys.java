package common;

/**
 * Created with IntelliJ IDEA.
 * User: BrayanRosas
 * Date: 12/14/15
 * Time: 8:36 PM
 * To change this template use File | Settings | File Templates.
 */
public enum EnumKeys {

    RESOURCE_KEY ("resourcemodels","name","customName","description","fontIcon","_id","from",""),
    ROOM_KEY    ("rooms","displayName","customDisplayName","emailAddress","enabled","_id","locationId","serviceId"),
    LOCATION_KEY ("locations","name","customName","description","path","_id","","");

    public final String nameCollection;
    public final String name;
    public final String customName;
    public final String icon;
    public final String description;
    public final String _id;
    public final String from;
    public final String serverId;

   private EnumKeys(String nameCollection,String name,String customName,String icon,String description,String _id,String from,String serverId){
        this.nameCollection=nameCollection;
        this.name=name;
        this.customName=customName;
        this.icon=icon;
        this.description=description;
        this._id=_id;
        this.from=from;
        this.serverId=serverId;
    }
}

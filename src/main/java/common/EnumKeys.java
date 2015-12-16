package common;

/**
 * Created with IntelliJ IDEA.
 * User: BrayanRosas
 * Date: 12/14/15
 * Time: 8:36 PM
 * To change this template use File | Settings | File Templates.
 */
public enum EnumKeys {

    RESOURCEKEY ("name","customName","fontIcon","description","_id","from");

    public final String name;
    public final String customName;
    public final String icon;
    public final String description;
    public final String _id;
    public final String from;

    private EnumKeys(String name,String customName,String icon,String description,String _id,String from){
        this.name=name;
        this.customName=customName;
        this.icon=icon;
        this.description=description;
        this._id=_id;
        this.from=from;
    }
}

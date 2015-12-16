package entities;

/**
 * Created with IntelliJ IDEA.
 * User: DamianVillanueva
 * Date: 12/15/15
 * Time: 12:28 AM
 * To change this template use File | Settings | File Templates.
 */
public class OutOfOrders {
    private String _Id;
    private String Title;
    private String RoomID;

    public void set_Id(String id){
        this._Id=id;
    }

    public String get_Id(){
        return _Id;
    }

    public void setTitle(String title){
        this.Title = title;
    }

    public String getTitle(){
        return Title;
    }

    public void setRoomID(String roomID){
        this.RoomID = roomID;
    }

    public String getRoomID(){
        return RoomID;
    }
}

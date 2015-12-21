package entities;

/**
 * Created with IntelliJ IDEA.
 * User: DamianVillanueva
 * Date: 12/15/15
 * Time: 12:28 AM
 * To change this template use File | Settings | File Templates.
 */
public class OutOfOrders {
    private String id;
    private String title;
    private String roomID;

    public void set_Id(String id){
        this.id = id;
    }

    public String get_Id(){
        return id;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    public void setRoomID(String roomID){
        this.roomID = roomID;
    }

    public String getRoomID(){
        return roomID;
    }
}

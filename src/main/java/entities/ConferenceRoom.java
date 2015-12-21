package entities;

/**
 * Created with IntelliJ IDEA.
 * User: DamianVillanueva
 * Date: 12/9/15
 * Time: 7:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class ConferenceRoom {
    private String Id;
    private String Name;
    private String DisplayName;
    private String Code;
    private String Capacity;
    private String Location;
    private String Resource;
    private Boolean Enabled;

    public void setId(String id){
        this.Id = id;
    }

    public String getId(){
        return Id;
    }

    public void setName(String name){
        this.Name = name;
    }

    public String getName(){
        return Name;
    }

    public void setDisplayName(String displayName){
        this.DisplayName = displayName;
    }

    public String getDisplayName(){
        return DisplayName;
    }

    public void setCode(String code){
        this.Code = code;
    }

    public String getCode(){
        return Code;
    }

    public void setCapacity(String capacity){
        this.Capacity = capacity;
    }

    public String getCapacity(){
        return Capacity;
    }

    public void setLocation(String location){
        this.Location = location;
    }

    public String getLocation(){
        return Location;
    }

    public void setResource(String resource){
        this.Resource=resource;
    }

    public String getResource(){
        return Resource;
    }

    public void setEnabled(Boolean enabled){
        this.Enabled = enabled;
    }

    public Boolean getEnabled(){
        return Enabled;
    }

}
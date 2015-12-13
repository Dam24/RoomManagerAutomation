package entities;

/**
 * Created with IntelliJ IDEA.
 * User: DamianVillanueva
 * Date: 12/9/15
 * Time: 7:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class Location {
    private String Id;
    private String Name;
    private String DisplayName;
    private String ParentLocation;
    private String Description;

    public void setId(String id){
        this.Id=id;
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

    public void setParentLocation(String parentLocation){
        this.ParentLocation = parentLocation;
    }

    public String getParentLocation(){
        return ParentLocation;
    }

    public void setDescription(String description){
        this.Description = description;
    }

    public String getDescription(){
        return Description;
    }
}
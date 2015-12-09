package entities;

/**
 * Created with IntelliJ IDEA.
 * User: BrayanRosas
 * Date: 12/9/15
 * Time: 2:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class Resource {

    String name;
    String displayName;
    String description;
    String icon;

    public Resource(String name, String displayName, String description, String icon){
        this.name=name;
        this.displayName=displayName;
        this.description=description;
        this.icon=icon;
    }

    public void setName(String newName){name=newName;}

    public void setDisplayName(String newDisplayName){displayName=newDisplayName;}

    public void setDescription(String newDescription){description=newDescription;}

    public void setIcon(String newIcon){icon=newIcon;}

    public String getName(){ return name;}

    public String getDisplayName(){ return displayName;}

    public String getDescription(){ return description;}

    public String getIcon(){ return icon;}




}

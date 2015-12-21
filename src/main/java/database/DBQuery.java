package database;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import entities.Resource;
import org.bson.Document;
import com.mongodb.Block;
import java.util.ArrayList;
import common.Enum;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.regex;

/**
 * Created with IntelliJ IDEA.
 * User: jhasmanyquiroz
 * Date: 12/10/15
 * Time: 9:11 AM
 * To change this template use File | Settings | File Templates.
 */
public class DBQuery {
    private static DBQuery instance;

    protected DBQuery() {
    }

    public static DBQuery getInstance() {
        if (instance == null)
            instance = new DBQuery();

        return instance;
    }

    public ArrayList<Resource> getResourcesBySearchCriteria(String searchCriteria) {
        final ArrayList<Resource> resources = new ArrayList<Resource>();
        try {
            FindIterable<Document> iterable =
                    DBManager
                            .getInstance()
                            .getCollection("resourcemodels")
                            .find(
                                    regex("name", searchCriteria)
                            );

            iterable.forEach(new Block<Document>() {
                @Override
                public void apply(final Document document) {
                    Resource resource = new Resource();
                    resource.setName((String)document.get("name"));
                    resource.setDisplayName((String) document.get("customName"));
                    resource.setIcon((String) document.get("fontIcon"));
                    resource.setDescription((String) document.get("description"));
                    resources.add(resource);
                }
            });
        } catch (Exception e) {
            System.err.println("ERROR: "+e.getClass().getName() + ": " + e.getMessage());
        }

        return resources;
    }

    public String getIdByKey(String collection, String key, String value) {
        String res = "";
        MongoCollection<Document> docs =
                DBManager
                        .getInstance()
                        .getCollection(collection)
                        ;
        MongoCursor<Document> cursor =
                docs
                        .find(eq(key, value))
                        .iterator();
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            res += doc.get("_id");
        }
        return res;
    }

    public String getMeetingIdByName(String nameMeeting) {
        return getIdByKey("meetings", "title", nameMeeting);
    }

    public String getRoomIdByName(String nameRoom) {
        return getIdByKey("rooms", "displayName", nameRoom);
    }

    public String getResourceIdByName(String resourceName) {
        return getIdByKey(Enum.RESOURCE_KEY.nameCollection,Enum.RESOURCE_KEY.name, resourceName);
    }


    public void closeMongoDB() {
        DBManager.getInstance().closeMongoDB();
    }

    /**
     * Get all resources that make match with search criteria by DB
     * @return  An array list with the names of the resources
     */
    public ArrayList<String> getResourcesNameByDB(String searchCriteria){
        ArrayList<Resource> resourcesByDB=DBQuery.getInstance().getResourcesBySearchCriteria(searchCriteria);
        ArrayList<String> resourcesNameByDB= new ArrayList<String>();
        /*
        Get the names of the  array list of resouces getting by DB and fill it in array list of strings
         */
        for (Resource resourceTemp : resourcesByDB){
            resourcesNameByDB.add(resourceTemp.getName());
        }
        return resourcesNameByDB;
    }
}
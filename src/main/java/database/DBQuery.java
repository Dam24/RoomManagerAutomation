package database;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import common.*;
import common.Enum;
import entities.ConferenceRoom;
import entities.Meeting;
import entities.Resource;
import org.bson.Document;
import com.mongodb.Block;
import java.util.ArrayList;

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

    /**
     * Obtain a list of Resources that matching with the search criteria
     * @param searchCriteria
     * @return
     */
    public ArrayList<Resource> getResourcesBySearchCriteria(String searchCriteria) {
        final ArrayList<Resource> resources = new ArrayList<Resource>();
        try {
            FindIterable<Document> iterable =
                    DBManager
                            .getInstance()
                            .getCollection(Enum.RESOURCE_KEY.nameCollection)
                            .find(
                                    regex(Enum.RESOURCE_KEY.name, searchCriteria)
                            );

            iterable.forEach(new Block<Document>() {
                @Override
                public void apply(final Document document) {
                    Resource resource = new Resource();
                    resource.setName((String)document.get(Enum.RESOURCE_KEY.name));
                    resource.setDisplayName((String) document.get(Enum.RESOURCE_KEY.customName));
                    resource.setIcon((String) document.get(Enum.RESOURCE_KEY.icon));
                    resource.setDescription((String) document.get(Enum.RESOURCE_KEY.description));
                    resources.add(resource);
                }
            });
        } catch (Exception e) {
        }

        return resources;
    }

    /**
     * Method that obtain the id of any collection based on a key and its value
     * @param collection
     * @param key
     * @param value
     * @return
     */
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
            res += doc.get(Constant.ID);
        }
        return res;
    }

    /**
     * Obtain the id of meeting based on a name
     * @param meeting
     * @return
     */
    public String getMeetingIdByName(Meeting meeting) {
        return getIdByKey(Enum.MEETING_kEY.nameCollection, Enum.MEETING_kEY.title, meeting.getTitle());
    }

    /**
     * Obtain the id of ConferenceRoom based on a name
     * @param conferenceRoom
     * @return
     */
    public String getRoomIdByName(ConferenceRoom conferenceRoom) {
        return getIdByKey(Enum.ROOM_KEY.nameCollection, Enum.ROOM_KEY.name, conferenceRoom.getName());
    }

    /**
     * Obtain the id of Location based on a name
     * @param nameLocation
     * @return
     */
    public String getLocationIdByName(String nameLocation) {
        return getIdByKey(Enum.LOCATION_KEY.nameCollection, Enum.LOCATION_KEY.name, nameLocation);
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


    /**
     * Close the MongoDB
     */
    public void closeMongoDB() {
        DBManager.getInstance().closeMongoDB();
    }

    public String getResourceIdByName (String resourceName){
        return getIdByKey(Enum.RESOURCE_KEY.nameCollection, Enum.RESOURCE_KEY.name, resourceName);

    }
}
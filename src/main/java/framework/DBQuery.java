package framework;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import entities.Resource;
import org.bson.Document;
import com.mongodb.Block;

import java.util.ArrayList;

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
        initialize();
    }

    public static DBQuery getInstance() {
        if (instance == null)
            instance = new DBQuery();

        return instance;
    }

    public String searchByValue(String collection, final String key, String value) {
        final String [] res = new String[1];
        Object objectRes = null;
        try {
            FindIterable<Document> iterable = DBManager.getInstance().getCollection(collection)
                    .find(new Document(key, value ));
            iterable.forEach(new Block<Document>() {
                @Override
                public void apply(final Document document) {
                    String element = document.toJson();
                    String elementId = element.split(",")[0];
                    String []id = elementId.split(":")[2].split("\"");
                    res[0] = id[1];
                }
            });
        } catch (Exception e) {
            System.err.println("ERROR: "+e.getClass().getName() + ": " + e.getMessage());
        } finally {
            DBManager.getInstance().closeMongoDB();
        }
        return res [0];
    }

    public void closeMongoDB() {
        DBManager.getInstance().closeMongoDB();
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
        } finally {
            DBManager.getInstance().closeMongoDB();
        }
        return resources;
    }

    private void initialize() {
        //To change body of created methods use File | Settings | File Templates.
    }
}
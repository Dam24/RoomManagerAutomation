package common;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.parser.ParseException;

/**
 * Created with IntelliJ IDEA.
 * User: jhasmanyquiroz
 * Date: 12/8/15
 * Time: 2:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class JSONReader {
    private JSONObject jsonObjectMain;
    private String directory;
    private final static Logger logger = Logger.getLogger(JSONReader.class);
    private String filePath;


    public JSONReader(String file) {
        directory = System.getProperty("user.dir");
        filePath = new File(directory+"/"+file).getAbsolutePath();
        parseJSON(filePath);
    }

    /**
     * Parses the json file into a JSONObject
     * @param filePath
     */
    private void parseJSON (String filePath) {
        try {
            FileReader reader = new FileReader(filePath);
            JSONParser jsonParser = new JSONParser();
            jsonObjectMain = (JSONObject) jsonParser.parse(reader);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException ex) {
            logger.error("FileNotFoundException when reading the configuration file ", ex);
        } catch (IOException ex) {
            logger.error("IOException when reading the configuration file ", ex);
        } catch (NullPointerException ex) {
            logger.error("NullPointerException when reading the configuration file ", ex);
        }
    }

    /**
     * Gets the jsonObject from the jsonArray and then gets a value given the key
     * @param objectName
     * @param idKey
     * @param idValue
     * @param key
     * @return
     */
    public String getKeyValue(String objectName, String idKey, String idValue, String key) {
        JSONObject jsonObject = getJSONObjectFromArrayById(objectName, idKey, idValue);
        return getKeyValueFromJSONObject(jsonObject, key);
    }

    public String getKeyValue(String objectName, String idKey, String idValue, String key, String subKey) {
        JSONObject jsonObject = getJSONObjectFromArrayById(objectName, idKey, idValue);
        JSONObject jsonSubObject = (JSONObject)jsonObject.get(key);
        return getKeyValueFromJSONObject(jsonSubObject, subKey);
    }

    private String getKeyValueFromJSONObject(JSONObject jsonObject, String key) {
        return jsonObject.get(key).toString();
    }

    /**
     * Gets the jsonObject which key and value matches the given parameters from a jsonArray
     * @param objectName
     * @param idKey
     * @param idValue
     * @return
     */
    private JSONObject getJSONObjectFromArrayById(String objectName, String idKey, String idValue) {
        JSONObject jsonObject = null;
        JSONArray array = (JSONArray) jsonObjectMain.get(objectName);

        for(Object object : array) {
            jsonObject = (JSONObject)object;
            if(jsonObject.get(idKey).equals(idValue)) {
                break;
            }
        }
        return jsonObject;
    }

    public String getKey(String key) {
        return (String)jsonObjectMain.get(key);
    }
}
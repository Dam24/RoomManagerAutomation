package steps.hooks;

import api.*;
import common.EnumKeys;
import entities.Location;
import entities.Resource;
import database.DBQuery;
import ui.PageTransporter;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: BrayanRosas
 * Date: 12/17/15
 * Time: 8:54 AM
 * To change this template use File | Settings | File Templates.
 */
public class SetUpConferenceRoom {

    public static ArrayList<Resource> resources=new ArrayList<Resource>();
    public static Resource resource;


    public static ArrayList<Location> locations=new ArrayList<Location>();
    public static Location location;
    public static String nameLocation="Location1";

    public static void beforeConferenceRoomsFeature(){
    }

    public static void afterConferenceRoomsFeature(){
        /*
        Delete Resource created
         */
        String idResource=DBQuery.getInstance().getIdByKey(EnumKeys.RESOURCE_KEY.nameCollection,EnumKeys.RESOURCE_KEY.name,"Printer");
        System.out.println("ID Resource to be eliminated : "+ idResource);
        resource.setName("Printer");
        resource.setID(idResource);
        resources.add(resource);
        APIMethodsResource.deleteResourcesById(resources);
        /*
        Delete the location created
         */
        String idLocation=DBQuery.getInstance().getIdByKey(EnumKeys.LOCATION_KEY.nameCollection, EnumKeys.LOCATION_KEY.name,nameLocation)  ;
        location.setName(nameLocation);
        location.setId(idLocation);
        locations.add(location);
        APIMethodsLocation.deleteLocations(locations);
        /*
        Delete the outOfOrders
         */
        String serviceId = DBQuery.getInstance().getIdByKey("services","name","Microsoft Exchange Server 2010 SP3");
        String roomId = DBQuery.getInstance().getIdByKey("rooms","displayName","Floor1Room14");
        String outOfOrderId = DBQuery.getInstance().getIdByKey("outoforders","roomId", roomId);
        APIMethodsOutOfOrder.deleteOutOfOrder(serviceId,roomId,outOfOrderId);
        /*
        Enable the room disable in the steps
         */
        APIMethodsRoom.activateConferenceRooms(roomId);
        PageTransporter.getInstance().refreshPage();
    }
}

package steps.hooks;

import common.Enum;
import entities.Location;
import entities.Resource;
import api.APIManager;
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
//    public static ConferenceRooms conferenceRooms;


    public static void beforeResourceFeature(){

        System.out.println("########## : Before Feature ROOMS ");

    }
//    public static void afterConferenceRoomsFeature(){
//        /*
//        Delete Resource created
//         */
//        System.out.println("########## : After Feature ROOMS ");
//
//        String idResource=DBQuery.getInstance().getIdByKey(Enum.RESOURCE_KEY.nameCollection, Enum.RESOURCE_KEY.name,"Printer");
//        System.out.println("ID Resource to be eliminated : "+ idResource);
//        resource.setName("Printer");
//        resource.setID(idResource);
//        resources.add(resource);
//        APIManager.getInstance().deleteResourcesById(resources);
//        /*
//        Delete the location created
//         */
//        String idLocation=DBQuery.getInstance().getIdByKey(Enum.LOCATION_KEY.nameCollection, common.Enum.LOCATION_KEY.name,nameLocation)  ;
//        location.setName(nameLocation);
//        location.setId(idLocation);
//        locations.add(location);
//        APIManager.getInstance().deleteLocationByID(locations);
//        /*
//        Delete the outOfOrders
//         */
//        String serviceId = DBQuery.getInstance().getIdByKey("services","name","Microsoft Exchange Server 2010 SP3");
//        String roomId = DBQuery.getInstance().getIdByKey("rooms","displayName","Floor1Room14");
//        String outOfOrderId = DBQuery.getInstance().getIdByKey("outoforders","roomId", roomId);
//        APIManager.getInstance().deleteOutOfOrder(serviceId,roomId,outOfOrderId);
//        /*
//        Enable the room disable in the steps
//         */
//        APIManager.getInstance().activateConferenceRooms(roomId);
//        PageTransporter.getInstance().refreshPage();
//    }
}

package steps.hooks;

import common.CommonMethod;
import entities.Resource;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: jhasmanyquiroz
 * Date: 12/16/15
 * Time: 10:41 AM
 * To change this template use File | Settings | File Templates.
 */
public class SetUpMeetings {
    public static ArrayList<Resource> meetings=new ArrayList<Resource>();

    public static void beforeMeetingFeature(){
        ArrayList<String> meetingsNames = new ArrayList<>();
        meetingsNames.add("Meetings #7");
        meetingsNames.add("Meetings ####");
        //meetings = APIManager.getInstance().createResourcesByName(meetingsNames);
        //PageTransporter.getInstance().refreshPage();
        //PageTransporter.getInstance().fixRefreshIsue();
        sigInTheTablet();
    }

    private static void sigInTheTablet() {
        CommonMethod.sigInToTablet();
    }

    public static void afterMeetingsFeature(){
        //APIManager.getInstance().deleteResourcesById(meetings);
        System.out.println("Ingresando al AFTER FEATURE");
    }
}

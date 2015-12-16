package steps.hooks;

import entities.Resource;
import framework.APIManager;
import ui.PageTransporter;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: BrayanRosas
 * Date: 12/15/15
 * Time: 10:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class SetUpResources {

   public static ArrayList<Resource> resources=new ArrayList<Resource>();

    public static void beforeResourceFeature(){
        ArrayList<String> resourceNames=new ArrayList<String>();
        resourceNames.add("Computer");
        resourceNames.add("Computer Assigned");
        resources=APIManager.getInstance().createResourcesByName(resourceNames);
        for (Resource res : resources) {
            System.out.println("################## "+ res.getName());
        }
        PageTransporter.getInstance().refreshPage();
        PageTransporter.getInstance().fixRefreshIsue();
    }
    public static void afterResourceFeature(){
        APIManager.getInstance().deleteResourcesById(resources);
    }
}
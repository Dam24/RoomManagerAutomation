package steps.hooks;

import api.APIMethodsResource;
import entities.Resource;
import api.APIManager;
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
   public static Resource resource=new Resource();
    public static void beforeResourceFeature(){
<<<<<<< HEAD



        resource.setName("Computer");
        resource.setDisplayName("Computer");
        resources.add(resource);
        resource.setName("Computer Assigned");
        resource.setDisplayName("Computer Assigned");
        resources.add(resource);
        resources=APIMethodsResource.createResources(resources);
=======
        ArrayList<String> resourceNames=new ArrayList<String>();
        resourceNames.add("Computer");
        resourceNames.add("Computer Assigned");

        ///////////////////////////////////////////////////////////////////////////////////////REVISAR
        //resources = APIManager.getInstance().createResourcesByName(resourceNames);
        //resources = APIMethodsResource.createResources();

>>>>>>> 685308d07638205fa9c520753478b7ec2bdeda72
        PageTransporter.getInstance().refreshPage();
        PageTransporter.getInstance().fixRefreshIsue();
    }
    public static void afterResourceFeature(){
        APIMethodsResource.deleteResourcesById(resources);
        PageTransporter.getInstance().navigateToMainPage().clickSignOutSuccessfully();
    }
}
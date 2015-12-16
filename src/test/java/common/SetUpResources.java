package common;

import cucumber.api.java.Before;
import entities.Resource;
import framework.APIManager;
import ui.PageTransporter;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: BrayanRosas
 * Date: 12/15/15
 * Time: 12:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class SetUpResources {

    public Resource beforeResourceFeature(String resourcesName){

        Resource res1=APIManager.getInstance().createResourceByName(resourcesName);
            PageTransporter.getInstance().refreshPage();
            PageTransporter.getInstance().fixRefreshIsue();
        return res1;
    }

    public void afterResourceFeature(ArrayList<Resource> resources){
        APIManager.getInstance().deleteResourcesById(resources);
    }

}






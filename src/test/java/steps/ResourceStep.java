package steps;

import api.APIMethodsResource;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import database.DBQuery;
import entities.Resource;
import api.APIManager;
import database.DBQuery;
import org.testng.Assert;
import ui.BaseMainPageObject;
import ui.PageTransporter;
import ui.pages.*;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: BrayanRosas
 * Date: 12/8/15
 * Time: 9:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class ResourceStep {

    private BaseMainPageObject mainPage;
    private SidebarMenuPage sidebar;
    private ResourcePage resourcePage;
    private AddResourcePage addResourcePage;
    private Resource resource1;
    private ResourceAssociationsPage resourceAssociationsPage;
    private ArrayList<Resource> resourcesCreatedByGiven=new ArrayList<>();

    public ResourceStep(Resource resource1){
        this.resource1=resource1;
        mainPage=new BaseMainPageObject();
        sidebar=mainPage.getSideBarMenu();
    }

    @Given("^I create a Resource with: \"([^\\\"]*)\" Name$")
    public void createResources(String resourceNames){
        ArrayList<String> resourcesNameArray = new ArrayList<String>();
        for (String name : resourceNames.split(",")){
            resourcesNameArray.add(name);
            resource1.setName(name);
            resource1.setDisplayName(name);
            resourcesCreatedByGiven.add(resource1);
        }
        resourcesCreatedByGiven= APIMethodsResource.createResources(resourcesCreatedByGiven);
        PageTransporter.getInstance().refreshPage();
        PageTransporter.getInstance().fixRefreshIsue();
    }
    @And("^I navigate to Resources page$")
    public void goToResourcePage(){
        mainPage.getSideBarMenu().clickOptionResource();
    }

    @When("^I try to create the Resource Name \"([^\\\"]*)\", \"([^\\\"]*)\" in the Resource page$")
    public void tryToCreateResource(String name,String displayName){
        resourcePage=sidebar.clickOptionResource();
        addResourcePage=resourcePage.clickAddButton();
        resource1.setName(name);
        resource1.setDisplayName(displayName);
        addResourcePage.createResource(resource1);
    }

    @Then("^an error text \"([^\\\"]*)\" is showed in the Resource form$")
    public void errorMessageIsShowed(String message){
        boolean expected=true;
        Assert.assertEquals(addResourcePage.isMessageShowed(message), expected);
    }

    @When("^I navigate to Resources page from AddResource$")
    public void navigateToResourcePageFromAddResourcePage(){
        addResourcePage.clickCancelResourceButton();
    }

    @Then("^only one Resource with the same name should be displayed in Resource list$")
    public void existTwoResourceSameName(){
        boolean expected=false;
        Assert.assertEquals(resourcePage.moreThatTwoResourceSameName(resource1), expected);
    }

    @And("^only one Resource with name \"([^\\\"]*)\" should be obtained by API$")
    public void existTwoResourcesSameNameByApi(String resourceName){
        Assert.assertTrue(APIMethodsResource.foundResourceSameName(resourceName));
    }

    @When("^I search Resources with search criteria \"([^\\\"]*)\"$")
    public void searchResource(String searchCriteria){
        resourcePage=sidebar.clickOptionResource();
        resourcePage.filterResource(searchCriteria);
    }

    @Then("^the Resources that match the search criteria \"([^\\\"]*)\" should be displayed in Resource List$")
    public void numResourcesFilter(String searchCriteria){
        Assert.assertEqualsNoOrder(resourcePage.getResourcesNameByUI().toArray(), DBQuery.getInstance().getResourcesNameByDB(searchCriteria).toArray());
    }

    @When("^I delete the Resource \"([^\\\"]*)\"$")
    public void deleteResourceByName(String resourceName){
        resourcePage=sidebar.clickOptionResource();
        resourceAssociationsPage=resourcePage.clickDeleteResource(resourceName);
        resourceAssociationsPage.deleteButtonConfirm();
    }

    @Then("^the Resource \"([^\\\"]*)\" should not be displayed in the Resources list$")
    public void isDisplayed(String resourceName){
        Assert.assertEquals(resourcePage.existInColumnName(resourceName),false);
    }

    @And("^the Resource \"([^\\\"]*)\" should not be obtained using the API$")
    public void theResourceIsPresentInAPI(String resourceName){
        resource1.setName(resourceName);
        Assert.assertFalse(APIMethodsResource.isFoundedTheResourceByApi(resource1));
    }

    @After("@ResourceFilter")
    public void deleteResourcesByScenario(){
        APIMethodsResource.deleteResourcesById(resourcesCreatedByGiven);
        PageTransporter.getInstance().refreshPage();
        PageTransporter.getInstance().fixRefreshIsue();
    }
}
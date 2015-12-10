package steps;

import common.EnumOptions;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import entities.Resource;
import org.testng.Assert;
import ui.BaseMainPageObject;
import ui.PageTransporter;
import ui.pages.AddResourcePage;
import ui.pages.LoginPage;
import ui.pages.ResourcePage;
import ui.pages.SidebarMenuPage;

/**
 * Created with IntelliJ IDEA.
 * User: BrayanRosas
 * Date: 12/8/15
 * Time: 9:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class ResourceStep {

    BaseMainPageObject mainPage;
    SidebarMenuPage sidebar;
    LoginPage loginPage;
    ResourcePage resourcePage;
    AddResourcePage addResourcePage;
    Resource resource1;

    public ResourceStep(Resource resource1){
        this.resource1=resource1;
        mainPage=new BaseMainPageObject();
        sidebar=mainPage.getSideBarMenu();
    }

    @When("^I try to create the Resource Name \"([^\\\"]*)\", \"([^\\\"]*)\" in the Resource page$")
    public void tryToCreateResource(String name,String displayName){
        resourcePage=sidebar.clickOption(EnumOptions.RESOURCES.option);
        addResourcePage=resourcePage.clickAddButton();
        resource1.setName(name);
        resource1.setDisplayName(displayName);
        addResourcePage.createResource(resource1);
    }

    @Given("^I sign in to Main page with user name \"([^\\\"]*)\" and password \"([^\\\"]*)\"$")
    public void signInToMainPAge(String user,String password){
        loginPage=PageTransporter.getInstance().navigateToLoginPage();
        loginPage.signIn(user,password);
    }

    @Then("^an error text \"([^\\\"]*)\" is showed in the Resource form$")
        public void errorMessageIsShowed(String message){
        boolean expected=true;
        Assert.assertEquals(addResourcePage.isMessageShowed(message), expected);
    }

    @When("I navigate to Resources page$")
    public void navigateToResourcePageFromAddResourcePage(){
        addResourcePage.clickCancelResourceButton();
    }

    @Then("^only one Resource with the same name should be displayed in Resource list$")
    public void existTwoResourceSameName(){
        boolean expected=false;
        Assert.assertEquals(resourcePage.moreThatTwoResourceSameName(resource1), expected);
    }

    @When("^I search Resources with search criteria \"([^\\\"]*)\"$")
    public void searchResource(String searchCriteria){
        resourcePage=sidebar.clickOption(EnumOptions.RESOURCES.option);
        resourcePage.filterResource(searchCriteria);
    }

    @Then("^the Resources that match the search criteria \"([^\\\"]*)\" should be displayed in Resource List$")
    public void numResourcesFilter(String searchCriteria){
        int actual=resourcePage.numOfResourcesFilter();
        Assert.assertEquals(3,actual);
    }

    @When("^I delete the Resource \"([^\\\"]*)\"$")
    public void deleteResourceByName(String resourceName){
        resourcePage=sidebar.clickOption(EnumOptions.RESOURCES.option);
        resourcePage.deleteResourceByName(resourceName);
    }
}

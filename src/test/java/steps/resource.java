package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
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
public class resource {

    SidebarMenuPage sidebar;
    LoginPage loginPage;
    ResourcePage resourcePage;
    AddResourcePage addResourcePage;


    @When("^I try to create the Resource Name \"([^\\\"]*)\", \"([^\\\"]*)\" in the Resource page$")
    public void tryToCreateResource(String user,String password){

            sidebar=new SidebarMenuPage();
            resourcePage=sidebar.clickOption("Resources");
            addResourcePage=resourcePage.clickAddButton();
            addResourcePage.createResource(user,password);
    }

    @Given("I sign in to Main page with user name \"([^\\\"]*)\" and password \"([^\\\"]*)\"$")
    public  void signInToMainPAge(String user,String password){
            loginPage=PageTransporter.getInstance().navigateToLoginPage();
            loginPage.signIn(user,password);
    }



}

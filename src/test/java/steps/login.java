package steps;
import common.CommonMethod;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;
import ui.PageTransporter;
import ui.pages.LoginPage;


/**
 * Created with IntelliJ IDEA.
 * User: BrayanRosas
 * Date: 12/8/15
 * Time: 5:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class login {
    LoginPage loginPage;

    @When("^I sign in to Main page with user name \"test\" and password \"Client123\"$")
    public void singinInvalidCredentials(String user,String password){


    }

}

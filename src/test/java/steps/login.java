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

    @Given("^I sign in to Main page with user name \"([^\\\"]*)\" and password \"([^\\\"]*)\"$")
    public void signInToMainPAge(String user,String password){
        if(!CommonMethod.theUserIsLogIn() ){
            loginPage=PageTransporter.getInstance().navigateToLoginPage();
            loginPage.signIn(user,password);
        }
    }
    @When("^I sign out of Admin Page$")
    public void signOut(){
        PageTransporter.getInstance().navigateToMainPage().getHeaderMenu().clickSignOutSuccessfully();
    }

}

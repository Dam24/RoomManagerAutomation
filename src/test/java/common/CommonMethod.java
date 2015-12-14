package common;

import framework.BrowserManager;
import framework.CredentialsManager;
import org.openqa.selenium.WebDriver;
import ui.PageTransporter;

/**
 * Created with IntelliJ IDEA.
 * User: BrayanRosas
 * Date: 12/8/15
 * Time: 1:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class CommonMethod {

    public static void navigateLoginPage() {
        PageTransporter.getInstance().navigateToLoginPage();
    }

    public static void signInToMainPage(){
        String user=CredentialsManager.getInstance().getAdminUserName();
        String password = CredentialsManager.getInstance().getAdminUserPassword();
        PageTransporter.getInstance().navigateToLoginPage().signIn(user, password);
    }

    public static void signOut(){
        PageTransporter.getInstance().navigateToMainPage().clickSignOutSuccessfully();
    }

    public static boolean theUserIsLogIn()
    {
        WebDriver driver = BrowserManager.getInstance().getDriver();
        if(driver.getCurrentUrl().contains("login"))  {
            return false;
        }
        else {
            return true;
        }
    }

    public static boolean isInTheTabletPage(){
        WebDriver driver = BrowserManager.getInstance().getDriver();
        if(driver.getCurrentUrl().contains("tablet")) {
            return true;
        }
        else {
            return false;
        }
    }
}
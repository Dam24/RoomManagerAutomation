package ui;

import framework.BrowserManager;
import framework.CredentialsManager;
import org.openqa.selenium.WebDriver;
import ui.pages.ConferenceRoomsPage;
import ui.pages.LoginPage;
import ui.pages.tablet.LoginTablePage;


/**
 * Created with IntelliJ IDEA.
 * User: DamianVillanueva
 * Date: 12/7/15
 * Time: 8:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class PageTransporter {

    private WebDriver driver = BrowserManager.getInstance().getDriver();
    private String baseLoginURL =
            CredentialsManager
                    .getInstance()
                    .getBaseAdminURL()
            ;
    private  String baseMainPage =
            CredentialsManager
                    .getInstance()
                    .getBaseMainURL()
            ;
    private String baseTabletLoginPage=
            CredentialsManager
                    .getInstance()
                    .getBaseTabletURL()
            ;

    private static PageTransporter instance;

    protected PageTransporter() {
        initialize();
    }

    public static PageTransporter getInstance() {
        if(instance == null) {
            instance = new PageTransporter();
        }

        return instance;
    }

    private void initialize() {
//        log.info("Initialize the page transporter");
    }

    private void goToURL(String url) {
        driver.navigate().to(url);
    }

    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }

    public LoginPage navigateToLoginPage(){
        goToURL(baseLoginURL);
        return  new LoginPage();
    }

    public BaseMainPageObject navigateToMainPage(){
        goToURL(baseMainPage);
        return  new BaseMainPageObject();
    }

    public LoginTablePage navigateToLoginTablePage(){
        goToURL(baseTabletLoginPage);
       return new LoginTablePage();
    }

    public void closeLoginPage(){
        driver.close();
    }
}
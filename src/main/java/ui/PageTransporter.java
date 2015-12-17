package ui;

import framework.BrowserManager;
import framework.CredentialsManager;
import org.openqa.selenium.WebDriver;
import ui.pages.ConferenceRoomsPage;
import ui.pages.LoginPage;
import ui.pages.ResourcePage;
import ui.pages.tablet.LoginTablePage;
import ui.pages.tablet.MainTablePage;


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
                    .getBaseAdminURL();
    private  String baseMainPage =
            CredentialsManager
                    .getInstance()
                    .getBaseMainURL();
    private String baseTabletLoginPage =
            CredentialsManager
                    .getInstance()
                    .getBaseTabletURL();
    private String baseTableHomePage =
            CredentialsManager
                    .getInstance()
                    .getBaseTabletHomeURL();
    /*
        hard code create the Api for keys
     */
    private String baseServerPage =
            CredentialsManager.getInstance()
                    .getBaseServerURL();
    private String baseResourcesPage =
            CredentialsManager
            .getInstance()
            .getResBaseResourcesURL();
    private String baseConferenceRooms =
            CredentialsManager
                    .getInstance()
                    .getBaseConferenceRooms();
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
        //log.info("Initialize the page transporter");
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

    public ResourcePage navigateToResourcePage(){
        goToURL(baseResourcesPage);
        return  new ResourcePage();
    }

    public ConferenceRoomsPage navigateToConferenceRoomsPage(){
        goToURL(baseConferenceRooms);
        return new ConferenceRoomsPage();
    }

    public void navigateToServerPage(){
        goToURL(baseServerPage);
    }

    public void closeLoginPage(){
        driver.close();
    }

    public MainTablePage navigateToMainTabletPage() {
        goToURL(baseTableHomePage);
        return new MainTablePage();
    }

    public void fixRefreshIsue(){
        navigateToMainPage().getSideBarMenu().clickOptionServer();
    }

    public void refreshPage(){
        driver.navigate().refresh();
        navigateToMainPage();
    }
}
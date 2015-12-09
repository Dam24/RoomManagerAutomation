package ui;

import framework.BrowserManager;
import org.openqa.selenium.WebDriver;
//import ui.pages.ConferenceRoomsPage;
import ui.pages.LoginPage;

/**
 * Created with IntelliJ IDEA.
 * User: DamianVillanueva
 * Date: 12/7/15
 * Time: 8:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class PageTransporter {

    private WebDriver driver = BrowserManager.getInstance().getDriver();
    private String baseLoginURL = "https://172.20.208.216:4040/admin/#/login";
    private  String baseMainPage = "https://172.20.208.216:4040/admin/#/admin";
    private String baseConferenceRooms = "https://172.20.208.121:4040/admin/#/admin/rooms/";

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
        System.out.println("Entered to navigate Main Page");
        return  new BaseMainPageObject();
    }

    public void closeLoginPage(){
        driver.close();
    }
}

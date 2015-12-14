package framework;

/**
 * Created with IntelliJ IDEA.
 * User: jhasmanyquiroz
 * Date: 12/7/15
 * Time: 2:39 PM
 * To change this template use File | Settings | File Templates.
 */
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class BrowserManager {
    private WebDriver driver;
    private WebDriverWait wait;
    private int implicitWait;

    private static BrowserManager instance = null;

    protected BrowserManager() {
        initialize();
    }

    /**
     * Return the instance of BrowserManager.
     */
    public static BrowserManager getInstance() {
        if(instance == null || instance.driver == null) {
            instance = new BrowserManager();
        }
        return instance;
    }

    /**
     * Initializes the web driver and wait.
    */
    private void initialize(){
       // recovery it from config file
        implicitWait=2;
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 20, 1000);
    }

    public void quitBrowser(){
        driver.quit();
    }

    public void closeBrowser(){

        driver.close();
    }

    public WebDriverWait getWaitDriver(){
        return wait;
    }

    public WebDriver getDriver(){
        return driver;
    }

    public int getImplicitWait(){
      return implicitWait;
    }

    public void setImplicitWait(int seconds){
        driver.manage().timeouts().implicitlyWait(seconds,TimeUnit.SECONDS);
    }
}
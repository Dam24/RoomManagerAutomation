package framework;

/**
 * Created with IntelliJ IDEA.
 * User: jhasmanyquiroz
 * Date: 12/7/15
 * Time: 2:39 PM
 * To change this template use File | Settings | File Templates.
 */
import common.JSONReader;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class BrowserManager {
    private WebDriver driver;
    private WebDriverWait wait;
    private final static Logger logger = Logger.getLogger(BrowserManager.class);
    private JSONReader jsonReader;
    private int implicitWait, explicitWait;
    private String chromeDriverPath;
    private String browserName, browser;

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
        logger.info("Initialize the BrowserDriver");

        browserName = System.getProperty("browserName");
        jsonReader = new JSONReader("browserConfig.json");
        implicitWait = Integer.parseInt(jsonReader.getKey("implicitWait"));
        explicitWait = Integer.parseInt(jsonReader.getKey("explicitWait"));
        browser = jsonReader.getKey("defaultBrowser");

        if(browserName == null || browserName.isEmpty() || browserName.equalsIgnoreCase(browser)){
            driver = new FirefoxDriver();
        } else
            if(browserName.equalsIgnoreCase("chrome")){
                chromeDriverPath = jsonReader.getKey("chromeDriverPath");
                System.setProperty("webdriver.chrome.driver", chromeDriverPath);
                driver = new ChromeDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, explicitWait);
    }

    public void quitBrowser(){
        driver.quit();
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
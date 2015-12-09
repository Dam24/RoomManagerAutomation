package ui;

/**
 * Created with IntelliJ IDEA.
 * User: jhasmanyquiroz
 * Date: 12/7/15
 * Time: 2:43 PM
 * To change this template use File | Settings | File Templates.
 */
import framework.BrowserManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;


public abstract class BasePageObject {
    protected WebDriver driver;
    protected WebDriverWait wait;

    /**
     * Initializes the web driver, wait and web elements
     */
    public BasePageObject() {
        this.driver = BrowserManager.getInstance().getDriver();
        this.wait = BrowserManager.getInstance().getWaitDriver();
        PageFactory.initElements(driver, this);
    }

    public abstract void waitUntilPageObjectIsLoaded();

    public boolean isPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
          }
    }
}
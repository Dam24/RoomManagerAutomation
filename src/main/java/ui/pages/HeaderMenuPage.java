package ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.BasePageObject;

/**
 * Created with IntelliJ IDEA.
 * User: jhasmanyquiroz
 * Date: 12/7/15
 * Time: 8:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class HeaderMenuPage extends BasePageObject {

    @FindBy(xpath = "//a[@ng-click='removeSession()']")
    WebElement linkSignOut2;

    @FindBy(xpath = "//span[contains(@class, 'ng-binding')]")
    private WebElement textUserName;

<<<<<<< HEAD
    public HeaderMenuPage() {
        PageFactory.initElements(driver, this);
        waitUntilPageObjectIsLoaded();
    }

=======
>>>>>>> c8be2c5751b41357f34b0a9c4716e297ac6328fb
    public LoginPage clickSignOutSuccessfully() {
        linkSignOut2.click();
        System.out.println("Entered to header click");
        return new LoginPage();
    }

    @Override
    public void waitUntilPageObjectIsLoaded() {
        wait.until(ExpectedConditions.visibilityOf(linkSignOut2));
    }
}

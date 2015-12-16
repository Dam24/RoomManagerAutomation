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

    public HeaderMenuPage() {
        PageFactory.initElements(driver, this);
        waitUntilPageObjectIsLoaded();
    }

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

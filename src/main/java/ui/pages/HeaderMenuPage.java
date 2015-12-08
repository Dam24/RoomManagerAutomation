package ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created with IntelliJ IDEA.
 * User: jhasmanyquiroz
 * Date: 12/7/15
 * Time: 8:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class HeaderMenuPage {
    @FindBy(linkText = "sign out")
    private WebElement linkSignOut;

    @FindBy(xpath = "//span[contains(@class, 'ng-binding')]")
    private WebElement textUserName;

    public HeaderMenuPage clickSignOutSuccessfully() {
        linkSignOut.click();
        return this;
    }
}

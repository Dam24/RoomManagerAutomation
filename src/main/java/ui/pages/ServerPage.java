package ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.BasePageObject;

/**
 * Created with IntelliJ IDEA.
 * User: BrayanRosas
 * Date: 12/14/15
 * Time: 12:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class ServerPage extends BasePageObject {

    @FindBy(xpath ="//div[@class='list-group']")
    private WebElement emailServer;

    public ServerPage() {
        PageFactory.initElements(driver, this);
        waitUntilPageObjectIsLoaded();
    }

    @Override
    public void waitUntilPageObjectIsLoaded() {
        wait.until(ExpectedConditions.visibilityOf(emailServer));
    }
}

package ui;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.pages.ResourceInfoPage;

/**
 * Created with IntelliJ IDEA.
 * User: BrayanRosas
 * Date: 12/10/15
 * Time: 7:22 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseResourcePropertiesPage extends BasePageObject {
    ResourceInfoPage resourceInfoPage;

    @FindBy(linkText = "Resource Associations")
    @CacheLookup
    private WebElement linkResourceAssociation;

    @FindBy(linkText = "Resource Info")
    @CacheLookup
    private WebElement linkResourceInfo;

    public BaseResourcePropertiesPage() {
        PageFactory.initElements(driver, this);
        waitUntilPageObjectIsLoaded();
    }




}

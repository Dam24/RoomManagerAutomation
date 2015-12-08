package ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import ui.BasePageObject;

/**
 * Created with IntelliJ IDEA.
 * User: DamianVillanueva
 * Date: 12/7/15
 * Time: 4:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class ResourceAssociationsPage extends BasePageObject {

    @FindBy(xpath = "//div[4]/div/div/div[2]/div/div/div[2]/div/div/")
    @CacheLookup
    WebElement listAvailableResources;

    @FindBy(xpath = "//div[4]/div/div/div[2]/div/div/div[2]/div[2]/div/")
    @CacheLookup
    WebElement listAssociatedResources;


    @Override
    public void waitUntilPageObjectIsLoaded() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}

package ui.pages;

import org.openqa.selenium.support.PageFactory;
import ui.BasePageLocations;

/**
 * Created with IntelliJ IDEA.
 * User: DamianVillanueva
 * Date: 12/11/15
 * Time: 10:09 AM
 * To change this template use File | Settings | File Templates.
 */
public class LocationInfoPage extends BasePageLocations {
    public LocationInfoPage() {
        PageFactory.initElements(driver, this);
        waitUntilPageObjectIsLoaded();
    }

    @Override
    public void waitUntilPageObjectIsLoaded() {
//        wait.until(ExpectedConditions.visibilityOf());
    }

    public LocationAssociationPage goToLocationAssociationPage(){
        return clickResourceAssociationsTab();
    }
}

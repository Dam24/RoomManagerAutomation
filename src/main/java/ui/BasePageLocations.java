package ui;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import ui.pages.LocationAssociationPage;
import ui.pages.LocationInfoPage;
import ui.pages.LocationsPage;

/**
 * Created with IntelliJ IDEA.
 * User: DamianVillanueva
 * Date: 12/11/15
 * Time: 9:57 AM
 * To change this template use File | Settings | File Templates.
 */
public class BasePageLocations extends BasePageObject{

    @FindBy(xpath = "//a[@ng-repeat='option in options' and contains(text(),'Location Info')]")
    @CacheLookup
    private WebElement tapLocationInfo;

    @FindBy(xpath = "//a[@ng-repeat='option in options' and contains(text(),'Location Associations')]")
    @CacheLookup
    private WebElement tapLocationAssociation;

    @FindBy(xpath = "//button[@ng-click='save()']")
    @CacheLookup
    private WebElement  buttonSave;

    @FindBy(xpath = "//button[@ng-click='cancel()']")
    @CacheLookup
    private WebElement buttonCancel;

    @Override
    public void waitUntilPageObjectIsLoaded() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    protected LocationInfoPage clickRoomInfoTab(){
        tapLocationInfo.click();
        return new LocationInfoPage();
    }

    protected LocationAssociationPage clickResourceAssociationsTab(){
        tapLocationAssociation.click();
        return new LocationAssociationPage();
    }

    protected LocationsPage clickSaveButton(){
        buttonSave.click();
        return new LocationsPage();
    }

    protected LocationsPage clickCancelButton(){
        buttonCancel.click();
        return new LocationsPage();
    }
}

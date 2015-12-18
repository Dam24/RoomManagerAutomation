package ui;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui.pages.ConferenceRoomsPage;
import ui.pages.OutOfOrderPlanningPage;
import ui.pages.ResourceAssociationsPage;
import ui.pages.RoomInfoPage;

/**
 * Created with IntelliJ IDEA.
 * User: DamianVillanueva
 * Date: 12/7/15
 * Time: 8:16 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class BasePageConferenceRoom extends BasePageObject{
    @FindBy(xpath = "//a[@ng-repeat='menuItem in breadcrumbMenu' and contains(text(),'Room Info')]")
    @CacheLookup
    private  WebElement tabRoomInfo;

    @FindBy(xpath = "//a[@ng-repeat='menuItem in breadcrumbMenu' and contains(text(),'Resource Associations')]")
    @CacheLookup
    private WebElement tabResourceAssociations;

    @FindBy(xpath = "//a[@ng-repeat='menuItem in breadcrumbMenu' and contains(text(),'Out of Order Planning')]")
    @CacheLookup
    private WebElement tabOutOfOrderPlanning;

    @FindBy(xpath = "//button[@ng-click='save()']")
    @CacheLookup
    WebElement  buttonSave;

    @FindBy(xpath = "//button[@ng-click='cancel()']")
    @CacheLookup
    private WebElement buttonCancel;

    public BasePageConferenceRoom() {
        PageFactory.initElements(driver, this);
        waitUntilPageObjectIsLoaded();
    }

    @Override
    public void waitUntilPageObjectIsLoaded() {

    }

    protected ConferenceRoomsPage clickSaveButton(){
        buttonSave.click();
        return new ConferenceRoomsPage();
    }

    protected void clickSaveButtonNoSuccessful(){
        buttonSave.click();
    }

    protected ConferenceRoomsPage clickCancelButton(){
        buttonCancel.click();
        return new ConferenceRoomsPage();
    }

    protected RoomInfoPage clickRoomInfoTab(){
        tabRoomInfo.click();
        return new RoomInfoPage();
    }

    protected ResourceAssociationsPage clickResourceAssociationsTab(){
        tabResourceAssociations.click();
        return new ResourceAssociationsPage();
    }

    protected OutOfOrderPlanningPage clickOutOfOrderPlanningTab(){
        tabOutOfOrderPlanning.click();
        return new OutOfOrderPlanningPage();
    }
}

package ui.pages;

import entities.ConferenceRoom;
import entities.Location;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui.BaseMainPageObject;

/**
 * Created with IntelliJ IDEA.
 * User: DamianVillanueva
 * Date: 12/11/15
 * Time: 9:49 AM
 * To change this template use File | Settings | File Templates.
 */
public class LocationsPage extends BaseMainPageObject {

    LocationInfoPage locationInfoPage;
    LocationAssociationPage locationAssociationPage;

    @FindBy(xpath = "//div[@class='ngCanvas']")
    @CacheLookup
    private WebElement tableLocation;

    public LocationsPage() {
        PageFactory.initElements(driver, this);
        waitUntilPageObjectIsLoaded();
    }

    @Override
    public void waitUntilPageObjectIsLoaded() {

    }

    private LocationInfoPage doubleClickOnSpecificLocation(String locationsName){
        Actions action = new Actions(driver);
        WebElement specificLocation = tableLocation.findElement(By.xpath("//div[@ng-dblclick='editLocation(row.entity)' and contains(text(),'" + locationsName + "')]"));
        action.moveToElement(specificLocation).perform();
        action.doubleClick(specificLocation).perform();
        return new LocationInfoPage();
    }

    public boolean isLocationAssociatedToRoom(ConferenceRoom room, Location location){
        locationInfoPage = doubleClickOnSpecificLocation(location.getDisplayName());
        locationAssociationPage=locationInfoPage.goToLocationAssociationPage();
        return locationAssociationPage.isRoomPresentOnAssociatedList(room.getDisplayName());
    }
}

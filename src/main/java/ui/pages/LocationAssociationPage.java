package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import ui.BasePageLocations;

/**
 * Created with IntelliJ IDEA.
 * User: DamianVillanueva
 * Date: 12/11/15
 * Time: 10:09 AM
 * To change this template use File | Settings | File Templates.
 */
public class LocationAssociationPage extends BasePageLocations {
    @FindBy(xpath = "//div[h4[contains(text(),'Available')]]")
    @CacheLookup
    private WebElement listAvailableRooms;

    @FindBy(xpath = "//div[h4[contains(text(),'Associated')]]")
    @CacheLookup
    private WebElement listAssociatedRooms;

    public void goToLocationAssociation(){
        clickResourceAssociationsTab();
    }

    public boolean isRoomPresentOnAssociatedList(String roomName){
        return listAssociatedRooms.findElement(By.xpath("//div[@class='row']/div[contains(text(),'" + roomName + "')]")).isDisplayed();
    }
}

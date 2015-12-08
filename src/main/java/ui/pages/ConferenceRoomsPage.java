package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import ui.BasePageObject;

/**
 * Created with IntelliJ IDEA.
 * User: DamianVillanueva
 * Date: 12/7/15
 * Time: 2:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class ConferenceRoomsPage extends BasePageObject {

    @FindBy(xpath = "//div/div[2]/div[1]/div/div")
    @CacheLookup
    WebElement resourcesPanel;

    @FindBy(id = "roomsGrid")
    @CacheLookup
    WebElement roomsTable;

    @FindBy(xpath = "//div/div[1]/div/label/input")
    @CacheLookup
    WebElement filterByRoom;

    @Override
    public void waitUntilPageObjectIsLoaded() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    private WebElement searchRoom(String roomName){
        WebElement room=roomsTable.findElement(By.xpath(""));
        return room;
    }
    private WebElement searchResource(String resourceName){
        WebElement resource=resourcesPanel.findElement(By.xpath(""));
        return resource;
    }

    public RoomInfoPage clickOnSpecificRoom(String roomName){
        WebElement buttonRoom=searchRoom(roomName);
        buttonRoom.click();
        return new RoomInfoPage();
    }

    public ConferenceRoomsPage clickOnSpecificResource(String resourceName){
        WebElement buttonResource=searchResource(resourceName);
        buttonResource.click();
        return this;
    }







}

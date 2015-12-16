package ui.pages;

import entities.ConferenceRooms;
import entities.Resource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
<<<<<<< HEAD
=======
import org.openqa.selenium.support.ui.ExpectedConditions;
>>>>>>> c8be2c5751b41357f34b0a9c4716e297ac6328fb
import ui.BaseMainPageObject;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: DamianVillanueva
 * Date: 12/7/15
 * Time: 2:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class ConferenceRoomsPage extends BaseMainPageObject {

    @FindBy(xpath = "//div[@class='row']")
    @CacheLookup
    private WebElement resourcesPanel;

    @FindBy(id = "roomsGrid")
    @CacheLookup
    private WebElement roomsTable;

    @FindBy(xpath = "//input[@type='text' and @ng-model='filterOptions.filterText']")
    @CacheLookup
    private WebElement inputFilterByRoom;

<<<<<<< HEAD
=======
    @FindBy(xpath = "//div[contains(@class,'ng-scope ngRow')]")
    private WebElement rowConferenceRoom;

>>>>>>> c8be2c5751b41357f34b0a9c4716e297ac6328fb
    public ConferenceRoomsPage() {
        PageFactory.initElements(driver, this);
        waitUntilPageObjectIsLoaded();
    }

    @Override
    public void waitUntilPageObjectIsLoaded() {
<<<<<<< HEAD
//        wait.until(ExpectedConditions.visibilityOf(roomsTable.findElement(By.xpath("//div[@ng-style='rowStyle(row)']"))));
=======
        wait.until(ExpectedConditions.visibilityOf(rowConferenceRoom));
>>>>>>> c8be2c5751b41357f34b0a9c4716e297ac6328fb
    }

    private ConferenceRoomsPage setInputFilterByRoom(String value){
        inputFilterByRoom.clear();
        inputFilterByRoom.sendKeys(value);
        return this;
    }

    private WebElement searchRoom(String roomDisplayName){
        //return driver.findElement(By.xpath("//div[contains(@class,'ng-scope ngRow')]/div[contains(@class,'col2')]//span[2][text(),'"+roomDisplayName+"']"));
        return roomsTable.findElement(By.xpath("//div[span[contains(text(),'" + roomDisplayName + "')]]"));
    }

    private WebElement searchResource(String resourceDisplayName){
        return resourcesPanel.findElement(By.xpath("//div[@ng-repeat='resource in resources']/span[span[contains(text(),'" + resourceDisplayName + "')]]"));
    }

    public RoomInfoPage clickOnSpecificRoom(ConferenceRooms room){
        WebElement buttonRoom=searchRoom(room.getDisplayName());
        buttonRoom.click();
        return new RoomInfoPage();
    }

    public RoomInfoPage doubleClickOnSpecificRoom(ConferenceRooms room){
        Actions action = new Actions(driver);
//        setInputFilterByRoom(room.getDisplayName());

        WebElement buttonRoom = searchRoom(room.getDisplayName());
//        WebElement buttonRoom = roomsTable.findElement(By.xpath("//div[span[contains(text(),'" +room.getDisplayName()+ "')]]"));
//        wait.until(ExpectedConditions.visibilityOf(buttonRoom));
//        wait.until(ExpectedConditions.visibilityOf(roomsTable.findElement(By.xpath("//div[@ng-style='rowStyle(row)']"))));
        action.moveToElement(buttonRoom).perform();
        action.doubleClick(buttonRoom).perform();
        return new RoomInfoPage();
    }

    public ConferenceRoomsPage clickOnSpecificResource(Resource resource){
        WebElement buttonResource=searchResource(resource.getDisplayName());
        buttonResource.click();
        return this;
    }

    public boolean isMessagePresent(String message){
        try {
            return driver.findElement(By.xpath("//div[@class='ng-binding ng-scope' and contains(text(),'" + message + "')]")).isDisplayed();
        }   catch (Exception e){
            return false;
        }
    }

    public boolean isResourceAssociate(String quantity, ConferenceRooms room){
        try {
            return roomsTable.findElement(By.xpath("//div[div/div[span[contains(text(),'" + room.getDisplayName() + "')]]]/following-sibling::div//span[contains(text(),' x "+quantity+" ')]")).isDisplayed();
        }   catch (Exception e){
            return false;
        }
    }

    public ConferenceRoomsPage clickOnDisabledConferenceRoom(ConferenceRooms room){
        WebElement disabledButton = roomsTable.findElement(By.xpath("//div[div/div[span[contains(text(),'" + room.getDisplayName() + "')]]]/preceding-sibling::div/preceding-sibling::div//span"));
        disabledButton.click();
        return this;
    }

    public boolean isConferenceRoomsDisabled(ConferenceRooms room){
        try {
            WebElement conferenceRoom = roomsTable.findElement(By.xpath("//div[div/div[span[contains(text(),'" + room.getDisplayName() + "')]]]/preceding-sibling::div/preceding-sibling::div//div[@ng-if='(row.entity.enabled == false)']"));
            return conferenceRoom.isDisplayed();
        }   catch(Exception e){
            return false;
        }
    }

    public boolean isCalendarPresent(ConferenceRooms room){
        try {
            WebElement calendar = roomsTable.findElement(By.xpath("//div[div/div[span[contains(text(),'" + room.getDisplayName() + "')]]]/preceding-sibling::div"));
            return calendar.isDisplayed();
        }   catch(Exception e){
            return false;
        }
    }

    public void ensureIsNotDisplayedResourceColumns(){
        List<WebElement> resourcesList = resourcesPanel.findElements(By.xpath("//div[@ng-repeat='resource in resources']"));
        for (WebElement option : resourcesList) {
            System.out.println("************************************** resource name: "+option.getText());
            WebElement op = option.findElement(By.xpath("//div[@ng-repeat='resource in resources']/span[span[contains(text(),'"+option.getText()+"')]]"));
            op.click();
        }
    }
}

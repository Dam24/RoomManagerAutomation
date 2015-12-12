package ui.pages;

import entities.ConferenceRooms;
import entities.Resource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import ui.BaseMainPageObject;

import java.util.Iterator;
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

    @Override
    public void waitUntilPageObjectIsLoaded() {
        //To change body of implemented methods use File | Settings | File Templates.
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
        setInputFilterByRoom(room.getDisplayName());
        WebElement buttonRoom = searchRoom(room.getDisplayName());
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

    public void ensureIsNotDisplayedResourceColumns(){
        List<WebElement> resourcesList = resourcesPanel.findElements(By.tagName("//div"));
        Iterator<WebElement> iterator = resourcesList.iterator();
        while (iterator.hasNext()) {
            try{
//                WebElement resource= iterator.next().findElement(By.xpath("//span[@class='btn btn-default btn-block ng-untouched ng-valid ng-dirty ng-valid-parse']"));
//                WebElement resource= iterator.next().findElement(By.xpath("//span[@class='btn btn-default btn-block ng-pristine ng-untouched ng-valid active']"));
//                resource.click();
                if(iterator.next().findElement(By.xpath("//span[@class='btn btn-default btn-block ng-pristine ng-untouched ng-valid active']")).isDisplayed()){
                    iterator.next().click();
                }
            }   catch (Exception e){
                iterator.next();
            }
        }
    }
}

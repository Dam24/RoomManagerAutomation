package ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import ui.BasePageConferenceRoom;

/**
 * Created with IntelliJ IDEA.
 * User: DamianVillanueva
 * Date: 12/7/15
 * Time: 3:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class RoomInfoPage extends BasePageConferenceRoom{

    @FindBy(className = "btn btn-default")
    @CacheLookup
    WebElement buttonRoomEnabled;

    @FindBy(className = "btn btn-default ng-hide")
    @CacheLookup
    WebElement buttonRoomDisabled;

    @FindBy(xpath = "//input[@type='text' and @ng-model='selectedRoom.displayName']")
    @CacheLookup
    WebElement  getPutName;

    @FindBy(xpath = "//input[@type='text' and @ng-model='selectedRoom.customDisplayName']")
    @CacheLookup
    WebElement  inputDisplayName;

    @FindBy(xpath = "//input[@type='text' and @ng-model='selectedRoom.code']")
    @CacheLookup
    WebElement inputCode;

    @FindBy(xpath = "//input[@type='text' and @ng-model='selectedRoom.capacity']")
    @CacheLookup
    WebElement inputCapacity;

    @FindBy(xpath = "//button[@ng-click='toggleTree()']")
    @CacheLookup
    WebElement buttonLocation;


    @Override
    public void waitUntilPageObjectIsLoaded() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    private RoomInfoPage clickRoomEnabledButton(){
        buttonRoomEnabled.click();
        return this;
    }

    private RoomInfoPage clickRoomDisabledButton(){
        buttonRoomDisabled.click();
        return this;
    }
    private RoomInfoPage clickLocationButton(){
        buttonLocation.click();
        return this;
    }

    private RoomInfoPage setInputDisplayName(String value){
        inputDisplayName.clear();
        inputDisplayName.sendKeys(value);
        return this;
    }

    private RoomInfoPage setInputCode(String value){
        inputCode.clear();
        inputCode.sendKeys(value);
        return this;
    }

    private RoomInfoPage setInputCapacity(String value){
        inputCapacity.clear();
        inputCapacity.sendKeys(value);
        return this;
    }

    private RoomInfoPage setInputWebElement(WebElement element, String value){
        element.clear();
        element.sendKeys(value);
        return this;
    }

}

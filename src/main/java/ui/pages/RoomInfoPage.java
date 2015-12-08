package ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import ui.BasePageObject;

/**
 * Created with IntelliJ IDEA.
 * User: DamianVillanueva
 * Date: 12/7/15
 * Time: 3:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class RoomInfoPage extends BasePageObject{

    @FindBy(className = "btn btn-default")
    @CacheLookup
    WebElement buttonRoomEnabled;

    @FindBy(className = "btn btn-default ng-hide")
    @CacheLookup
    WebElement buttonRoomDisabled;


    @FindBy(xpath = "//form/div[1]/input")
    @CacheLookup
    WebElement  getPutName;

    @FindBy(xpath = "//form/div[2]/input")
    @CacheLookup
    WebElement  inputDisplayName;

    @FindBy(xpath = "//div[3]/input")
    @CacheLookup
    WebElement inputCode;

    @FindBy(xpath = "//div[4]/input")
    @CacheLookup
    WebElement inputCapacity;

    @FindBy(xpath = "//span/button")
    @CacheLookup
    WebElement buttonLocation;

    @Override
    public void waitUntilPageObjectIsLoaded() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}

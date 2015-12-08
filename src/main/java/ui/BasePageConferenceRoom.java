package ui;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

/**
 * Created with IntelliJ IDEA.
 * User: DamianVillanueva
 * Date: 12/7/15
 * Time: 8:16 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class BasePageConferenceRoom extends BasePageObject{
    @FindBy(xpath = "//div/div/a")
    @CacheLookup
    WebElement tabRoomInfo;

    @FindBy(xpath = "//a[2]")
    @CacheLookup
    WebElement tabResourceAssociations;

    @FindBy(xpath = "//a[3]")
    @CacheLookup
    WebElement tabOutOfOrderPlanning;

    @FindBy(xpath = "//div[3]/div[2]/button")
    @CacheLookup
    WebElement  buttonSave;

    @FindBy(xpath = "//div[3]/div/button")
    @CacheLookup
    WebElement buttonCancel;
}

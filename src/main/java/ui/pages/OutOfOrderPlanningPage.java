package ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import ui.BasePageObject;

/**
 * Created with IntelliJ IDEA.
 * User: DamianVillanueva
 * Date: 12/7/15
 * Time: 4:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class OutOfOrderPlanningPage extends BasePageObject {

    @FindBy(xpath = "//div[2]/div/div/div/label")
    @CacheLookup
    WebElement buttonELement;

    @FindBy(xpath = "//date-picker/div/input")
    @CacheLookup
    WebElement inputFromDate;

    @FindBy(xpath = "//div[2]/div/div/date-picker/div/input")
    @CacheLookup
    WebElement inputToDate;

    @FindBy(xpath = "//td/input")
    @CacheLookup
    WebElement inputFromHour;

    @FindBy(xpath = "//td[3]/input")
    @CacheLookup
    WebElement inputFromMinutes;

    @FindBy(xpath = "//div[2]/table/tbody/tr[2]/td[4]/button")
    @CacheLookup
    WebElement toggleFromMeridian;

    @FindBy(xpath = "div[2]/table/tbody/tr[2]/td/input")
    @CacheLookup
    WebElement inputToHour;

    @FindBy(xpath = "div[2]/table/tbody/tr[2]/td[3]/input")
    @CacheLookup
    WebElement inputToMinutes;

    @FindBy(xpath = "//div[2]/div/div[2]/table/tbody/tr[2]/td[4]/button")
    @CacheLookup
    WebElement toggleToMeridian;

    @FindBy(xpath = "//form/div/div/div/label")
    @CacheLookup
    WebElement dropdownTitle;

    @FindBy(xpath = "//form/div/div/input")
    @CacheLookup
    WebElement inputTitle;

    @FindBy(xpath = "//textarea")
    @CacheLookup
    WebElement inputDescription;

    @Override
    public void waitUntilPageObjectIsLoaded() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}

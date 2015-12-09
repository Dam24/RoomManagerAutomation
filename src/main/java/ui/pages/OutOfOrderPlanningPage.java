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

    @FindBy(xpath = "//span[@class='fa fa-calendar']/")
    @CacheLookup
    WebElement buttonElementCalendar;

    @FindBy(xpath = "//date-picker[@model='form.from.value']/div/input[@type='text']")
    @CacheLookup
    WebElement inputFromDate;

    @FindBy(xpath = "//date-picker[@model='form.to.value']/div/input[@type='text']")
    @CacheLookup
    WebElement inputToDate;

    @FindBy(xpath = "//table[@ng-model='form.from.value']/tbody/tr/td/input[@ng-model= 'hours']")
    @CacheLookup
    WebElement inputFromHour;

    @FindBy(xpath = "//table[@ng-model='form.from.value']/tbody/tr/td/input[@ng-model= 'minutes']")
    @CacheLookup
    WebElement inputFromMinutes;

    @FindBy(xpath = "//table[@ng-model='form.from.value']/tbody/tr/td/button[@type='button' and @ng-click='toggleMeridian()']")
    @CacheLookup
    WebElement toggleFromMeridian;

    @FindBy(xpath = "//table[@ng-model='form.to.value']/tbody/tr/td/input[@ng-model= 'hours']")
    @CacheLookup
    WebElement inputToHour;

    @FindBy(xpath = "//table[@ng-model='form.to.value']/tbody/tr/td/input[@ng-model= 'minutes']")
    @CacheLookup
    WebElement inputToMinutes;

    @FindBy(xpath = "//table[@ng-model='form.to.value']/tbody/tr/td/button[@type='button' and @ng-click='toggleMeridian()']")
    @CacheLookup
    WebElement toggleToMeridian;

    @FindBy(xpath = "//div[@class='input-group-btn check-dropdown']/label[@class='btn btn-default']")
    @CacheLookup
    WebElement dropdownTitle;

    @FindBy(xpath = "//input[@type='text' and @ng-model='form.title.value']")
    @CacheLookup
    WebElement inputTitle;

    @FindBy(xpath = "//textarea[@ng-model='form.description.value']")
    @CacheLookup
    WebElement inputDescription;

    @Override
    public void waitUntilPageObjectIsLoaded() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    private OutOfOrderPlanningPage clickElementCalendarButton(){
        buttonElementCalendar.click();
        return this;
    }

    private OutOfOrderPlanningPage setInputWebElement(WebElement element, String value){
        element.clear();
        element.sendKeys(value);
        return this;
    }
    private OutOfOrderPlanningPage clickWebElementOnPage(WebElement element){
        element.click();
        return this;
    }


}

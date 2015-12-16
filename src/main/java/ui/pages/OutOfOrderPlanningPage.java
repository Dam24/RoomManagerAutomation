package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.BasePageConferenceRoom;

/**
 * Created with IntelliJ IDEA.
 * User: DamianVillanueva
 * Date: 12/7/15
 * Time: 4:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class OutOfOrderPlanningPage extends BasePageConferenceRoom {

    @FindBy(xpath = "//span[@class='fa fa-calendar']/")
    @CacheLookup
    private WebElement buttonElementCalendar;

    @FindBy(xpath = "//date-picker[@model='form.from.value']/div/input[@type='text']")
    @CacheLookup
    private WebElement inputFromDate;

    @FindBy(xpath = "//date-picker[@model='form.to.value']/div/input[@type='text']")
    @CacheLookup
    private WebElement inputToDate;

    @FindBy(xpath = "//table[@ng-model='form.from.value']/tbody/tr/td/input[@ng-model= 'hours']")
    @CacheLookup
    private WebElement inputFromHour;

    @FindBy(xpath = "//table[@ng-model='form.from.value']/tbody/tr/td/input[@ng-model= 'minutes']")
    @CacheLookup
    private WebElement inputFromMinutes;

    @FindBy(xpath = "//table[@ng-model='form.from.value']/tbody/tr/td/button[@type='button' and @ng-click='toggleMeridian()']")
    @CacheLookup
    private WebElement toggleFromMeridian;

    @FindBy(xpath = "//table[@ng-model='form.to.value']/tbody/tr/td/input[@ng-model= 'hours']")
    @CacheLookup
    private WebElement inputToHour;

    @FindBy(xpath = "//table[@ng-model='form.to.value']/tbody/tr/td/input[@ng-model= 'minutes']")
    @CacheLookup
    private WebElement inputToMinutes;

    @FindBy(xpath = "//table[@ng-model='form.to.value']/tbody/tr/td/button[@type='button' and @ng-click='toggleMeridian()']")
    @CacheLookup
    private WebElement toggleToMeridian;

    @FindBy(xpath = "//div[@class='input-group-btn check-dropdown']/label[@class='btn btn-default']")
    @CacheLookup
    private WebElement dropDownTitle;

    @FindBy(xpath = "//ul[@class='dropdown-menu']")
    @CacheLookup
    private WebElement menuDropDownTitle;

    @FindBy(xpath = "//input[@type='text' and @ng-model='form.title.value']")
    @CacheLookup
    private WebElement inputTitle;

    @FindBy(xpath = "//textarea[@ng-model='form.description.value']")
    @CacheLookup
    private WebElement inputDescription;

    @FindBy(xpath = "//table[@ng-model='form.from.value']")
    @CacheLookup
    private WebElement fromHoursOptions;

    @FindBy(xpath = "//table[@ng-model='form.to.value']")
    @CacheLookup
    private WebElement toHoursOptions;

    public OutOfOrderPlanningPage() {
        PageFactory.initElements(driver, this);
        waitUntilPageObjectIsLoaded();
    }

    @Override
    public void waitUntilPageObjectIsLoaded() {
        wait.until(ExpectedConditions.visibilityOf(inputDescription));
    }

    private OutOfOrderPlanningPage clickElementCalendarButton(){
        buttonElementCalendar.click();
        return this;
    }

    private OutOfOrderPlanningPage setInputWebElement(WebElement element, String value){
        Actions action = new Actions(driver);
        action.sendKeys(element,value).build().perform();
        return this;
    }

    private OutOfOrderPlanningPage clickWebElement(WebElement element){
        element.click();
        return this;
    }

    private OutOfOrderPlanningPage selectTitle(String reason){
        dropDownTitle.click();
        WebElement specificTitle = menuDropDownTitle.findElement(By.xpath("//li[a[contains(text(),'" + reason + "')]]"));
        specificTitle.click();
        return this;
    }

    //este metodo setea la hora. minutos tanto del from como del to
    private OutOfOrderPlanningPage setFromTimeRoom(WebElement element, String hrs, String type){
        WebElement increment = element.findElement(By.xpath("//table[@ng-model='form.from.value']//td/a[@ng-click='increment"+type+"()']"));
        WebElement decrement = element.findElement(By.xpath("//table[@ng-model='form.from.value']//td/a[@ng-click='decrement"+type+"()']"));
        WebElement inputElement= element.findElement(By.xpath("//table[@ng-model='form.from.value']//td/input[@ng-change='update"+type+"()']"));
        int actualHrs=Integer.parseInt(inputElement.getAttribute("value"));
        while (actualHrs != Integer.parseInt(hrs)){
            if(actualHrs > Integer.parseInt(hrs)){
                decrement.click();
                actualHrs --;
            }
            else {
                increment.click();
                actualHrs++;
            }
        }
        return this;
    }

    private OutOfOrderPlanningPage setToTimeRoom(WebElement element, String hrs, String type){
        WebElement incrementTo = toHoursOptions.findElement(By.xpath("//table[@ng-model='form.to.value']//tr/td/a[@ng-click='increment"+type+"()']"));
        WebElement decrementTo = toHoursOptions.findElement(By.xpath("//table[@ng-model='form.to.value']//tr/td/a[@ng-click='decrement"+type+"()']"));
        WebElement inputElement= toHoursOptions.findElement(By.xpath("//table[@ng-model='form.to.value']//tr/td/input[@ng-change='update"+type+"()']"));

        int actualHrs=Integer.parseInt(inputElement.getAttribute("value"));
        while (actualHrs != Integer.parseInt(hrs)){
            if(actualHrs > Integer.parseInt(hrs)){
                decrementTo.click();
                actualHrs --;
            }
            else {
                incrementTo.click();
                actualHrs++;
            }
        }
        return this;
    }

    public ConferenceRoomsPage setOutOfOrderPlanning(String fromDate, String toDate, String fromHours, String toHours, String reason, String description){
        setFromTimeRoom(fromHoursOptions, fromHours, "Hours");
        setFromTimeRoom(fromHoursOptions, fromHours, "Minutes");
        setToTimeRoom(toHoursOptions, toHours,"Hours");
        setToTimeRoom(toHoursOptions, toHours, "Minutes");
        selectTitle(reason);
        setInputWebElement(inputDescription,description);
        return clickSaveButton();
    }

    public OutOfOrderPlanningPage setOutOfOrderPlanningNoSuccessful(String fromDate, String toDate, String fromHours, String toHours, String reason, String description){
        setFromTimeRoom(fromHoursOptions, fromHours, "Hours");
        setFromTimeRoom(fromHoursOptions, fromHours, "Minutes");
        setToTimeRoom(toHoursOptions, toHours,"Hours");
        setToTimeRoom(toHoursOptions, toHours, "Minutes");
        selectTitle(reason);
        setInputWebElement(inputDescription,description);
        clickSaveButtonNoSuccessful();
        return this;
    }

    public boolean messageContent(){
        return driver.findElement(By.xpath("//div[@ng-repeat='message in form.to.errorMessages']/small[contains(text(),'\"To\" field must be greater than \"From\" field')]")).isDisplayed();
    }

    public ConferenceRoomsPage cancelReservation(){
        return clickCancelButton();
    }

}

package ui.pages.tablet;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.BasePageObject;

/**
 * Created with IntelliJ IDEA.
 * User: BrayanRosas
 * Date: 12/12/15
 * Time: 3:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class ScheduleTabletPage extends BasePageObject {

    @FindBy(xpath= "//div[@class='header-secondary-bar']")
    private WebElement headerBarSecond;

    /*
    Web elements to create meeting
     */
    @FindBy(xpath= "//input[@id='txtOrganizer']")
    private WebElement inputOrganizer;

    @FindBy(xpath= "//input[@id='txtSubject']")
    private WebElement inputSubject;

    @FindBy(xpath= "//input[@type='time' and @ng-model='editable.from']")
    private WebElement inputFrom;

    @FindBy(xpath= "//input[@type='time' and @ng-model='editable.to']")
    private WebElement inputTo;

    @FindBy(xpath= "//input[@id='//input[@id='_value']")
    private WebElement inputAttendees;

    @FindBy(xpath= "//input[@textarea='txtBody']")
    private WebElement textAreaBody;

    @FindBy(xpath= "//div[@class='form-bar']/button[@class='clean item item-btn']")
    private WebElement buttonCreateMeeting;

    public ScheduleTabletPage() {
        PageFactory.initElements(driver, this);
        waitUntilPageObjectIsLoaded();
    }
    @Override
    public void waitUntilPageObjectIsLoaded() {
        wait.until(ExpectedConditions.visibilityOf(headerBarSecond));
    }

    public MainTablePage createBasicMeeting(String organizer ,String subject,String from,String to,String attendees,String body){
      inputOrganizer.sendKeys(organizer);
      inputSubject.sendKeys(subject);
      inputFrom.sendKeys(from);
      inputTo.sendKeys(to);
      inputAttendees.sendKeys(attendees);
      textAreaBody.sendKeys(body);
      buttonCreateMeeting.click();
      return new MainTablePage();
    }


}

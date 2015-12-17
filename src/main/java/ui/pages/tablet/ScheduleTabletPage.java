package ui.pages.tablet;

import entities.Meeting;
import framework.BrowserManager;
import framework.CredentialsManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
    private HeaderTabletPage headerTabletPage;

    @FindBy(xpath= "//div[@class='header-secondary-bar']")
    private WebElement headerBarSecond;

    @FindBy(xpath= "//input[@id='txtOrganizer']")
    private WebElement inputOrganizer;

    @FindBy(xpath= "//input[@id='txtSubject']")
    private WebElement inputSubject;

    @FindBy(xpath= "//input[@type='time' and @ng-model='editable.from']")
    private WebElement inputFrom;

    @FindBy(xpath= "//input[@type='time' and @ng-model='editable.to']")
    private WebElement inputTo;

    @FindBy(xpath= "//input[@id='_value']")
    private WebElement inputAttendees;

    @FindBy(xpath= "//textarea[@id='txtBody']")
    private WebElement textAreaBody;

    @FindBy(xpath= "//div[@class='form-bar']/button[@class='clean item item-btn']")
    private WebElement buttonCreateMeeting;

    @FindBy(xpath = "//input[contains(@ng-model, 'dialog.credentials.username')]")
    private WebElement inputCredentialsUsername;

    @FindBy(xpath = "//input[contains(@ng-model, 'dialog.credentials.password')]")
    private WebElement inputCredentialsPassword;

    @FindBy(xpath = "//button[contains(@ng-click, 'dialog.ok()')]")
    private WebElement buttonCredentialsAccept;

    @FindBy(xpath = "//button[contains(@ng-click, 'dialog.modalDismiss()')]")
    private WebElement buttonCredentialsCancel;

    @FindBy(css = "div.ng-binding.ng-scope")
    private WebElement dialogCreateMeetingMessage;

    private WebElement dialogRemoveMeetingMessage;

    @FindBy(xpath = "//div[contains(@class, 'toast ng-scope toast-error')]")
    private WebElement dialogErrorCreateMeetingMessage;

    @FindBy(xpath = "//small[contains(@ng-show, 'formErrors.organizer')]")
    private WebElement labelErrorOrganizer;

    @FindBy(xpath = "//small[contains(@ng-show, 'formErrors.title')]")
    private WebElement labelErrorSubject;

    @FindBy(xpath = "//button/span[contains(text(),'Remove')]")
    private WebElement buttonDeleteMeeting;

    @FindBy(css = "css=div.vis-panel.vis-center")
    private WebElement containerMeetings;

    private Meeting meeting;

    public ScheduleTabletPage() {
        headerTabletPage = new HeaderTabletPage();
        PageFactory.initElements(driver, this);
        waitUntilPageObjectIsLoaded();
    }
    @Override
    public void waitUntilPageObjectIsLoaded() {
        wait.until(ExpectedConditions.visibilityOf(headerBarSecond));
    }

    public ScheduleTabletPage createBasicMeeting(Meeting meeting){
        this.meeting = meeting;
        inputOrganizer.sendKeys(meeting.getOrganizer());
        inputSubject.sendKeys(meeting.getTitle());
        inputFrom.clear();
        inputFrom.sendKeys(meeting.getFrom());
        inputTo.clear();
        inputTo.sendKeys(meeting.getTo());
        inputAttendees.sendKeys(meeting.getAttendees());
        textAreaBody.sendKeys(meeting.getBody());
        return this;
    }

    public ScheduleTabletPage clickOnCreateMeeting() {
        buttonCreateMeeting.click();
        return this;
    }

    public ScheduleTabletPage loginCredentialsUserExchange() {
        inputCredentialsUsername.clear();
        inputCredentialsUsername
                .sendKeys(
                        CredentialsManager
                                .getInstance()
                                .getExchangeUserName()
                )
        ;
        inputCredentialsPassword.clear();
        inputCredentialsPassword
                .sendKeys(
                        CredentialsManager
                                .getInstance()
                                .getExchangeUserPassword()
                )
        ;
        buttonCredentialsAccept.click();
        return this;
    }

    public boolean isSuccessfullyMessageDisplayed(String message) {
        return dialogCreateMeetingMessage.getText().equalsIgnoreCase(message);
    }

    public boolean isUnSuccessfullyMessagerDisplayed(String message) {
        return dialogErrorCreateMeetingMessage.getText().equalsIgnoreCase(message);
    }

    public boolean isConflictMessageDisplayed(String message) {
        return dialogRemoveMeetingMessage.getText().equalsIgnoreCase(message);
    }

    public boolean errorMessageIsDisplayed(String message) {
        return (labelErrorOrganizer.getText().equalsIgnoreCase(message)) ||
               (labelErrorSubject.getText().equalsIgnoreCase(message));
    }

    public boolean isMeetingPresentScheduleBar() {
        return isPresent(
                            By
                            .xpath(
                                    "//div[@class='vis-item-content']/span[contains(text(), '" + meeting.getTitle() + "')]"
                            )
                        )
        ;
    }

    public MainTablePage clickMainTabletPage() {
        return headerTabletPage.clickGoMainTabletPage();
    }

    public Meeting getMeeting() {
        return meeting;
    }

    public ScheduleTabletPage clickOnCancelCredentials() {
        buttonCredentialsCancel.click();
        return this;
    }

    public ScheduleTabletPage clickOnDeleteMeeting() {
        driver.findElement(By.xpath("//div[@class='vis-item-content']/span[contains(text(), '" +
                            meeting.getTitle() + "')]/parent::div/parent::div/parent::div"))
        .click();
        buttonDeleteMeeting.click();
        return this;
    }

    public ScheduleTabletPage typeCredentialsExchangePassword() {
        inputCredentialsPassword.clear();
        inputCredentialsPassword
                .sendKeys(
                        CredentialsManager
                                .getInstance()
                                .getExchangeUserPassword()
                )
        ;
        buttonCredentialsAccept.click();
        return this;
    }
}
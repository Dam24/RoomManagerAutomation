package ui.pages.tablet;

import entities.Meeting;
import framework.CredentialsManager;
import org.openqa.selenium.By;
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

    @FindBy(css = "div.ng-binding.ng-scope")
    private WebElement dialogCreateMeetingMessage;

    private Meeting meeting;

    public ScheduleTabletPage() {
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
        wait.until(ExpectedConditions.visibilityOf(dialogCreateMeetingMessage));
        return this;
    }

    public boolean isSuccessfullyMessageDisplayed(String message) {
        return dialogCreateMeetingMessage.getText().equalsIgnoreCase(message);
    }

    public boolean isMeetingPresentScheduleBar() {
        return isPresent(
                            By
                            .xpath(
                                    "//div[@class='vis-item-content']/span[contains(text(), '"+meeting.getTitle()+"')]"
                            )
                        )
        ;
    }
}
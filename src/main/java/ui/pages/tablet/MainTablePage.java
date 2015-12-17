package ui.pages.tablet;

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
 * Time: 1:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class MainTablePage extends BasePageObject {
    private boolean isResourceInTablet;

    @FindBy(xpath = "//div[contains(@class, 'vis-time-axis vis-background')]")
    private WebElement scheduleBar;

    @FindBy(xpath= "//div[@class='header']")
    private WebElement headerTablePage;

    @FindBy(xpath= "//div[@id='timeline-container']/rm-vis/div")
    private WebElement buttonSchedule;

    @FindBy(xpath= "//div[@class='tile-button-search']")
    private WebElement buttonSearch;

    //@FindBy(xpath = "//div[contains(@class, 'meeting-title')]")
    @FindBy(xpath = "//div[contains(@ng-bind, 'next._title')]")
    private WebElement labelMeetingTitle;

    @FindBy(xpath = "//div[contains(@ng-bind, 'next._organizer')]")
    private WebElement labelMeetingOrganizer;

    @FindBy(xpath = "//div[contains(@class, 'info')]")
    private WebElement divAvailableSection;

    public MainTablePage() {
        PageFactory.initElements(driver, this);
        waitUntilPageObjectIsLoaded();
    }
    @Override
    public void waitUntilPageObjectIsLoaded() {
        wait.until(ExpectedConditions.visibilityOf(scheduleBar));
    }

    public boolean isResourceInRoomOfTablet(String resourceCustomName){
       if(isPresent(By.xpath("//div[@class='header']//div[@ng-bind='resource.name'][text()='"+resourceCustomName+"']"))) {
           isResourceInTablet=true;
       }
       else{
           isResourceInTablet=false;
       }
        return isResourceInTablet;
    }

    public ScheduleTabletPage clickScheduleButton(){
        buttonSchedule.click();

        return new ScheduleTabletPage();
    }

    public boolean isMeetingPresent(String meetingTitle, String meetingOrganizer) {
        return (isMeetingTitlePresent(meetingTitle))&&
               (isMeetingOrganizerPresent(meetingOrganizer));
    }

    private boolean isMeetingTitlePresent(String meetingTitle) {
        return labelMeetingTitle.getText().equalsIgnoreCase(meetingTitle);
    }

    private boolean isMeetingOrganizerPresent(String meetingOrganizer) {
        return labelMeetingOrganizer.getText().equalsIgnoreCase(meetingOrganizer);
    }

    public boolean isPresentInMain() {
        return divAvailableSection != null;
    }
}
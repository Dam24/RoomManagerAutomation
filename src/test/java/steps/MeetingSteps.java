package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import entities.Meeting;
import framework.CredentialsManager;
import org.testng.Assert;
import ui.PageTransporter;
import ui.pages.tablet.LoginTablePage;
import ui.pages.tablet.MainTablePage;
import ui.pages.tablet.ScheduleTabletPage;

/**
 * Created with IntelliJ IDEA.
 * User: BrayanRosas
 * Date: 12/12/15
 * Time: 1:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class MeetingSteps {
    LoginTablePage loginTablePage;
    MainTablePage mainTablePage;
    ScheduleTabletPage scheduleTabletPage;


    @Given("^I navigate to Tablet page$")
    public void navigateToTabletPage(){
        loginTablePage=PageTransporter.getInstance().navigateToLoginTablePage();
        loginTablePage.sigInToTable(
                CredentialsManager
                        .getInstance()
                        .getRoomManagerService(),
                CredentialsManager
                        .getInstance()
                        .getTabletUserName(),
                CredentialsManager
                        .getInstance()
                        .getTabletUserPassword())
        ;
    }

    @And("^I select the \"([^\\\"]*)\" Conference Room$")
    public void selectConferenceRoom(String conferenceRoomName){
        mainTablePage = loginTablePage.selectSomeConferenceRooms(conferenceRoomName);
    }

    @Then("^the Resource \"([^\\\"]*)\" should not be displayed in the Resource Tablet list$")
    public void isResourceTabletRoomList(String resourceCustomName){
         boolean actual = mainTablePage.isResourceInRoomOfTablet(resourceCustomName);
         boolean expected = false;
         Assert.assertEquals(actual,expected);
    }

    @Given("^I navigate to Schedule page$")
    public void navigateToSchedulePage(){
        scheduleTabletPage = mainTablePage.clickScheduleButton();
    }

    @When("^I create a meeting with the following information: \"([^\\\"]*)\", \"([^\\\"]*)\", \"([^\\\"]*)\", \"([^\\\"]*)\", \"([^\\\"]*)\", \"([^\\\"]*)\"$")
    public void createMeetingWithFollowingInformation(String organizer, String subject,
                                                      String from, String to,
                                                      String attendees, String body) {
        Meeting meeting = new Meeting();
        meeting.setOrganizer(organizer);
        meeting.setTitle(subject);
        //meeting.setFrom(from);
        //meeting.setTo(to);
        meeting.setNow();
        meeting.setAttendees(attendees);
        meeting.setBody(body);
        scheduleTabletPage = scheduleTabletPage
                                .createBasicMeeting(meeting)
                                .loginCredentialsUserExchange()
        ;
    }

    @Then("^an information message \"([^\\\"]*)\" should be displayed$")
    public void informationMessageShouldBeDisplayed(String message) {
        Assert.assertTrue(
                            scheduleTabletPage
                                .isSuccessfullyMessageDisplayed(message)
                            , "Meeting is Created"
                        )
        ;
    }

    @And("^the meeting should be displayed in the Schedule bar$")
    public void meetingShouldDisplayedTheScheduleBar() {
        Assert.assertTrue(scheduleTabletPage.isMeetingPresentScheduleBar(),
                "Meeting is present in the ScheduleBar");
    }

    @And("^the meeting information should be displayed in the Next section of Main page$")
    public  void meetingInformationShouldDisplayedNextSectionMainPage() {

    }

    @And("^the meeting should be listed in the meetings of Room using the API$")
    public void meetingShouldListedMeetingsRoomUsingAPI() {

    }
}
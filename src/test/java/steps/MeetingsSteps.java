package steps;

import api.APIMethodsMeeting;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import entities.ConferenceRoom;
import entities.Meeting;
import framework.CredentialsManager;
import database.DBQuery;
import org.testng.Assert;
import ui.PageTransporter;
import ui.pages.tablet.LoginTablePage;
import ui.pages.tablet.MainTablePage;
import ui.pages.tablet.ScheduleTabletPage;

/**
* Created with IntelliJ IDEA.
* User: jhasmanyquiroz
* Date: 12/15/15
* Time: 1:40 PM
* To change this template use File | Settings | File Templates.
*/
public class MeetingsSteps {
    private MainTablePage mainTablePage;
    private ScheduleTabletPage scheduleTabletPage;
    private Meeting meeting;
    private LoginTablePage loginTablePage;
    private DBQuery dbQuery;
    private ConferenceRoom conferenceRoom;

    public MeetingsSteps(Meeting meeting) {
        this.meeting = meeting;
        mainTablePage = new MainTablePage();
        dbQuery = DBQuery.getInstance();
        conferenceRoom = new ConferenceRoom();
    }

    @Given("^I navigate to Schedule page$")
    public void navigateToSchedulePage(){
        scheduleTabletPage = mainTablePage.clickScheduleButton();
    }

    @When("^I create successfully a meeting with the following information: \"([^\\\"]*)\", \"([^\\\"]*)\", \"([^\\\"]*)\", \"([^\\\"]*)\", \"([^\\\"]*)\", \"([^\\\"]*)\"$")
    public void createSuccessfullyMeetingWithFollowingInformation(String organizer, String subject,
                                                      String from, String to,
                                                      String attendees, String body) {
        meeting.setMeeting(organizer, subject, from, to, attendees, body);
        scheduleTabletPage = scheduleTabletPage
                                .createBasicMeeting(meeting)
                                .clickOnCreateMeeting()
                                .loginCredentialsUserExchange()
        ;
    }

    @Then("^an information message should be displayed \"([^\\\"]*)\"$")
    public void anInformationMessageShouldBeDisplayed(String message) {
        Assert.assertTrue(scheduleTabletPage.isSuccessfullyMessageDisplayed(message), "Meeting is Created");
    }

    @And("^the meeting should be displayed in the Schedule bar$")
    public void meetingShouldBeDisplayedInTheScheduleBar() {
        Assert.assertTrue(scheduleTabletPage.isMeetingPresentScheduleBar(meeting),
                          "Meeting is present in the ScheduleBar Tablet Page");
    }

    @And("^the meeting information should be displayed in the Next section of Main page$")
    public void meetingInformationShouldBeDisplayedInTheNextSectionOfMainPage() {
        mainTablePage = scheduleTabletPage.clickMainTabletPage();
        Assert.assertTrue(mainTablePage.isMeetingPresent(meeting.getTitle(), meeting.getOrganizer()),
                         "Meeting is present in the Main Tablet Page");
    }

    @And("^the meeting should be listed in the meetings of Room using the API$")
    public void meetingShouldBeListedInTheMeetingsOfRoomUsingTheAPI() {
        String roomId = DBQuery.getInstance().getRoomIdByName(conferenceRoom);
        meeting.setId(dbQuery.getMeetingIdByName(meeting));

        Assert.assertTrue(APIMethodsMeeting.isMeetingInTheRoom(meeting, roomId));
    }

    /*
    Update the meeting
     */
    @When("^I update the meeting information: \"([^\\\"]*)\", \"([^\\\"]*)\", \"([^\\\"]*)\", \"([^\\\"]*)\", \"([^\\\"]*)\", \"([^\\\"]*)\"$")
    public void updateTheMeetingInformation(String organizer, String subject,
                                            String from, String to,
                                            String attendees, String body) {
        meeting.setMeeting(organizer, subject, from, to, attendees, body);
        scheduleTabletPage = scheduleTabletPage
                .clickOnUpdateMeeting(meeting)
                .typeCredentialsExchangePassword()
        ;
    }

    @When("^I create successfully a meeting with the following information here: \"([^\\\"]*)\", \"([^\\\"]*)\", \"([^\\\"]*)\", \"([^\\\"]*)\", \"([^\\\"]*)\", \"([^\\\"]*)\"$")
    public void createSuccessfullyMeetingWithFollowingInformationHere(String organizer, String subject,
                                                                  String from, String to,
                                                                  String attendees, String body) {
        meeting.setOrganizer(organizer);
        meeting.setTitle(subject);
        meeting.setFrom(from);
        meeting.setTo(to);
        meeting.setAttendees(attendees);
        meeting.setBody(body);
        meeting = APIMethodsMeeting.createMeeting(meeting, conferenceRoom);
    }

    /*
    Scenario: Remove the Meeting
     */
    @Given("^I navigate to Tablet page$")
    public void navigateToTabletPage(){
        loginTablePage= PageTransporter.getInstance().navigateToLoginTablePage();
        loginTablePage.sigInToTable(
                CredentialsManager
                        .getInstance()
                        .getRoomManagerService(),
                CredentialsManager
                        .getInstance()
                        .getTabletUserName(),
                CredentialsManager
                        .getInstance()
                        .getTabletUserPassword());
    }

    @And("^I select the \"([^\\\"]*)\" Conference Room$")
    public void selectConferenceRoom(String conferenceRoomName){
        mainTablePage = loginTablePage.selectSomeConferenceRooms(conferenceRoomName);
        conferenceRoom.setName(conferenceRoomName);
    }

    @Then("^the Resource \"([^\\\"]*)\" should not be displayed in the Resource Tablet list$")
    public void isResourceTabletRoomList(String resourceCustomName){
        boolean actual = mainTablePage.isResourceInRoomOfTablet(resourceCustomName);
        boolean expected = false;
        Assert.assertEquals(actual,expected);
    }

    @When("^I remove the meeting$")
    public void removeTheMeeting() {
        scheduleTabletPage = scheduleTabletPage
                                .clickOnDeleteMeeting(meeting)
                                .typeCredentialsExchangePassword();
    }

    @And("^the meeting should not be displayed in the Schedule bar$")
    public void theMeetingShouldNotBeDisplayedInTheScheduleBar() {
        Assert.assertFalse(scheduleTabletPage.isMeetingPresentScheduleBar(meeting),
                                "Meeting is not present in the ScheduleBar Tablet Page"
                            )
        ;
    }

    @And("^the meeting information should not be displayed in the Next section of Main page$")
    public void theMeetingInformationShouldNotBeDisplayedInTheNextSectionOfmainPage() {
        mainTablePage = scheduleTabletPage.clickMainTabletPage();
        Assert.assertFalse(mainTablePage.isMeetingPresent(meeting.getTitle(), meeting.getOrganizer()),
                            "Meeting is not present in the Main Tablet Page");
    }

    @And("^the meeting should not be listed in the meetings of Room using the API$")
    public void theMeetingShouldNotBeListedInTheMeetingsOfRoomUsingTheApi() {
        String roomId = dbQuery.getRoomIdByName(conferenceRoom);
       Assert.assertFalse(APIMethodsMeeting.isMeetingInTheRoom(meeting, roomId));
    }

    /*
    Try to create a meeting with missing information
     */
    @When("^I create unsuccessfully a meeting with the following information: \"([^\\\"]*)\", \"([^\\\"]*)\", \"([^\\\"]*)\", \"([^\\\"]*)\", \"([^\\\"]*)\", \"([^\\\"]*)\"$")
    public void createUnsuccessfullyMeetingWithTheFollowingInformation(String organizer, String subject,
                                                                       String from, String to,
                                                                       String attendees, String body) {
        meeting.setMeeting(organizer, subject, from, to, attendees, body);
        scheduleTabletPage = scheduleTabletPage.createBasicMeeting(meeting).clickOnCreateMeeting();
    }

    @Then("^an error \"([^\\\"]*)\" message should be displayed$")
    public void errorMessageShouldBeDisplayed(String message) {
        Assert.assertTrue(scheduleTabletPage.errorMessageIsDisplayed(message), "Meeting is not Created");
    }

    /*
    Try to create a meeting at the same time than other meeting
     */
    @Then("an information conflict message should be displayed \"([^\\\"]*)\"$")
    public void anInformationConflictMessageShouldBeDisplayed(String message) {
        Assert.assertTrue(scheduleTabletPage.isConflictMessageDisplayed(message));
    }

    @After("@Meetings")
    public void deleteMeeting() {
        String idMeeting = dbQuery.getMeetingIdByName(meeting);
        meeting.setId(idMeeting);
        String roomId = dbQuery.getRoomIdByName(conferenceRoom);
        APIMethodsMeeting.deleteMeetingById(meeting, roomId);
    }
}
package steps;

import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import entities.Meeting;
import framework.APIManager;
import framework.CredentialsManager;
import framework.DBQuery;
import org.testng.Assert;
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
    private Meeting meeting = new Meeting();

    @Given("^I navigate to Schedule page$")
    public void navigateToSchedulePage(){
        mainTablePage = new MainTablePage();
        scheduleTabletPage = mainTablePage.clickScheduleButton();
    }

    @When("^I create successfully a meeting with the following information: \"([^\\\"]*)\", \"([^\\\"]*)\", \"([^\\\"]*)\", \"([^\\\"]*)\", \"([^\\\"]*)\", \"([^\\\"]*)\"$")
    public void createSuccessfullyMeetingWithFollowingInformation(String organizer, String subject,
                                                      String from, String to,
                                                      String attendees, String body) {
        meeting.setOrganizer(organizer);
        meeting.setTitle(subject);
        meeting.setNow(from, to);
//        meeting.setFrom(from);
//        meeting.setTo(to);
        meeting.setAttendees(attendees);
        meeting.setBody(body);
        scheduleTabletPage = scheduleTabletPage
                                .createBasicMeeting(meeting)
                                .clickOnCreateMeeting()
                                .loginCredentialsUserExchange()
        ;
    }

    @Then("^an information message should be displayed \"([^\\\"]*)\"$")
    public void anInformationMessageShouldBeDisplayed(String message) {
        Assert.assertTrue(
                            scheduleTabletPage
                                .isSuccessfullyMessageDisplayed(message)
                            , "Meeting is Created"
                        )
        ;
    }

    @And("^the meeting should be displayed in the Schedule bar$")
    public void meetingShouldBeDisplayedInTheScheduleBar() {
        Assert.assertTrue(scheduleTabletPage.isMeetingPresentScheduleBar(),
                          "Meeting is present in the ScheduleBar Tablet Page");
    }

    @And("^the meeting information should be displayed in the Next section of Main page$")
    public void meetingInformationShouldBeDisplayedInTheNextSectionOfMainPage() {
        mainTablePage = scheduleTabletPage.clickMainTabletPage();
        Assert.assertTrue(mainTablePage
                            .isMeetingPresent(scheduleTabletPage
                                                .getMeeting().getTitle(),
                                              scheduleTabletPage
                                                .getMeeting()
                                                .getOrganizer()
                                             ),
                            "Meeting is present in the Main Tablet Page"
                         )
        ;
    }

    @And("^the meeting should be listed in the meetings of Room using the API$")
    public void meetingShouldBeListedInTheMeetingsOfRoomUsingTheAPI() {
        String roomName = DBQuery.getInstance().getRoomIdByName(CredentialsManager.getInstance()
                .getRoomName());
        String idMeeting = DBQuery.getInstance().getMeetingIdByName(meeting.getTitle());

        Assert.assertTrue(APIManager.getInstance().isMeetingInTheRoom(idMeeting, roomName));
    }

    /*
    Update the meeting
     */
    @When("^I update the meeting information: \"([^\\\"]*)\", \"([^\\\"]*)\", \"([^\\\"]*)\", \"([^\\\"]*)\", \"([^\\\"]*)\", \"([^\\\"]*)\"$")
    public void updateTheMeetingInformation(String organizer, String subject,
                                            String from, String to,
                                            String attendees, String body) {
        meeting.setOrganizer(organizer);
        meeting.setTitle(subject);
        meeting.setNow(from, to);
//        meeting.setFrom(from);
//        meeting.setTo(to);
        meeting.setAttendees(attendees);
        meeting.setBody(body);
        scheduleTabletPage = scheduleTabletPage
                .createBasicMeeting(meeting)
                .clickOnCreateMeeting()
                .loginCredentialsUserExchange()
        ;
        scheduleTabletPage = scheduleTabletPage
                .clickOnUpdateMeeting()
                .typeCredentialsExchangePassword()
        ;
    }

    /*
    Scenario: Remove the Meeting
     */
    @When("^I remove the meeting$")
    public void removeTheMeeting() {
        scheduleTabletPage = scheduleTabletPage
                                .clickOnDeleteMeeting()
                                .typeCredentialsExchangePassword()
        ;
    }

    @And("^the meeting should not be displayed in the Schedule bar$")
    public void theMeetingShouldNotBeDisplayedInTheScheduleBar() {
        System.out.println("THE MEETING SHOULD NOT BE PRESENT "+scheduleTabletPage.isMeetingPresentScheduleBar());
        Assert.assertFalse(scheduleTabletPage.isMeetingPresentScheduleBar(),
                                "Meeting is not present in the ScheduleBar Tablet Page"
                            )
        ;
    }

    @And("^the meeting information should not be displayed in the Next section of Main page$")
    public void theMeetingInformationShouldNotBeDisplayedInTheNextSectionOfmainPage() {
        mainTablePage = scheduleTabletPage.clickMainTabletPage();
        Assert.assertFalse(mainTablePage
                                .isMeetingPresent(scheduleTabletPage
                                                    .getMeeting()
                                                    .getTitle(),
                                                  scheduleTabletPage
                                                    .getMeeting()
                                                    .getOrganizer()
                                                 ),
                            "Meeting is not present in the Main Tablet Page"
                           )
        ;
    }

    @And("^the meeting should not be listed in the meetings of Room using the API$")
    public void theMeetingShouldNotBeListedInTheMeetingsOfRoomUsingTheApi() {
        String roomName = DBQuery.getInstance().getRoomIdByName(CredentialsManager.getInstance()
                .getRoomName());
        String idMeeting = DBQuery.getInstance().getMeetingIdByName(meeting.getTitle());

        Assert.assertFalse(APIManager.getInstance().isMeetingInTheRoom(idMeeting, roomName));
    }

    /*
    Try to create a meeting with missing information
     */
    @When("^I create unsuccessfully a meeting with the following information: \"([^\\\"]*)\", \"([^\\\"]*)\", \"([^\\\"]*)\", \"([^\\\"]*)\", \"([^\\\"]*)\", \"([^\\\"]*)\"$")
    public void createUnsuccessfullyMeetingWithTheFollowingInformation(String organizer, String subject,
                                                                       String from, String to,
                                                                       String attendees, String body) {
        meeting.setOrganizer(organizer);
        meeting.setTitle(subject);
        //meeting.setNow(from, to);
        meeting.setFrom(from);
        meeting.setTo(to);
        meeting.setAttendees(attendees);
        meeting.setBody(body);
        scheduleTabletPage = scheduleTabletPage
                                    .createBasicMeeting(meeting)
                                    .clickOnCreateMeeting()
        ;
    }

    @Then("^an error \"([^\\\"]*)\" message should be displayed$")
    public void errorMessageShouldBeDisplayed(String message) {
        Assert.assertTrue(scheduleTabletPage
                .errorMessageIsDisplayed(message),
                "Meeting is not Created"
        )
        ;
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
        String idMeeting = DBQuery.getInstance().getMeetingIdByName(meeting.getTitle());
        String roomName = DBQuery.getInstance().getRoomIdByName(CredentialsManager.getInstance()
        .getRoomName());
        APIManager.getInstance().deleteMeetingById(idMeeting, roomName);
    }
}
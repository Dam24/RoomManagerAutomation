//package steps;
//
//import common.CommonMethod;
//import cucumber.api.java.After;
//import cucumber.api.java.en.And;
//import cucumber.api.java.en.Given;
//import cucumber.api.java.en.Then;
//import cucumber.api.java.en.When;
//import entities.Meeting;
//import framework.BrowserManager;
//import framework.CredentialsManager;
//import org.omg.CORBA.TIMEOUT;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.testng.Assert;
//import ui.PageTransporter;
//import ui.pages.tablet.LoginTablePage;
//import ui.pages.tablet.MainTablePage;
//import ui.pages.tablet.ScheduleTabletPage;
//
//import java.sql.DriverManager;
//import java.util.concurrent.TimeUnit;
//
///**
//* Created with IntelliJ IDEA.
//* User: BrayanRosas
//* Date: 12/12/15
//* Time: 1:16 PM
//* To change this template use File | Settings | File Templates.
//*/
//public class MeetingSteps {
//    private LoginTablePage loginTablePage;
//    private MainTablePage mainTablePage;
//    private ScheduleTabletPage scheduleTabletPage;
//    private Meeting meeting = new Meeting();
//
//    @Given("^I navigate to Tablet page$")
//    public void navigateToTabletPage(){
//        System.out.println("URL - "+ PageTransporter.getInstance().getCurrentURL());
//        if (!CommonMethod.isInTheTabletPage()) {
//            loginTablePage = PageTransporter.getInstance().navigateToLoginTablePage();
//            loginTablePage.sigInToTable(
//                    CredentialsManager
//                            .getInstance()
//                            .getRoomManagerService(),
//                    CredentialsManager
//                            .getInstance()
//                            .getTabletUserName(),
//                    CredentialsManager
//                            .getInstance()
//                            .getTabletUserPassword())
//            ;
//        }
//        else {
//            mainTablePage = PageTransporter.getInstance().navigateToMainTabletPage();
//        }
//    }
//
//    @And("^I select the \"([^\\\"]*)\" Conference Room$")
//    public void selectConferenceRoom(String conferenceRoomName){
//        if (!CommonMethod.isInTheTabletPage()) {
//            mainTablePage = loginTablePage.selectSomeConferenceRooms(conferenceRoomName);
//        }
//    }
//
//    @Then("^the Resource \"([^\\\"]*)\" should not be displayed in the Resource Tablet list$")
//    public void isResourceTabletRoomList(String resourceCustomName){
//         boolean actual = mainTablePage.isResourceInRoomOfTablet(resourceCustomName);
//         boolean expected = false;
//         Assert.assertEquals(actual,expected);
//    }
//
//    @Given("^I navigate to Schedule page$")
//    public void navigateToSchedulePage(){
//       // if (!CommonMethod.isInTheTabletPage()) {
//          //  scheduleTabletPage = mainTablePage.clickScheduleButton();
//        //} else {
//            scheduleTabletPage = mainTablePage.clickScheduleButton();
//        //}
//    }
//
//    @When("^I create a meeting with the following information: \"([^\\\"]*)\", \"([^\\\"]*)\", \"([^\\\"]*)\", \"([^\\\"]*)\", \"([^\\\"]*)\", \"([^\\\"]*)\"$")
//    public void createMeetingWithFollowingInformation(String organizer, String subject,
//                                                      String from, String to,
//                                                      String attendees, String body) {
//        meeting.setOrganizer(organizer);
//        meeting.setTitle(subject);
//        //meeting.setNow(from, to);
//        meeting.setFrom(from);
//        meeting.setTo(to);
//        meeting.setAttendees(attendees);
//        meeting.setBody(body);
//        System.out.println("ORGANIZER - " + meeting.getOrganizer().equals(""));
//        System.out.println("TITLE - " + meeting.getTitle().equals(""));
//        if (meeting.getOrganizer().equals("") || meeting.getTitle().equals("")) {
//            scheduleTabletPage = scheduleTabletPage
//                                    .createBasicMeeting(meeting)
//                                    .clickOnCreateMeeting()
//            ;
//        }
//        else {
//            scheduleTabletPage = scheduleTabletPage
//                                    .createBasicMeeting(meeting)
//                                    .clickOnCreateMeeting()
//                                    .loginCredentialsUserExchange()
//            ;
//        }
////        scheduleTabletPage = scheduleTabletPage
////                                .createBasicMeeting(meeting)
////                                .loginCredentialsUserExchange()
////        ;
//    }
//
//    @Then("^an information message \"([^\\\"]*)\" should be displayed$")
//    public void informationMessageShouldBeDisplayed(String message) {
//        Assert.assertTrue(
//                            scheduleTabletPage
//                                .isSuccessfullyMessageDisplayed(message)
//                            , "Meeting is Created"
//                        )
//        ;
//    }
//
//    @And("^the meeting should be displayed in the Schedule bar$")
//    public void meetingShouldBeDisplayedInTheScheduleBar() {
//        Assert.assertTrue(scheduleTabletPage.isMeetingPresentScheduleBar(),
//                          "Meeting is present in the ScheduleBar Tablet Page");
//    }
//
//    @And("^the meeting information should be displayed in the Next section of Main page$")
//    public void meetingInformationShouldBeDisplayedInTheNextSectionOfMainPage() {
//        mainTablePage = scheduleTabletPage.clickMainTabletPage();
//        System.out.println("MEETING PRESENT"+mainTablePage.isMeetingPresent(scheduleTabletPage
//                .getMeeting().getTitle(),
//                scheduleTabletPage
//                        .getMeeting()
//                        .getOrganizer()));
//        Assert.assertTrue(mainTablePage
//                            .isMeetingPresent(scheduleTabletPage
//                                                .getMeeting().getTitle(),
//                                              scheduleTabletPage
//                                                .getMeeting()
//                                                .getOrganizer()
//                                             ),
//                            "Meeting is present in the Main Tablet Page"
//                         )
//        ;
//    }
//
//    @And("^the meeting should be listed in the meetings of Room using the API$")
//    public void meetingShouldBeListedInTheMeetingsOfRoomUsingTheAPI() {
//    }
//
//    /*
//    SCENARIO'S STEPS - Try to create a meeting with missing information.
//     */
//    @Then("^an error \"([^\\\"]*)\" message should be displayed$")
//    public void anErrorMessageShouldBeDisplayed(String message) {
//        if (meeting.getOrganizer().equals("") || meeting.getTitle().equals("")) {
//            Assert.assertFalse(scheduleTabletPage
//                                .errorMessageIsDisplayed(message),
//                                "Meeting is not Created"
//                              )
//            ;
//        } else {
//            Assert.assertTrue(scheduleTabletPage
//                    .isSuccessfullyMessageDisplayed(message),
//                    "Meeting is not created");
//            ;
//        }
//    }
//
//    @And("^the meeting should not be displayed in the Schedule bar$")
//    public void theMeetingShouldNotBeDisplayedInTheScheduleBar() {
//        if (!(meeting.getOrganizer().equals("") || meeting.getTitle().equals(""))) {
//            System.out.println("NULLLLLL"+meeting.getOrganizer());
//            scheduleTabletPage =
//                    scheduleTabletPage
//                            .clickOnCancelCredentials()
//            ;
//        }
//        System.out.println("THE MEETING SHOULD NOT BE PRESENT "+scheduleTabletPage.isMeetingPresentScheduleBar());
//        Assert.assertFalse(scheduleTabletPage.isMeetingPresentScheduleBar(),
//                                "Meeting is not present in the ScheduleBar Tablet Page"
//                            )
//        ;
//    }
//
//    @And("^the meeting information should not be displayed in the Next section of Main page$")
//    public void theMeetingInformationShouldNotBeDisplayedInTheNextSectionOfmainPage() {
//        mainTablePage = scheduleTabletPage.clickMainTabletPage();
//        Assert.assertFalse(mainTablePage
//                                .isMeetingPresent(scheduleTabletPage
//                                                    .getMeeting()
//                                                    .getTitle(),
//                                                  scheduleTabletPage
//                                                    .getMeeting()
//                                                    .getOrganizer()
//                                                 ),
//                            "Meeting is not present in the Main Tablet Page"
//                           )
//        ;
//    }
//
//    @And("^the meeting should not be listed in the meetings of Room using the API$")
//    public void theMeetingShouldNotBeListedInTheMeetingsOfRoomUsingTheAPI() {}
//}

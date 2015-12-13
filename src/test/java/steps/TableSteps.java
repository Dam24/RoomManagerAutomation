package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
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
public class TableSteps {
    LoginTablePage loginTablePage;
    MainTablePage mainTablePage;
    ScheduleTabletPage scheduleTabletPage;


    @When("^I navigate to Tablet page$")
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
        mainTablePage=loginTablePage.selectSomeConferenceRooms(conferenceRoomName);
    }

    @Then("^the Resource \"([^\\\"]*)\" should not be displayed in the Resource Tablet list$")
    public void isResourceTabletRoomList(String resourceCustomName){
         boolean actual=mainTablePage.isResourceInRoomOfTablet(resourceCustomName);
         boolean expected=false;
         Assert.assertEquals(actual,expected);
    }

    @Given("^I navigate to Schedule page$")
    public void navigateToSchedulePage(){
        scheduleTabletPage=mainTablePage.clickScheduleButton();
    }
}

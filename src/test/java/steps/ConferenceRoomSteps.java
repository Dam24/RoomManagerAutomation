package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import ui.PageTransporter;
import ui.pages.ConferenceRoomsPage;
import ui.pages.RoomInfoPage;

/**
 * Created with IntelliJ IDEA.
 * User: DamianVillanueva
 * Date: 12/7/15
 * Time: 8:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class ConferenceRoomSteps {
    private ConferenceRoomsPage conferenceRoomsPage;
    private RoomInfoPage roomInfoPage;

    @Given("I navigate to Conference Rooms page")
    public void I_navigate_to_Conference_Rooms_page(){
        conferenceRoomsPage= PageTransporter.getInstance().navigateToConferenceRoomPage();
    }
    @And("I displayed the \"(.*?)\" Resource in the Conference Room's Table")
    public void I_displayed_the_Resource_in_the_Conference_Rooms_Table(String  resourceName){
        conferenceRoomsPage.clickOnSpecificResource(resourceName);
    }

    @When("I associate the \"(.*?)\" Resource to the \"(.*?)\" Conference Room with quantity \"(.*?)\"")
    public void I_associate_the_Resource_to_the_Conference_Room_with_quantity(String resourceName, String roomName, int quantity){
        roomInfoPage=conferenceRoomsPage.clickOnSpecificRoom(roomName);


    }

    @Given("aaaaa")
    public void aaaaa(){


    }




}

package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import entities.ConferenceRooms;
import entities.Location;
import entities.Resource;
import org.testng.Assert;
import ui.BaseMainPageObject;
import ui.BasePageConferenceRoom;
import ui.pages.*;

import static org.junit.Assert.assertTrue;


/**
 * Created with IntelliJ IDEA.
 * User: DamianVillanueva
 * Date: 12/7/15
 * Time: 8:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class ConferenceRoomSteps {
    private BaseMainPageObject baseMainPageObject;
    private ConferenceRoomsPage conferenceRoomsPage;
    private RoomInfoPage roomInfoPage;
    private ResourceAssociationsPage resourceAssociationsPage;
    private BasePageConferenceRoom basePageConferenceRoom;
    private LocationsPage locationsPage;
    private LocationInfoPage locationInfoPage;

    ConferenceRooms conferenceRooms;
    Resource resource;
    Location location;

    public ConferenceRoomSteps(ConferenceRooms conferenceRooms, Resource resource, Location location){
        this.conferenceRooms = conferenceRooms;
        this.resource = resource;
        this.location = location;
        baseMainPageObject = new BaseMainPageObject();
    }

    @Given("I navigate to Conference Rooms page")
    public void I_navigate_to_Conference_Rooms_page(){
        conferenceRoomsPage = baseMainPageObject.getSideBarMenu().goToConferenceRoomsPage();
    }


    @And("I create a resource with the following dates: \"(.*?)\", \"(.*?)\"")
    public void I_create_a_resource_with_the_following_dates(String resourceName, String resourceDisplayName){
        resource.setName(resourceName);
        resource.setDisplayName(resourceDisplayName);
    }

    @And("I displayed the \"(.*?)\" Resource in the Conference Room's Table")
    public void I_displayed_the_Resource_in_the_Conference_Rooms_Table(String  resourceDisplayName){
        conferenceRoomsPage.ensureIsNotDisplayedResourceColumns();
        conferenceRoomsPage.clickOnSpecificResource(resource);
    }

    @When("I associate the \"(.*?)\" Resource to the \"(.*?)\" Conference Room with quantity \"(.*?)\"")
    public void I_associate_the_Resource_to_the_Conference_Room_with_quantity(String resourceDisplayName, String roomDisplayName, String quantity){
        conferenceRooms.setName(roomDisplayName);
        conferenceRooms.setDisplayName(roomDisplayName);
        roomInfoPage=conferenceRoomsPage.doubleClickOnSpecificRoom(conferenceRooms);
        resourceAssociationsPage=roomInfoPage.gotoAssociationPage();
        conferenceRoomsPage=resourceAssociationsPage.associateResource(resource, quantity);
    }

    @Then("the \"(.*?)\" message should be displayed")
    public void the_message_should_be_displayed(String message){
        assertTrue(conferenceRoomsPage.isMessagePresent(message));
    }

    @And("the Resource should be displayed with quantity \"(.*?)\" for the Conference Room")
    public void the_Resource_should_be_displayed_with_quantity_for_the_Conference_Room(String quantity){
        assertTrue(conferenceRoomsPage.isResourceAssociate(quantity, conferenceRooms));
    }

    @And("I create a Location with the following details: \"(.*?)\", \"(.*?)\"")
    public void I_create_a_Location_with_the_following_details(String locationName, String locationDisplayName){
        location.setName(locationName);
        location.setDisplayName(locationDisplayName);
    }

    @When("I associate the \"(.*?)\" Conference Room with the Location in the Room Info page")
    public void I_associate_the_Conference_Room_with_Location_in_the_Room_Info_page(String roomDisplayName){
        conferenceRooms.setName(roomDisplayName);
        conferenceRooms.setDisplayName(roomDisplayName);
        roomInfoPage=conferenceRoomsPage.doubleClickOnSpecificRoom(conferenceRooms);
        conferenceRoomsPage = roomInfoPage.setAssociateLocation(location);
    }

    @When("I navigate to Location Page")
    public void I_navigate_to_Location_Associations_page_of_Location(){
        locationsPage=conferenceRoomsPage.getSideBarMenu().goToLocationPage();
    }

    @Then("the Conference Room should be associated with Location in the Location Associations")
    public void the_Conference_Room_should_be_associated_with_Location_in_the_Location_Associations(){
        assertTrue(locationsPage.isLocationAssociatedToRoom(conferenceRooms,location));
    }

    @When("I disabled the \"(.*?)\" Conference Room")
    public void I_disabled_the_Conference_Room(String roomName){
        conferenceRooms.setName(roomName);
        conferenceRooms.setDisplayName(roomName);
        conferenceRoomsPage.clickOnDisabledConferenceRoom(conferenceRooms);
    }

    @And("the Conference Room should be disabled")
    public void the_Conference_Room_should_be_disabled(){
        assertTrue(conferenceRoomsPage.isConferenceRoomsDisabled(conferenceRooms));
    }

    @Then("^the Resource \"(.*?)\" should not be displayed with the quantity \"(.*?)\" list of Conference Room \"(.*?)\"$")
    public void isTheResourceInAssociatedList(String resourceDispalyName,String quantity,String roomDisplayName){
        conferenceRooms.setDisplayName(roomDisplayName);
        conferenceRoomsPage.doubleClickOnSpecificRoom(conferenceRooms);
        Assert.assertFalse(conferenceRoomsPage.isResourceAssociate(quantity, conferenceRooms));
    }


}
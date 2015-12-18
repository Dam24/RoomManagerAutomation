package steps;

import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import entities.ConferenceRooms;
import entities.Location;
import entities.OutOfOrders;
import entities.Resource;
import framework.APIManager;
import framework.DBQuery;
import org.testng.Assert;
import ui.BaseMainPageObject;
import ui.BasePageConferenceRoom;
import ui.PageTransporter;
import ui.pages.*;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;

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
    private OutOfOrderPlanningPage outOfOrderPlanningPage;
    private BasePageConferenceRoom basePageConferenceRoom;
    private LocationsPage locationsPage;
    private LocationInfoPage locationInfoPage;

    ConferenceRooms conferenceRooms;
    Resource resource;
    Location location;
    OutOfOrders outOfOrders;

    public ConferenceRoomSteps(ConferenceRooms conferenceRooms, Resource resource, Location location, OutOfOrders outOfOrders){
        this.conferenceRooms = conferenceRooms;
        this.resource = resource;
        this.location = location;
        this.outOfOrders = outOfOrders;
        baseMainPageObject = new BaseMainPageObject();
    }

    private ArrayList<Resource> resourcesCreatedByGiven = new ArrayList<>();
    private ArrayList<Location> locationsCreateByGiven = new ArrayList<>();

    @Given("I navigate to Conference Rooms page")
    public void I_navigate_to_Conference_Rooms_page(){
        conferenceRoomsPage = baseMainPageObject.getSideBarMenu().goToConferenceRoomsPage();
    }


    @And("I create a resource with the following dates: \"(.*?)\", \"(.*?)\"")
    public void I_create_a_resource_with_the_following_dates(String resourceName, String resourceDisplayName){
        resource.setName(resourceName);
        resource.setDisplayName(resourceName);
        ArrayList<String> resourcesNameArray = new ArrayList<String>();
        Collections.addAll(resourcesNameArray, resource.getName().split(","));
        resourcesCreatedByGiven = APIManager.getInstance().createResourcesByName(resourcesNameArray);
        PageTransporter.getInstance().refreshPage();
    }

    @And("I displayed the \"(.*?)\" Resource in the Conference Room's Table")
    public void I_displayed_the_Resource_in_the_Conference_Rooms_Table(String  resourceDisplayName){
        conferenceRoomsPage.ensureIsNotDisplayedResourceColumns();
        conferenceRoomsPage.clickOnSpecificResource(resource);
    }

    @When("I associate the \"(.*?)\" Resource to the \"(.*?)\" Conference Room with quantity \"(.*?)\"")
    public void I_associate_the_Resource_to_the_Conference_Room_with_quantity(String resourceDisplayName, String roomDisplayName, int quantity){
        conferenceRooms.setName(roomDisplayName);
        conferenceRooms.setDisplayName(roomDisplayName);
        resource.setQuantity(quantity);
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
        baseMainPageObject= conferenceRoomsPage;
    }

    @And("I create a Location with the following details: \"(.*?)\", \"(.*?)\"")
    public void I_create_a_Location_with_the_following_details(String locationName, String locationDisplayName){
        location.setName(locationName);
        location.setDisplayName(locationDisplayName);
        ArrayList<String> locationsNameArray = new ArrayList<String>();
        Collections.addAll(locationsNameArray, location.getName().split(","));
        locationsCreateByGiven = APIManager.getInstance().createLocationsByName(locationsNameArray);
        PageTransporter.getInstance().refreshPage();
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

    @When("I reserve the \"(.*?)\" Conference Room with the following information: \"(.*?)\", \"(.*?)\", \"(.*?)\", \"(.*?)\", \"(.*?)\", \"(.*?)\"")
    public void I_reserve_the_Conference_Room_with_the_following_information(String roomName, String fromDate, String toDate, String fromHours, String toHours, String reason, String description){
        conferenceRooms.setName(roomName);
        conferenceRooms.setDisplayName(roomName);
        outOfOrders.setTitle(reason);
        roomInfoPage = conferenceRoomsPage.doubleClickOnSpecificRoom(conferenceRooms);
        outOfOrderPlanningPage = roomInfoPage.gotoOutOfOrderPlanningPage();
        outOfOrderPlanningPage.setOutOfOrderPlanningNoSuccessful(fromDate,toDate,fromHours,toHours,reason,description);
    }

    @And("the Resource association should be obtained using the API for Conference Room")
    public void the_Resource_association_should_be_obtained_using_the_API(){
        String idResource = DBQuery.getInstance().getIdByKey("resourcemodels", "name",resource.getName());
        String idConferenceRoom = DBQuery.getInstance().getIdByKey("rooms", "displayName", conferenceRooms.getName());
        Resource resourceOnCR = APIManager.getInstance().getResourceInConferenceRoomById(idConferenceRoom,idResource);
        assertEquals(resource.getQuantity(), resourceOnCR.getQuantity());
    }

    @And("the Conference Room should be associated with Location on API")
    public void the_Conference_Room_should_be_associated_with_Location_on_API(){
        ConferenceRooms roomAPI = APIManager.getInstance().getConferenceRoomByName(conferenceRooms.getName());
        Location locationAPI = APIManager.getInstance().getLocationByID(roomAPI.getLocation());
        assertEquals(roomAPI.getLocation(), locationAPI.getId());
    }

    @And("a calendar icon should be displayed for Conference Room in the Out Of Order Column")
    public void calendarIsDisplayed(){
        assertTrue(conferenceRoomsPage.isCalendarPresent(conferenceRooms));
    }

    @And("the Conference Room should be reserve on the API")
    public void the_Conference_Room_should_be_reserve_on_the_API(){
        String roomId = DBQuery.getInstance().getIdByKey("rooms","displayName",conferenceRooms.getName());
        OutOfOrders outOfOrdersAPI = APIManager.getInstance().getOutOfOrderByTitle(outOfOrders.getTitle(), roomId);
        assertEquals(outOfOrdersAPI.getRoomID(),roomId);
    }

    @And("the Conference Room not should be reserve on the API")
    public void the_Conference_Room_not_should_be_reserve_on_the_API(){
        ConferenceRooms roomAPI = APIManager.getInstance().getConferenceRoomByName(conferenceRooms.getName());
        OutOfOrders outOfOrdersAPI = APIManager.getInstance().getOutOfOrderByTitle(outOfOrders.getTitle(), roomAPI.getId());
        assertNotSame(outOfOrdersAPI.getRoomID(),roomAPI.getId());
    }

    @And("the API should be displayed disabled to the Conference Room")
    public void the_API_should_be_displayed_disabled_to_the_Conference_Room(){
        ConferenceRooms roomAPI = APIManager.getInstance().getConferenceRoomByName(conferenceRooms.getName());
        assertEquals(roomAPI.getEnabled(),false);
    }

    @Then("a message should be displayed on the OutOfOrderPage")
    public void messageOnOutOfOrderPage(){
        assertTrue(outOfOrderPlanningPage.messageContent());
    }

    @When("I cancel the reservation")
    public void I_cancel_the_reservation(){
        conferenceRoomsPage = outOfOrderPlanningPage.cancelReservation();
    }

    @Then("^the Resource \"(.*?)\" should not be displayed with the quantity \"(.*?)\" list of Conference Room \"(.*?)\"$")
    public void isTheResourceInAssociatedList(String resourceDispalyName,String quantity,String roomDisplayName){
        conferenceRooms.setDisplayName(roomDisplayName);
        conferenceRoomsPage.doubleClickOnSpecificRoom(conferenceRooms);
        Assert.assertFalse(conferenceRoomsPage.isResourceAssociate(quantity, conferenceRooms));
    }

}
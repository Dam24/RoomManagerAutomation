package steps;

import api.APIMethodsLocation;
import api.APIMethodsOutOfOrder;
import api.APIMethodsResource;
import api.APIMethodsRoom;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import database.DBQuery;
import entities.ConferenceRoom;
import entities.Location;
import entities.OutOfOrder;
import entities.Resource;
import org.testng.Assert;
import ui.BaseMainPageObject;
import ui.PageTransporter;
import ui.pages.*;
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
    private LocationsPage locationsPage;

    private DBQuery dbQuery;

    ConferenceRoom conferenceRoom;
    Resource resource;
    Location location;
    OutOfOrder outOfOrder;

    public ConferenceRoomSteps(ConferenceRoom conferenceRooms, Resource resource, Location location, OutOfOrder outOfOrders){
        this.conferenceRoom = conferenceRooms;
        this.resource = resource;
        this.location = location;
        this.outOfOrder = outOfOrders;
        baseMainPageObject = new BaseMainPageObject();
        dbQuery = DBQuery.getInstance();
    }

    @Given("I navigate to Conference Rooms page")
    public void I_navigate_to_Conference_Rooms_page(){
        conferenceRoomsPage = baseMainPageObject.getSideBarMenu().goToConferenceRoomsPage();
    }

    @And("I create a resource with the following dates: \"(.*?)\"")
    public void I_create_a_resource_with_the_following_dates(String resourceName){
        resource.setName(resourceName);
        resource.setDisplayName(resourceName);
        APIMethodsResource.createResource(resource);
        PageTransporter.getInstance().refreshPage();
    }

    @And("I displayed the Resource in the Conference Room's Table")
    public void I_displayed_the_Resource_in_the_Conference_Rooms_Table(){
        conferenceRoomsPage.ensureIsNotDisplayedResourceColumns();
        conferenceRoomsPage.clickOnSpecificResource(resource);
    }

    @When("I associate the Resource to the \"(.*?)\" Conference Room with quantity \"(.*?)\"")
    public void I_associate_the_Resource_to_the_Conference_Room_with_quantity(String roomDisplayName, int quantity){
        conferenceRoom.setName(roomDisplayName);
        conferenceRoom.setDisplayName(roomDisplayName);
        resource.setQuantity(quantity);
        roomInfoPage = conferenceRoomsPage.doubleClickOnSpecificRoom(conferenceRoom);
        resourceAssociationsPage=roomInfoPage.gotoAssociationPage();
        conferenceRoomsPage = resourceAssociationsPage.associateResource(resource, quantity);
    }

    @Then("the \"(.*?)\" message should be displayed")
    public void the_message_should_be_displayed(String message){
        assertTrue(conferenceRoomsPage.isMessagePresent(message));
    }

    @And("the Resource should be displayed with quantity \"(.*?)\" for the Conference Room")
    public void the_Resource_should_be_displayed_with_quantity_for_the_Conference_Room(String quantity){
        assertTrue(conferenceRoomsPage.isResourceAssociate(quantity, conferenceRoom));
        baseMainPageObject= conferenceRoomsPage;
    }

    @And("I create a Location with the following details: \"(.*?)\", \"(.*?)\"")
    public void I_create_a_Location_with_the_following_details(String locationName, String locationDisplayName){
        location.setName(locationName);
        location.setDisplayName(locationDisplayName);
        APIMethodsLocation.createLocation(location);
        PageTransporter.getInstance().refreshPage();
    }

    @When("I associate the \"(.*?)\" Conference Room with the Location in the Room Info page")
    public void I_associate_the_Conference_Room_with_Location_in_the_Room_Info_page(String roomDisplayName){
        conferenceRoom.setName(roomDisplayName);
        conferenceRoom.setDisplayName(roomDisplayName);
        roomInfoPage=conferenceRoomsPage.doubleClickOnSpecificRoom(conferenceRoom);
        conferenceRoomsPage = roomInfoPage.setAssociateLocation(location);
    }

    @When("I navigate to Location Page")
    public void I_navigate_to_Location_Associations_page_of_Location(){
        locationsPage=conferenceRoomsPage.getSideBarMenu().goToLocationPage();
    }

    @Then("the Conference Room should be associated with Location in the Location Associations")
    public void the_Conference_Room_should_be_associated_with_Location_in_the_Location_Associations(){
        assertTrue(locationsPage.isLocationAssociatedToRoom(conferenceRoom,location));
    }

    @When("I disabled the \"(.*?)\" Conference Room")
    public void I_disabled_the_Conference_Room(String roomName){
        conferenceRoom.setName(roomName);
        conferenceRoom.setDisplayName(roomName);
        conferenceRoomsPage.clickOnDisabledConferenceRoom(conferenceRoom);
    }

    @And("the Conference Room should be disabled")
    public void the_Conference_Room_should_be_disabled(){
        assertTrue(conferenceRoomsPage.isConferenceRoomsDisabled(conferenceRoom));
    }

    @When("I reserve the \"(.*?)\" Conference Room with the following information: \"(.*?)\", \"(.*?)\", \"(.*?)\", \"(.*?)\", \"(.*?)\", \"(.*?)\"")
    public void I_reserve_the_Conference_Room_with_the_following_information(String roomName, String fromDate, String toDate, String fromHours, String toHours, String reason, String description){
        conferenceRoom.setName(roomName);
        conferenceRoom.setDisplayName(roomName);
        outOfOrder.setTitle(reason);
        roomInfoPage = conferenceRoomsPage.doubleClickOnSpecificRoom(conferenceRoom);
        outOfOrderPlanningPage = roomInfoPage.gotoOutOfOrderPlanningPage();
        outOfOrderPlanningPage.setOutOfOrderPlanningNoSuccessful(fromDate,toDate,fromHours,toHours,reason,description);
    }

    @And("the Resource association should be obtained using the API for Conference Room")
    public void the_Resource_association_should_be_obtained_using_the_API(){
        resource.setID(dbQuery.getIdByKey("resourcemodels", "name", resource.getName()));
        conferenceRoom.setId(dbQuery.getIdByKey("rooms", "displayName", conferenceRoom.getName()));
        int resQuantity = APIMethodsResource.getIsResourceInConferenceRoomById(conferenceRoom, resource);
        assertEquals(resource.getQuantity(), resQuantity);
    }

    @And("the Conference Room should be associated with Location on API")
    public void the_Conference_Room_should_be_associated_with_Location_on_API(){
        conferenceRoom = APIMethodsRoom.getConferenceRoomByName(conferenceRoom);
        location.setId(dbQuery.getLocationIdByName(location.getName()));
        assertEquals(conferenceRoom.getLocation(), location.getId());
    }

    @And("a calendar icon should be displayed for Conference Room in the Out Of Order Column")
    public void calendarIsDisplayed(){
        assertTrue(conferenceRoomsPage.isCalendarPresent(conferenceRoom));
    }

    @And("the Conference Room should be reserve on the API")
    public void the_Conference_Room_should_be_reserve_on_the_API(){
        conferenceRoom.setId(DBQuery.getInstance().getIdByKey("rooms","displayName",conferenceRoom.getName()));
        OutOfOrder outOfOrdersAPI = APIMethodsOutOfOrder.getOutOfOrderByTitle(conferenceRoom);
        assertEquals(outOfOrdersAPI.getRoomID(),conferenceRoom.getId());
    }

    @And("the Conference Room not should be reserve on the API")
    public void the_Conference_Room_not_should_be_reserve_on_the_API(){
        ConferenceRoom roomAPI = APIMethodsRoom.getConferenceRoomByName(conferenceRoom);
        OutOfOrder outOfOrdersAPI = APIMethodsOutOfOrder.getOutOfOrderByTitle(conferenceRoom);
        assertNotSame(outOfOrdersAPI.getRoomID(),roomAPI.getId());
    }

    @And("the API should be displayed disabled to the Conference Room")
    public void the_API_should_be_displayed_disabled_to_the_Conference_Room(){
        conferenceRoom = APIMethodsRoom.getConferenceRoomByName(conferenceRoom);
        assertEquals(conferenceRoom.getEnabled(),false);
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
    public void isTheResourceInAssociatedList(String quantity,String roomDisplayName){
        conferenceRoom.setDisplayName(roomDisplayName);
        conferenceRoomsPage.doubleClickOnSpecificRoom(conferenceRoom);
        Assert.assertFalse(conferenceRoomsPage.isResourceAssociate(quantity, conferenceRoom));
    }

    @After("@AssignResource")
    public void deleteResourcesByScenario(){

        APIMethodsResource.deleteResourceByID(resource);
        PageTransporter.getInstance().refreshPage();
    }

    @After("@AssignLocation")
    public void deleteRLocationByScenario(){
        APIMethodsLocation.deleteLocationByID(location);
        PageTransporter.getInstance().refreshPage();
    }

    @After("@DisableRoom")
    public void activateRoom(){
        APIMethodsRoom.activateConferenceRooms(conferenceRoom);
        PageTransporter.getInstance().refreshPage();
    }
}
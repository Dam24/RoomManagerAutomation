
@ConferenceRooms
Feature: Conference Room to administrate room and resources

    Background:
      Given I login as "DamianVP" with password "Client123"
      And I navigate to Conference Rooms page

  @AssignResourceOnRoom
  Scenario Outline: User should be able Assign to Resources to Conference Room
    Given I navigate to Conference Rooms page
    And I create a resource with the following dates: "<Resource Name>", "<Resource Display Name>"
    And I displayed the "<Resource Display Name>" Resource in the Conference Room's Table
    When I associate the "<Resource Display Name>" Resource to the "<Room Display Name>" Conference Room with quantity "<Quantity>"
    Then the "<Message>" message should be displayed
    And the Resource should be displayed with quantity "<Quantity>" for the Conference Room
    And the "<Resource Name>" Resource association should be obtained using the API
    And the "<Resource Name>" Resource should be assigned to "<Room Name>" Conference Room into DataBase.

  Examples:
    |Resource Name    |Resource Display Name|Quantity |Room Display Name        |Message                     |
    |Computer         |PC                   |5        |Floor1Room100            |Room successfully Modified  |


  @ReserveRoom
  Scenario Outline: Reserve a Room
    Given I navigate to Conference Rooms page
    When I reserve the "<Room Name>" Conference Room with the following information: "<From Date>", "<To Date>", "<From Hrs>", "<To Hrs>", "<Reason>", "<Description>"
    Then the "<Message>" message should be displayed
    And a calendar icon should be displayed for "<Room Name>" Conference Room in the column "Out Of Order"
    When I navigate to Tablet page
    Then the "<Room Name>" Conference Room should be displayed "Reason" in the search page
    And the "<Room Name>" Conference Room should be reserve on the API
    And the "<Room Name>" Conference Room should be reserve into DataBase.

  Examples:
    |Room Name     |From Date|To Date|From Hrs|To Hrs|Reason|Description|
    |Floor1Room100 |5        |8      |      |      |           |

  @ReserveRoom
  Scenario Outline: User not should be able to reserve a Conference Room with incorrect dates.
    When I reserve the "<Room Name>" Conference Room with the following information: "<From Date>", "<To Date>", "<From Hrs>", "<To Hrs>", "<Reason>", "<Description>"
    Then the "<Message>" message should be displayed
      And the "<Room Name>" Conference Room should not be reserve into DataBase
      And the "<Room Name>" Conference Room should not be reserve on the API
    When I go to Tablet page
    Then the "<Room Name>" Conference Room should be available in the search page
      And the room  not should be saved on the data base.

  Examples:
    |Room Name|From Date|To Date|From Hrs|To Hrs|Reason|Description|Message|

  @AssignRoomOnLocation
  Scenario Outline: User should be able to assign a Conference Room in a specific Location.

    Given I create a Location with the following details: "<Location Name>", "<Location Display Name>"
    And I navigate to Conference Rooms page
    When I associate the "<Room Name>" Conference Room with the Location in the Room Info page
    Then the "<Message>" message should be displayed
    When I navigate to Location Page
    Then the Conference Room should be associated with Location in the Location Associations
    And the "<Room Name>" Conference Room should be associated with Location "<Location Name>" on API
    And the "<Room Name>" Conference Room should be saved with Location "<Location Name>" into DataBase

  Examples:
    |Room Name       |Location Name |Location Display Name|Message|
    |Floor1Room100   |Location1     |Location1            |       |


  @DisableRoom
  Scenario Outline: User should be able to disable a Conference Room
    Given I navigate to Conference Rooms page
    When I disabled the "<Room Name>" Conference Room
    Then the "<Message>" message should be displayed
    And the Conference Room should be disabled
    When I navigate to Tablet page
    Then the "<Room Name>" Conference Room should not be displayed.
    And "<Room Name>" Conference Room should be disabled on API
    And the property Disabled should be false into DataBase

  Examples:
    |Room Name       |Message       |
    |Floor1Room100   |Location1     |

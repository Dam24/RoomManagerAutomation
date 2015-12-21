@Rooms
Feature: Rooms

  @AssignResource
  Scenario Outline: User should be able Assign to Resources to Conference Room
    Given I create a resource with the following dates: "<Resource Name>", "<Resource Display Name>"
      And I navigate to Conference Rooms page
      And I displayed the "<Resource Display Name>" Resource in the Conference Room's Table
    When I associate the "<Resource Display Name>" Resource to the "<Room Display Name>" Conference Room with quantity "<Quantity>"
    Then the "<Message>" message should be displayed
      And the Resource should be displayed with quantity "<Quantity>" for the Conference Room
      And the Resource association should be obtained using the API for Conference Room
      Examples:
        |Resource Name    |Resource Display Name|Quantity |Room Display Name        |Message                     |
        |Printer          |Printer              |5        |Floor1Room19             |Room successfully Modified  |

  @ReserveRoom
  Scenario Outline: Reserve a Room
    Given I navigate to Conference Rooms page
    When I reserve the "<Room Name>" Conference Room with the following information: "<From Date>", "<To Date>", "<From Hrs>", "<To Hrs>", "<Reason>", "<Description>"
    Then the "<Message>" message should be displayed
      And a calendar icon should be displayed for Conference Room in the Out Of Order Column
      And the Conference Room should be reserve on the API
      Examples:
        |Room Name     |From Date|To Date|From Hrs|To Hrs|Reason                   |Description           |Message                   |
        |Floor1Room14  |5        |8      |10      |11     |Closed for reparations   |this is a description |Room successfully Modified|

  @ReserveRoomNegative
  Scenario Outline: User not should be able to reserve a Conference Room with incorrect dates.
    Given I navigate to Conference Rooms page
    When I reserve the "<Room Name>" Conference Room with the following information: "<From Date>", "<To Date>", "<From Hrs>", "<To Hrs>", "<Reason>", "<Description>"
    Then a message should be displayed on the OutOfOrderPage
    When I cancel the reservation
    Then the Conference Room not should be reserve on the API
      Examples:
        |Room Name    |From Date       |To Date       |From Hrs|To Hrs  |Reason                    |Description           |Message                     |
        |Floor1Room13|12/15/2015      |12/16/2015    | 11      |9      |Closed for reparations    |This is a description |"To" field must be greater than "From" field  |

  @AssignLocation
  Scenario Outline: User should be able to assign a Conference Room in a specific Location.
    Given I create a Location with the following details: "<Location Name>", "<Location Display Name>"
      And I navigate to Conference Rooms page
    When I associate the "<Room Name>" Conference Room with the Location in the Room Info page
    Then the "<Message>" message should be displayed
    When I navigate to Location Page
    Then the Conference Room should be associated with Location in the Location Associations
      And the Conference Room should be associated with Location on API
      Examples:
        |Room Name       |Location Name |Location Display Name|Message                          |
        |Floor1Room14    |Location1     |Location1            |Room successfully Modified       |

  @DisableRoom
  Scenario Outline: User should be able to disable a Conference Room
    Given I navigate to Conference Rooms page
    When I disabled the "<Room Name>" Conference Room
    Then the "<Message>" message should be displayed
      And the Conference Room should be disabled
      And the API should be displayed disabled to the Conference Room
      Examples:
        |Room Name       |Message                             |
        |Floor1Room14    |Room Floor1Room14 was disabled      |


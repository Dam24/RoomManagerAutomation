@Tablet
Feature: Meetings

  Background:
    Given I navigate to Tablet page
    And I select the "Floor1Room51" Conference Room

  Scenario: Create a meeting
    Given I navigate to Schedule page
#    When I create a meeting with the following information: "<Organizer>", "<Subject>", "<From>", "<To>", "<Attendees>", "<Body>"
#    Then an information message "<Message>" should be displayed
#    And the meeting should be displayed in the Schedule bar
#    And the meeting information should be displayed in the Next section of Main page
#    And the meeting should be listed in the meetings of Room using the API
#
#  Examples:
#    | Organizer       | Subject            | From | To   | Attendees | Body | Message                     |
#    | jhasmany.quiroz | Meeting about task | 9:00 | 9:30 |           |      | meeting successfully crated |
#
#
#  Scenario Outline: Try to create a meeting with missing information
#    Given I navigate to Available section
#    When I create a meeting with the following information: "<Organizer>", "<Subject>", "<From>", "<To>", "<Attendees>", "<Body>"
#    Then an error "<Error>" message should be displayed
#    And the meesting should not be displayed in the Schedule bar
#    And the meeting information should not be displayed in the Next section of Main page
#    And the meeting should not be listed in the meetings of Room using the API
#
#  Examples:
#  "<Organizer>", "<Subject>", "<From>", "<To>", "<Attendees>", "<Body>", "<Error>"
#
#
#
#  Scenario: Try to create a meeting at the same time than other meeting
#    Given I navigate to Available section
#    And I create a meeting with the following information: "fblajbf", "kdf", "8:00", "9:00", "jidflg", "jdslf"
#    When I create a meeting with the following information: "adsk", "kdf", "8:00", "9:00", "jidflg", "jdslf"
#    Then an error "<Error>" message should be displayed
#    And the meesting should not be displayed in the Schedule bar
#    And the meeting information should not be displayed in the Next section of Main page
#    And the meeting should not be listed in the meetings of Room using the API
#
#
#  Scenario: Try to create a meeting in the room out of order
#    Given I have a room "" with a state Out Of Order between the hours "" to ""
#    And I navigate to Available section
#    When I create a meeting with the following information: "fblajbf", "kdf", "8:00", "9:00", "jidflg", "jdslf"
#    Then an error "<djkfdskljfalsd>" message should be displayed
#    And the meesting should not be displayed in the Schedule bar
#    And the meeting information should not be displayed in the Next section of Main page
#    And the meeting should not be listed in the meetings of Room using the API
#
#
#  Scenario: Update a meeting
#    Given I navigate to Available section
#    And I create a meeting with the following information: "fblajbf", "kdf", "8:00", "9:00", "jidflg", "jdslf"
#    When I update the meeting information: "fblajbf", "kdf", "8:00", "9:00", "jidflg", "jdslf"
#    Then an information message should be displayed
#    And the meeting should be displayed in the Schedule bar
#    And the meeting information should be displayed in the Next section of Main page
#    And the meeting should be listed in the meetings of Room using the API
#
#
#  Scenario: Remove a meeting
#    Given I navigate to Available section
#    And I create a meeting with the following information: "fblajbf", "kdf", "8:00", "9:00", "jidflg", "jdslf"
#    When I remove the meeting
#    Then an information message should be displayed
#    And the meesting should be removed from the the Schedule bar
#    And the meeting information should be removed from the Next section of Main page
#    And the meeting should be listed in the meetings of Room using the API
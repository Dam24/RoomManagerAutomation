@Tablet
Feature: Meetings
  Background:
    Given I'm logged in with the user "" in the tablet main page
    And I select the "FloorRoom10" Conference Room


  Scenario Outline: Create a meeting
    Given I navigate to 'Schedule' page
    When I create a meeting with the following information: "<Organizer>", "<Subject>", "<From>", "<To>", "<Attendees>", "<Body>"
    Then an information message should be displayed
    And the meeting should be displayed in the Schedule bar
    And the meeting information should be displayed in the Next section of Main page
    And the meeting should be listed in the meetings of Room using the API

  Examples:
    |Organizer | Subject | From | To | Attendees | Body | Message |
    | jhasmany.quiroz | Meeting about task | 9:00 | 9:30 |  |  | meeting successfully crated |
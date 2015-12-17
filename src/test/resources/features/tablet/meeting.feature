@Tablet
Feature: Meetings

  Background:
    Given I navigate to Tablet page
    And I select the "Floor1Room54" Conference Room

  Scenario Outline: Create a meeting
    Given I navigate to Schedule page
    When I create a meeting with the following information: "<Organizer>", "<Subject>", "<From>", "<To>", "<Attendees>", "<Body>"
    Then an information message "<Message>" should be displayed
    And the meeting should be displayed in the Schedule bar
    And the meeting information should be displayed in the Next section of Main page
    And the meeting should be listed in the meetings of Room using the API

  Examples:
    | Organizer       | Subject             | From    | To      | Attendees | Body                  | Message                      |
    | jhasmany.quiroz | Meeting #2          | 13:35   | 13:40   |           | This is a message     | Meeting successfully created |


  Scenario Outline: Try to create a meeting with missing information
    Given I navigate to Schedule page
    When I create a meeting with the following information: "<Organizer>", "<Subject>", "<From>", "<To>", "<Attendees>", "<Body>"
    Then an error "<Message>" message should be displayed
    And the meeting should not be displayed in the Schedule bar
    And the meeting information should not be displayed in the Next section of Main page
    And the meeting should not be listed in the meetings of Room using the API

  Examples:
    | Organizer       | Subject             | From    | To      | Attendees | Body                  | Message                 |
    |                 | Meeting #3          | 13:40   | 13:45   |           | This is a message     | Organizer is required   |
    #| jhasmany.quiroz |                     | 10:40   | 10:55   |           | This is a message     | Subject is required     |

  Scenario: Try to create a meeting at the same time than other meeting
    Given I navigate to Schedule page
    And I create a meeting with the following information: "jhasmany.quiroz", "Meeting #4", "13:45", "13:50", "", "This is a message"
    And an information message "Meeting successfully created" should be displayed
    When I create a meeting with the following information: "jhasmany.quiroz", "Meeting #5", "13:50", "13:55", "", "This is a message"
    Then an error "There is a conflict with another meeting, please choose another time interval" message should be displayed
    And the meeting should not be displayed in the Schedule bar
    And the meeting information should not be displayed in the Next section of Main page
    And the meeting should not be listed in the meetings of Room using the API

#  Scenario Outline: Try to create a meeting at the same time than other meeting
#    Given I navigate to Schedule page
#    And I create a meeting with the following information: "<Organizer>", "<Subject>", "<From>", "<To>", "<Attendees>", "<Body>"
#    When I create a meeting with the following information: "<Organizer>", "<Subject>", "<From>", "<To>", "<Attendees>", "<Body>"
#    Then an error "<Message>" message should be displayed
#    And the meeting should not be displayed in the Schedule bar
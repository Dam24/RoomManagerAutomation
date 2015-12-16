@Meetings
Feature: Meetings
  Background:
    Given I'm Sign In the login page selecting the Room "Floor1Room70"

  @CreateMeeting
  Scenario Outline: : Create a meeting
    Given I navigate to Schedule page
    When I create successfully a meeting with the following information: "<Organizer>", "<Subject>", "<From>", "<To>", "<Attendees>", "<Body>"
    Then an information message should be displayed "<Message>"
    And the meeting should be displayed in the Schedule bar
    And the meeting information should be displayed in the Next section of Main page
    And the meeting should be listed in the meetings of Room using the API

  Examples:
    | Organizer       | Subject             | From    | To      | Attendees | Body                  | Message                      |
    | jhasmany.quiroz | Meeting #2          | 22:00   | 22:05   |           | This is a message     | Meeting successfully created |

  Scenario: Remove a meeting
    Given I navigate to Schedule page
    And I create successfully a meeting with the following information: "jhasmany.quiroz", "Meeting #4", "22:05", "22:10", "", "This is a message"
    When I remove the meeting
    Then an information message should be displayed "Meeting successfully removed"
    And the meeting should not be displayed in the Schedule bar
    And the meeting information should not be displayed in the Next section of Main page
#    And the meeting should not be listed in the meetings of Room using the API

  @MeetingMissing
  Scenario Outline: Try to create a meeting with missing information
    Given I navigate to Schedule page
    When I create unsuccessfully a meeting with the following information: "<Organizer>", "<Subject>", "<From>", "<To>", "<Attendees>", "<Body>"
    Then an error "<Message>" message should be displayed
    And the meeting should not be displayed in the Schedule bar
    And the meeting information should not be displayed in the Next section of Main page
#    And the meeting should not be listed in the meetings of Room using the API

  Examples:
    | Organizer       | Subject             | From    | To      | Attendees | Body                  | Message                 |
    |                 | Meeting #3          | 22:10   | 22:15   |           | This is a message     | Organizer is required   |
#    | jhasmany.quiroz |                     | 22:15   | 10:20   |           | This is a message     | Subject is required     |

#  @RemoveMeeting
#  Scenario: Update a meeting
#    Given I navigate to Available section
#    And I create successfully a meeting with the following information: "", "Planning", "08:00", "09:00", "", "Close the Door 08:05"
#    When I update the meeting information: "Planning Change Hour", "11:00", "11:30", "Close the Door 11:05"
#    Then an information message should be displayed "Meeting successfully updated"
#    And the meeting should be displayed in the Schedule bar
#    And the meeting information should be displayed in the Next section of Main page
#    And the meeting should be listed in the meetings of Room using the API
#
  @SameTimeMeeting
  Scenario: Try to create a meeting at the same time than other meeting
    Given I navigate to Schedule page
    And I create successfully a meeting with the following information: "jhasmany.quiroz", "Meeting #4", "22:20", "22:25", "", "This is a message"
    And an information message should be displayed "Meeting successfully created"
    When I create successfully a meeting with the following information: "jhasmany.quiroz", "Meeting #5", "22:20", "22:25", "", "This is a message"
    Then an information conflict message should be displayed "There is a conflict with another meeting, please choose another time interval"
    And the meeting should not be displayed in the Schedule bar
    And the meeting information should not be displayed in the Next section of Main page
#    And the meeting should not be listed in the meetings of Room using the API

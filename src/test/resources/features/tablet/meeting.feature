@Meetings
Feature: Meetings

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
    | jhasmany.quiroz | Meeting #2          | 21:00   | 21:05   |           | This is a message     | Meeting successfully created |


  @SameTimeMeeting
  Scenario: Try to create a meeting at the same time than other meeting
    Given I navigate to Schedule page
    And I create successfully a meeting with the following information: "jhasmany.quiroz", "Meeting #7", "21:25", "21:30", "", "This is a message"
    And an information message should be displayed "Meeting successfully created"
    When I create successfully a meeting with the following information: "jhasmany.quiroz", "Meeting #8", "21:30", "21:35", "", "This is a message"
    Then an information conflict message should be displayed "There is a conflict with another meeting, please choose another time interval"
    And the meeting should not be displayed in the Schedule bar
    And the meeting information should not be displayed in the Next section of Main page
    And the meeting should not be listed in the meetings of Room using the API


  @MeetingMissing
  Scenario Outline: Try to create a meeting with missing information
    Given I navigate to Schedule page
    When I create unsuccessfully a meeting with the following information: "<Organizer>", "<Subject>", "<From>", "<To>", "<Attendees>", "<Body>"
    Then an error "<Message>" message should be displayed
    And the meeting should not be displayed in the Schedule bar
    And the meeting information should not be displayed in the Next section of Main page
    And the meeting should not be listed in the meetings of Room using the API

  Examples:
    | Organizer       | Subject             | From    | To      | Attendees | Body                  | Message                 |
    |                 | Meeting #4          | 21:10   | 21:15   |           | This is a message     | Organizer is required   |
#    | jhasmany.quiroz |                     | 22:15   | 10:20   |           | This is a message     | Subject is required     |


  @RemoveMeeting
  Scenario: Remove a meeting
    Given I navigate to Schedule page
    And I create successfully a meeting with the following information: "jhasmany.quiroz", "Meeting #3", "21:05", "21:10", "", "This is a message"
    When I remove the meeting
    Then an information message should be displayed "Meeting successfully removed"
    And the meeting should not be displayed in the Schedule bar
    And the meeting information should not be displayed in the Next section of Main page
    And the meeting should not be listed in the meetings of Room using the API

  @UpdateMeeting
  Scenario: Update a meeting
    Given I navigate to Schedule page
    And I create successfully a meeting with the following information: "jhasmany.quiroz", "Meeting #6", "21:20", "21:25", "", "This is a message"
    When I update the meeting information: "jhasmany.quiroz", "Meeting Update", "21:20", "21:25", "", "This is a message Update"
    Then an information message should be displayed "Meeting successfully updated"
    And the meeting should be displayed in the Schedule bar
    And the meeting information should be displayed in the Next section of Main page
    And the meeting should be listed in the meetings of Room using the API
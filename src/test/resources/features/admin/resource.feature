Feature: @Resources
Background:
#Given I sign in to Main page with user name "BrayanRosas" and password "Client123"
#    And I create a Resource with: "fa-desktop", "Computer", "Lab Computer", "A personal computer only for use of students" created
#
#  Scenario: Delete a Resource that is assigned to different Conference Room
#    When I delete the Resource "Computer"
##    Given I associate the Resource "Lab Computer" to: "Floor1Room1"

#    Then the Resource "Computer" should not be displayed in the Resources list
##    When I navigate to Conference Rooms page
#    Then the Resource "Lab Computer" should not be displayed in the "Associated" list of Conference Room "Floor1Room1"
#    When I navigate to Tablet page
#    Then the Resource "Lab Computer" should not be displayed in the Resource Tablet list
#      And the Resource "Lab Computer" should not be obtained using the API
#
#
  Scenario: Search Resources that match the search criteria
#    Given I have created "3" Resources which names: "Router", "Computer2", "Personal Computer"
    When I search Resources with search criteria "PC"
    Then the Resources that match the search criteria "PC" should be displayed in Resource List

#  Scenario: Cannot create two Resources with the same Name
#    When I try to create the Resource Name "Computer", "PC" in the Resource page
#    Then an error text "A resource with the same name already exists, please choose another name" is showed in the Resource form
#    When I navigate to Resources page
#    And only one Resource with the same name should be displayed in Resource list
#    When I navigate to Tablet page
#    Then only one Resource with the same name should be displayed in Tablet list
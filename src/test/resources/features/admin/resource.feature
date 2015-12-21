@Resources
Feature: Resources
  Background:
    Given I sign in to Main page with user name "BrayanRosas" and password "Client123"

#  Scenario: Cannot create two Resources with the same Name
#    When I try to create the Resource Name "Computer", "PC" in the Resource page
#    Then an error text "A resource with the same name already exists, please choose another name" is showed in the Resource form
#    When I navigate to Resources page from AddResource
#    Then only one Resource with the same name should be displayed in Resource list
#      And only one Resource with name "Computer" should be obtained by API
#
#  @ResourceFilter
#  Scenario: Search Resources that match the search criteria
#    Given I create a Resource with: "Computer PC,Personal PC,PC" Name
#    When I search Resources with search criteria "PC"
#    Then the Resources that match the search criteria "PC" should be displayed in Resource List

  Scenario: Delete a Resource that is assigned to different Conference Room
    When I delete the Resource "Computer Assigned"
    Then the Resource "Computer Assigned" should not be displayed in the Resources list
      And the Resource "Computer Assigned" should not be obtained using the API
    When I navigate to Tablet page
      And I select the "Floor1Room16" Conference Room
    Then the Resource "Computer Assigned" should not be displayed in the Resource Tablet list
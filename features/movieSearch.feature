Feature: Movie Search Module

  Scenario Outline: Search for currently running movie in selected city and validate results
    Given user is on the Home page
    When user type and enter city "<city>"
    Then user searches for running movie "<movie>" from recommended section
    And the "<movie>" should be present in recommended movie section playing in "<city>"
  
  Examples:
  | city      | movie          |
  | Kolkata  | Dhumketu        |
  | Mumbai    | Param Sundari  |


  Scenario: Validate UI elements on the Movie Search screen
    Given user is on the Movie Search screen
    Then all required UI elements should be displayed correctly

  Scenario: Explore upcoming movies and validate "Cinemas near you" link
    Given user is on the Home page and click a city
    When user clicks on the Movies tab
    And user clicks the Explore Upcoming Movies link
    Then the "Cinemas near you" link should be visible

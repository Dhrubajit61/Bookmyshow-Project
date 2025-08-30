Feature: City Search Module

  Scenario: Search city by entering a valid city name
    Given user is on the City Search page
     When user enters a valid city name "<valid_city>"
    Then search results should display cities matching "<valid_city>"
   
   Examples:
   |valid_city|result|
   |Kolkata| Kolkata|
   |Mumbai|Mumbai|

  Scenario: Search city by entering an invalid/non-existing city name
    Given user is on the City Search page
    When user enters an invalid city name "<invalidCity>"
    Then an error message "<errorMsg>" should be displayed
    
    Examples:
   | invalidCity    | errorMsg  |
   |  Xyzabc         | No results found.  |
    | UnknownCity123 | No results found.   |
    
  Scenario: Select city by clicking city icons
    Given user is on the City Search page
    When user selects the city icon for "<cityIcon>"
    Then search results should display cities related to "<cityIcon>"
    
  Examples:
         | cityIcon    | 
         | Kolkata     | 
	     | Mumbai      |

  Scenario: View all cities from city name dropdown
    Given user is on the City Search page
    When user clicks on city name dropdown and selects "View All Cities"
     Then the list should include few "<city>"
    
    Examples:
  	| city     |
  	| Bareilly | 
	| Dehradun | 
	| Goa      | 
	| Kollapur | 

    

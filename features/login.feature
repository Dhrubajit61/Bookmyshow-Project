Feature: Login Module

  Scenario Outline: Validate login with valid credentials
    Given user is on the Login page
    When user enters mobile "<mobile>" and clicks Next
    And user waits to enter OTP manually and clicks Login
    Then "<result>" message should be displayed

  Examples:
    | mobile        |  result      |
    | 9848759484    | Verify your Mobile Number     |
    
  Scenario Outline: Validate login with invalid mobile number
  Given user is on the Login page
  When user enters mobile "<mobile>"
  Then "<result>" message should be shown

     Examples:
  | mobile      | result                          |
  | 12345       | Invalid mobile number           |
  | 1111  | Invalid mobile number           |
  | 9999      | Invalid mobile number           |
  
   Scenario:Verify login UI elements are functional.
      Given user is on the Login page
      Then Login modal should be displayed
      Then "<texts>" should be displayed
      Examples:
      | texts         |
      | Get Started  |
      
      


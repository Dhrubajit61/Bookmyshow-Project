Feature: Gift Card Balance Check

  Scenario: Validate Check Gift Card Balance icon and error message
    Given the user is on the Gift Card section
    Then the "Check Gift Card Balance" icon should be visible
    When the user clicks on the "Check Gift Card Balance" icon
    And enters an invalid gift card number "<Invalid_Gift_Card>"
    And submits the balance check form
    Then the error message "<Error_msg>" should be displayed
 Examples:
    |Invalid_Gift_Card| Error_msg 							   |
    | dgfgfgffhv       | Invalid Gift voucher Code. (#-4426)   |
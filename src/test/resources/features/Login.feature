Feature: Login functionality

  Scenario: Successful login
    Given the user is on the home page
    Then the user navigates to the login page
    When user enters the credentials
    And clicks on Login
    Then the user should be redirected to the accounts page


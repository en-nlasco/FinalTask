#@Run
#@Before
Feature: Modify account

  Background:
    Given Demoqa shop homepage is opened
    And he clicks account link menu
    And my-account page is opened

  Scenario: 01: Make sign up form creates account

    Given customer enters his new username, email and password in registry form
    And submits his request in registry form
    Then Demoqa shop  login page is opened
    When he clicks back to home
    And clicks account link menu
    When he enters his username and password in login form
    And submits Login request
    Then account name is set as entered username


  Scenario: 02: New user creates account and modifies account name

    Given customer enters his new username, email and password in registry form
    And submits his request in registry form
    Then Demoqa shop  login page is opened
    When he clicks back to home
    And clicks account link menu
    When he enters his username and password in login form
    And submits Login request
    When he click Account settings
    And He enters his First Name
    And He enters his Second Name
    And He enters his new account name
    And submits save request
    Then account name is set as his new Name

  Scenario: 03: Existed user modifies email on account details

    Given Customer enters his credentials in login form
      | Field    | value       |
      | username | tester      |
      | password | FDSA@cde123 |
    And submits Login request
    When he click Account settings
    And He enters his new email
    And submits save request
    Then message email was changed is displayed

  Scenario: 04: Existed user modifies password with wrong value on account details

    Given Customer enters his credentials in login form
      | Field    | value       |
      | username | tester      |
      | password | FDSA@cde123 |
    And submits Login request
    When he click Account settings
    And He enters not valid current password
    Then message not valid current password is displayed
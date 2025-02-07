
@tag
Feature: Error Validation
  I want to use this template for my feature file

  @ErrorValidation
  Scenario Outline: Negative test for Error Validation
    Given I landed on the Ecommerce page 
    When Logged in with the username <name> and password <password>
    Then verify "Incorrect email or password." is dispalyed

     Examples: 
      | name                       |password     |
      | mahesh.gscit1075@gmail.com |P455w0rd@1@  |

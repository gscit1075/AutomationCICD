
@tag
Feature: Purchase the Product from Ecommerce 
  I want to use this template for my feature file

  Background: 
  Given I landed on the Ecommerce page 


  @Purchase
  Scenario Outline: Positive test of purchasing the products
    Given Logged in with the username <name> and password <password>
    When Add the product<productName> to the cart
    And checkout <productName> and submit the order
    Then verify the message "THANKYOU FOR THE ORDER." is display

    Examples: 
      | name                       |password  |productName      |
      | mahesh.gscit1075@gmail.com |P455w0rd@1|IPHONE 13 PRO    |
      



@tag
Feature: Purchase the order from Ecommerece website
  I want to use this template for my feature file

Background:
Given I landed on Ecommerce Page


 

  @tag2
  Scenario Outline: Positive test of submitting the order
    Given Logged in with username  <name> and passowrd <password>
    When add the product <productName> to cart
    And i Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed in ConfirmationPage

    Examples: 
      | name                    |  password|  Product Name|
      |  gowthamiupsc@gmail.com | Vidya@01 |  QWERTY|
      

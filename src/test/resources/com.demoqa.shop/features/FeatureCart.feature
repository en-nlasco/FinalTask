Feature: Scenarios for Cart functionality

  Background:
    Given 'Home' page is displayed
    When User select an item from main page
    And Selected item is displayed on a new page
    And User select Color
    And User select Size
    And User click on 'Add to cart' button

  Scenario: User add items to Cart
    And User press on 'Cart' button
    And User is on 'Cart' page
    Then Selected item is added to cart

  Scenario: User increase quantity of an item
    And User press on 'Cart' button
    And User is on Cart page
    And User increase quantity of product by clicking '+' button
    And User click on 'Update Shopping Cart'
    Then Cart page is updated with right quantity of product

  Scenario: User remove item from Cart
    And User press on 'Cart' button
    When User is on Cart page
    And User remove an item by pressing 'Clear cart' button
    Then Items are removed from Cart



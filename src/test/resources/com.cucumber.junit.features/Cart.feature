@Regression
@REQ_ATLAS-20
Feature: Creating cart and adding product


  @TEST_ATLAS-21
  Scenario: Create cart and add product to the cart
    Given Cart is created
    And Product 2876350 with quantity 2 is added to the cart
    When User open the Cart page
    Then Product "Kruidvat Sensitive Handzeep Navulling" is added with quantity 2


  @TEST_ATLAS-22
  Scenario: Create cart and add product to the cart (building payload with pojo)
    Given Cart is created
    And Product 2876350 with quantity 2 is added to the cart using pojo
    When User open the Cart page
    Then Product "Kruidvat Sensitive Handzeep Navulling" is added with quantity 2
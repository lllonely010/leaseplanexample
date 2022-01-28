@feature:search @feature:product
Feature: Search product

  @positive
  Scenario Outline: Search available product
    When I call the get search product endpoint <product>
    Then verify the search results of product should be displayed
    And verify the product <product> should be in Search results
    And the schema should match with the specification defined in "search_product.json"
    Examples:
      | product |
      | apple   |
      | mango   |
      | tofu    |
      | water   |

  @negative
  Scenario: Search unavailable product
    When I call the get search product endpoint "tea"
    Then verify not found error should be displayed in search results

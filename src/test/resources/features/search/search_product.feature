@feature:search @feature:product
Feature: Search product

  @positive
  Scenario Outline: Search test with available product
    When I call the get search test product endpoint <product>
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
  Scenario: Search test with unavailable product
    When I call the get search test product endpoint tea
    Then verify not found error should be displayed in search results

  @negative
  Scenario: Search test without product
    When I call the get search test endpoint
    Then verify unauthorized error should be displayed in search result

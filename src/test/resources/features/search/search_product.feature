Feature: Search product

  Scenario: Search available product
    When I call the get search product endpoint "apple"
    Then verify the search results of product should be displayed
    And verify the product "apple" should be in Search results
    And the schema should match with the specification defined in "search_product.json"

  Scenario: Search unavailable product
    When I call the get search product endpoint "tea"
    Then verify not found error should be displayed in search results

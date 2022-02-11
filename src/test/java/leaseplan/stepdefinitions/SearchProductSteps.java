package leaseplan.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import leaseplan.actions.SearchProductActions;
import leaseplan.actions.CommonActions;
import net.thucydides.core.annotations.Steps;

import static net.serenitybdd.rest.SerenityRest.*;
import static org.hamcrest.Matchers.*;

public class SearchProductSteps {

    @Steps
    public CommonActions commonActions;

    @Steps
    public SearchProductActions searchProductActions;

    @When("^I call the get search test product endpoint (.*)$")
    public void iCallTheGetSearchProductEndpoint(String product) {
        searchProductActions.searchTestProducts(product);
    }

    @When("I call the get search test endpoint")
    public void iCallTheGetSearchTestEndpoint() {
        searchProductActions.searchTest();
    }

    @Then("verify the search results of product should be displayed")
    public void theSearchResultsOfProductShouldBeDisplayed(){ commonActions.responseCodeIs(200);
    }

    @Then("verify the product list should not be empty in Search results")
    public void theProductShouldBeDisplayedInSearchResults() {
        commonActions.responseShouldNotBeEmptyList();
    }

    @Then("^verify the product (.*) should be in Search results$")
    public void theProductShouldBedInSearchResults(String product) {
        searchProductActions.verityProductInResponseResult(product);
    }

    @Then("verify not found error should be displayed in search results")
    public void notFoundErrorShouldBeDisplayedInSearchResult() {
        then().statusCode(404).body("detail.error", is(true));
    }

    @Then("verify unauthorized error should be displayed in search result")
    public void unauthorizedErrorShouldBeDisplayedInSearchResult() {
        then().statusCode(401).body("detail", is("Not authenticated"));
    }

    @And("the schema should match with the specification defined in {string}")
    public void the_schema_should_match_with_the_specification(String spec) {
        commonActions.verifyResponseSchema(spec);
    }
}

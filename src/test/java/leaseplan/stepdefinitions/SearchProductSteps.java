package leaseplan.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import leaseplan.actions.SearchProductActions;
import leaseplan.actions.CommonActions;
import net.thucydides.core.annotations.Steps;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static org.hamcrest.Matchers.*;

public class SearchProductSteps {

    @Steps
    public CommonActions commonSteps;

    @Steps
    public SearchProductActions searchProductActions;
    @When("^I call the get search product endpoint (.*)$")
    public void iCallTheGetSearchProductEndpoint(String product) {
        searchProductActions.searchProducts(product);
    }

    @Then("verify the search results of product should be displayed")
    public void theSearchResultsOfProductShouldBeDisplayed(){ commonSteps.responseCodeIs(200, lastResponse());
    }

    @Then("verify the product list should not be empty in Search results")
    public void theProductShouldBeDisplayedInSearchResults() {
        commonSteps.responseShouldNotBeEmptyList(lastResponse());
    }

    @Then("^verify the product (.*) should be in Search results$")
    public void theProductShouldBedInSearchResults(String product) {
        searchProductActions.verityProductInResponse(lastResponse(),product);
    }

    @Then("verify not found error should be displayed in search results")
    public void notFoundErrorShouldBeDisplayedInSearchResult() {
        restAssuredThat(response -> response.statusCode(404).body("detail.error", is(true)));
    }

    @And("the schema should match with the specification defined in {string}")
    public void the_schema_should_match_with_the_specification(String spec) {
        commonSteps.verifyResponseSchema(lastResponse(), spec);
    }
}

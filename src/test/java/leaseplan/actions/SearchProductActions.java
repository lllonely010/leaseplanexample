package leaseplan.actions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import leaseplan.common.RequestSpec;
import net.serenitybdd.rest.SerenityRest;
import java.util.HashMap;
import java.util.List;

import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static org.assertj.core.api.Assertions.assertThat;

public class SearchProductActions {

    @When("Get response from search product {string}")
    public Response searchTestProducts(String product) {
        return SerenityRest.given().log().uri().spec(RequestSpec.searchReqSpec()).pathParam("product",product ).get("v1/search/test/{product}");
    }

    @When("Get response from search ")
    public Response searchTest() {
        return SerenityRest.given().log().uri().spec(RequestSpec.searchReqSpec()).get("v1/search/test");
    }

    @Then("Verify the product in response result")
    public void verityProductInResponseResult(String pro){
        List<HashMap<String, Object>> products = lastResponse().jsonPath().getList("$");
        assertThat(products).anyMatch(product -> product.get("title").toString().contains(pro));
    }

}

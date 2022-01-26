package leaseplan.actions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import leaseplan.common.RequestSpec;
import net.serenitybdd.rest.SerenityRest;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchProductActions {

    @When("Get response from search product {string}")
    public Response searchProducts(String product) {
        return SerenityRest.given().log().uri().spec(RequestSpec.searchReqSpec()).pathParam("product",product ).get("v1/search/test/{product}");
    }

    @When("Get the product list from response")
    public List<HashMap<String, Object> > getProducts(Response productRes, String pro){
        List<HashMap<String, Object>> products = productRes.jsonPath().getList("$");
        return products.stream().filter(product -> product.get("title").toString().contains(pro)).collect(Collectors.toList());
    }

    @Then("Verify the product in response")
    public void verityProductInResponse(Response productRes, String pro){
        assertThat(this.getProducts(productRes,pro).size()).isPositive();
    }

}

package leaseplan.actions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import leaseplan.common.RequestSpec;
import net.serenitybdd.rest.SerenityRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchProductActions {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchProductActions.class);

    @When("Get response from search product {string}")
    public Response searchTestProducts(String product) {
        return SerenityRest.given().log().uri().spec(RequestSpec.searchReqSpec()).pathParam("product",product ).get("v1/search/test/{product}");
    }

    @When("Get response from search ")
    public Response searchTest() {
        return SerenityRest.given().log().uri().spec(RequestSpec.searchReqSpec()).get("v1/search/test");
    }

    @When("Get the product list from response")
    public List<HashMap<String, Object> > getProducts(Response productRes, String pro){
        List<HashMap<String, Object>> products = productRes.jsonPath().getList("$");
        LOGGER.info("Response list size is {}", products.size());
        LOGGER.info("Response list is {}", products);
        return products.stream().filter(product -> product.get("title").toString().contains(pro)).collect(Collectors.toList());
    }

    @Then("Verify the product in response")
    public void verityProductInResponse(Response productRes, String pro){
        List<HashMap<String, Object>> filteredProducts = this.getProducts(productRes,pro);
        LOGGER.info("Response filtered list size is {}", filteredProducts.size());
        LOGGER.info("Response list is {}", filteredProducts);
        assertThat(filteredProducts.size()).isPositive();
    }

}

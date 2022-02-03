package leaseplan.actions;

import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.Assertions.assertThat;


public class CommonActions {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonActions.class);

    @Then("Verify that API response is {int}")
    public void responseCodeIs(int responseCode, Response lastResponse) {
        LOGGER.info("Response status code is {}", lastResponse.statusCode());
        assertThat(lastResponse.statusCode()).isEqualTo(responseCode);
    }

    @Then("Verify that response list isn't an empty list")
    public void responseShouldNotBeEmptyList(Response lastResponse) {
        List<HashMap<String, Object>> res = lastResponse.getBody().jsonPath().getList("$");
        LOGGER.info("Response list size is {}", res.size());
        assertThat(res.size()).isNotZero();
    }

    @Then("Verify response schema with {string}")
    public void verifyResponseSchema(Response lastResponse, String schemaJson) {
        lastResponse.then().body(matchesJsonSchemaInClasspath("schema/" + schemaJson));
    }



}

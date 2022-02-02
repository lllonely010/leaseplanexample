package leaseplan.actions;

import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.Assertions.assertThat;


public class CommonActions {

    @Then("Verify that API response is {int}")
    public void responseCodeIs(int responseCode, Response lastResponse) {
        assertThat(lastResponse.statusCode()).isEqualTo(responseCode);
    }

    @Then("Verify that response isn't an empty list")
    public void responseShouldNotBeEmptyList(Response lastResponse) {
        assertThat(lastResponse.getBody().jsonPath().getList("$").size()).isNotZero();
    }

    @Then("Verify response schema with {string}")
    public void verifyResponseSchema(Response lastResponse, String schemaJson) {
        lastResponse.then().body(matchesJsonSchemaInClasspath("schema/" + schemaJson));
    }

}

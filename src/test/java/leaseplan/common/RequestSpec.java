package leaseplan.common;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import net.thucydides.core.guice.Injectors;
import net.thucydides.core.util.EnvironmentVariables;

import static net.serenitybdd.core.environment.EnvironmentSpecificConfiguration.from;

public class RequestSpec {

    public static RequestSpecification searchReqSpec() {
        EnvironmentVariables environmentVariables = Injectors.getInjector()
                .getInstance(EnvironmentVariables.class);

        String baseUrl = from(environmentVariables).getProperty("baseurl");

        return new RequestSpecBuilder()
                     .setBaseUri(baseUrl)
                     .setBasePath("api")
                     .setContentType("application/json")
                     .build();
    }
}
## Introduction

This is a example Rest API test solution for sample endpoint available in https://waarkoop-server.herokuapp.com. 

Tests are written using a combination of SerenityBDD, RestAssured, Cucumber, Junit & Maven.

## Framework & Design Considerations
- Serenity BDD is a library that makes it easier to write high quality automated acceptance tests, with powerful reporting and living documentation features. It has strong support for both web testing with Selenium, and API testing using RestAssured.
- API calls & validations are made using RestAssured and SerenityRest which is a wrapper on top of RestAssured.
- Tests are written in BDD Gherkin format in Cucumber feature files, and it is represented as a living documentation in the test report.
- Each domain package consist of an Action class where API actions are defined.
- These domain models are called from a step-definitions class which are in-turn called from BDD tests.
- A test scenario to validate API response schema has been included for this endpoint in the respective feature file. The API spec for schema comparison is placed inside "schema" folder in test resources. The specs are generated from https://www.liquid-technologies.com/online-json-to-schema-converter.

### The project directory structure

```Gherkin
src
  + test
    + java                          Test runners and supporting code
      + leaseplan                   Domain model package consisting of all functionality
        + actions                   Package for API calls and User actions
            SearchProductActions    API calls and User actions of search product
        + common                    Package for all common actions and steps
            CommonSteps             All common actions across all the domain models
             RequestSpec            Common Request Spec for the API calls
        + stepdefinitions           Step definitions for the BDD feature
        ExampleTestSuite            TestSuite 
    + resources
      + features                    Feature files directory
          search_product.feature    Feature containing BDD scenarios
      + schema                      Folder containing json schema for API schema validation
      Serenity.conf                 Configurations file
```
## Executing the tests 
Run `mvn clean verify` from the command line.

The test results will be recorded here `target/site/serenity/index.html`.
Please run the below command from root directory to open the result after execution.
```bash
open target/site/serenity/index.html 
```
## Gitlab report
- Go to CI/CD --> Pipelines, select the pipeline --> test tab, the online junit report will be there.
- Go to download artifact, open serenity report.


### Additional configurations

Additional command line parameters can be passed for switching the application environment.

Run mvn clean verify -Denvironment=dev

Configurations to for different environments are set in the `test/resources/serenity.conf` file. In real time projects each environment can be configured with its baseurl to run the tests based on different environments.
```
environments {
  default {
    baseurl = "https://waarkoop-server.herokuapp.com"
  }
  dev {
    baseurl = "https://waarkoop-server.herokuapp.com"
  }
  staging {
    baseurl = "https://waarkoop-server.herokuapp.com"
  }
}
```

## My Update of this assignment:
- groupId and artifactId: Change the project identifier
- Remove gradle setting: Since we will use Maven
- Remove some setting in pox.xml: for clean up
- Upgrade serenity:Use the latest since we start a new project
- Restructure code: for better maintenance
- Test suite and test scenarios: for better coverage


## Requirement in feature file 
- Please use endpoint GET https://waarkoop-server.herokuapp.com/api/v1/search/test/{product} for getting the products.
- Available products: "apple", "mango", "tofu", "water"
- Prepare Positive and negative scenarios


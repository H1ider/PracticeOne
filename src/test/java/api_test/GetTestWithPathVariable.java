package api_test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetTestWithPathVariable {
    private static final Logger LOGGER = LogManager.getLogger(GetTestWithPathVariable.class);

    @Test
    public void getSingleUser(){
        LOGGER.info("--------------------API Test: Read(Get) A Single User with Path Variable");

        // Specify the base URL or endpoint of the REST API
        RestAssured.baseURI = "https://reqres.in/api/users";

        // Get the RequestSpecification of the request that we want to send to the server
        //we're creating a request
        RequestSpecification httpRequest = RestAssured.given();

        // make a get request to the server and this will create a response
        String id = "2";
        Response response = httpRequest.request(Method.GET, id);
        LOGGER.debug(response.getBody().asPrettyString());

        // validating the response status code
        Assert.assertEquals(response.getStatusCode(), 200);

        // get the json path object from the response
        JsonPath jsonpath = response.jsonPath();


        //validating the specified email exist in the response body
        String expectedEmailID = "janet.weaver@reqres.in";
        String actualEmailID = jsonpath.getString("data.email");
        Assert.assertEquals(actualEmailID, expectedEmailID);


        LOGGER.info("--------------------End Test: Read(Get) A Single User with Path Variable");
    }

    @Test
    public void attemptToGetUserWithInvalidId(){
        LOGGER.info("--------------------API Test: Attempt To Retrieve User With Invalid id");

        // Specify the base URL or endpoint of the REST API
        RestAssured.baseURI = "https://reqres.in/api/users";

        // Get the RequestSpecification of the request that we want to send to the server
        //we're creating a request
        RequestSpecification httpRequest = RestAssured.given();

        // make a get request to the server and this will create a response
        String invalidId = "23";
        Response response = httpRequest.request(Method.GET, invalidId);
        LOGGER.debug(response.getBody().asPrettyString());

        // validating the response status code
        Assert.assertEquals(response.getStatusCode(), 404);

        JsonPath jsonpath = response.jsonPath();
        Assert.assertEquals(jsonpath.get().toString(), "{}");

        LOGGER.info("--------------------End Test: Attempt To Retrieve User With Invalid id");
    }
}

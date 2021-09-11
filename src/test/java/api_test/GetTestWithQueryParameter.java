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
import java.util.List;

public class GetTestWithQueryParameter {
    private static final Logger LOGGER = LogManager.getLogger(GetTestWithQueryParameter.class);

    @Test
    public void getUsersWithQueryParameter(){
        LOGGER.info("--------------------API Test: Read(Get) ALL Users with query Parameter");

        // Specify the base URL or endpoint of the REST API
        RestAssured.baseURI = "https://reqres.in/api/users";

        // Get the RequestSpecification of the request that we want to send to the server
        //we're creating a request
        RequestSpecification httpRequest = RestAssured.given();

        // make a get request to the server and this will create a response
        Response response = httpRequest.queryParam("page","2").request(Method.GET);
        LOGGER.debug(response.getBody().asPrettyString());

        // validating the response status code
        Assert.assertEquals(response.getStatusCode(), 200);

        // get the json path object from the response
        JsonPath jsonpath = response.jsonPath();

        List<String> list = jsonpath.get("data.email");

        //validating the specified email exist in the response body
        String emailID = "michael.lawson@reqres.in";
        boolean emailExist = list.contains(emailID);

        Assert.assertTrue(emailExist, emailID + " does not exist");
        LOGGER.info("--------------------End Test: Read(Get) ALL Users with query Parameter");
    }
}

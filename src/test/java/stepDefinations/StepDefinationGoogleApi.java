package stepDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import pojoClasses.AddPlace;
import pojoClasses.location;
import resources.APIresources;
import resources.TestDataBuild;
import resources.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static resources.APIresources.addPlaceApi;

public class StepDefinationGoogleApi extends Utils {
    ResponseSpecification responser;
    Response response1;
    RequestSpecification res;
    static String placeId;
    TestDataBuild data=new TestDataBuild();
    @Given("Add place payload with {string} {string} {string}")
    public void add_place_payload_with(String name, String language, String address) throws IOException {

        //spec builder code
        res=given().spec(requestSpecification()).body(data.buildTestData(name, language, address));

    }

    @When("User call {string} API using {string} http request")
    public void user_call_API_using_post_http_request(String resource,String methodType) {

        APIresources apiR=APIresources.valueOf(resource);
        System.out.println(apiR.getResource());
        responser =new ResponseSpecBuilder().expectContentType(ContentType.JSON).expectStatusCode(200).build();
        System.out.println(methodType);
        if(methodType.equalsIgnoreCase("POST")) {
            response1 = (Response) res.when().post(apiR.getResource()).then().spec(responser).extract();
            System.out.println(response1.asString());
        }
        else if(methodType.equalsIgnoreCase("GET"))
        {
            response1 = (Response) res.when().get(apiR.getResource()).then().spec(responser).extract();
            System.out.println(response1.asString());
        }

    }
    @Then("Api call is success with status code {int}")
    public void api_call_is_success_with_status_code(Integer int1) {
        Assert.assertEquals(response1.getStatusCode(),200);


    }
    @Then("{string} in response body is {string}")
    public void in_response_body_is(String string, String string2) {
        String strResponse=response1.asString();
        //JsonPath js=new JsonPath(strResponse);
        Assert.assertEquals(jsonClass(strResponse,string),string2);
        placeId=jsonClass(strResponse,"place_id");

    }

    @Then("verify place_Id created maps to {string} using {string}")
    public void verify_place_Id_created_maps_to_using(String name, String resource) throws IOException {
        //spec builder code
        res=given().spec(requestSpecification()).queryParam("place_id",placeId);
        user_call_API_using_post_http_request(resource,"GET");
        Assert.assertEquals(jsonClass(response1.asString(),"name"),name);
    }
    @Given("delete payload is available")
    public void delete_payload_is_available() throws IOException {
        //spec builder code
        res=given().spec(requestSpecification()).body(data.deletePlacedata(placeId));

    }

}

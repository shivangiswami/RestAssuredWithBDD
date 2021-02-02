package stepDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import org.junit.Assert;
import pojoClasses.GetRates;
import pojoClasses.Rates;

import java.text.SimpleDateFormat;
import java.util.Date;
import static io.restassured.RestAssured.given;

public class Steps {
    //Starting coforge
    private String ratesApiurl;
    private String specificDateUrl;
    @Given("Rates API for Latest Foreign Exchange rates")
    public void ratesApiForLatestForiegnExg()
    {
        ratesApiurl="https://api.ratesapi.io/api/latest";
    }
    @Given("Rates API for specific date Foreign Exchange rates")
    public void ratesApiForSpecificDateForiegnExg()
    {
        specificDateUrl="https://api.ratesapi.io/api/";
    }
    @When("The API is available")
    public void apiAvailability()
    {
        RestAssured.baseURI=ratesApiurl;
    }
    @When("The specific date Foreign Exchange API is available")
    public void apiSpecificDateAvailability()
    {
        RestAssured.baseURI=specificDateUrl;
    }
    @Then("An automated test suite should run which will assert the success status of the response")
    public void validateStatusCode()
    {
        given().log().all().when().log().all().get().then().assertThat().statusCode(200);
    }

    @Then("Assert the response")
    public void assertAPIResponse()
    {
        //Using pojo classes
        GetRates gr= given().log().all().when().log().all().get().then().extract().as(GetRates.class);
        Assert.assertTrue(gr.getBase().equals("EUR"));
        Assert.assertTrue(gr.getDate().equals("2020-12-23"));

        Rates rates=gr.getRates();
        double actualRate=rates.getAUD();
        Assert.assertTrue(actualRate==1.6118);

        //Assering whole response as string
        String response=given().log().all().when().log().all().get().then().extract().response().asString();
        String expectedResponse="{\"base\":\"EUR\",\"rates\":{\"GBP\":0.907,\"HKD\":9.4321,\"IDR\":17351.03,\"ILS\":3.9193,\"DKK\":7.438,\"INR\":89.7945,\"CHF\":1.0837,\"MXN\":24.4449,\"CZK\":26.372,\"SGD\":1.6235,\"THB\":36.772,\"HRK\":7.544,\"MYR\":4.9437,\"NOK\":10.6238,\"CNY\":7.9549,\"BGN\":1.9558,\"PHP\":58.453,\"SEK\":10.1213,\"PLN\":4.5025,\"ZAR\":17.7895,\"CAD\":1.5671,\"ISK\":155.9,\"BRL\":6.2867,\"RON\":4.871,\"NZD\":1.7208,\"TRY\":9.2946,\"JPY\":125.99,\"RUB\":91.636,\"KRW\":1347.45,\"USD\":1.2166,\"HUF\":362.65,\"AUD\":1.6118},\"date\":\"2020-12-23\"}";
        Assert.assertTrue(response.equals(expectedResponse));

    }

    @When("An incorrect or incomplete url is provided")
    public void incorrectURL()
    {
        RestAssured.baseURI=ratesApiurl+1;
    }
    @Then("Assert the negative response")
    public void negativeResponse()
    {
        int StatusCode=given().log().all().when().log().all().get().then().extract().statusCode();
        Assert.assertTrue(StatusCode!=200);
    }

    @Then("An automated test suite should run which will assert the success status of the specific date response")
    public void assertSpecificDateResponse()
    {
        given().log().all().pathParam("date","2010-01-12").when().log().all().get("{date}").then().assertThat().statusCode(200);

    }
    @Then("An automated test suite should run which will assert the whole response")
    public void assertSpecificDateWholeResponse()
    {


        //Assering whole response as string
        String response=given().log().all().pathParam("date","2010-01-12").when().log().all().get("{date}").then().extract().response().asString();
        String expectedResponse="{\"base\":\"EUR\",\"rates\":{\"GBP\":0.8972,\"HKD\":11.2301,\"IDR\":13281.14,\"PLN\":4.0838,\"DKK\":7.4405,\"LVL\":0.7093,\"INR\":66.21,\"CHF\":1.4743,\"MXN\":18.4995,\"CZK\":26.258,\"SGD\":2.0133,\"THB\":47.839,\"BGN\":1.9558,\"MYR\":4.8424,\"NOK\":8.1825,\"CNY\":9.8863,\"HRK\":7.2753,\"PHP\":66.106,\"SEK\":10.2215,\"LTL\":3.4528,\"ZAR\":10.8264,\"CAD\":1.4959,\"BRL\":2.5309,\"RON\":4.1405,\"EEK\":15.6466,\"NZD\":1.9573,\"TRY\":2.1084,\"JPY\":132.41,\"RUB\":42.6974,\"KRW\":1627.4,\"USD\":1.4481,\"HUF\":268.18,\"AUD\":1.5668},\"date\":\"2010-01-12\"}";
        Assert.assertTrue(response.equals(expectedResponse));
    }
    @Then("An automated test suite should run which will validate that the response matches for the current date")
    public void assertfutureDateWholeResponse()
    {
        Date date = new Date();
        String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
        String response=given().log().all().pathParam("date","2021-01-12").when().log().all().get("{date}").then().extract().response().asString();
        String expectedResponse="{\"base\":\"EUR\",\"rates\":{\"GBP\":0.89795,\"HKD\":9.4532,\"IDR\":17314.06,\"ILS\":3.9221,\"DKK\":7.4389,\"INR\":89.6845,\"CHF\":1.0851,\"MXN\":24.3475,\"CZK\":26.299,\"SGD\":1.6195,\"THB\":36.689,\"HRK\":7.5461,\"MYR\":4.951,\"NOK\":10.5108,\"CNY\":7.9624,\"BGN\":1.9558,\"PHP\":58.559,\"SEK\":10.0763,\"PLN\":4.5022,\"ZAR\":17.8252,\"CAD\":1.5656,\"ISK\":155.9,\"BRL\":6.3608,\"RON\":4.8725,\"NZD\":1.7168,\"TRY\":9.2275,\"JPY\":126.38,\"RUB\":90.5914,\"KRW\":1344.97,\"USD\":1.2193,\"HUF\":361.62,\"AUD\":1.6039},\"date\":\""+modifiedDate+"\"}";
        System.out.println(response);
        System.out.println(expectedResponse);
        Assert.assertTrue(response.equals(expectedResponse));
    }

}

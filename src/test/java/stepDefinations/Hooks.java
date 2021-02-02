package stepDefinations;


import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {
   @Before("@DeletePlace")
    public void findPlaceId() throws IOException {
          StepDefinationGoogleApi steps=new StepDefinationGoogleApi();
          if(StepDefinationGoogleApi.placeId==null) {
              steps.add_place_payload_with("abc", "eee", "addd");
              steps.user_call_API_using_post_http_request("addPlaceApi", "POST");
              steps.in_response_body_is("status", "OK");
              System.out.println(StepDefinationGoogleApi.placeId);
          }
    }
}

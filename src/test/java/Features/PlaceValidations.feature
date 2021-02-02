Feature: Validating place Api
 @AddPlace
  Scenario Outline: Verify if place is successfully added using Addplace API
    Given Add place payload with "<Name>" "<Language>" "<Address>"
    When User call "addPlaceApi" API using "POST" http request
    Then Api call is success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_Id created maps to "<Name>" using "getPlaceApi"
    Examples:
      |Name    |Language |Address|
      |Shivangi|Hindi    |Sarla Roses|
      |Ashish  |English  |Sarla roses another room|

@DeletePlace
  Scenario: Verify if delete place API is working
    Given delete payload is available
    When User call "deletePlaceApi" API using "POST" http request
    Then Api call is success with status code 200


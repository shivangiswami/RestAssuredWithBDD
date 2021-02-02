Feature: Rates API Validation

  Scenario: Rates API Validation of response code
    Given Rates API for Latest Foreign Exchange rates
    When The API is available
    Then An automated test suite should run which will assert the success status of the response


  Scenario: Rates API Validation for values of response
    Given Rates API for Latest Foreign Exchange rates
    When The API is available
    Then Assert the response

  Scenario: Rates API Validation negative case
    Given Rates API for Latest Foreign Exchange rates
    When An incorrect or incomplete url is provided
    Then Assert the negative response

  Scenario: Rates API specific date foreign exchange status code
    Given Rates API for specific date Foreign Exchange rates
    When The specific date Foreign Exchange API is available
    Then An automated test suite should run which will assert the success status of the specific date response

  Scenario: Rates API specific date foreign exchange assert response
    Given Rates API for specific date Foreign Exchange rates
    When The specific date Foreign Exchange API is available
    Then An automated test suite should run which will assert the whole response

  Scenario: Rates API foreign exchange assert response
    Given Rates API for specific date Foreign Exchange rates
    When The specific date Foreign Exchange API is available
    Then An automated test suite should run which will validate that the response matches for the current date


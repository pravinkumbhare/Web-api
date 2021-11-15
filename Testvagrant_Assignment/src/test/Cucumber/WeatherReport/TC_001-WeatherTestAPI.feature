@E2E_API
Feature: Weather Functionality to check city temperature through API

  Background:
    Given I am an authorized user

  Scenario: Search the city name and check city temperature from the API
    Given Search the city name to check the temperature
    Then Verify the temperature of the city
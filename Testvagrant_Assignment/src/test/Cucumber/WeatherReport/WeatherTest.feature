@E2E
Feature: Weather Functionality to check city temperature

#  Background:
#  Given Flow till landing page

  Scenario: Search the city name and check city temperature
    Given Enter the city name in search location text box
    And Select the city and state name from the dropdown
    Then Verify the temperature of the city selected


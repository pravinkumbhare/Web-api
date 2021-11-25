@E2E
Feature: Weather Functionality to check city temperature
  Compare the city temperature from two platform

    Scenario: Read the temperature from two platform WEB & API and compare it
      #Web
      Given Open the weather application
      When Enter the city name in search location text box
      And Select the city and state name
      Then Read the temperature of the city selected
      #API
      Given I am an authorized user
      And Search the city name to check the temperature
      And Check the temperature of the city
      Then Verify the temperature of the city from two platform


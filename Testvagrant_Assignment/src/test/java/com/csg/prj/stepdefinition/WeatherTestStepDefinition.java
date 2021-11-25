package com.csg.prj.stepdefinition;

import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;
import org.testng.annotations.*;
import pageobjects.web.BasePageObject;
import pageobjects.web.WebSearchLocation;
import pageobjects.web.WebWeatherForecast;

@Listeners(listener.TestListener.class)

public class WeatherTestStepDefinition extends BasePageObject {
    WebSearchLocation webSearchLocation = new WebSearchLocation(driver);
    WebWeatherForecast webWeatherForecast = new WebWeatherForecast(driver);

    /*@Before
    public void setUp(){
        initialization();
    }*/

    @After
    public void tearDown() {
        try {
            logger.info("Closing browser...");
            if (driver != null) {
                driver.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    @Given("^Open the weather application$")
    public void Open_the_weather_application() throws Throwable {
        initialization();
    }

    @When("^Enter the city name in search location text box$")
    public void Enter_the_city_name_in_search_location_text_box() throws Throwable {
        Assert.assertTrue(webSearchLocation.searchLocation(), "Not able to enter/search the location.");
    }

    @And("^Select the city and state name$")
    public void select_the_city_and_state_name() throws Throwable {
        Assert.assertTrue(webSearchLocation.selectCityFromTheListAppeared(),
                "Unable to select city name from the dropdown list appeared.");
    }

    @Then("^Read the temperature of the city selected$")
    public void read_the_temperature_of_the_city_selected() throws Throwable {
        webTemperature = webWeatherForecast.getTemperatureForSelectedCity();
        Assert.assertNotNull(webTemperature, "Web/UI Weather temperature is not visible.");
        logger.info("Web/UI Weather temperature is : " + webWeatherForecast.getTemperatureForSelectedCity());
    }

}

package com.csg.prj.web.main_glue;

import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.testng.Assert;
import org.testng.annotations.*;
import pageobject.web.BasePageObject;
import pageobject.web.WebSearchLocation;
import pageobject.web.WebWeatherForecast;

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

    @Given("^Flow till landing page$")
    public void flow_till_landing_page() throws Throwable {
        initialization();
    }

    @Given("^Enter the city name in search location text box$")
    public void enter_the_city_name_in_search_location_text_box() throws Throwable {
        Assert.assertTrue(webSearchLocation.searchLocation(), "Not able to enter/search the location.");
    }

    @And("^Select the city and state name from the dropdown$")
    public void select_the_city_and_state_name_from_the_dropdown() throws Throwable {
        Assert.assertTrue(webSearchLocation.selectCityFromTheListAppeared(),
                "Unable to select city name from the dropdown list appeared.");
    }

    @Then("^Verify the temperature of the city selected$")
    public void verify_the_temperature_of_the_city_selected() throws Throwable {
        webTemperature = webWeatherForecast.getTemperatureForSelectedCity();
        Assert.assertNotNull(webTemperature, "Web/UI Weather temperature is not visible.");
        logger.info("Web/UI Weather temperature is : " + webWeatherForecast.getTemperatureForSelectedCity());
    }

}

package com.csg.prj.web.main_glue;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import screen.web.BasePageObject;
import setupConfig.Constants;
import setupConfig.Resources;
import setupConfig.tempComparator;
import setupConfig.weather;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Listeners(setupConfig.TestListener.class)

public class WeatherTestAPIStepDefinition extends BasePageObject {
    private static Response response;
    RequestSpecification request;

    @Given("^I am an authorized user$")
    public void i_Am_An_Authorized_User() {
        RestAssured.baseURI = prop.getProperty("BaseURL");
        request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.queryParam("appid", prop.getProperty("KEY"));
    }

    @Given("^Search the city name to check the temperature$")
    public void search_The_City_Name_To_Check_The_Temperature() {
        request.queryParam("q",Constants.CITY_NAME);
        response = request.get(Resources.getResourceData());
        String jsonString = response.asString();
        logger.info("jsonString : "+ jsonString);
    }

    @Then("^Verify the temperature of the city$")
    public void verify_The_Temperature_Of_The_City() {
        try {
            if(response.getStatusCode()==Constants.STATUS_CODE_SUCCESS){

                JsonPath jsonPath = new JsonPath(response.asString());
                String temp = jsonPath.getString("main.temp");
                logger.info("Temperature is "+temp);
                apiTemperature = (int) temperatureConversion(Constants.TEMPERATURE_TYPE, temp);
                Assert.assertNotNull(apiTemperature, "API weather temperature is not visible.");
                logger.info("API Temperature is : "+ apiTemperature);
            }else {
                Assert.fail("Status Code is: "+ response.getStatusCode() +" not able to fetch data");
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    @Given("^Read the temperature from WEB & API platform$")
    public void read_The_Temperature_From_WEB_API_Platform() {
        logger.info("Weather temperature from API platform : "+apiTemperature);
        logger.info("Weather temperature from WEB platform : "+webTemperature);
    }

    @Then("^Verify the temperature of the city from two platform$")
    public void verifyTheTemperatureOfTheCityFromTwoPlatform() {
        weather.compareTemperature();
        Assert.assertEquals(apiTemperature, webTemperature, "Weather Temperature of WEB & API platform is not matching.");
    }
}

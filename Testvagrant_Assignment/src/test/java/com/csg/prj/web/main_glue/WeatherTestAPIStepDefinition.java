package com.csg.prj.web.main_glue;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Listeners;
import screen.web.BasePageObject;
import setupConfig.Constants;
import setupConfig.Resources;

@Listeners(setupConfig.TestListener.class)

public class WeatherTestAPIStepDefinition extends BasePageObject {
    private static Response response;

    @Given("^I am an authorized user$")
    public void i_Am_An_Authorized_User() {
        RestAssured.baseURI = prop.getProperty("BaseURL");
    }

    @Given("^Search the city name to check the temperature$")
    public void search_The_City_Name_To_Check_The_Temperature() {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.queryParam("q",Constants.CITY_NAME);
        request.queryParam("appid", prop.getProperty("KEY"));
        response = request.get(Resources.getReadResourceData());
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
                temperatureConversion(Constants.TEMPERATURE_TYPE, temp);
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

}

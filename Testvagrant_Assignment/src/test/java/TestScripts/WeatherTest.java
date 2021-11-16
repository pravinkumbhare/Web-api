package TestScripts;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screen.web.BasePageObject;
import screen.web.WebSearchLocation;
import screen.web.WebWeatherForecast;

public class WeatherTest extends BasePageObject {
    WebSearchLocation webSearchLocation = new WebSearchLocation(driver);
    WebWeatherForecast webWeatherForecast = new WebWeatherForecast(driver);

    WeatherTest(){super();}

    @BeforeMethod
    public void setUp(){
        initialization();
    }

    @Test
    public void testUI() {

        Assert.assertTrue(webSearchLocation.searchLocation(), "Not able to enter/search the location.");
        Assert.assertTrue(webSearchLocation.selectCityFromTheListAppeared(),
                "Unable to select city name from the dropdown list appeared.");
        Assert.assertNotNull(webWeatherForecast.getTemperatureForSelectedCity(), "Forecast temperature is not visible.");
    }

    @AfterMethod
    public void tearDown() {
        try {
            logger.info("Closing browser...");
            if (driver != null) {driver.close();}
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}

package screen.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import screen.abstractScreen.WeatherForecastAbstract;

/**
 * Created by pravin.kumbhare on 30-10-2021.
 */
public class WebWeatherForecast extends BasePageObject implements WeatherForecastAbstract {

    public WebWeatherForecast(){}

    public WebWeatherForecast(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

}

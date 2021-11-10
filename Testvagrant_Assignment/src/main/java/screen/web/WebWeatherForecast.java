package screen.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import screen.abstractScreen.WeatherForecastAbstract;

/**
 * Created by pravin.kumbhare on 30-10-2021.
 */
public class WebWeatherForecast extends BasePageObject implements WeatherForecastAbstract {

    public WebWeatherForecast(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public WebWeatherForecast() {}

    private enum Element{
        currentTemp(By.xpath("//h2[contains(text(),'Current Weather')]/..//div[@class='temp']"));

        private final By locator;
        Element(By locator){
            this.locator = locator;
        }
    }

    /**
     * This method is used to fetch temperature from the selected city.
     * @return boolean
     * @since 09.11.2021
     */
    @Override
    public String getTempForSelectedCity() {
        try{
            waitForElementToPresent(Element.currentTemp.locator, 10);
            logger.info("current Temperature is : "+ getText(Element.currentTemp.locator));
            return getText(Element.currentTemp.locator);

        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }
}

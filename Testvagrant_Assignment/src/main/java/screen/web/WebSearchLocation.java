package screen.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import screen.abstractScreen.SearchLocationAbstract;
import setupConfig.Constants;

import java.util.List;

/**
 * Created by pravin.kumbhare on 30-10-2021.
 */
public class WebSearchLocation extends BasePageObject implements SearchLocationAbstract{

    public WebSearchLocation(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public WebSearchLocation() {

    }

    private enum Element{
        searchLocation(By.xpath("//input[@name='query']")),
        listOfSearchedCity(By.xpath("//div[@class='results-container']/div[contains(@class,'search-bar-result search-result')]"));

        private final By locator;
        Element(By locator){
            this.locator = locator;
        }
    }

    /**
     * This method is used to search the location to check the temperature
     * @return boolean
     * @since 09.11.2021
     */
    @Override
    public boolean searchLocation() {
        boolean isEntered = false;
        try {
            waitForElementToPresent(Element.searchLocation.locator, Constants.timeInSeconds);
            isEntered = enterTextIntoTextBox(Element.searchLocation.locator, Constants.cityName);

        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return isEntered;
    }

    /**
     * This method is used to select City from the list appeared
     * @return boolean
     * @since 09.11.2021
     */
    @Override
    public boolean selectCityFromTheListAppeared() {
        boolean isSearched = false;
        try {
            waitForElementToPresent(Element.listOfSearchedCity.locator, 10);
            List<WebElement> listOfSearchedCity = listOfWebElements(Element.listOfSearchedCity.locator);
            for (WebElement cityName : listOfSearchedCity) {
                logger.info("City Name : "+cityName.getText());
                if(cityName.getText().contains(Constants.state)){
                    logger.info("City : "+ cityName.getText());
                    cityName.click();
                    isSearched = true;
                    break;
                }
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return isSearched;
    }
}

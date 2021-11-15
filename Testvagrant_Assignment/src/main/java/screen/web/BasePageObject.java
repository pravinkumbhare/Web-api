package screen.web;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import setupConfig.Constants;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by pravin.kumbhare on 30-10-2021.
 */
public class BasePageObject {
    protected static final Logger logger = LoggerFactory.getLogger(BasePageObject.class);
    public static WebDriver driver;
    protected WebDriverWait wait;
    FileInputStream fis;
    protected static Properties prop;
    protected static ChromeOptions options = null;
    protected static int webTemperature, apiTemperature;

    public BasePageObject() {
        try {
            prop = new Properties();
            fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\environment.properties");
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to initialise and open the browser
     */
    public static void initialization() {
        try {
            if(prop.getProperty("BROWSER").equalsIgnoreCase("chrome")){
                options = notificationPopup();
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\browserDriver\\chromedriver.exe");
                driver = new ChromeDriver(options);
                logger.info("Launching browser..");
                driver.get(prop.getProperty("URL"));
                logger.info("Open the site " + prop.getProperty("URL"));

            }else if(prop.getProperty("BROWSER").equalsIgnoreCase("firefox")){
                //do some stuff
            }else if(prop.getProperty("BROWSER").equalsIgnoreCase("edge")){
                //do some stuff
            } else {
                Assert.fail("Please provide the valid browser name.");
            }

        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    /**
     * This method is used to click on Allow on Show Notifications popup
     *
     * @return ChromeOptions
     */
    public static ChromeOptions notificationPopup() {
        try {
            //Create a map to store  preferences
            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("profile.default_content_setting_values.notifications", 2);
            options = new ChromeOptions();
            options.setExperimentalOption("prefs", prefs);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return options;
    }
/*

    ScreenFactory screenFactory = new ScreenFactory();
    public WebSearchLocation webSearchLocation =
            screenFactory.getScreen(ScreenFactory.PLATFORM_WEB, ScreenFactory.SEARCH_LOCATION_SCREEN);
    public WebWeatherForecast webWeatherForecast =
            screenFactory.getScreen(ScreenFactory.PLATFORM_WEB, ScreenFactory.WEATHER_FORECAST_SCREEN);
*/

    /**
     * This method is used to return webElement
     * @param locator is used to pass locator of webElement
     * @return WebElement
     */
    public WebElement webElement(By locator) {
        return driver.findElement(locator);
    }

    /**
     * This method is used to read the text from the specific webElement
     * @param locator is used to pass locator of webElement
     * @return String
     */
    public String getText(By locator) {
        return driver.findElement(locator).getText();
    }

    /**
     * This method is used to get the list of WebElements
     * @param locator is used to pass locator of webElement
     * @return List<WebElement>
     * @since 08.11.2021
     */
    public List<WebElement> listOfWebElements(By locator) {
        return driver.findElements(locator);
    }

    /**
     * This method is used to enter data into text field.
     * @param locator is used to pass locator of webElement
     * @param textData is used to pass data into textField
     * @since 09.11.2021
     */
    public boolean enterTextIntoTextBox(final By locator, String textData) {
        boolean isTextEntered = false;
        try {
            waitForElementToPresent(locator, Constants.TIME_IN_SECONDS);
            webElement(locator).clear();
            webElement(locator).sendKeys(textData);
            isTextEntered = true;
        } catch (Exception e) {
            logger.error("Could not sent text into : " + locator);
        }
        return isTextEntered;
    }

    /**
     * This method is used to wait till the webElement to be visible
     * @param locator is used to pass locator of webElement
     * @param timeOutInSecond is used to specify time
     * @since 09.11.2021
     */
    public void waitForElementToPresent(By locator, int timeOutInSecond) {
        try {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            wait = new WebDriverWait(driver, timeOutInSecond);
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * This method is used to handle alert popUp in webpage.
     *
     * @return boolean
     * @since 09.11.2021
     */
    public boolean alertAccept() {
        boolean isAlert = false;
        try {
            Alert alert = driver.switchTo().alert();
            logger.info("Alert message is : " + alert.getText());
            alert.accept();
            isAlert = true;
        } catch (Exception e) {
            logger.error("Exception : Accepting Alert Message : " + e.getMessage());
        } finally {
            waitForPageToLoad();
        }
        return isAlert;
    }


    /**
     * This method is used to wait for a page to load
     *
     * @return boolean
     * @since 30.10.2021
     */
    public boolean waitForPageToLoad() {
        try {
            wait.until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver wdriver) {
                    return ((JavascriptExecutor) driver).executeScript(
                            "return document.readyState").equals("complete");
                }
            });
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    /**
     * This method is used to wait for a specific time
     * @param timeInSeconds is used to specify time
     * @since 09.11.2021
     */
    public void implicitWait(long timeInSeconds){
        try{
            driver.manage().timeouts().implicitlyWait(timeInSeconds, TimeUnit.SECONDS);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public float temperatureConversion(String tempType, String temp){
        float Fahrenheit, Celsius, tempConversion;
        tempConversion = Float.parseFloat(temp);

        switch(tempType) {
            case "FahrenheitToCelsius":
                Celsius = ((tempConversion-32)*5)/9;
                logger.info("Temperature in degree celsius is: "+Celsius);
                tempConversion = Celsius;
                break;
            case "CelsiusToFahrenheit":
                Fahrenheit =((tempConversion*9)/5)+32;
                logger.info("Temperature in Fahrenheit is: "+Fahrenheit);
                tempConversion = Fahrenheit;
                break;
            default:
                logger.info("Please provide valid temperature conversion type : "+tempType);
        }
        return tempConversion;
    }

    protected String toFetchDataFromString(String pattern, By locator){
        String fetchedData = "";
        try{
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(getText(locator));
            while(m.find()) {
                logger.info("Current Temperature in integer :"+ m.group(1));
                fetchedData = fetchedData + m.group(1);
            }
        }catch (Exception e){
            logger.info(e.getMessage());
        }
        return fetchedData;
    }

}

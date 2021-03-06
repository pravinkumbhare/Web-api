package factory;

import pageobjects.web.BasePageObject;
import pageobjects.web.WebSearchLocation;
import pageobjects.web.WebWeatherForecast;

public class PageFactory extends BasePageObject {

    public static final String SEARCH_LOCATION_SCREEN = "SEARCH_LOCATION_SCREEN";
    public static final String WEATHER_FORECAST_SCREEN = "WEATHER_FORECAST_SCREEN";

    public static final String PLATFORM_WEB = "WEB";
    public static final String PLATFORM_ANDROID = "ANDROID";
    public static final String PLATFORM_IOS = "IOS";

    public static final String WEB = "web";
    public static final String ANDROID = "android";
    public static final String IOS = "ios";

    private WebSearchLocation webSearchLocation = null;
    private WebWeatherForecast webWeatherForecast = null;

    public <Screen> Screen getScreen(String platform, String screenName) {

        if (platform.equalsIgnoreCase(WEB) && screenName.equalsIgnoreCase(SEARCH_LOCATION_SCREEN)) {
            webSearchLocation = new WebSearchLocation();
            return (Screen) webSearchLocation;

        } else if (platform.equalsIgnoreCase(WEB) && screenName.equalsIgnoreCase(WEATHER_FORECAST_SCREEN)) {
            webWeatherForecast = new WebWeatherForecast();
            return (Screen) webWeatherForecast;

        } else if (platform.equalsIgnoreCase(ANDROID) && screenName.equalsIgnoreCase(SEARCH_LOCATION_SCREEN)) {

            //Not Applicable
            logger.info("Not Applicable");

        } else if (platform.equalsIgnoreCase(IOS) && screenName.equalsIgnoreCase(SEARCH_LOCATION_SCREEN)) {

            //Not Applicable
            logger.info("Not Applicable");

        } else {
            logger.info("Invalid Platform");
        }
        return null;
    }

}

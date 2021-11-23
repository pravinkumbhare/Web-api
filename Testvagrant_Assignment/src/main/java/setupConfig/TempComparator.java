package setupConfig;

import pageobject.web.BasePageObject;

import java.util.Comparator;

public class TempComparator extends BasePageObject implements Comparator<weather> {

    @Override
    public int compare(weather o1, weather o2) {
        try {
            if (o1.temp == o2.temp) {
                logger.info("Weather temperature from two platform UI and API is matching.");
                return 0;

            } else {
                CustomException.validate(o1, o2);
                return -1;
            }

        } catch (WeatherTemperatureNotMatchException ex) {
            logger.info("Caught the exception");

            // printing the message from InvalidAgeException object
            logger.info("Exception is : " + ex);
        }
        return -1;
    }

    /**
     * This is a Custom Exception
     */
    static class WeatherTemperatureNotMatchException extends Exception {

        public WeatherTemperatureNotMatchException(String str) {
            super(str);
            logger.info("Called the constructor of parent Exception.");
        }
    }

    /**
     * This class uses custom exception WeatherTemperatureNotMatchException
     */
    public static class CustomException {

        // method to compare weather
        static void validate(weather temp1, weather temp2) throws WeatherTemperatureNotMatchException {
            if (temp1 != temp2) {

                logger.info("Throwing an object of user defined exception.");
                throw new WeatherTemperatureNotMatchException("Weather temperature from two platform UI and API is not matching.");
            } else {
                logger.info("Weather temperature from two platform UI and API is matching.");
            }
        }
    }

}

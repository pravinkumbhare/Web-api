package config;

import pageobjects.web.BasePageObject;

import java.util.Comparator;

public class TempComparator extends BasePageObject implements Comparator<weather> {

    @Override
    public int compare(weather o1, weather o2) {
        try {
            if (o1.temp == o2.temp) {
                logger.info("Weather temperature from two platform UI: "+ o2.temp +"and API: "+ o1.temp +" is matching.");
                return 0;

            } else {
                CustomException.throwException(o1, o2);
                return -1;
            }

        } catch (WeatherTemperatureNotMatchException ex) {
            logger.info("Caught the exception");
            ex.printStackTrace();
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
        static void throwException(weather obj1, weather obj2) throws WeatherTemperatureNotMatchException {
            if (obj1.temp != obj2.temp) {

                logger.info("Throwing an object of user defined exception.");
                throw new WeatherTemperatureNotMatchException("Weather temperature from two platform UI : "+ obj2.temp +" " +
                        "and API : "+ obj1.temp +" is not matching.");
            }
        }
    }

}

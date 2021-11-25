package config;

import pageobjects.web.BasePageObject;

public class weather extends BasePageObject {
    int temp;

    public weather(int temp) {
        this.temp = temp;
    }

    public static void compareTemperature() {
        new weather(webTemperature);
        new weather(apiTemperature);

        new TempComparator();
    }
}

package pageobject.ios;

import pageobject.abstractpage.SearchLocationAbstract;

public class IOSSearchLocation implements SearchLocationAbstract {
    @Override
    public boolean searchLocation() {
        return false;
    }

    @Override
    public boolean selectCityFromTheListAppeared() {
        return false;
    }
}

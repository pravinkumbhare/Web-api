package pageobjects.android;

import pageobjects.abstractpage.SearchLocationAbstract;

public class AndroidSearchLocation implements SearchLocationAbstract {
    @Override
    public boolean searchLocation() {
        return false;
    }

    @Override
    public boolean selectCityFromTheListAppeared() {
        return false;
    }
}

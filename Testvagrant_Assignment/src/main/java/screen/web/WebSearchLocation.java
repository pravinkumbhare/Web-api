package screen.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import screen.abstractScreen.SearchLocationAbstract;

/**
 * Created by pravin.kumbhare on 30-10-2021.
 */
public class WebSearchLocation extends BasePageObject implements SearchLocationAbstract{

    public WebSearchLocation(){}

    public WebSearchLocation(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    /**
     *
     * @return
     */
    @Override
    public boolean searchLocation() {
        return false;
    }
}

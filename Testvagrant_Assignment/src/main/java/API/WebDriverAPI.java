package API;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class WebDriverAPI {
    public WebDriver driver;

    public WebDriverAPI(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    /**
     * This method is used to wait until the visibility of the expected element
     * @param element
     * @return
     */
    public void waitForElementToAppear(WebElement element){
        System.out.println("Element Name is : "+element.getText());

        try{
            WebDriverWait wait = new WebDriverWait(driver, 60);
            wait.until(ExpectedConditions.presenceOfElementLocated((By) element));

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    /**
     * This method is used for adding wait for given time
     * @param time
     */
    public void implicitWait(long time){
        try{
            driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}

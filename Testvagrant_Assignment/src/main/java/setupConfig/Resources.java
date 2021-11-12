package setupConfig;

import org.testng.Assert;
import screen.web.BasePageObject;

/**
 * Created by pravin.kumbhare on 12.11.2021
 */
public class Resources extends BasePageObject {
    static String resource;

    public static String getReadResourceData(){
        try {
            resource = prop.getProperty("RESOURCE");

            if(!resource.isEmpty()){
                logger.info("Provided resource is : "+ resource);
            }else {
                Assert.fail("Please check the provided resource : "+resource);
            }
        }catch (Exception e){
            logger.info(e.getMessage());
        }
        return resource;
    }
}

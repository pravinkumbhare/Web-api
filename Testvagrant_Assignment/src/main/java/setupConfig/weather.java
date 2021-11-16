package setupConfig;

import screen.web.BasePageObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class weather extends BasePageObject {
    String platformSourceToCheckTemp; int temp;

    weather(String platformSourceToCheckTemp, int temp) {
        this.platformSourceToCheckTemp = platformSourceToCheckTemp;
        this.temp = temp;
    }

    public static void compareTemperature(){
        List<weather> list = new ArrayList<weather>();
        list.add(new weather(Constants.WEB_PLATFORM, webTemperature));
        list.add((new weather(Constants.API_PLATFORM, apiTemperature)));

        Comparator comparator = new tempComparator();
        Collections.sort(list, comparator);
        for (weather we : list) {
            logger.info(we.platformSourceToCheckTemp +"  "+ we.temp);
        }
    }

}

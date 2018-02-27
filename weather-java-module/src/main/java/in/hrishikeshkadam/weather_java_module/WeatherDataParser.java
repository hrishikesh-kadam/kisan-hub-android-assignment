package in.hrishikeshkadam.weather_java_module;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import in.hrishikeshkadam.weather_java_module.model.YearlyData;

/**
 * Created by Hrishikesh Kadam on 26/02/2018
 */

public class WeatherDataParser {

    private static final Logger LOGGER;

    static {

        Logger logger = Logger.getLogger(WeatherDataParser.class.getSimpleName());
        logger.setLevel(Level.FINEST);
        LOGGER = logger;
        LOGGER.log(Level.FINEST, "-> static block");
    }

    public static ArrayList<YearlyData> parseYearOrderedDownloadedBody(String downloadedBody) {

        ArrayList<YearlyData> yearlyDataArrayList = new ArrayList<>();

        String[] downloadedBodyArray = downloadedBody.split("\r\n");
        int lineNumber = -1;

        for (int i = 0; i < downloadedBodyArray.length; i++) {

            if (downloadedBodyArray[i].matches("Year\\s+JAN.*")) {
                lineNumber = i;
                break;
            }
        }

        if (lineNumber == -1) {
            try {
                throw new ParseException("-> Error while parsing downloaded data", 0);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }

        for (int i = lineNumber + 1; i < downloadedBodyArray.length; i++) {

            String[] str = downloadedBodyArray[i].split("\\s+");
            yearlyDataArrayList.add(new YearlyData(str));
        }

        return yearlyDataArrayList;
    }
}

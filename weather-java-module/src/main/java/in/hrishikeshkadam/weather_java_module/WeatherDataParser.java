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

        if (downloadedBody == null || downloadedBody.length() == 0)
            return null;

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
            yearlyDataArrayList.add(constructYearlyOrderedData(str));
        }

        return yearlyDataArrayList;
    }

    public static YearlyData constructYearlyOrderedData(String[] str) {

        YearlyData yearlyData = new YearlyData();

        try {

            yearlyData.setYear(Integer.parseInt(str[0]));
            yearlyData.setJanuaryValue(str[1]);
            yearlyData.setFebruaryValue(str[2]);
            yearlyData.setMarchValue(str[3]);
            yearlyData.setAprilValue(str[4]);
            yearlyData.setMayValue(str[5]);
            yearlyData.setJuneValue(str[6]);
            yearlyData.setJulyValue(str[7]);
            yearlyData.setAugustValue(str[8]);
            yearlyData.setSeptemberValue(str[9]);
            yearlyData.setOctoberValue(str[10]);
            yearlyData.setNovemberValue(str[11]);
            yearlyData.setDecemberValue(str[12]);
            yearlyData.setWinterValue(str[13]);
            yearlyData.setSpringValue(str[14]);
            yearlyData.setSummerValue(str[15]);
            yearlyData.setAutumnValue(str[16]);
            yearlyData.setAnnualValue(str[17]);

        } catch (ArrayIndexOutOfBoundsException e) {
        }

        switch (str.length) {

            case 2:
            case 18:
                break;

            case 4:
                yearlyData.setWinterValue(yearlyData.getMarchValue());
                yearlyData.setMarchValue(null);
                break;

            case 5:
                yearlyData.setWinterValue(yearlyData.getAprilValue());
                yearlyData.setAprilValue(null);
                break;

            case 6:
                yearlyData.setWinterValue(yearlyData.getMayValue());
                yearlyData.setMayValue(null);
                break;

            case 8:
                yearlyData.setWinterValue(yearlyData.getJuneValue());
                yearlyData.setSpringValue(yearlyData.getJulyValue());
                yearlyData.setJuneValue(null);
                yearlyData.setJulyValue(null);
                break;

            case 9:
                yearlyData.setWinterValue(yearlyData.getJulyValue());
                yearlyData.setSpringValue(yearlyData.getAugustValue());
                yearlyData.setJulyValue(null);
                yearlyData.setAugustValue(null);
                break;

            case 10:
                yearlyData.setWinterValue(yearlyData.getAugustValue());
                yearlyData.setSpringValue(yearlyData.getSeptemberValue());
                yearlyData.setAugustValue(null);
                yearlyData.setSeptemberValue(null);
                break;

            case 12:
                yearlyData.setWinterValue(yearlyData.getSeptemberValue());
                yearlyData.setSpringValue(yearlyData.getOctoberValue());
                yearlyData.setSummerValue(yearlyData.getNovemberValue());
                yearlyData.setSeptemberValue(null);
                yearlyData.setOctoberValue(null);
                yearlyData.setNovemberValue(null);
                break;

            case 13:
                yearlyData.setWinterValue(yearlyData.getOctoberValue());
                yearlyData.setSpringValue(yearlyData.getNovemberValue());
                yearlyData.setSummerValue(yearlyData.getDecemberValue());
                yearlyData.setOctoberValue(null);
                yearlyData.setNovemberValue(null);
                yearlyData.setDecemberValue(null);
                break;

            case 14:
                yearlyData.setSummerValue(yearlyData.getWinterValue());
                yearlyData.setWinterValue(yearlyData.getNovemberValue());
                yearlyData.setSpringValue(yearlyData.getDecemberValue());
                yearlyData.setNovemberValue(null);
                yearlyData.setDecemberValue(null);
                break;

            case 16:
                yearlyData.setAugustValue(yearlyData.getSummerValue());
                yearlyData.setSummerValue(yearlyData.getSpringValue());
                yearlyData.setSpringValue(yearlyData.getWinterValue());
                yearlyData.setWinterValue(yearlyData.getDecemberValue());
                yearlyData.setDecemberValue(null);
                break;
        }

        return yearlyData;
    }
}

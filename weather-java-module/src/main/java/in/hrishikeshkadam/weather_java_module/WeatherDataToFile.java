package in.hrishikeshkadam.weather_java_module;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import in.hrishikeshkadam.weather_java_module.model.YearlyData;

/**
 * Created by Hrishikesh Kadam on 28/02/2018
 */

public class WeatherDataToFile {

    public static void initCSV(File file) {

        try {

            file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file, true);

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("region_code,")
                    .append("weather_param,")
                    .append("year,")
                    .append("key,")
                    .append("value\n");

            fileWriter.append(stringBuilder.toString());
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void appendToCSV(File parentFile, String fileName, String regionCode,
                                   String weatherParam, ArrayList<YearlyData> yearlyDataArrayList) {

        if (yearlyDataArrayList == null || yearlyDataArrayList.size() == 0)
            return;

        FileWriter fileWriter;

        try {

            fileWriter = new FileWriter(new File(parentFile, fileName), true);
            StringBuilder stringBuilder = new StringBuilder();
            String formattedWeatherParam = getFormattedWeatherParam(weatherParam);

            for (YearlyData yearlyData : yearlyDataArrayList) {

                LinkedHashMap<String, String> map = yearlyData.getMapKeyValue();

                for (Map.Entry<String, String> entry : map.entrySet()) {

                    String value = entry.getValue() == null || entry.getValue().equals("---") ?
                            "NA" : entry.getValue();

                    stringBuilder.append(String.format("%s,%s,%s,%s,%s\n",
                            regionCode, formattedWeatherParam, yearlyData.getYear(),
                            entry.getKey(), value));
                }
            }

            fileWriter.append(stringBuilder.toString());
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getFormattedWeatherParam(String weatherParam) {

        switch (weatherParam) {
            case "Tmax":
                return "Max temp";
            case "Tmin":
                return "Min temp";
            case "Tmean":
                return "Mean temp";
            default:
                return weatherParam;
        }
    }
}

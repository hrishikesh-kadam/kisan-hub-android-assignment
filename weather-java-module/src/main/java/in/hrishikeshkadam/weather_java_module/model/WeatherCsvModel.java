package in.hrishikeshkadam.weather_java_module.model;

/**
 * Created by Hrishikesh Kadam on 01/03/2018
 */

public class WeatherCsvModel {

    private String regionCode;
    private String weatherParam;
    private long year;
    private String key;
    private String value;

    public WeatherCsvModel(String regionCode, String weatherParam, long year, String key, String value) {
        this.regionCode = regionCode;
        this.weatherParam = weatherParam;
        this.year = year;
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getWeatherParam() {
        return weatherParam;
    }

    public void setWeatherParam(String weatherParam) {
        this.weatherParam = weatherParam;
    }

    public long getYear() {
        return year;
    }

    public void setYear(long year) {
        this.year = year;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "WeatherCsvModel{" +
                "regionCode='" + regionCode + '\'' +
                ", weatherParam='" + weatherParam + '\'' +
                ", year=" + year +
                ", key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    public String toCSVString() {
        return String.format("%s, %s, %s, %s, %s",
                regionCode, weatherParam, year, key, value);
    }
}

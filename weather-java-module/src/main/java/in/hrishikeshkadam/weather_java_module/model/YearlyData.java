package in.hrishikeshkadam.weather_java_module.model;

/**
 * Created by Hrishikesh Kadam on 26/02/2018
 */

public class YearlyData {

    private int year;
    private String januaryValue;
    private String februaryValue;
    private String marchValue;
    private String aprilValue;
    private String mayValue;
    private String juneValue;
    private String julyValue;
    private String augustValue;
    private String septemberValue;
    private String octoberValue;
    private String novemberValue;
    private String decemberValue;
    private String winterValue;
    private String springValue;
    private String summerValue;
    private String autumnValue;
    private String annualValue;

    public YearlyData(String[] str) {

        try {
            year = Integer.parseInt(str[0]);
            januaryValue = str[1];
            februaryValue = str[2];
            marchValue = str[3];
            aprilValue = str[4];
            mayValue = str[5];
            juneValue = str[6];
            julyValue = str[7];
            augustValue = str[8];
            septemberValue = str[9];
            octoberValue = str[10];
            novemberValue = str[11];
            decemberValue = str[12];
            winterValue = str[13];
            springValue = str[14];
            summerValue = str[15];
            autumnValue = str[16];
            annualValue = str[17];
        } catch (ArrayIndexOutOfBoundsException e) {
        }

    }

    public int getYear() {
        return year;
    }

    public String getJanuaryValue() {
        return januaryValue;
    }

    public String getFebruaryValue() {
        return februaryValue;
    }

    public String getMarchValue() {
        return marchValue;
    }

    public String getAprilValue() {
        return aprilValue;
    }

    public String getMayValue() {
        return mayValue;
    }

    public String getJuneValue() {
        return juneValue;
    }

    public String getJulyValue() {
        return julyValue;
    }

    public String getAugustValue() {
        return augustValue;
    }

    public String getSeptemberValue() {
        return septemberValue;
    }

    public String getOctoberValue() {
        return octoberValue;
    }

    public String getNovemberValue() {
        return novemberValue;
    }

    public String getDecemberValue() {
        return decemberValue;
    }

    public String getWinterValue() {
        return winterValue;
    }

    public String getSpringValue() {
        return springValue;
    }

    public String getSummerValue() {
        return summerValue;
    }

    public String getAutumnValue() {
        return autumnValue;
    }

    public String getAnnualValue() {
        return annualValue;
    }

    @Override
    public String toString() {
        return "YearlyData{" +
                "year=" + year +
                ", januaryValue='" + januaryValue + '\'' +
                ", februaryValue='" + februaryValue + '\'' +
                ", marchValue='" + marchValue + '\'' +
                ", aprilValue='" + aprilValue + '\'' +
                ", mayValue='" + mayValue + '\'' +
                ", juneValue='" + juneValue + '\'' +
                ", julyValue='" + julyValue + '\'' +
                ", augustValue='" + augustValue + '\'' +
                ", septemberValue='" + septemberValue + '\'' +
                ", octoberValue='" + octoberValue + '\'' +
                ", novemberValue='" + novemberValue + '\'' +
                ", decemberValue='" + decemberValue + '\'' +
                ", winterValue='" + winterValue + '\'' +
                ", springValue='" + springValue + '\'' +
                ", summerValue='" + summerValue + '\'' +
                ", autumnValue='" + autumnValue + '\'' +
                ", annualValue='" + annualValue + '\'' +
                '}';
    }
}

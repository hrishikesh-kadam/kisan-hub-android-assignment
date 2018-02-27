package in.hrishikeshkadam.weather_java_module.model;

import java.util.LinkedHashMap;

/**
 * Created by Hrishikesh Kadam on 26/02/2018
 */

public class YearlyData {

    public static final String JAN = "JAN";
    public static final String FEB = "FEB";
    public static final String MAR = "MAR";
    public static final String APR = "APR";
    public static final String MAY = "MAY";
    public static final String JUN = "JUN";
    public static final String JUL = "JUL";
    public static final String AUG = "AUG";
    public static final String SEP = "SEP";
    public static final String OCT = "OCT";
    public static final String NOV = "NOV";
    public static final String DEC = "DEC";
    public static final String WIN = "WIN";
    public static final String SPR = "SPR";
    public static final String SUM = "SUM";
    public static final String AUT = "AUT";
    public static final String ANN = "ANN";
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

    public LinkedHashMap<String, String> getMapKeyValue() {

        LinkedHashMap<String, String> map = new LinkedHashMap<>();

        map.put(JAN, januaryValue);
        map.put(FEB, februaryValue);
        map.put(MAR, marchValue);
        map.put(APR, aprilValue);
        map.put(MAY, mayValue);
        map.put(JUN, juneValue);
        map.put(JUL, julyValue);
        map.put(AUG, augustValue);
        map.put(SEP, septemberValue);
        map.put(OCT, octoberValue);
        map.put(NOV, novemberValue);
        map.put(DEC, decemberValue);
        map.put(WIN, winterValue);
        map.put(SPR, springValue);
        map.put(SUM, summerValue);
        map.put(AUT, autumnValue);
        map.put(ANN, annualValue);

        return map;
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

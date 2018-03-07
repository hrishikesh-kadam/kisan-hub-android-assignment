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

    public YearlyData() {
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getJanuaryValue() {
        return januaryValue;
    }

    public void setJanuaryValue(String januaryValue) {
        this.januaryValue = januaryValue;
    }

    public String getFebruaryValue() {
        return februaryValue;
    }

    public void setFebruaryValue(String februaryValue) {
        this.februaryValue = februaryValue;
    }

    public String getMarchValue() {
        return marchValue;
    }

    public void setMarchValue(String marchValue) {
        this.marchValue = marchValue;
    }

    public String getAprilValue() {
        return aprilValue;
    }

    public void setAprilValue(String aprilValue) {
        this.aprilValue = aprilValue;
    }

    public String getMayValue() {
        return mayValue;
    }

    public void setMayValue(String mayValue) {
        this.mayValue = mayValue;
    }

    public String getJuneValue() {
        return juneValue;
    }

    public void setJuneValue(String juneValue) {
        this.juneValue = juneValue;
    }

    public String getJulyValue() {
        return julyValue;
    }

    public void setJulyValue(String julyValue) {
        this.julyValue = julyValue;
    }

    public String getAugustValue() {
        return augustValue;
    }

    public void setAugustValue(String augustValue) {
        this.augustValue = augustValue;
    }

    public String getSeptemberValue() {
        return septemberValue;
    }

    public void setSeptemberValue(String septemberValue) {
        this.septemberValue = septemberValue;
    }

    public String getOctoberValue() {
        return octoberValue;
    }

    public void setOctoberValue(String octoberValue) {
        this.octoberValue = octoberValue;
    }

    public String getNovemberValue() {
        return novemberValue;
    }

    public void setNovemberValue(String novemberValue) {
        this.novemberValue = novemberValue;
    }

    public String getDecemberValue() {
        return decemberValue;
    }

    public void setDecemberValue(String decemberValue) {
        this.decemberValue = decemberValue;
    }

    public String getWinterValue() {
        return winterValue;
    }

    public void setWinterValue(String winterValue) {
        this.winterValue = winterValue;
    }

    public String getSpringValue() {
        return springValue;
    }

    public void setSpringValue(String springValue) {
        this.springValue = springValue;
    }

    public String getSummerValue() {
        return summerValue;
    }

    public void setSummerValue(String summerValue) {
        this.summerValue = summerValue;
    }

    public String getAutumnValue() {
        return autumnValue;
    }

    public void setAutumnValue(String autumnValue) {
        this.autumnValue = autumnValue;
    }

    public String getAnnualValue() {
        return annualValue;
    }

    public void setAnnualValue(String annualValue) {
        this.annualValue = annualValue;
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

# KisanHub Android Assignment

[![APK](https://img.shields.io/badge/Download%20APK-v1.0-brightgreen.svg)](https://github.com/hrishikesh-kadam/kisan-hub-android-assignment/raw/master/KisanHub%20Android%20Assignment.apk)

## Problem Statement

URL: https://www.metoffice.gov.uk/climate/uk/summaries/datasets#yearOrdered [Year ordered
statistics section]

1. Data parsing - Please use Java.

    1.1 Use above URL and write code to programmatically download the data for Max temp, Min
    temp, Mean temp, Sunshine, and Rainfall for UK, England, Wales, and Scotland regions.

    1.2 Parse ALL downloaded files and create a single file called weather.csv to write the above
    data in the following format:

    region_code,weather_param,year, key, value<br>
    England,Max temp,1910,JAN,5.4<br>
    England,Max temp,1910,FEB,6.8<br>
    ..<br>
    ..<br>
    England,Min temp, 1910,JAN,2.3<br>
    ..<br>
    Wales, Max temp,1910,JAN,5.4<br>
    and so on

    Note: Please handle missing data by putting N/A in the output file weather.csv

2. Find 3 interesting facts from the above dataset and present it to us.

## Usage

- Please find formatted file in Download/weather.csv
- I found interesting facts by quering db through Android Instrumented Tests and they are as follows -

  1. Hottest month of UK from Jan 1910 to Jan 2018 was Jul 2006 (23.2 °C)
  2. Coldest month of UK from Jan 1910 to Jan 2018 was Jan 1963 (-4.4 °C)
  3. Highest month Rainfall UK received from Jan 1910 to Jan 2018 was Dec 2015 (218.8 mm)

## Screenshots

<img src="https://github.com/hrishikesh-kadam/kisan-hub-android-assignment/raw/master/screenshots/Screenshot_20180301-064219.jpg" width="240" height="426">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<img src="https://github.com/hrishikesh-kadam/kisan-hub-android-assignment/raw/master/screenshots/Screenshot_20180301-064233.jpg" width="240" height="426"><br/><br/><br/>
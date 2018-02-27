package in.hrishikeshkadam.kisanhub;

import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import in.hrishikeshkadam.kisanhub.data.WeatherContract.WeatherEntry;
import in.hrishikeshkadam.weather_java_module.WeatherDataDownloader;
import in.hrishikeshkadam.weather_java_module.WeatherDataParser;
import in.hrishikeshkadam.weather_java_module.model.Region;
import in.hrishikeshkadam.weather_java_module.model.YearlyData;

/**
 * Created by Hrishikesh Kadam on 26/02/2018
 */

public class WeatherAsyncTaskLoader extends AsyncTaskLoader {

    public static final String LOG_TAG = WeatherAsyncTaskLoader.class.getSimpleName();
    private ArrayList<Region> regionArrayList;

    public WeatherAsyncTaskLoader(@NonNull Context context, Bundle bundle) {
        super(context);
        Log.v(LOG_TAG, "-> constructor");

        //noinspection unchecked
        regionArrayList = bundle.containsKey("regionArrayList") ?
                (ArrayList<Region>) bundle.getSerializable("regionArrayList") : null;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        Log.v(LOG_TAG, "-> onStartLoading");

        forceLoad();
    }

    @Nullable
    @Override
    public Object loadInBackground() {
        Log.v(LOG_TAG, "-> loadInBackground");

        int noOfRowsDeleted =
                getContext().getContentResolver().delete(WeatherEntry.CONTENT_URI, null, null);
        Log.d(LOG_TAG, "-> loadInBackground -> noOfRowsDeleted = " + noOfRowsDeleted);

        for (Region region : regionArrayList) {

            ArrayList<String> weatherParams = region.getWeatherParams();

            for (String weatherParam : weatherParams) {

                String downloadedBody =
                        WeatherDataDownloader.downloadByYear(weatherParam, region.getRegionCode());

                ArrayList<YearlyData> yearlyDataArrayList =
                        WeatherDataParser.parseYearOrderedDownloadedBody(downloadedBody);

                storeInSqliteDbBulkInsert(region.getRegionCode(), weatherParam, yearlyDataArrayList);


            }
        }

        return null;
    }

    private void storeInSqliteDb(String regionCode, String weatherParam,
                                 ArrayList<YearlyData> yearlyDataArrayList) {
        Log.v(LOG_TAG, "-> storeInSqliteDb");

        if (yearlyDataArrayList == null || yearlyDataArrayList.size() == 0)
            return;

        for (YearlyData yearlyData : yearlyDataArrayList) {

            LinkedHashMap<String, String> map = yearlyData.getMapKeyValue();

            for (Map.Entry<String, String> entry : map.entrySet()) {

                ContentValues contentValues = new ContentValues();
                contentValues.put(WeatherEntry.COLUMN_REGION_CODE, regionCode);
                contentValues.put(WeatherEntry.COLUMN_WEATHER_PARAM, weatherParam);
                contentValues.put(WeatherEntry.COLUMN_YEAR, yearlyData.getYear());
                contentValues.put(WeatherEntry.COLUMN_KEY, entry.getKey());
                contentValues.put(WeatherEntry.COLUMN_VALUE, entry.getValue());

                getContext().getContentResolver().insert(WeatherEntry.CONTENT_URI, contentValues);
            }
        }
    }

    private void storeInSqliteDbBulkInsert(String regionCode, String weatherParam,
                                           ArrayList<YearlyData> yearlyDataArrayList) {
        Log.v(LOG_TAG, "-> storeInSqliteDbBulkInsert");

        if (yearlyDataArrayList == null || yearlyDataArrayList.size() == 0)
            return;

        ArrayList<ContentValues> contentValuesArrayList = new ArrayList<>();

        for (YearlyData yearlyData : yearlyDataArrayList) {

            LinkedHashMap<String, String> map = yearlyData.getMapKeyValue();

            for (Map.Entry<String, String> entry : map.entrySet()) {

                ContentValues contentValues = new ContentValues();
                contentValues.put(WeatherEntry.COLUMN_REGION_CODE, regionCode);
                contentValues.put(WeatherEntry.COLUMN_WEATHER_PARAM, weatherParam);
                contentValues.put(WeatherEntry.COLUMN_YEAR, yearlyData.getYear());
                contentValues.put(WeatherEntry.COLUMN_KEY, entry.getKey());
                contentValues.put(WeatherEntry.COLUMN_VALUE, entry.getValue());
                contentValuesArrayList.add(contentValues);
            }
        }

        ContentValues[] contentValuesArray = new ContentValues[contentValuesArrayList.size()];
        contentValuesArrayList.toArray(contentValuesArray);

        getContext().getContentResolver().bulkInsert(
                WeatherEntry.CONTENT_URI, contentValuesArray);
    }
}

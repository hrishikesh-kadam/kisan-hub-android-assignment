package in.hrishikeshkadam.kisanhub;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import in.hrishikeshkadam.kisanhub.data.WeatherContract.WeatherEntry;
import in.hrishikeshkadam.weather_java_module.WeatherDataDownloader;
import in.hrishikeshkadam.weather_java_module.WeatherDataParser;
import in.hrishikeshkadam.weather_java_module.WeatherDataToFile;
import in.hrishikeshkadam.weather_java_module.model.Region;
import in.hrishikeshkadam.weather_java_module.model.YearlyData;

import static android.content.Context.NOTIFICATION_SERVICE;

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
        Log.v(LOG_TAG, "-> loadInBackground -> noOfRowsDeleted = " + noOfRowsDeleted);

        WeatherDataToFile.initCSV(new File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                "weather.csv"));

        for (Region region : regionArrayList) {

            ArrayList<String> weatherParams = region.getWeatherParams();

            for (String weatherParam : weatherParams) {

                String downloadedBody =
                        WeatherDataDownloader.downloadByYear(weatherParam, region.getRegionCode());

                ArrayList<YearlyData> yearlyDataArrayList =
                        WeatherDataParser.parseYearOrderedDownloadedBody(downloadedBody);

                storeInSqliteDbBulkInsert(region.getRegionCode(), weatherParam, yearlyDataArrayList);

                WeatherDataToFile.appendToCSV(
                        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                        "weather.csv", region.getRegionCode(), weatherParam,
                        yearlyDataArrayList);
            }
        }

        //notifyFormattingComplete();

        return null;
    }

    private void notifyFormattingComplete() {
        Log.v(LOG_TAG, "-> notifyFormattingComplete");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            CharSequence name = getContext().getString(R.string.channel_weather_csv_name);
            String description = getContext().getString(R.string.channel_weather_csv_description);
            int importance = NotificationManager.IMPORTANCE_LOW;

            NotificationChannel mChannel =
                    new NotificationChannel(MainApplication.CHANNEL_WEATHER_CSV_ID, name, importance);
            mChannel.setDescription(description);

            NotificationManager notificationManager = (NotificationManager) getContext().getSystemService(
                    NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(mChannel);
        }

        if (Build.VERSION.SDK_INT >= 24) {
            try {
                Method m = StrictMode.class.getMethod("disableDeathOnFileUriExposure");
                m.invoke(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Intent intent = new Intent();
        intent.setAction(android.content.Intent.ACTION_VIEW);
        File file = new File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                "weather.csv");
        intent.setDataAndType(Uri.fromFile(file), "text/comma-separated-values");

        PendingIntent pendingIntent = PendingIntent.getActivity(getContext(), 0, intent, 0);

        Notification notification =
                new NotificationCompat.Builder(getContext(), MainApplication.CHANNEL_WEATHER_CSV_ID)
                        .setContentTitle("Data stored in Download/weather.csv file")
                        .setContentText("Tap to open file")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setPriority(Notification.PRIORITY_HIGH)
                        .setContentIntent(pendingIntent).build();

        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        NotificationManager notificationManager =
                (NotificationManager) getContext().getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);
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

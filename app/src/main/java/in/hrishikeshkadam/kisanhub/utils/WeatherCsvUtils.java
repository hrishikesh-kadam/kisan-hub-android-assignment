package in.hrishikeshkadam.kisanhub.utils;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

import in.hrishikeshkadam.kisanhub.data.WeatherContract.WeatherEntry;
import in.hrishikeshkadam.weather_java_module.model.WeatherCsvModel;

/**
 * Created by Hrishikesh Kadam on 01/03/2018
 */

public class WeatherCsvUtils {

    private static final String LOG_TAG = WeatherCsvModel.class.getSimpleName();

    public static String getInNotInSelectionString(String[] exceptionKeys) {

        if (exceptionKeys == null || exceptionKeys.length == 0)
            throw new UnsupportedOperationException("String[] exceptionKeys cannot be null or empty");

        StringBuilder inNotInSelectionString = new StringBuilder();
        inNotInSelectionString.append("(");

        for (int i = 0; i < exceptionKeys.length; i++) {

            if (i == exceptionKeys.length - 1)
                inNotInSelectionString.append("?");
            else
                inNotInSelectionString.append("?,");
        }

        inNotInSelectionString.append(")");

        return inNotInSelectionString.toString();
    }

    public static String[] getAppendedSelectionArgs(String[] selectionArgs, String[] exceptionKeys) {

        if (exceptionKeys == null || exceptionKeys.length == 0)
            throw new UnsupportedOperationException("String[] exceptionKeys cannot be null or empty");

        ArrayList<String> selectionArgsList = new ArrayList<>();
        selectionArgsList.addAll(Arrays.asList(selectionArgs));
        selectionArgsList.addAll(Arrays.asList(exceptionKeys));
        selectionArgs = new String[selectionArgsList.size()];
        selectionArgsList.toArray(selectionArgs);

        return selectionArgs;
    }

    public static WeatherCsvModel getMax(Context context, String regionCode, String weatherParam,
                                         String[] exceptionKeys) {
        Log.v(LOG_TAG, "-> getMax");

        String selection = WeatherEntry.COLUMN_REGION_CODE + " = ? AND " +
                WeatherEntry.COLUMN_WEATHER_PARAM + " = ? AND " +
                WeatherEntry.COLUMN_KEY + " NOT IN " + getInNotInSelectionString(exceptionKeys);

        String[] selectionArgs = {regionCode, weatherParam};
        selectionArgs = getAppendedSelectionArgs(selectionArgs, exceptionKeys);

        Cursor cursor = context.getContentResolver().query(
                WeatherEntry.CONTENT_URI, null, selection,
                selectionArgs, null);

        //Log.d(LOG_TAG, "-> Cursor count = " + cursor.getCount());

        if (cursor == null || cursor.getCount() < 1)
            return null;

        WeatherCsvModel csvModel = null;
        double maxTemp = 0;

        for (int i = 0; i < cursor.getCount(); i++) {

            cursor.moveToPosition(i);
            double temp = 0;

            try {

                String valueString =
                        cursor.getString(cursor.getColumnIndex(WeatherEntry.COLUMN_VALUE));
                if (valueString == null)
                    continue;
                temp = Double.parseDouble(valueString);

            } catch (NumberFormatException e) {
            }

            if (maxTemp < temp) {
                maxTemp = temp;
                csvModel = new WeatherCsvModel(
                        cursor.getString(cursor.getColumnIndex(WeatherEntry.COLUMN_REGION_CODE)),
                        cursor.getString(cursor.getColumnIndex(WeatherEntry.COLUMN_WEATHER_PARAM)),
                        cursor.getLong(cursor.getColumnIndex(WeatherEntry.COLUMN_YEAR)),
                        cursor.getString(cursor.getColumnIndex(WeatherEntry.COLUMN_KEY)),
                        cursor.getString(cursor.getColumnIndex(WeatherEntry.COLUMN_VALUE)));
            }
        }

        cursor.close();
        return csvModel;
    }

    public static WeatherCsvModel getMin(Context context, String regionCode, String weatherParam,
                                         String[] exceptionKeys) {
        Log.v(LOG_TAG, "-> getMin");

        String selection = WeatherEntry.COLUMN_REGION_CODE + " = ? AND " +
                WeatherEntry.COLUMN_WEATHER_PARAM + " = ? AND " +
                WeatherEntry.COLUMN_KEY + " NOT IN " + getInNotInSelectionString(exceptionKeys);

        String[] selectionArgs = {regionCode, weatherParam};
        selectionArgs = getAppendedSelectionArgs(selectionArgs, exceptionKeys);

        Cursor cursor = context.getContentResolver().query(
                WeatherEntry.CONTENT_URI, null, selection,
                selectionArgs, null);

        //Log.d(LOG_TAG, "-> Cursor count = " + cursor.getCount());

        if (cursor == null || cursor.getCount() < 1)
            return null;

        WeatherCsvModel csvModel = null;
        double minTemp = Double.MAX_VALUE;

        for (int i = 0; i < cursor.getCount(); i++) {

            cursor.moveToPosition(i);
            double temp = Double.MAX_VALUE;

            try {

                String valueString =
                        cursor.getString(cursor.getColumnIndex(WeatherEntry.COLUMN_VALUE));
                if (valueString == null)
                    continue;
                temp = Double.parseDouble(valueString);

            } catch (NumberFormatException e) {
            }

            if (minTemp > temp) {
                minTemp = temp;
                csvModel = new WeatherCsvModel(
                        cursor.getString(cursor.getColumnIndex(WeatherEntry.COLUMN_REGION_CODE)),
                        cursor.getString(cursor.getColumnIndex(WeatherEntry.COLUMN_WEATHER_PARAM)),
                        cursor.getLong(cursor.getColumnIndex(WeatherEntry.COLUMN_YEAR)),
                        cursor.getString(cursor.getColumnIndex(WeatherEntry.COLUMN_KEY)),
                        cursor.getString(cursor.getColumnIndex(WeatherEntry.COLUMN_VALUE)));
            }
        }

        cursor.close();
        return csvModel;
    }
}

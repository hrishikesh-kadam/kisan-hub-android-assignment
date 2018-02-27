package in.hrishikeshkadam.kisanhub.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import in.hrishikeshkadam.kisanhub.data.WeatherContract.WeatherEntry;

/**
 * Created by Hrishikesh Kadam on 27/02/2018
 */

public class WeatherDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "weather.db";
    public static final int DATABASE_VERSION = 1;

    public WeatherDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String CREATE_TABLE = "CREATE TABLE " + WeatherEntry.TABLE_NAME + "(" +
                WeatherEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                WeatherEntry.COLUMN_REGION_CODE + " TEXT NOT NULL, " +
                WeatherEntry.COLUMN_WEATHER_PARAM + " TEXT NOT NULL, " +
                WeatherEntry.COLUMN_YEAR + " INTEGER NOT NULL, " +
                WeatherEntry.COLUMN_KEY + " TEXT NOT NULL, " +
                WeatherEntry.COLUMN_VALUE + " TEXT)";

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        throw new UnsupportedOperationException("onUpgrade not yet implemented");
    }
}

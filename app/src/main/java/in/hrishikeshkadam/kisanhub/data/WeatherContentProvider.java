package in.hrishikeshkadam.kisanhub.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import in.hrishikeshkadam.kisanhub.data.WeatherContract.WeatherEntry;

/**
 * Created by Hrishikesh Kadam on 27/02/2018
 */

public class WeatherContentProvider extends ContentProvider {

    public static final int WEATHER = 100;
    public static final int WEATHER_WITH_ID = 101;

    private static final UriMatcher uriMatcher = buildUriMatcher();
    private WeatherDbHelper weatherDbHelper;

    private static UriMatcher buildUriMatcher() {

        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(WeatherContract.AUTHORITY, WeatherContract.PATH_WEATHER, WEATHER);
        uriMatcher.addURI(WeatherContract.AUTHORITY, WeatherContract.PATH_WEATHER + "/#", WEATHER_WITH_ID);
        return uriMatcher;
    }

    @Override
    public boolean onCreate() {

        weatherDbHelper = new WeatherDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        final SQLiteDatabase db = weatherDbHelper.getReadableDatabase();
        Cursor cursor;
        int match = uriMatcher.match(uri);

        switch (match) {

            case WEATHER:
                cursor = db.query(WeatherEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {

        Uri returnUri;
        final SQLiteDatabase db = weatherDbHelper.getWritableDatabase();

        switch (uriMatcher.match(uri)) {

            case WEATHER:
                long id = db.insert(WeatherEntry.TABLE_NAME, null, values);
                if (id > 0)
                    returnUri = ContentUris.withAppendedId(WeatherEntry.CONTENT_URI, id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri + " in insert method");
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return returnUri;
    }

    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {

        final SQLiteDatabase db = weatherDbHelper.getWritableDatabase();
        int noOfRowsInserted = 0;
        int match = uriMatcher.match(uri);

        switch (match) {

            case WEATHER:

                db.beginTransaction();
                try {
                    for (ContentValues value : values) {
                        db.insert(WeatherEntry.TABLE_NAME, null, value);
                        ++noOfRowsInserted;
                    }
                    db.setTransactionSuccessful();
                } finally {
                    db.endTransaction();
                }

                break;

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        if (noOfRowsInserted > 0)
            getContext().getContentResolver().notifyChange(uri, null);

        return noOfRowsInserted;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        throw new UnsupportedOperationException("getType not yet implemented");
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {

        final SQLiteDatabase db = weatherDbHelper.getWritableDatabase();
        int noOfRowsDeleted;
        int match = uriMatcher.match(uri);

        switch (match) {

            case WEATHER:

                noOfRowsDeleted = db.delete(WeatherEntry.TABLE_NAME, null, null);
                break;

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        if (noOfRowsDeleted > 0)
            getContext().getContentResolver().notifyChange(uri, null);

        return noOfRowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection,
                      @Nullable String[] selectionArgs) {
        throw new UnsupportedOperationException("update not yet implemented");
    }
}

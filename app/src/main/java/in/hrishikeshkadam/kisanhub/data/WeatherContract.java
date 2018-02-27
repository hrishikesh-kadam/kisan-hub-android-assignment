package in.hrishikeshkadam.kisanhub.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Hrishikesh Kadam on 27/02/2018
 */

public class WeatherContract {

    public static final String AUTHORITY = "in.hrishikeshkadam.kisanhub";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    public static final String PATH_WEATHER = "weather";

    public static final class WeatherEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_WEATHER).build();
        public static final String TABLE_NAME = "weather";

        public static final String COLUMN_REGION_CODE = "region_code";
        public static final String COLUMN_WEATHER_PARAM = "weather_param";
        public static final String COLUMN_YEAR = "year";
        public static final String COLUMN_KEY = "key";
        public static final String COLUMN_VALUE = "value";
    }
}

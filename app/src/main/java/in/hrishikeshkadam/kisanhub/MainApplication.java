package in.hrishikeshkadam.kisanhub;

import android.app.Application;
import android.util.Log;

/**
 * Created by Hrishikesh Kadam on 26/02/2018
 */

public class MainApplication extends Application {

    private static final String LOG_TAG = MainApplication.class.getSimpleName();
    public static final String CHANNEL_WEATHER_CSV_ID = "channelWeatherCsvId";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v(LOG_TAG, "-> onCreate");

        AndroidLoggingHandler.reset(new AndroidLoggingHandler());
    }
}

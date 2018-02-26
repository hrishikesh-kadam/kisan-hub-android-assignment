package in.hrishikeshkadam.kisanhub;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.util.ArrayList;

import in.hrishikeshkadam.weather_java_module.model.Region;
import in.hrishikeshkadam.weather_java_module.WeatherDataDownloader;

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

        for (Region region : regionArrayList) {

            ArrayList<String> weatherParams = region.getWeatherParams();

            for (String weatherParam : weatherParams) {

                WeatherDataDownloader.downloadByYear(weatherParam, region.getRegionCode());
            }
        }

        return null;
    }
}

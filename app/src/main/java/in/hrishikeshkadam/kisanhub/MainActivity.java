package in.hrishikeshkadam.kisanhub;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

import in.hrishikeshkadam.weather_java_module.model.Region;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks {

    public static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static final int WEATHER_ASYNC_TASK_LOADER_ID = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(LOG_TAG, "-> onCreate");

        setContentView(R.layout.activity_main);

        ArrayList<Region> regionArrayList = new ArrayList<>();
        regionArrayList.add(new Region("UK",
                new ArrayList<>(Arrays.asList("Tmax", "Tmin", "Tmean", "Sunshine", "Rainfall"))));
        regionArrayList.add(new Region("England",
                new ArrayList<>(Arrays.asList("Tmax", "Tmin", "Tmean", "Sunshine", "Rainfall"))));
        regionArrayList.add(new Region("Wales",
                new ArrayList<>(Arrays.asList("Tmax", "Tmin", "Tmean", "Sunshine", "Rainfall"))));
        regionArrayList.add(new Region("Scotland",
                new ArrayList<>(Arrays.asList("Tmax", "Tmin", "Tmean", "Sunshine", "Rainfall"))));

        Bundle bundle = new Bundle();
        bundle.putSerializable("regionArrayList", regionArrayList);

        getSupportLoaderManager().initLoader(WEATHER_ASYNC_TASK_LOADER_ID, bundle, this);
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {

        switch (id) {
            case WEATHER_ASYNC_TASK_LOADER_ID:
                Log.v(LOG_TAG, "-> onCreateLoader -> " + getLoaderString(id));
                return new WeatherAsyncTaskLoader(this, args);
        }

        return null;
    }

    @Override
    public void onLoadFinished(Loader loader, Object data) {

        switch (loader.getId()) {

            case WEATHER_ASYNC_TASK_LOADER_ID:
                Log.v(LOG_TAG, "-> onLoadFinished -> " + getLoaderString(loader.getId()));
                break;
        }
    }

    @Override
    public void onLoaderReset(Loader loader) {
        Log.v(LOG_TAG, "-> onLoaderReset -> " + getLoaderString(loader.getId()));

    }

    public String getLoaderString(int id) {

        switch (id) {

            case WEATHER_ASYNC_TASK_LOADER_ID:
                return WeatherAsyncTaskLoader.LOG_TAG;

            default:
                throw new UnsupportedOperationException("Unknown id : " + id);
        }
    }
}

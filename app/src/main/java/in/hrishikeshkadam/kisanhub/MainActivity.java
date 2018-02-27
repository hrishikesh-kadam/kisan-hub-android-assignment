package in.hrishikeshkadam.kisanhub;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
    public static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 102;
    private Bundle bundle;

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

        bundle = new Bundle();
        bundle.putSerializable("regionArrayList", regionArrayList);

        if (Build.VERSION.SDK_INT >= 23)
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.v(LOG_TAG, "-> onRequestPermissionsResult");

        switch (requestCode) {
            case WRITE_EXTERNAL_STORAGE_REQUEST_CODE:

                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(LOG_TAG, "-> onRequestPermissionsResult -> WRITE_EXTERNAL_STORAGE PERMISSION_GRANTED");
                } else {
                    Log.e(LOG_TAG, "-> onRequestPermissionsResult -> WRITE_EXTERNAL_STORAGE PERMISSION_DENIED");
                }

                getSupportLoaderManager().initLoader(WEATHER_ASYNC_TASK_LOADER_ID, bundle, this);
                break;

            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
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

package in.hrishikeshkadam.kisanhub;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import in.hrishikeshkadam.weather_java_module.model.Region;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks {

    public static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static final int WEATHER_ASYNC_TASK_LOADER_ID = 101;
    public static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 102;
    @BindViews({R.id.ukWeatherParamsLayout, R.id.englandWeatherParamsLayout,
            R.id.walesWeatherParamsLayout, R.id.scotlandWeatherParamsLayout})
    List<ConstraintLayout> regionWeatherParamsLayoutList;
    @BindView(R.id.buttonDownloadFormat)
    Button buttonDownloadFormat;
    @BindView(R.id.checkboxSelectAll)
    CheckBox checkboxSelectAll;
    @BindView(R.id.loadingMaskLayout)
    ConstraintLayout loadingMaskLayout;
    private String[] regionCodes = new String[]{"UK", "England", "Wales", "Scotland"};
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(LOG_TAG, "-> onCreate");

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initCheckBoxesWeatherParams();

        /*if (Build.VERSION.SDK_INT >= 23)
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    WRITE_EXTERNAL_STORAGE_REQUEST_CODE);*/
    }

    private void initCheckBoxesWeatherParams() {
        Log.v(LOG_TAG, "-> initCheckBoxesWeatherParams");

        for (int i = 0; i < regionWeatherParamsLayoutList.size(); i++) {

            ConstraintLayout constraintLayout = regionWeatherParamsLayoutList.get(i);
            CheckBox checkBox = constraintLayout.findViewById(R.id.checkboxRegion);
            checkBox.setText(regionCodes[i]);
        }
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

                loadingMaskLayout.setVisibility(View.INVISIBLE);

                Intent intent = new Intent(this, DetailsActivity.class);
                startActivity(intent);
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

    @OnClick(R.id.buttonDownloadFormat)
    public void onClick() {
        Log.v(LOG_TAG, "-> onClickButtonDownloadFormat");

        loadingMaskLayout.setVisibility(View.VISIBLE);

        ArrayList<Region> regionArrayList = constructRegionArrayList();

        Log.v(LOG_TAG, "-> onClickButtonDownloadFormat -> " + regionArrayList);

        bundle = new Bundle();
        bundle.putSerializable("regionArrayList", regionArrayList);

        getSupportLoaderManager().restartLoader(WEATHER_ASYNC_TASK_LOADER_ID, bundle, this);
    }

    private ArrayList<Region> constructRegionArrayList() {
        Log.v(LOG_TAG, "-> constructRegionArrayList");

        ArrayList<Region> regionArrayList = new ArrayList<>();

        for (int i = 0; i < regionWeatherParamsLayoutList.size(); i++) {

            ConstraintLayout constraintLayout = regionWeatherParamsLayoutList.get(i);
            CheckBox checkBox = constraintLayout.findViewById(R.id.checkboxRegion);

            if (!checkBox.isChecked())
                continue;

            String region = (String) checkBox.getText();
            ArrayList<String> weatherParams = new ArrayList<>();

            checkBox = constraintLayout.findViewById(R.id.checkboxMaxTemp);
            if (checkBox.isChecked())
                weatherParams.add("Tmax");

            checkBox = constraintLayout.findViewById(R.id.checkboxMinTemp);
            if (checkBox.isChecked())
                weatherParams.add("Tmin");

            checkBox = constraintLayout.findViewById(R.id.checkboxMeanTemp);
            if (checkBox.isChecked())
                weatherParams.add("Tmean");

            checkBox = constraintLayout.findViewById(R.id.checkboxSunshine);
            if (checkBox.isChecked())
                weatherParams.add("Sunshine");

            checkBox = constraintLayout.findViewById(R.id.checkboxRainfall);
            if (checkBox.isChecked())
                weatherParams.add("Rainfall");

            regionArrayList.add(new Region(region, weatherParams));
        }

        return regionArrayList;
    }

    @OnCheckedChanged(R.id.checkboxSelectAll)
    public void onCheckedChanged(CheckBox checkboxSelectAll) {
        Log.v(LOG_TAG, "-> onCheckedChanged");

        boolean state = checkboxSelectAll.isChecked();

        for (int i = 0; i < regionWeatherParamsLayoutList.size(); i++) {

            ConstraintLayout constraintLayout = regionWeatherParamsLayoutList.get(i);
            CheckBox checkBox = constraintLayout.findViewById(R.id.checkboxRegion);
            checkBox.setChecked(state);
            checkBox = constraintLayout.findViewById(R.id.checkboxMaxTemp);
            checkBox.setChecked(state);
            checkBox = constraintLayout.findViewById(R.id.checkboxMinTemp);
            checkBox.setChecked(state);
            checkBox = constraintLayout.findViewById(R.id.checkboxMeanTemp);
            checkBox.setChecked(state);
            checkBox = constraintLayout.findViewById(R.id.checkboxSunshine);
            checkBox.setChecked(state);
            checkBox = constraintLayout.findViewById(R.id.checkboxRainfall);
            checkBox.setChecked(state);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.v(LOG_TAG, "-> onSaveInstanceState");

        ArrayList<Region> regionArrayList = constructRegionArrayList();
        outState.putSerializable("regionArrayList", regionArrayList);

        outState.putBoolean("isLoadingMaskLayoutVisible",
                loadingMaskLayout.getVisibility() == View.VISIBLE);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.v(LOG_TAG, "-> onRestoreInstanceState");

        int visibility = savedInstanceState.getBoolean("isLoadingMaskLayoutVisible") ?
                View.VISIBLE : View.INVISIBLE;

        if (visibility == View.VISIBLE)
            getSupportLoaderManager().initLoader(WEATHER_ASYNC_TASK_LOADER_ID, bundle, this);

        loadingMaskLayout.setVisibility(visibility);

        @SuppressWarnings("unchecked") ArrayList<Region> regionArrayList =
                (ArrayList<Region>) savedInstanceState.getSerializable("regionArrayList");

        if (regionArrayList == null)
            regionArrayList = new ArrayList<>();

        for (Region region : regionArrayList) {

            ConstraintLayout constraintLayout = null;
            CheckBox checkBox;

            switch (region.getRegionCode()) {

                case "UK":
                    constraintLayout = regionWeatherParamsLayoutList.get(0);
                    checkBox = constraintLayout.findViewById(R.id.checkboxRegion);
                    checkBox.setChecked(true);
                    break;

                case "England":
                    constraintLayout = regionWeatherParamsLayoutList.get(1);
                    checkBox = constraintLayout.findViewById(R.id.checkboxRegion);
                    checkBox.setChecked(true);
                    break;

                case "Wales":
                    constraintLayout = regionWeatherParamsLayoutList.get(2);
                    checkBox = constraintLayout.findViewById(R.id.checkboxRegion);
                    checkBox.setChecked(true);
                    break;

                case "Scotland":
                    constraintLayout = regionWeatherParamsLayoutList.get(3);
                    checkBox = constraintLayout.findViewById(R.id.checkboxRegion);
                    checkBox.setChecked(true);
                    break;
            }

            if (constraintLayout == null)
                continue;

            ArrayList<String> weatherParams = region.getWeatherParams();

            for (String weatherParam : weatherParams) {

                switch (weatherParam) {

                    case "Tmax":
                        checkBox = constraintLayout.findViewById(R.id.checkboxMaxTemp);
                        checkBox.setChecked(true);
                        break;

                    case "Tmin":
                        checkBox = constraintLayout.findViewById(R.id.checkboxMinTemp);
                        checkBox.setChecked(true);
                        break;

                    case "Tmean":
                        checkBox = constraintLayout.findViewById(R.id.checkboxMeanTemp);
                        checkBox.setChecked(true);
                        break;

                    case "Sunshine":
                        checkBox = constraintLayout.findViewById(R.id.checkboxSunshine);
                        checkBox.setChecked(true);
                        break;

                    case "Rainfall":
                        checkBox = constraintLayout.findViewById(R.id.checkboxRainfall);
                        checkBox.setChecked(true);
                        break;
                }
            }
        }
    }
}

package in.hrishikeshkadam.kisanhub;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.hrishikeshkadam.kisanhub.data.WeatherContract;

public class DetailsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks {

    public static final String LOG_TAG = DetailsActivity.class.getSimpleName();
    public static final int VIEW_WEATHER_CSV_LOADER_ID = 101;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    DetailsAdapter detailsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(LOG_TAG, "-> onCreate");


        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        detailsAdapter = new DetailsAdapter(null);
        recyclerView.setAdapter(detailsAdapter);

        getSupportLoaderManager().initLoader(VIEW_WEATHER_CSV_LOADER_ID, null, this);
    }

    public String getLoaderString(int id) {

        switch (id) {

            case VIEW_WEATHER_CSV_LOADER_ID:
                return "VIEW_WEATHER_CSV_LOADER_ID";

            default:
                throw new UnsupportedOperationException("Unknown id : " + id);
        }
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {

        switch (id) {
            case VIEW_WEATHER_CSV_LOADER_ID:
                Log.v(LOG_TAG, "-> onCreateLoader -> " + getLoaderString(id));
                return new CursorLoader(this, WeatherContract.WeatherEntry.CONTENT_URI,
                        null, null, null, null);
        }

        return null;
    }

    @Override
    public void onLoadFinished(Loader loader, Object data) {

        switch (loader.getId()) {

            case VIEW_WEATHER_CSV_LOADER_ID:
                Log.v(LOG_TAG, "-> onLoadFinished -> " + getLoaderString(loader.getId()));

                detailsAdapter.swapCursor((Cursor) data);
                break;
        }
    }

    @Override
    public void onLoaderReset(Loader loader) {
        Log.v(LOG_TAG, "-> onLoaderReset -> " + getLoaderString(loader.getId()));
    }
}

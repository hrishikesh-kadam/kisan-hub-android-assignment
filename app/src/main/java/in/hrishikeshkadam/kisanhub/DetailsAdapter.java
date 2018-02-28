package in.hrishikeshkadam.kisanhub;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.hrishikeshkadam.kisanhub.data.WeatherContract.WeatherEntry;
import in.hrishikeshkadam.weather_java_module.WeatherDataToFile;

/**
 * Created by Hrishikesh Kadam on 28/02/2018
 */

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.ViewHolder> {

    public static final String LOG_TAG = DetailsAdapter.class.getSimpleName();
    private static final int EMPTY_VIEW = 1;
    private static final int NORMAL_VIEW = 2;
    Cursor cursor;

    public DetailsAdapter(Cursor cursor) {
        this.cursor = cursor;
    }

    public void swapCursor(Cursor cursor) {

        if (cursor != null)
            Log.d(LOG_TAG, "-> swapCursor -> " + cursor.getCount());

        this.cursor = cursor;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {

        if (cursor == null || cursor.getCount() == 0)
            return EMPTY_VIEW;
        else
            return NORMAL_VIEW;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ViewHolder viewHolder = null;
        View itemView = null;

        switch (viewType) {

            case EMPTY_VIEW:
                itemView = LayoutInflater.from(
                        parent.getContext()).inflate(R.layout.empty_view, parent, false);
                viewHolder = new EmptyViewHolder(itemView);
                break;

            case NORMAL_VIEW:
                itemView = LayoutInflater.from(
                        parent.getContext()).inflate(R.layout.item_weather_csv, parent, false);
                viewHolder = new NormalViewHolder(itemView);
                break;

            default:
                throw new UnsupportedOperationException("Unknown viewType: " + viewType);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        int viewType = getItemViewType(position);

        switch (viewType) {

            case NORMAL_VIEW:

                cursor.moveToPosition(position);

                String regionCode =
                        cursor.getString(cursor.getColumnIndex(WeatherEntry.COLUMN_REGION_CODE));
                String weatherParam = WeatherDataToFile.getFormattedWeatherParam(
                        cursor.getString(cursor.getColumnIndex(WeatherEntry.COLUMN_WEATHER_PARAM)));
                Long year =
                        cursor.getLong(cursor.getColumnIndex(WeatherEntry.COLUMN_YEAR));
                String key =
                        cursor.getString(cursor.getColumnIndex(WeatherEntry.COLUMN_KEY));
                String value =
                        cursor.getString(cursor.getColumnIndex(WeatherEntry.COLUMN_VALUE));

                NormalViewHolder normalViewHolder = (NormalViewHolder) holder;
                normalViewHolder.textView.setText(String.format("%s, %s, %s, %s, %s",
                        regionCode, weatherParam, year, key, value));
                break;
        }
    }

    @Override
    public int getItemCount() {

        if (cursor == null || cursor.getCount() == 0)
            return 1;
        return cursor.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class EmptyViewHolder extends ViewHolder {

        public EmptyViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class NormalViewHolder extends ViewHolder {

        @BindView(R.id.textView)
        TextView textView;

        public NormalViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

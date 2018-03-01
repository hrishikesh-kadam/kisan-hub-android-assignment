package in.hrishikeshkadam.kisanhub;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import in.hrishikeshkadam.kisanhub.utils.WeatherCsvUtils;
import in.hrishikeshkadam.weather_java_module.model.WeatherCsvModel;

import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    private static final String LOG_TAG = ExampleInstrumentedTest.class.getSimpleName();

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("in.hrishikeshkadam.kisanhub", appContext.getPackageName());
    }

    @Test
    public void checkInterestingFacts() throws Exception {

        Context appContext = InstrumentationRegistry.getTargetContext();

        WeatherCsvModel csvModel;

        csvModel = WeatherCsvUtils.getMax(appContext, "UK", "Tmax",
                new String[]{"WIN", "SPR", "SUM", "AUT", "ANN"});

        if (csvModel != null)
            Log.d(LOG_TAG, "-> Max Temperature " + csvModel.toCSVString());

        csvModel = WeatherCsvUtils.getMin(appContext, "UK", "Tmin",
                new String[]{"WIN", "SPR", "SUM", "AUT", "ANN"});

        if (csvModel != null)
            Log.d(LOG_TAG, "-> Min Temperature " + csvModel.toCSVString());

        csvModel = WeatherCsvUtils.getMax(appContext, "UK", "Rainfall",
                new String[]{"WIN", "SPR", "SUM", "AUT", "ANN"});

        if (csvModel != null)
            Log.d(LOG_TAG, "-> Highest Rainfall " + csvModel.toCSVString());
    }
}

package in.hrishikeshkadam.weather_java_module;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import in.hrishikeshkadam.weather_java_module.rest.RetrofitSingleton;
import in.hrishikeshkadam.weather_java_module.rest.WebServices;
import retrofit2.Call;
import retrofit2.Response;

public class WeatherDataDownloader {

    public static final Logger LOGGER;
    public static WebServices webServices;

    static {

        Logger logger = Logger.getLogger(WeatherDataDownloader.class.getSimpleName());
        logger.setLevel(Level.FINEST);
        LOGGER = logger;
        LOGGER.log(Level.FINEST, "-> static block");

        webServices = RetrofitSingleton.getRetrofit().create(WebServices.class);
    }

    public static String downloadByYear(String weatherParam, String regionCode) {
        LOGGER.log(Level.FINEST, "-> downloadByYear");

        Call<String> call = webServices.getWeatherData(weatherParam, "date", regionCode);
        Response<String> response = null;

        try {

            response = call.execute();

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (response == null || !response.isSuccessful())
            return null;

        if (response.body() != null)
            //noinspection ConstantConditions
            LOGGER.log(Level.FINEST, "-> " + response.body().substring(0, response.body().indexOf("\n")));

        return response.body();
    }
}

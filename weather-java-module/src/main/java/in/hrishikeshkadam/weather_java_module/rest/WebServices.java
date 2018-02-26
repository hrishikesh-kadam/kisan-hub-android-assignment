package in.hrishikeshkadam.weather_java_module.rest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WebServices {

    @GET("{weather_param}/{order_by}/{region_code}.txt")
    Call<String> getWeatherData(@Path("weather_param") String weatherParam, @Path("order_by") String orderBy,
                                @Path("region_code") String regionCode);
}

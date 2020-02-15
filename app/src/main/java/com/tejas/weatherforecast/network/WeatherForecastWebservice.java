package com.tejas.weatherforecast.network;

import com.tejas.weatherforecast.model.WeatherForecastRoot;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherForecastWebservice {
    @GET("/data/2.5/forecast")
    Call<WeatherForecastRoot> getWeatherForecast(@Query("q") String cityName, @Query("units") String units,
                                                 @Query("appid") String appId);
}

package com.tejas.weatherforecast.repositories;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.tejas.weatherforecast.constants.Constants;
import com.tejas.weatherforecast.network.WeatherForecastWebservice;
import com.tejas.weatherforecast.model.WeatherForecastRoot;

import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class WeatherForecastRepository {

    private final WeatherForecastWebservice webservice;
    private final Executor executor;

    @Inject
    public WeatherForecastRepository(WeatherForecastWebservice webservice, Executor executor) {
        this.webservice = webservice;
        this.executor = executor;
    }

    private MutableLiveData<WeatherForecastRoot> weatherForecastLiveData = new MutableLiveData<>();

    public LiveData<WeatherForecastRoot> getWeatherForecast(String cityName) {
        executor.execute(() -> {
            webservice.getWeatherForecast(cityName, "metric", Constants.APP_ID).enqueue(new Callback<WeatherForecastRoot>() {
                @Override
                public void onResponse(Call<WeatherForecastRoot> call, Response<WeatherForecastRoot> response) {
                    executor.execute(() -> {
                        WeatherForecastRoot user = response.body();
                        weatherForecastLiveData.postValue(user);
                    });
                }

                @Override
                public void onFailure(Call<WeatherForecastRoot> call, Throwable t) { }

            });
        });
        return weatherForecastLiveData;
    }
}

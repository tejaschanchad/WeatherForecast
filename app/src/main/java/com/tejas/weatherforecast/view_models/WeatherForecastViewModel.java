package com.tejas.weatherforecast.view_models;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.tejas.weatherforecast.model.WeatherForecastRoot;
import com.tejas.weatherforecast.repositories.WeatherForecastRepository;

import javax.inject.Inject;

public class WeatherForecastViewModel extends ViewModel {

    private LiveData<WeatherForecastRoot> weatherForecast;
    private WeatherForecastRepository weatherForecastRepo;

    @Inject
    public WeatherForecastViewModel(WeatherForecastRepository weatherForecastRepo) {
        this.weatherForecastRepo = weatherForecastRepo;
    }

    public void initWeatherForecastData(String cityName) {
        if (this.weatherForecast != null) {
            return;
        }
        weatherForecast = weatherForecastRepo.getWeatherForecast(cityName);
    }

    public LiveData<WeatherForecastRoot> getWeatherForecastData() {
        return this.weatherForecast;
    }
}

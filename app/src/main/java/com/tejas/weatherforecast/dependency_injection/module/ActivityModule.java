package com.tejas.weatherforecast.dependency_injection.module;

import com.tejas.weatherforecast.view.WeatherForecastActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract WeatherForecastActivity contributeWeatherForecastActivity();
}

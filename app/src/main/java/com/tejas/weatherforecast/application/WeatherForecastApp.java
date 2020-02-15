package com.tejas.weatherforecast.application;

import android.app.Activity;
import android.app.Application;

import com.tejas.weatherforecast.dependency_injection.component.DaggerWeatherForecastAppComponent;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class WeatherForecastApp extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        this.initDagger();
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }

    private void initDagger() {
        DaggerWeatherForecastAppComponent.builder().application(this).build().inject(this);
    }
}

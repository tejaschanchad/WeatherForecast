package com.tejas.weatherforecast.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.tejas.weatherforecast.dependency_injection.component.DaggerWeatherForecastAppComponent;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class WeatherForecastApp extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        this.initDagger();
        context = getApplicationContext();
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }

    private void initDagger(){
        DaggerWeatherForecastAppComponent.builder().application(this).build().inject(this);
    }
}

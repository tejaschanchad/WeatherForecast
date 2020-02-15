package com.tejas.weatherforecast.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;

import com.tejas.weatherforecast.dependency_injection.component.DaggerWeatherForecastAppComponent;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class WeatherForecastApp extends Application implements HasActivityInjector {

    public static ConnectivityManager mConMgr;

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        this.initDagger();

        // Create ConnectivityManager Object....
        if (mConMgr == null) {
            mConMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        }
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }

    private void initDagger() {
        DaggerWeatherForecastAppComponent.builder().application(this).build().inject(this);
    }
}

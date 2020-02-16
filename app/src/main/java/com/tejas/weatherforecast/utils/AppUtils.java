package com.tejas.weatherforecast.utils;

import com.tejas.weatherforecast.application.WeatherForecastApp;

public class AppUtils {

    public static boolean isInternetAvailable() {
        if(WeatherForecastApp.mConMgr.getActiveNetworkInfo() != null
                && WeatherForecastApp.mConMgr.getActiveNetworkInfo().isAvailable()
                && WeatherForecastApp.mConMgr.getActiveNetworkInfo().isConnected()) {
            return true;
        } else {
            return false;
        }
    }
}

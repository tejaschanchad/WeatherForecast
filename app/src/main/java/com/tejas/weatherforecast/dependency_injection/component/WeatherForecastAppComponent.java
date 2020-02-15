package com.tejas.weatherforecast.dependency_injection.component;

import android.app.Application;

import com.tejas.weatherforecast.application.WeatherForecastApp;
import com.tejas.weatherforecast.dependency_injection.module.ActivityModule;
import com.tejas.weatherforecast.dependency_injection.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules={AndroidSupportInjectionModule.class, ActivityModule.class, AppModule.class})
public interface WeatherForecastAppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        WeatherForecastAppComponent build();
    }

    void inject(WeatherForecastApp app);
}

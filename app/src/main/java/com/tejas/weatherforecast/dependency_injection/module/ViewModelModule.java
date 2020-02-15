package com.tejas.weatherforecast.dependency_injection.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.tejas.weatherforecast.dependency_injection.key.ViewModelKey;
import com.tejas.weatherforecast.view_models.FactoryViewModel;
import com.tejas.weatherforecast.view_models.WeatherForecastViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(WeatherForecastViewModel.class)
    abstract ViewModel bindWeatherForecastViewModel(WeatherForecastViewModel repoViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(FactoryViewModel factory);
}

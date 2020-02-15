package com.tejas.weatherforecast.dependency_injection.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tejas.weatherforecast.network.WeatherForecastWebservice;
import com.tejas.weatherforecast.repositories.WeatherForecastRepository;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ViewModelModule.class)
public class AppModule {
    @Provides
    Executor provideExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    @Provides
    @Singleton
    WeatherForecastRepository provideWeatherForecastRepository(WeatherForecastWebservice webservice, Executor executor) {
        return new WeatherForecastRepository(webservice, executor);
    }

    private static String BASE_URL = "https://api.openweathermap.org";

    @Provides
    Gson provideGson() { return new GsonBuilder().create(); }

    @Provides
    Retrofit provideRetrofit(Gson gson) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BASE_URL)
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    WeatherForecastWebservice provideApiWebservice(Retrofit restAdapter) {
        return restAdapter.create(WeatherForecastWebservice.class);
    }
}

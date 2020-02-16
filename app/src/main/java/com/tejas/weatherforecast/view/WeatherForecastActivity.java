package com.tejas.weatherforecast.view;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.tejas.weatherforecast.R;
import com.tejas.weatherforecast.model.WeatherForecastRoot;
import com.tejas.weatherforecast.utils.AppUtils;
import com.tejas.weatherforecast.view.adapters.WeatherForecastCardsAdapter;
import com.tejas.weatherforecast.view_models.WeatherForecastViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.DispatchingAndroidInjector;

public class WeatherForecastActivity extends BaseActivity implements AdapterView.OnItemSelectedListener{

    private final String TAG = WeatherForecastActivity.this.getClass().getName();

    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mWeatherForecastCardsAdapter;

    @Inject
    ViewModelProvider.Factory mViewModelFactory;
    private WeatherForecastViewModel mWeatherForecastViewModel;

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);

        this.configureDagger();

        initViewModel(); // ViewModel Init...

        initView(); // Initialized view related to activity.
    }

    /**
     * Initialized view related to activity.
     */
    private void initView() {
        setAppBar(getResources().getString(R.string.appbar_title)); // Set AppBar Text.

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Spinner click listener
        spinner.setOnItemSelectedListener(WeatherForecastActivity.this);
        // Prepare list for spinner
        List<String> categories = new ArrayList<String>();
        String citiesArr[] = getResources().getStringArray(R.array.cities_array);
        for(int i=0; i < citiesArr.length; i++) {
            categories.add(citiesArr[i]);
        }

        ArrayAdapter<String> dataAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        // Prepare Recycle View with Cards for showing weather forecast
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_forecast);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        // mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        if(position != 0) { // If position is zero then no need to call web-service
            String city = adapterView.getItemAtPosition(position).toString();

            if (AppUtils.isInternetAvailable()) {
                configureViewModel(city);
            } else {
                alert(getResources().getString(R.string.txt_no_internet));
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    // ViewModel Init...
    private void initViewModel() {
        mWeatherForecastViewModel =
                ViewModelProviders.of(this, mViewModelFactory).get(WeatherForecastViewModel.class);
    }

    /**
     * This is ViewModel configuration when user select city...
     *
     * @param cityName Name of city from spinner control
     */
    private void configureViewModel(String cityName) {
        mWeatherForecastViewModel.initWeatherForecastData(cityName);
        mWeatherForecastViewModel
                .getWeatherForecastData()
                .observe(this, WeatherForecastRoot -> updateWeatherForecastData(WeatherForecastRoot));
    }

    /**
     * Update UI when observer get data from VieModel...
     *
     * @param weatherForecastRoot Root data of Weather Forecast for specific city.
     */
    private void updateWeatherForecastData(@Nullable WeatherForecastRoot weatherForecastRoot) {
        if (weatherForecastRoot != null) {
            // Set weather forecast data to Recycle View adapter
            mWeatherForecastCardsAdapter = new WeatherForecastCardsAdapter(weatherForecastRoot);

            final Context context = mRecyclerView.getContext();
            final LayoutAnimationController controller =
                    AnimationUtils.loadLayoutAnimation(context, R.anim.layout_bottom_anim);

            mRecyclerView.setLayoutAnimation(controller);
            mRecyclerView.setAdapter(mWeatherForecastCardsAdapter);
            mWeatherForecastCardsAdapter.notifyDataSetChanged();
            mRecyclerView.scheduleLayoutAnimation();
        }
    }

    private void configureDagger() {
        AndroidInjection.inject(this);
    }
}

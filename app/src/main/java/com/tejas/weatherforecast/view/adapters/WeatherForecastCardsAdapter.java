package com.tejas.weatherforecast.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tejas.weatherforecast.R;
import com.tejas.weatherforecast.constants.Constants;
import com.tejas.weatherforecast.model.List;
import com.tejas.weatherforecast.model.WeatherForecastRoot;

import java.util.ArrayList;

public class WeatherForecastCardsAdapter extends RecyclerView.Adapter<WeatherForecastCardsAdapter.WeatherForecastViewHolder> {

    private ArrayList<List> mWeatherForecastDataSet;

    public static class WeatherForecastViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_date_time;
        private TextView tv_temp;

        public WeatherForecastViewHolder(View itemView) {
            super(itemView);
            this.tv_date_time = (TextView) itemView.findViewById(R.id.tv_date_time);
            this.tv_temp = (TextView) itemView.findViewById(R.id.tv_temp);
        }
    }

    public WeatherForecastCardsAdapter(WeatherForecastRoot weatherForecastRoot) {
        this.mWeatherForecastDataSet = (ArrayList<List>) weatherForecastRoot.getList();
    }

    @Override
    public WeatherForecastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_forecast_card, parent, false);
        WeatherForecastViewHolder myViewHolder = new WeatherForecastViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final WeatherForecastViewHolder holder, final int listPosition) {
        TextView tv_date_time = holder.tv_date_time;
        TextView tv_temp = holder.tv_temp;

        tv_date_time.setText("Date/Time: " + mWeatherForecastDataSet.get(listPosition).getDtTxt());

        tv_temp.setText("Temp (Min|Max): "
                + String.valueOf(mWeatherForecastDataSet.get(listPosition).getMain().getTempMin())
                + " | " + String.valueOf(mWeatherForecastDataSet.get(listPosition).getMain().getTempMax())
                + " " + Constants.CELSIUS_UNICODE);
    }

    @Override
    public int getItemCount() {
        return mWeatherForecastDataSet.size();
    }
}

package com.androidarchcomp.viewmodelcase.adapter.weather;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidarchcomp.viewmodelcase.R;
import com.androidarchcomp.viewmodelcase.models.weather.WeatherData;
import com.androidarchcomp.viewmodelcase.ui.home.WeatherViewModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterWeather extends RecyclerView.Adapter<AdapterWeather.WeatherDataViewHolder> {

    private List<WeatherData> data = new ArrayList<>();
    private List<Integer> idList = new ArrayList<>(Arrays.asList(R.drawable.clear, R.drawable.rain, R.drawable.snow, R.drawable.clouds));

    public AdapterWeather(WeatherViewModel viewModel, LifecycleOwner lifecycleOwner) {
        viewModel.getWeatherData().observe(lifecycleOwner, weatherData -> {
            data.clear();
            if (weatherData != null) {
                data.addAll(weatherData);
                notifyDataSetChanged();
            }
        });
    }


    @NonNull
    @Override
    public WeatherDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_weather_item, parent, false);
        return new WeatherDataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherDataViewHolder holder, int position) {
        WeatherData weather = data.get(position);
        holder.setDataSet(weather);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class WeatherDataViewHolder extends RecyclerView.ViewHolder {
        TextView txtWeatherDay, txtWeatherDegree, txtWeatherMaxMin, txtDescription, txtHumidity, txtNightDegree;
        ImageView imgWeatherIcon, imgStatusIcon;


        WeatherDataViewHolder(@NonNull View itemView) {
            super(itemView);
            bindViews();
        }

        private void bindViews() {
            txtWeatherDay = itemView.findViewById(R.id.txt_weather_day);
            txtDescription = itemView.findViewById(R.id.txt_description);
            txtHumidity = itemView.findViewById(R.id.txt_humidity);
            txtNightDegree = itemView.findViewById(R.id.txt_night_degree);
            txtWeatherMaxMin = itemView.findViewById(R.id.txt_max_min_degrees);
            txtWeatherDegree = itemView.findViewById(R.id.txt_weather_degree);
            imgWeatherIcon = itemView.findViewById(R.id.img_weather_icon);
            imgStatusIcon = itemView.findViewById(R.id.img_status_icon);
        }

        private void setDataSet(WeatherData weather) {
            txtWeatherDay.setText(weather.getDay());
            txtDescription.setText(weather.getDescription());
            txtHumidity.setText(weather.getHumidity());
            txtNightDegree.setText((weather.getNight()));
            txtWeatherMaxMin.setText(String.format("%s-%s", weather.getMin(), weather.getMax()));
            txtWeatherDegree.setText(weather.getDegree());
            Glide.with(imgWeatherIcon.getContext()).load(weather.getIcon()).into(imgWeatherIcon);
            imgStatusIcon.setImageResource(idList.get(weather.getImageResourceIndex(weather.getStatus())));

        }
    }
}

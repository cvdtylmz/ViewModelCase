package com.androidarchcomp.viewmodelcase.ui.home;

import androidx.annotation.NonNull;
import android.util.Log;

import com.androidarchcomp.viewmodelcase.enums.ServiceType;
import com.androidarchcomp.viewmodelcase.models.weather.Weather;
import com.androidarchcomp.viewmodelcase.models.weather.WeatherData;
import com.androidarchcomp.viewmodelcase.networking.RemoteApi;


import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherViewModel extends ViewModel {


    private final MutableLiveData<List<WeatherData>> weatherData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> weatherLoadError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> weatherLoading = new MutableLiveData<>();

    private Call<Weather> weatherCall;


    public LiveData<List<WeatherData>> getWeatherData () { return weatherData;}
    LiveData<Boolean> getWeatherError() {
        return weatherLoadError;
    }
    LiveData<Boolean> getWeatherLoading() {
        return weatherLoading;
    }

    void fetchWeather(String city){
        weatherLoading.setValue(true);
        weatherCall = RemoteApi.getInstance(ServiceType.WEATHER).getWeather("TR",city);
        weatherCall.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(@NonNull Call<Weather> call, @NonNull Response<Weather> response) {
                weatherLoadError.setValue(false);
                weatherData.setValue(response.body() != null ? response.body().getWeatherData() : null);
                weatherLoading.setValue(false);
                weatherCall = null;
            }

            @Override
            public void onFailure(@NonNull Call<Weather> call, @NonNull Throwable t) {
                Log.e(getClass().getSimpleName(), "onFailure: ",t);
                weatherLoadError.setValue(true);
                weatherLoading.setValue(false);
            }
        });
    }

    @Override
    protected void onCleared() {
        if(weatherCall !=null) {
            weatherCall.cancel();
            weatherCall = null;
        }
    }
}

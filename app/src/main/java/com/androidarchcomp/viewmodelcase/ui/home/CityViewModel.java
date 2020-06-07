package com.androidarchcomp.viewmodelcase.ui.home;

import androidx.annotation.NonNull;

import com.androidarchcomp.viewmodelcase.enums.ServiceType;
import com.androidarchcomp.viewmodelcase.models.cities.Cities;
import com.androidarchcomp.viewmodelcase.networking.RemoteApi;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CityViewModel extends ViewModel {

    private final MutableLiveData <Cities> cities = new MutableLiveData<>();
    private final MutableLiveData<Boolean> citiesLoading = new MutableLiveData<>();
    private final MutableLiveData<Boolean> citiesError = new MutableLiveData<>();

    private Call<Cities> citiesCall;

    public CityViewModel () {
        fetchCities();
    }

    LiveData<Cities> getCities () {
        return cities;
    }

    LiveData<Boolean> getCitiesLoading () {
        return citiesLoading;
    }
    LiveData<Boolean> getCitiesError () {
        return citiesError;
    }

    private void fetchCities () {
        citiesLoading.setValue(true);
        citiesCall = RemoteApi.getInstance(ServiceType.CITY).getCities();
        citiesCall.enqueue(new Callback<Cities>() {
            @Override
            public void onResponse(@NonNull Call <Cities> call, @NonNull Response<Cities> response) {
                citiesLoading.setValue(false);
                cities.setValue(response.body());
                citiesError.setValue(false);
                citiesCall = null;
            }

            @Override
            public void onFailure(@NonNull  Call<Cities> call,@NonNull Throwable t) {
                citiesLoading.setValue(false);
                citiesError.setValue(true);
            }
        });
    }

    @Override
    protected void onCleared() {
        if (citiesCall != null) {
            citiesCall.cancel();
            citiesCall = null;
        }
    }
}

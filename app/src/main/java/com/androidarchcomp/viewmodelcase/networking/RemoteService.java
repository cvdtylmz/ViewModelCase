package com.androidarchcomp.viewmodelcase.networking;

import com.androidarchcomp.viewmodelcase.models.cities.Cities;
import com.androidarchcomp.viewmodelcase.models.weather.Weather;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface RemoteService {
    @Headers({"Content-Type:application/json","x-ibm-client-id:30cdc2a0-a978-4a08-99cf-3a791be38716","x-ibm-client-secret:X2uO7hJ0qF2rV3qD4pN2qD4wA6sR6fM8nC0qI7xK2rS8aJ5oE5"})
    @GET("/v1/provinces")
    Call<Cities> getCities ();

    @Headers({"Content-Type:application/json","Authorization:apikey  2PSWcIK2eU6zmpMTcwycby:0vcYX2KOwdtxfYFksUmGDy"})
    @GET("/weather/getWeather")
    Call<Weather> getWeather (@Query("data.lang = tr") String lang, @Query("data.city") String city);
}

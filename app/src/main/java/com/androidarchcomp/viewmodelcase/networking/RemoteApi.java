package com.androidarchcomp.viewmodelcase.networking;

import com.androidarchcomp.viewmodelcase.enums.ServiceType;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class RemoteApi {

    private static final String WEATHER_URL = "https://api.collectapi.com";
    private static final String CITY_URL ="https://api.sandbox.isbank.com.tr";
    private static Retrofit retrofit;
    private static RemoteService remoteService;

    public static RemoteService getInstance (ServiceType type) {
        switch (type) {
            case CITY:
                if (remoteService != null) {
                    return remoteService;
                }
                if (retrofit == null) {
                    initializeRetrofit(type);
                }
                break;
            case WEATHER:
                initializeRetrofit(type);
                break;
        }

        remoteService = retrofit.create(RemoteService.class);
        return remoteService;
    }

    private static void initializeRetrofit(ServiceType type) {
        switch (type) {
            case CITY:
                retrofit = new Retrofit.Builder()
                        .baseUrl(CITY_URL)
                        .addConverterFactory(MoshiConverterFactory.create())
                        .build();
                break;
            case WEATHER:
                retrofit = new Retrofit.Builder()
                        .baseUrl(WEATHER_URL)
                        .addConverterFactory(MoshiConverterFactory.create())
                        .build();
                break;
        }
    }


    public RemoteApi() {

    }
}

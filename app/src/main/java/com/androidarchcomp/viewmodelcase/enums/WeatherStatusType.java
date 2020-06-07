package com.androidarchcomp.viewmodelcase.enums;


import android.os.Build;

import java.util.Arrays;

import androidx.annotation.RequiresApi;
import androidx.annotation.NonNull;

public enum WeatherStatusType {
    CLEAR ("Clear"),
    RAIN("Rain"),
    SNOW ("Snow"),
    CLOUDS ("Clouds");

    private final String name;

    WeatherStatusType(String s) {
        name = s;

    }

    @NonNull
    public String toString() {
        return this.name;
    }

    public int toInteger () { return this.ordinal();}

    public boolean equalsName(String otherName) {
        // (otherName == null) check is not needed because name.equals(null) returns false
        return name.equals(otherName);
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean in(WeatherStatusType sun, WeatherStatusType cloud, WeatherStatusType clear, WeatherStatusType rainy) {
        return Arrays.stream(new WeatherStatusType[]{sun,cloud,clear,rainy}).anyMatch(weatherStatusType -> weatherStatusType == this);
    }

}

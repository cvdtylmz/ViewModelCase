package com.androidarchcomp.viewmodelcase.enums;


import android.os.Build;

import java.util.Arrays;

import androidx.annotation.RequiresApi;
import androidx.annotation.NonNull;

public enum WeatherStatusType {
    CLEAR ("Clear",0),
    RAIN("Rain",1),
    SNOW ("Snow",2),
    CLOUDS ("Clouds",3);

    private final String name;
    private final int index;

    WeatherStatusType(String s, int i) {
        name = s;
        index = i;
    }

    @NonNull
    public String toString() {
        return this.name;
    }

    public int toInteger () { return this.index;}

    public boolean equalsName(String otherName) {
        // (otherName == null) check is not needed because name.equals(null) returns false
        return name.equals(otherName);
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean in(WeatherStatusType sun, WeatherStatusType cloud, WeatherStatusType clear, WeatherStatusType rainy) {
        return Arrays.stream(new WeatherStatusType[]{sun,cloud,clear,rainy}).anyMatch(weatherStatusType -> weatherStatusType == this);
    }

}

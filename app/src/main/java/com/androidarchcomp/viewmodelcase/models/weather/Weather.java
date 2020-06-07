package com.androidarchcomp.viewmodelcase.models.weather;

import android.os.Parcel;
import android.os.Parcelable;

import com.squareup.moshi.Json;

import java.util.ArrayList;
import java.util.List;

public class Weather implements Parcelable {
    @Json(name = "success")
    private Boolean success;
    @Json(name = "city")
    private String city;
    @Json(name = "result")
    private List<WeatherData> result = null;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.success);
        dest.writeString(this.city);
        dest.writeList(this.result);
    }

    public Weather() {

    }
    public List<WeatherData> getWeatherData () {
        return result;
    }

    private Weather(Parcel in) {
        this.success = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.city = in.readString();
        this.result = new ArrayList<>();
        in.readList(this.result, WeatherData.class.getClassLoader());
    }

    public static final Parcelable.Creator<Weather> CREATOR = new Parcelable.Creator<Weather>() {
        @Override
        public Weather createFromParcel(Parcel source) {
            return new Weather(source);
        }

        @Override
        public Weather[] newArray(int size) {
            return new Weather[size];
        }
    };
}

package com.androidarchcomp.viewmodelcase.models.weather;

import android.os.Parcel;
import android.os.Parcelable;

import com.androidarchcomp.viewmodelcase.enums.WeatherStatusType;
import com.squareup.moshi.Json;

public class WeatherData implements Parcelable {
    @Json(name = "date")
    private String date;
    @Json(name = "day")
    private String day;
    @Json(name = "icon")
    private String icon;
    @Json(name = "description")
    private String description;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getNight() {
        return night;
    }

    public void setNight(String night) {
        this.night = night;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    @Json(name = "status")
    private String status;
    @Json(name = "degree")
    private String degree;
    @Json(name = "min")
    private String min;
    @Json(name = "max")
    private String max;
    @Json(name = "night")
    private String night;
    @Json(name = "humidity")
    private String humidity;
    private int imageResourceIndex;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.date);
        dest.writeString(this.day);
        dest.writeString(this.icon);
        dest.writeString(this.description);
        dest.writeString(this.status);
        dest.writeString(this.degree);
        dest.writeString(this.min);
        dest.writeString(this.max);
        dest.writeString(this.night);
        dest.writeString(this.humidity);
    }

    public WeatherData() {
    }

    private WeatherData(Parcel in) {
        this.date = in.readString();
        this.day = in.readString();
        this.icon = in.readString();
        this.description = in.readString();
        this.status = in.readString();
        this.degree = in.readString();
        this.min = in.readString();
        this.max = in.readString();
        this.night = in.readString();
        this.humidity = in.readString();
    }

    public static final Parcelable.Creator<WeatherData> CREATOR = new Parcelable.Creator<WeatherData>() {
        @Override
        public WeatherData createFromParcel(Parcel source) {
            return new WeatherData(source);
        }

        @Override
        public WeatherData[] newArray(int size) {
            return new WeatherData[size];
        }
    };

    public int getImageResourceIndex (String name) {
        WeatherStatusType type = WeatherStatusType.valueOf(name.toUpperCase().replace("İ","I"));
        return imageResourceIndex = type.toInteger();
    }
}

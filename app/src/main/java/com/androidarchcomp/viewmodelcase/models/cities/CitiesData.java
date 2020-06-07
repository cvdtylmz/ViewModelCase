package com.androidarchcomp.viewmodelcase.models.cities;


import android.os.Parcel;
import android.os.Parcelable;

import com.squareup.moshi.Json;

import java.util.List;

public class CitiesData implements Parcelable {

    @Json(name = "code")
    private String code;
    @Json(name = "name")
    private String name;
    @Json(name = "calling_code")
    private List<String> callingCode = null;


    @Override
    public int describeContents() {
        return 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.code);
        dest.writeString(this.name);
        dest.writeStringList(this.callingCode);
    }

    public CitiesData() {

    }

    private CitiesData(Parcel in) {
        this.code = in.readString();
        this.name = in.readString();
        this.callingCode = in.createStringArrayList();
    }

    public static final Parcelable.Creator<CitiesData> CREATOR = new Parcelable.Creator<CitiesData>() {
        @Override
        public CitiesData createFromParcel(Parcel source) {
            return new CitiesData(source);
        }

        @Override
        public CitiesData[] newArray(int size) {
            return new CitiesData[size];
        }
    };
}

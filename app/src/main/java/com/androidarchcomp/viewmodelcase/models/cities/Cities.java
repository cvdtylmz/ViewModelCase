package com.androidarchcomp.viewmodelcase.models.cities;

import android.os.Parcel;
import android.os.Parcelable;

import com.squareup.moshi.Json;

import java.util.List;

public class Cities implements Parcelable {
    @Json(name = "data")
    private List<CitiesData> data = null;

    public List<CitiesData> getData() {
        return data;
    }

    public void setData(List<CitiesData> data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.data);
    }

    public Cities() {

    }

    private Cities(Parcel in) {
        this.data = in.createTypedArrayList(CitiesData.CREATOR);
    }

    public static final Parcelable.Creator<Cities> CREATOR = new Parcelable.Creator<Cities>() {
        @Override
        public Cities createFromParcel(Parcel source) {
            return new Cities(source);
        }

        @Override
        public Cities[] newArray(int size) {
            return new Cities[size];
        }
    };
}

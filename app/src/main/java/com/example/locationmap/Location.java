package com.example.locationmap;

import com.google.gson.annotations.SerializedName;

public class Location {
    @SerializedName("latitude")
    String latitude;

    @SerializedName("longitude")
    String longitude;

    public Location(String latitude, String longitude, String time) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.time = time;
    }

    @SerializedName("time")
    String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}

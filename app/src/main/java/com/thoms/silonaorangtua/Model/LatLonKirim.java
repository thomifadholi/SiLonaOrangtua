package com.thoms.silonaorangtua.Model;

public class LatLonKirim {
    public String latitude;
    public String longitude;

    public LatLonKirim(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public LatLonKirim(){}

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

package com.app.weatherapp.dto;

import com.google.gson.annotations.SerializedName;

public class MainDto {
    @SerializedName("temp")
    private float currentTemp;

    @SerializedName("temp_min")
    private float minTemp;

    @SerializedName("temp_max")
    private float maxTemp;

    @SerializedName("humidity")
    private int humidity;

    public float getCurrentTemp() {
        return currentTemp;
    }

    public float getMinTemp() {
        return minTemp;
    }

    public float getMaxTemp() {
        return maxTemp;
    }

    public int getHumidity() {
        return humidity;
    }


    public MainDto(float currentTemp, float minTemp, float maxTemp, int humidity) {
        this.currentTemp = currentTemp;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.humidity = humidity;
    }
}

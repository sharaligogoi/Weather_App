package com.app.weatherapp.dto;

import com.google.gson.annotations.SerializedName;

public class WeatherDetailsDto {
    @SerializedName("main")
    private final MainDto mainDto;

    public MainDto getMainDto() {
        return mainDto;
    }

    @SerializedName("name")
    private final String cityName;

    public String getCityName() {
        return cityName;
    }


    public WeatherDetailsDto(MainDto mainDto, String cityName) {
        this.mainDto = mainDto;
        this.cityName = cityName;
    }

}


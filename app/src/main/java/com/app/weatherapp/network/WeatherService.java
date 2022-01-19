package com.app.weatherapp.network;

import com.app.weatherapp.dto.WeatherDetailsDto;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WeatherService {

    @GET("data/2.5/weather")
    Call<WeatherDetailsDto> getWeatherDetails(@Query("q") String city);

}

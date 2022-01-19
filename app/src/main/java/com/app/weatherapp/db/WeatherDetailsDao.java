package com.app.weatherapp.db;

import com.app.weatherapp.models.WeatherDetailsModel;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface WeatherDetailsDao {

    @Insert
    void addWeatherDetails(WeatherDetailsModel... list);

    @Query("SELECT * FROM weather_details WHERE cityName = :name")
    WeatherDetailsModel getWeatherDetails(String name);
}

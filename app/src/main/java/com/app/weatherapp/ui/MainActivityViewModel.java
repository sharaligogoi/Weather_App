package com.app.weatherapp.ui;

import com.app.weatherapp.models.WeatherDetailsModel;
import com.app.weatherapp.repositories.WeatherDetailsRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {
    private final  WeatherDetailsRepository repo = new WeatherDetailsRepository();
    public Map<String, LiveData<WeatherDetailsModel>> details = new HashMap<>();

    private final List<String> cityList = new ArrayList<>();

    {
        cityList.add("Delhi");
        cityList.add("Bengaluru");
        cityList.add("Guwahati");
        cityList.add("Jaipur");
        cityList.add("Kerala");

        for(String city: cityList){
            details.put(city, repo.getWeatherInfo(city));
        }
    }
}

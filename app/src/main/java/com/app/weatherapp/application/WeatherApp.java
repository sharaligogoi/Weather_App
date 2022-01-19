package com.app.weatherapp.application;

import android.app.Application;
import android.content.Context;
import com.app.weatherapp.repositories.WeatherDetailsRepository;

public class WeatherApp extends Application {

    private static Context appContext;

    public static Context getAppContext(){
        return appContext;
    }

    public WeatherDetailsRepository repo;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();

    }

}

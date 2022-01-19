package com.app.weatherapp.db;

import android.content.Context;

import com.app.weatherapp.application.WeatherApp;
import com.app.weatherapp.models.WeatherDetailsModel;

import org.jetbrains.annotations.Nullable;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {WeatherDetailsModel.class}, version = 1)
public abstract class WeatherDetailsDatabase extends RoomDatabase {

    public abstract WeatherDetailsDao weatherDetailsDao();

    @Nullable
    private static WeatherDetailsDatabase INSTANCE = null;

    public static WeatherDetailsDatabase getWeatherDetailsDbInstance() {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(WeatherApp.getAppContext(),
                    WeatherDetailsDatabase.class, "weatherDetailsDb")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

}


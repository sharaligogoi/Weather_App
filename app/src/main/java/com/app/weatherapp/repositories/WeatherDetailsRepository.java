package com.app.weatherapp.repositories;

import android.util.Log;

import com.app.weatherapp.application.WeatherApp;
import com.app.weatherapp.db.WeatherDetailsDatabase;
import com.app.weatherapp.dto.WeatherDetailsDto;
import com.app.weatherapp.mappers.WeatherDetailsDtoMapper;
import com.app.weatherapp.models.WeatherDetailsModel;
import com.app.weatherapp.network.ServiceBuilder;
import com.app.weatherapp.network.WeatherService;
import com.app.weatherapp.utils.NetworkUtils;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherDetailsRepository {
    WeatherService service;
    WeatherDetailsDatabase db;
    WeatherDetailsDtoMapper dtoMapper = new WeatherDetailsDtoMapper();



    public WeatherDetailsRepository() {
        service = (WeatherService) new ServiceBuilder()
                .buildMovieServices(WeatherService.class);
        db = WeatherDetailsDatabase.getWeatherDetailsDbInstance();

    }



    public LiveData<WeatherDetailsModel> getWeatherInfo(String cityName) {
        final MutableLiveData<WeatherDetailsModel> ld = new MutableLiveData<>();

        if(NetworkUtils.isNetworkConnected(WeatherApp.getAppContext())) {

            Call<WeatherDetailsDto> call = service.getWeatherDetails(cityName);

            call.enqueue(new Callback<WeatherDetailsDto>() {
                @Override
                public void onResponse(
                        @NonNull Call<WeatherDetailsDto> call,
                        @NonNull Response<WeatherDetailsDto> response
                ) {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            db.weatherDetailsDao().addWeatherDetails(dtoMapper.mapToDomain(response.body()));
                            ld.postValue(dtoMapper.mapToDomain(response.body()));

                        }
                    } else {
                        Log.d("repo", "onResponse: " + response.body());
                    }
                }

                @Override
                public void onFailure(
                        @NonNull Call<WeatherDetailsDto> call,
                        @NonNull Throwable t
                ) {
                    t.printStackTrace();

                }
            });
        }

        else {
            WeatherDetailsModel detail = db.weatherDetailsDao().getWeatherDetails(cityName);
            ld.postValue(detail);
        }

        return  ld;
    }

}


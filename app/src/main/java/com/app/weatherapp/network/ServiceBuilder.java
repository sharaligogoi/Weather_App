package com.app.weatherapp.network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceBuilder {
     private final String BASE_URL = "https://api.openweathermap.org/";

     private final OkHttpClient.Builder okHttp = new OkHttpClient.Builder()
            .addInterceptor(
                    new NetworkInterceptor(
                            "appid","d32091b3739b5fd38c00f4b681ca4004"
                    )
            ).addInterceptor(
                    new NetworkInterceptor(
                            "language", "en-US"
                    )
            );

     private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttp.build()).build();

   public Object buildMovieServices(Class serviceObject){
        return retrofit.create(serviceObject);
    }


}

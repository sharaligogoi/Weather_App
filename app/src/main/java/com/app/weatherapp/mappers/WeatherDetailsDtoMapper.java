package com.app.weatherapp.mappers;


import com.app.weatherapp.dto.WeatherDetailsDto;
import com.app.weatherapp.models.WeatherDetailsModel;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class WeatherDetailsDtoMapper {

    public WeatherDetailsModel mapToDomain(WeatherDetailsDto entity){

        return new WeatherDetailsModel(
                0,
                entity.getCityName(),
                convertToCelsius(entity.getMainDto().getCurrentTemp()),
                convertToCelsius(entity.getMainDto().getMinTemp()),
                convertToCelsius(entity.getMainDto().getMaxTemp()),
                entity.getMainDto().getHumidity()
        );
    }



    String convertToCelsius(float kelvin){
        Float val = kelvin-273;
        DecimalFormat df = new DecimalFormat("#");
        df.setRoundingMode(RoundingMode.CEILING);

        return df.format(val)+ " \u2103";
    }



}



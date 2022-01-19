package com.app.weatherapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "weather_details")
public class WeatherDetailsModel implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int detailsId;
    private String cityName;
    private String currentTemp;
    private String minTemp;
    private String maxTemp;
    private int humidity;

    protected WeatherDetailsModel(Parcel in) {
        detailsId = in.readInt();
        cityName = in.readString();
        currentTemp = in.readString();
        minTemp = in.readString();
        maxTemp = in.readString();
        humidity = in.readInt();
    }

    public static final Creator<WeatherDetailsModel> CREATOR = new Creator<WeatherDetailsModel>() {
        @Override
        public WeatherDetailsModel createFromParcel(Parcel in) {
            return new WeatherDetailsModel(in);
        }

        @Override
        public WeatherDetailsModel[] newArray(int size) {
            return new WeatherDetailsModel[size];
        }
    };

    public int getDetailsId() { return detailsId; }


    public String getCityName() {
        return cityName;
    }


    public String getCurrentTemp() {
        return currentTemp;
    }



    public String getMinTemp() {
        return minTemp;
    }



    public String getMaxTemp() {
        return maxTemp;
    }



    public int getHumidity() {
        return humidity;
    }




    public WeatherDetailsModel(int detailsId, String cityName, String currentTemp, String minTemp, String maxTemp, int humidity) {
        this.detailsId = detailsId;
        this.cityName = cityName;
        this.currentTemp = currentTemp;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.humidity = humidity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(detailsId);
        parcel.writeString(cityName);
        parcel.writeString(currentTemp);
        parcel.writeString(minTemp);
        parcel.writeString(maxTemp);
        parcel.writeInt(humidity);
    }
}

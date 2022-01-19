package com.app.weatherapp.ui;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.app.weatherapp.R;
import com.app.weatherapp.adapters.CityListAdapter;
import com.app.weatherapp.models.WeatherDetailsModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements CityListAdapter.OnItemClickListener {

    private TextView tvCityName, tvTemperatureScale, tvMinTemp, tvMaxTemp;
    private RecyclerView rcvCityNames;

    private MainActivityViewModel mainActivityViewModel;
    private CityListAdapter adapter;

    private final List<WeatherDetailsModel> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initRecyclerView();

        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);



        for (LiveData<WeatherDetailsModel> ld : mainActivityViewModel.details.values()) {
            ld.observe(
                   this,
                   weatherDetailsModel -> {
                        if (weatherDetailsModel != null) {
                            if (weatherDetailsModel.getCityName().equals("Bengaluru")) {
                                showWeatherInMainView(weatherDetailsModel);
                            }

                            list.add(weatherDetailsModel);
                            ld.removeObservers(this);
                            adapter.notifyDataSetChanged();
                        }

                        else {
                            Toast.makeText(MainActivity.this, "Check your network", Toast.LENGTH_SHORT).show();
                        }
                    }
            );
        }
    }

    private void initViews() {
        tvCityName = findViewById(R.id.tvCityName);
        tvTemperatureScale = findViewById(R.id.tvTemperatureScale);
        tvMinTemp = findViewById(R.id.tvMinTemp);
        tvMaxTemp = findViewById(R.id.tvMaxTemp);
        rcvCityNames = findViewById(R.id.rcvCities);
    }

    private void initRecyclerView() {
        rcvCityNames.setLayoutManager(
                new LinearLayoutManager(
                        this,
                        LinearLayoutManager.HORIZONTAL,
                        true
                )
        );

        adapter = new CityListAdapter(list, this);
        rcvCityNames.setAdapter(adapter);
    }

    @Override
    public void onClick(int position) {
        final WeatherDetailsModel weather = list.get(position);
        showWeatherInMainView(weather);
    }

    private void showWeatherInMainView(@NonNull final WeatherDetailsModel weather) {
        tvCityName.setText(weather.getCityName());
        tvTemperatureScale.setText(weather.getCurrentTemp());
        tvMaxTemp.setText("Maximum temp: "+weather.getMaxTemp());
        tvMinTemp.setText("Minimum temp: "+weather.getMinTemp());
    }
}
package com.app.weatherapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.weatherapp.R;
import com.app.weatherapp.models.WeatherDetailsModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CityListAdapter extends RecyclerView.Adapter<CityListAdapter.CityViewHolder> {

    List<WeatherDetailsModel> weatherDetails;
    OnItemClickListener clickListener;

    public CityListAdapter(
            List<WeatherDetailsModel> weatherDetails,
            OnItemClickListener clickListener
    ) {
        this.weatherDetails = weatherDetails;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_whether_details, viewGroup, false);

        return new CityViewHolder(view, clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder cityViewHolder, int i) {
        cityViewHolder.onBind(weatherDetails.get(i));
    }

    @Override
    public int getItemCount() {
        return weatherDetails.size();
    }

    public static class CityViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvCityName = itemView.findViewById(R.id.tvRcvCityName);
        private final TextView tvHumidity = itemView.findViewById(R.id.tvRcvHumidity);

        public CityViewHolder(@NonNull View itemView, OnItemClickListener clickListener) {
            super(itemView);

            itemView.setOnClickListener(view -> {
                clickListener.onClick(getAdapterPosition());
            });
        }

        void onBind(WeatherDetailsModel details) {
            tvCityName.setText(details.getCityName());
            tvHumidity.setText(String.valueOf(details.getHumidity()));
        }
    }

    public interface OnItemClickListener {
        void onClick(int position);
    }
}

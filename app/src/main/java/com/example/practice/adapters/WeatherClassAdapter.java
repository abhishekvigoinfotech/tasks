package com.example.practice.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practice.R;
import com.example.practice.models.WeatherApiModel;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class WeatherClassAdapter extends RecyclerView.Adapter<WeatherClassAdapter.ViewHolder> {

    Context context;
    ArrayList<WeatherApiModel> weatherApiModelArrayList;
    public WeatherClassAdapter(Context context, ArrayList<WeatherApiModel> weatherApiModelArrayList){
        this.context = context;
        this.weatherApiModelArrayList = weatherApiModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.weatherapi_layout_design, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.textView.setText(weatherApiModelArrayList.get(position).name);
//        holder.textView.setText("weatherApiModelArrayList.get(position).name");

    }

    @Override
    public int getItemCount() {
        return weatherApiModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        MaterialTextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.city_name_textview);
        }
    }
}

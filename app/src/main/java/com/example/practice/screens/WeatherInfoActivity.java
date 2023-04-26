package com.example.practice.screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import com.example.practice.R;
import com.example.practice.adapters.WeatherClassAdapter;
import com.example.practice.models.WeatherApiModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WeatherInfoActivity extends AppCompatActivity {
    String url = "https://restcountries.com/v3.1/all?fields=capital";
    RecyclerView recyclerView;

    ArrayList<WeatherApiModel> weatherApiModelArrayList = new ArrayList<>();
    WeatherClassAdapter weatherClassAdapter = new WeatherClassAdapter(WeatherInfoActivity.this, weatherApiModelArrayList);

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_info);
        settingUpIds();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    WeatherInfoActivity.this.runOnUiThread(() -> {
                        try {
                            JSONArray countries = new JSONArray(responseData);
                            for (int i = 0; i < countries.length(); i++) {
                                JSONObject arr = countries.getJSONObject(i);
                                weatherApiModelArrayList.add(new WeatherApiModel(arr.getString("capital")));
                            }
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    });
                }
            }
        });

        recyclerView.setAdapter(weatherClassAdapter);
        weatherClassAdapter.notifyDataSetChanged();

    }

    private void settingUpIds() {
        recyclerView = findViewById(R.id.weather_info_recycler_view);
    }
}
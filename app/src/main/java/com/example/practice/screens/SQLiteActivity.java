package com.example.practice.screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.practice.R;
import com.example.practice.db.MyDbHandler;
import com.example.practice.models.OkhttpModel;
import com.example.practice.models.SQLiteModel;
import com.google.android.material.textview.MaterialTextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SQLiteActivity extends AppCompatActivity {

    //    String url = "http://numbersapi.com/random";
    String url = "https://api.chucknorris.io/jokes/random";
    MaterialTextView textView;
    MyDbHandler dbHandler = new MyDbHandler(SQLiteActivity.this);
    String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new java.util.Date());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sqlite);
        settingUpIds();
        fetchingDataFromNumbersApi();

    }

    private void settingUpIds() {
        textView = findViewById(R.id.display_api_response);
    }

    private void fetchingDataFromNumbersApi() {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                SQLiteActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(SQLiteActivity.this, "fail: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                e.printStackTrace();
            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String res = response.body().string();
                    SQLiteActivity.this.runOnUiThread(() -> {
                        try {
                            Toast.makeText(SQLiteActivity.this, String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
                            JSONObject jsonObject = new JSONObject(res);
                            String data = jsonObject.getString("value");
                            SQLiteModel sqLiteModel = new SQLiteModel("", "", "");
                            sqLiteModel.setTimestamp(timeStamp);
                            sqLiteModel.setUrl(url);
                            sqLiteModel.setResponse(data);
                            textView.setText(data);
                            dbHandler.addRecord(sqLiteModel);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    });
                }
            }
        });
    }

}
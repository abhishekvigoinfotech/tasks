package com.example.practice.screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.practice.R;
import com.example.practice.adapters.OkhttpClassAdapter;
import com.example.practice.models.OkhttpModel;

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

public class OkhttpActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<OkhttpModel> okhttpModelArrayList =  new ArrayList<>();
    OkhttpClassAdapter okhttpClassAdapter = new OkhttpClassAdapter(OkhttpActivity.this, okhttpModelArrayList);

    //    String url = "https://reqres.in/api/users?page=1";
    String url = "https://jsonplaceholder.typicode.com/posts";
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);

        settingUpIds();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        progressDialog = new ProgressDialog(OkhttpActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog_layout);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        progressDialog.setCanceledOnTouchOutside(false);
        fetchingDataFromOkhttp();


    }

    private void fetchingDataFromOkhttp() {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()){
                    progressDialog.dismiss();
                    String res = response.body().string();
                    OkhttpActivity.this.runOnUiThread(() -> {
                        try {
                            JSONArray arr = new JSONArray(res);
                            for (int i = 0; i< arr.length(); i++){
                                JSONObject object = arr.getJSONObject(i);
                                okhttpModelArrayList.add(new OkhttpModel(object.getString("id"), object.getString("title")));
                            }
                            recyclerView.setAdapter(okhttpClassAdapter);
                            okhttpClassAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            Toast.makeText(OkhttpActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                            throw new RuntimeException(e);
                        }
                    });
                }
            }
        });
    }

    private void settingUpIds() {
        recyclerView = findViewById(R.id.okhttp_recycler_view);
    }
}
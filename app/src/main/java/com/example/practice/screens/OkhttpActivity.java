package com.example.practice.screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
        initialTasks();
    }

    private void initialTasks() {
        if (isOnline()) {
            fetchingDataFromOkhttp();
        } else {
            try {
                progressDialog.dismiss();
                AlertDialog.Builder builder =new AlertDialog.Builder(this);
                builder.setTitle("No internet Connection");
                builder.setMessage("Please turn on internet connection to continue");
                builder.setNegativeButton("close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        OkhttpActivity.this.finish();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            } catch (Exception e) {
                Log.d("noInternetDialog", "Show Dialog: " + e.getMessage());
            }
        }
    }

    private void fetchingDataFromOkhttp() {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                OkhttpActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(OkhttpActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                e.printStackTrace();
            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()){
//                    Log.d("onResponse", "onResponse: "+response.code());
                    progressDialog.dismiss();
                    String res = response.body().string();
                    OkhttpActivity.this.runOnUiThread(() -> {
                        try {
                            Toast.makeText(OkhttpActivity.this, String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
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

    public boolean isOnline() {
        ConnectivityManager conMgr = (ConnectivityManager) getApplicationContext().getSystemService(OkhttpActivity.this.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

        if(netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()){
            Toast.makeText(OkhttpActivity.this, "No Internet connection!", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
    private void settingUpIds() {
        recyclerView = findViewById(R.id.okhttp_recycler_view);
    }
}
package com.example.practice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.practice.screens.LogRecords;
import com.example.practice.screens.SQLiteActivity;
import com.example.practice.screens.WeatherInfoActivity;
import com.example.practice.screens.OkhttpActivity;
import com.example.practice.screens.RecyclerView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {

    TextInputEditText aadhaarNoTextInputEditText;
    MaterialButton fetchDetailsButton, NextActivity, okhttp, carApiBtn, sqliteBtn, recordsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        settingUpIds();
        fetchDetailsButton.setOnClickListener(v -> displayNumber());
        NextActivity.setOnClickListener(v -> intent());
        okhttp.setOnClickListener(v -> intent2());
        carApiBtn.setOnClickListener(v -> intent3());
        sqliteBtn.setOnClickListener(v -> intent4());
        recordsBtn.setOnClickListener(v -> intent5());

    }

    private void intent() {
        startActivity(new Intent(this, RecyclerView.class));
    }
    private void intent2() {
        startActivity(new Intent(this, OkhttpActivity.class));
    }
    private void intent3() {
        startActivity(new Intent(this, WeatherInfoActivity.class));
    }
    private void intent4() {
        startActivity(new Intent(this, SQLiteActivity.class));
    }
    private void intent5() {
        startActivity(new Intent(this, LogRecords.class));
    }

    private void displayNumber() {
        if (aadhaarNoTextInputEditText.getEditableText().toString().isEmpty()){
            Toast.makeText(this, "Please Enter Aadhaar Number", Toast.LENGTH_SHORT).show();
        } else {
            Snackbar.make(this.getCurrentFocus(), aadhaarNoTextInputEditText.getEditableText().toString(), Snackbar.LENGTH_SHORT).show();
        }
    }

    private void settingUpIds() {
        aadhaarNoTextInputEditText =  findViewById(R.id.aadhaar_text_input);
        fetchDetailsButton = findViewById(R.id.fetch_details_button);
        NextActivity = findViewById(R.id.next_activity_button);
        okhttp = findViewById(R.id.okhttp_activity_button);
        carApiBtn = findViewById(R.id.weatherinfo_activity_button);
        sqliteBtn = findViewById(R.id.sqlite_activity_button);
        recordsBtn = findViewById(R.id.records_activity_button);
    }
}
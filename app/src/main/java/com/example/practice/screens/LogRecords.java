package com.example.practice.screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.practice.R;
import com.example.practice.adapters.RecordsAdapter;
import com.example.practice.db.MyDbHandler;
import com.example.practice.models.SQLiteModel;

import java.util.List;

public class LogRecords extends AppCompatActivity {

    RecyclerView recyclerView;
    MyDbHandler dbHandler = new MyDbHandler(LogRecords.this);
    List<SQLiteModel> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_records);
        settingUpIds();
        dataList = dbHandler.getAllRecords();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecordsAdapter recordsAdapter = new RecordsAdapter(LogRecords.this, dataList);
        recyclerView.setAdapter(recordsAdapter);
        recordsAdapter.notifyDataSetChanged();


    }

    private void settingUpIds() {
        recyclerView = findViewById(R.id.records_rec_view);
    }
}
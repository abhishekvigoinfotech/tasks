package com.example.practice.screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.practice.MainActivity;
import com.example.practice.R;
import com.example.practice.adapters.RecyclerViewAdapter;
import com.example.practice.models.RecyclerViewModel;

import java.util.ArrayList;

public class RecyclerView extends AppCompatActivity {

    androidx.recyclerview.widget.RecyclerView recyclerView;
    ArrayList<RecyclerViewModel> recyclerViewModelArrayList =  new ArrayList<>();
    RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(RecyclerView.this, recyclerViewModelArrayList);

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        settingUpIds();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        for (int i = 1; i < 11; i++){
            recyclerViewModelArrayList.add(new RecyclerViewModel(R.drawable.ic_launcher_background, "Abhishek "+i));
        }
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.notifyDataSetChanged();

    }

    private void settingUpIds() {
        recyclerView = findViewById(R.id.recycler_view);
    }
}
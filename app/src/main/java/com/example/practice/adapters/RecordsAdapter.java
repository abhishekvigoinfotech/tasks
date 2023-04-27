package com.example.practice.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practice.R;
import com.example.practice.models.SQLiteModel;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

public class RecordsAdapter extends RecyclerView.Adapter<RecordsAdapter.ViewHolder> {

    Context context;
    List<SQLiteModel> dataList;
//    String timestamp;
//    String url;
//    String response;

    public RecordsAdapter(Context context, List<SQLiteModel> dataList) {
        this.context = context;
        this.dataList = dataList;
//        this.timestamp = timestamp;
//        this.url = url;
//        this.response = response;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.display_records_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.timestamp.setText(dataList.get(position).getTimestamp());
        holder.url.setText(dataList.get(position).getUrl());
        holder.response.setText(dataList.get(position).getResponse());

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        MaterialTextView timestamp, url, response;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            timestamp = itemView.findViewById(R.id.display_timestamp_textview);
            url = itemView.findViewById(R.id.display_url_textview);
            response = itemView.findViewById(R.id.display_response_textview);
        }
    }
}

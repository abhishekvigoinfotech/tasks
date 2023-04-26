package com.example.practice.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practice.R;
import com.example.practice.models.OkhttpModel;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;


public class OkhttpClassAdapter extends RecyclerView.Adapter<OkhttpClassAdapter.ViewHolder> {
    Context context;
    ArrayList<OkhttpModel> okhttpModelArrayList;

    public OkhttpClassAdapter(Context context, ArrayList<OkhttpModel> response){
        this.context = context;
        this.okhttpModelArrayList = response;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.okhttp_layout_design, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.id.setText(okhttpModelArrayList.get(position).id);
        holder.title.setText(okhttpModelArrayList.get(position).title);

    }

    @Override
    public int getItemCount() {
        return okhttpModelArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        MaterialTextView id, title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.okhttp_id_text_view);
            title = itemView.findViewById(R.id.okhttp_title_text_view);
        }
    }
}

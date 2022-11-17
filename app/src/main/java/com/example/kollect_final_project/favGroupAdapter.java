package com.example.kollect_final_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class favGroupAdapter extends RecyclerView.Adapter<favGroupAdapter.MyViewHolder>{
    ArrayList<String> arrayList;
    public favGroupAdapter(ArrayList<String> arrayList){
        this.arrayList = arrayList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View v = layoutInflater.inflate(R.layout.favorite_group_row_item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.group_id.setText(arrayList.get(position).toString());
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView group_id;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
             group_id = itemView.findViewById(R.id.fav_group_id);

        }
    }
}

package com.example.kollect_final_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Artist> artists;

    public CustomAdapter(Context context, ArrayList<Artist> artists) {

        this.context = context;
        this.artists = artists;
    }

    @Override
    public int getCount() {
        return artists.size();
    }

    @Override
    public Object getItem(int i) {
        return artists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.data_item, null, true);

            holder.tvname = (TextView) convertView.findViewById(R.id.name);
            holder.tvgroups = (TextView) convertView.findViewById(R.id.groups);
            holder.tvprice= (TextView) convertView.findViewById(R.id.price);


            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }

        holder.tvname.setText("Name: "+artists.get(position).getName());
        holder.tvgroups.setText("Groups: "+artists.get(position).getGroups());
        holder.tvprice.setText("Price: "+artists.get(position).getPrice());

        return convertView;
    }

    private class ViewHolder {

        protected TextView tvname, tvgroups, tvprice;
    }
}

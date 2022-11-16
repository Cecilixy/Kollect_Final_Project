package com.example.kollect_final_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapterPosts extends BaseAdapter {
    private Context context;
    private ArrayList<Post> posts;

    public CustomAdapterPosts(Context context, ArrayList<Post> posts) {

        this.context = context;
        this.posts = posts;
    }

    @Override
    public int getCount() {
        return posts.size();
    }

    @Override
    public Object getItem(int i) {
        return posts.get(i);
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
            convertView = inflater.inflate(R.layout.data_item_posts, null, true);

            holder.tvSellername = (TextView) convertView.findViewById(R.id.seller_name);
            holder.tvArtistname = (TextView) convertView.findViewById(R.id.artist_name);
            holder.tvgroups = (TextView) convertView.findViewById(R.id.groups);
            holder.tvprice= (TextView) convertView.findViewById(R.id.price);

            holder.tvstatus = (TextView) convertView.findViewById(R.id.status);
            holder.tvUserID = (TextView) convertView.findViewById(R.id.userID);


            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }

        holder.tvSellername.setText("Seller Name: "+ posts.get(position).getSellerName());
        holder.tvArtistname.setText("Artist Name: "+ posts.get(position).getArtistName());
        holder.tvgroups.setText("Artist Groups: "+ posts.get(position).getGroups());
        holder.tvprice.setText("Price: "+ posts.get(position).getPrice());
        holder.tvstatus.setText("Status: "+ posts.get(position).getStatus());
        holder.tvUserID.setText("User_ID: " + posts.get(position).getUserID());

        return convertView;
    }

    private class ViewHolder {

        protected TextView tvSellername, tvArtistname,tvgroups, tvprice, tvstatus, tvUserID;
    }
}

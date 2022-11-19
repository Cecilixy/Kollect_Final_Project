package com.example.kollect_final_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapterUsers extends BaseAdapter {
    private Context context;
    private ArrayList<User> users;

    public CustomAdapterUsers(Context context, ArrayList<User> users) {

        this.context = context;
        this.users = users;
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int i) {
        return users.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CustomAdapterUsers.ViewHolder holder;

        if (convertView == null) {
            holder = new CustomAdapterUsers.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.data_item_users, null, true);

            holder.tvUsername = (TextView) convertView.findViewById(R.id.user_name);
            holder.tvGender = (TextView) convertView.findViewById(R.id.gender);
            holder.tvPassword = (TextView) convertView.findViewById(R.id.password);
            holder.tvInstagramID= (TextView) convertView.findViewById(R.id.instagram_id);
            holder.tvFavArtist = (TextView) convertView.findViewById(R.id.fav_artist);
            holder.tvFavGroup = (TextView) convertView.findViewById(R.id.fav_group);

            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (CustomAdapterUsers.ViewHolder)convertView.getTag();
        }

        holder.tvUsername.setText("User Name: "+ users.get(position).getUser_name());
        holder.tvGender.setText("Gender: "+ users.get(position).getGender());
        holder.tvPassword.setText("Password: "+ users.get(position).getPassword());
        holder.tvInstagramID.setText("Instagram ID: "+ users.get(position).getInsta_id());
        holder.tvFavArtist.setText("Favorite Artist: "+ users.get(position).getFav_artist());
        holder.tvFavGroup.setText("Favorite Group: "+ users.get(position).getFav_group());


        return convertView;
    }

    private class ViewHolder {

        protected TextView tvUsername, tvGender,tvPassword, tvInstagramID, tvFavArtist, tvFavGroup;
    }
}

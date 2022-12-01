package com.example.kollect_final_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class SearchResult extends AppCompatActivity {
    private ListView listView;
    private ArrayList<Post> userModelArrayList;
    private CustomAdapterPosts customAdapterPosts;
    private MySQLiteOpenHelper databaseHelper;
    private String artist_name;
    private String group_name;
    private String blacklist_name;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        Intent g_name = getIntent();
        group_name = g_name.getStringExtra("g_name");

        Intent a_name = getIntent();
        artist_name = a_name.getStringExtra("a_name");

        Intent b_name = getIntent();
        blacklist_name = b_name.getStringExtra("b_name");


        listView = (ListView) findViewById(R.id.listview);

        databaseHelper = new MySQLiteOpenHelper(this);

        if (group_name != null){

            userModelArrayList = databaseHelper.getSearchedGroupPosts(group_name);}
        else if (artist_name != null){
            userModelArrayList = databaseHelper.getSearchedGroupPosts(artist_name);
        }else if (blacklist_name != null){
            userModelArrayList = databaseHelper.getSearchedGroupPosts(blacklist_name);}


        customAdapterPosts = new CustomAdapterPosts(this,userModelArrayList);
        listView.setAdapter(customAdapterPosts);


    }
}
package com.example.kollect_final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class getAllBlacklist extends AppCompatActivity {

    private ListView listView;
    private ArrayList<Blacklist> blacklistModelArrayList;
    private CustomAdapterBlacklist customAdapterBlacklist;
    private MySQLiteOpenHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_all_blacklist);

        listView = (ListView) findViewById(R.id.listview);

        databaseHelper = new MySQLiteOpenHelper(this);

        blacklistModelArrayList = databaseHelper.getAllBlacklist();

        customAdapterBlacklist = new CustomAdapterBlacklist(this,blacklistModelArrayList);
        listView.setAdapter(customAdapterBlacklist);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getAllBlacklist.this, UpdateandDeleteUsers.class);
                intent.putExtra("user", blacklistModelArrayList.get(position));
                startActivity(intent);
            }
        });
    }
}
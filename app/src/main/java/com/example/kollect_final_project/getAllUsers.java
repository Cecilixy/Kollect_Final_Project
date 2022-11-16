package com.example.kollect_final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class getAllUsers extends AppCompatActivity {

    private ListView listView;
    private ArrayList<User> userModelArrayList;
    private CustomAdapterUsers customAdapterUsers;
    private MySQLiteOpenHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_all_users);

        listView = (ListView) findViewById(R.id.listview);

        databaseHelper = new MySQLiteOpenHelper(this);

        userModelArrayList = databaseHelper.getAllUsers();

        customAdapterUsers = new CustomAdapterUsers(this,userModelArrayList);
        listView.setAdapter(customAdapterUsers);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getAllUsers.this, UpdateandDeleteUsers.class);
                intent.putExtra("user", userModelArrayList.get(position));
                startActivity(intent);
            }
        });

    }
}
package com.example.kollect_final_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Profile extends AppCompatActivity {
    private TextView change_info_txt;
    private ImageView change_info_arr;
    private TextView completed_purchase_txt;
    private ImageView completed_purchase_arr;
    private TextView pre_txt;
    private ImageView pre_arr;
    private TextView get_pre_txt;
    private ImageView get_pre_arr;
    private TextView blacklist_txt;
    private ImageView blacklist_arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        change_info_txt = (TextView)findViewById(R.id.change_info_txt);
        change_info_arr = (ImageView)findViewById(R.id.change_info_arr);
        completed_purchase_txt = (TextView)findViewById(R.id.completed_purchase_txt);
        completed_purchase_arr = (ImageView)findViewById(R.id.completed_purchase_arr);
        pre_txt = (TextView)findViewById(R.id.pre_txt);
        pre_arr = (ImageView)findViewById(R.id.pre_arr);
        get_pre_txt = (TextView)findViewById(R.id.get_pre_txt);
        get_pre_arr = (ImageView)findViewById(R.id.get_pre_arr);
        blacklist_txt = (TextView)findViewById(R.id.blacklist_txt);
        blacklist_arr = (ImageView)findViewById(R.id.blacklist_arr);

        change_info_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent change_info = new Intent(Profile.this, Change_personal_info.class);
                startActivity(change_info);
            }
        });
        change_info_arr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent change_info = new Intent(Profile.this, Change_personal_info.class);
                startActivity(change_info);
            }
        });


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.profile);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.favorite:
                        startActivity(new Intent(getApplicationContext(),Favorite.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.search:
                        startActivity(new Intent(getApplicationContext(),Search.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(),Profile.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
}
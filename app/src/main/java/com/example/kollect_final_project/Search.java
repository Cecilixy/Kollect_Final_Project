package com.example.kollect_final_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Search extends AppCompatActivity {

    private Button search_group_btn;
    private EditText search_group;
    private Button search_artist_btn;
    private EditText search_artist;
    private EditText search_blacklist;
    private Button search_blacklist_btn;
    private String artist_name;
    private String group_name;
    private String blacklist_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        search_group_btn = (Button)findViewById(R.id.search_group_btn);
        search_group = (EditText)findViewById(R.id.search_group);
        search_artist_btn = (Button)findViewById(R.id.search_artist_btn);
        search_artist = (EditText)findViewById(R.id.search_artist);
        search_blacklist_btn = (Button)findViewById(R.id.search_blacklist_btn);
        search_blacklist = (EditText)findViewById(R.id.search_blacklist);

        search_group_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                group_name = search_group.getText().toString();
                Intent intent = new Intent(Search.this, SearchResult.class);
                intent.putExtra("g_name",group_name);
                startActivity(intent);

            }

        });
        search_artist_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                artist_name = search_artist.getText().toString();
                Intent intent = new Intent(Search.this, SearchResult.class);
                intent.putExtra("a_name",artist_name);
                startActivity(intent);

            }

        });
        search_blacklist_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                blacklist_name = search_blacklist.getText().toString();
                Intent intent = new Intent(Search.this, SearchResult.class);
                intent.putExtra("b_name",blacklist_name);
                startActivity(intent);

            }

        });


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.search);
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
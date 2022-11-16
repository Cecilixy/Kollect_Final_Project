package com.example.kollect_final_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Favorite extends AppCompatActivity {
    private RecyclerView recyclerView1;
    private ArrayList<String> arrayList1;
    private RecyclerView recyclerView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        recyclerView1 = findViewById(R.id.groups_added);
        recyclerView2 = findViewById(R.id.artists_added);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.favorite);
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
        arrayList1 = new ArrayList<String>();
        arrayList1.add("LIAM");
        arrayList1.add("ILAM");
        arrayList1.add("LIMA");
        arrayList1.add("LIAM");
        arrayList1.add("AMLI");
        arrayList1.add("AMLI");
        arrayList1.add("AMLI");
        arrayList1.add("LIAM");
        arrayList1.add("ILAM");
        arrayList1.add("LIMA");
        arrayList1.add("LIAM");
        arrayList1.add("AMLI");
        arrayList1.add("AMLI");
        arrayList1.add("AMLI");
        arrayList1.add("AMLI");
        arrayList1.add("LIAM");
        arrayList1.add("ILAM");
        arrayList1.add("LIMA");
        arrayList1.add("LIAM");
        arrayList1.add("AMLI");
        arrayList1.add("AMLI");
        arrayList1.add("AMLI");
        arrayList1.add("LIAM");
        arrayList1.add("ILAM");
        arrayList1.add("LIMA");
        arrayList1.add("LIAM");
        arrayList1.add("AMLI");
        arrayList1.add("AMLI");
        arrayList1.add("AMLI");
        LinearLayoutManager  linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        favGroupAdapter myAdapter = new favGroupAdapter(arrayList1);
        recyclerView1.setAdapter(myAdapter);

        LinearLayoutManager  linearLayoutManager2 = new LinearLayoutManager(getApplicationContext());
        recyclerView2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        favGroupAdapter myAdapter2 = new favGroupAdapter(arrayList1);
        recyclerView2.setAdapter(myAdapter2);
    }
}
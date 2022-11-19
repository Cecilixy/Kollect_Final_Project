package com.example.kollect_final_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Favorite extends AppCompatActivity implements AddFavoriteDialog.AddFavoriteDialogListener {
    private RecyclerView recyclerView1;
    private ArrayList<String> arrayList1;
    private ArrayList<String> arrayList2;
    private RecyclerView recyclerView2;
    private Button addGroup;
    private Button addArtist;
    private MySQLiteOpenHelper databaseHelper;
    //private ArrayList<favartistlist> FavartistlistModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        recyclerView1 = findViewById(R.id.groups_added);
        recyclerView2 = findViewById(R.id.artists_added);
        addGroup = findViewById(R.id.addGroupBtn);
        addArtist = findViewById(R.id.addArtistBtn);
        databaseHelper = new MySQLiteOpenHelper(this);
       // favoritelistModelArrayList = databaseHelper.getall;

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
        arrayList2 = new ArrayList<String>();
        arrayList1.add("aaa");
        arrayList1.add("aaa");
        arrayList2.add("bbb");
        arrayList2.add("bbb");
        arrayList2.add("bbb");
        arrayList2.add("bbb");
        recyclerView1.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        favGroupAdapter myAdapter = new favGroupAdapter(arrayList1);
        recyclerView1.setAdapter(myAdapter);

        recyclerView2.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        favGroupAdapter myAdapter2 = new favGroupAdapter(arrayList2);
        recyclerView2.setAdapter(myAdapter2);

        addGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }

        });
        addArtist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
    }

    public void openDialog(){
            AddFavoriteDialog addFavoriteDialog = new AddFavoriteDialog();
            addFavoriteDialog.show(getSupportFragmentManager(),"example dialog");
    }
    @Override
    public void applyTexts(String groupname, Switch what) {
        if(!what.isChecked()){
            arrayList1.add(groupname);
        }else{
            arrayList2.add(groupname);
        }
    }

}
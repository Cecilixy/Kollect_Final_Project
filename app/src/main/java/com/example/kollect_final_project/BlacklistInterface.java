package com.example.kollect_final_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class BlacklistInterface extends AppCompatActivity {
    private Button btnAdd, btnGetAll;

    private ListView listView;
    private ArrayList<Blacklist> blacklistModelArrayList;
    private CustomAdapterBlacklist customAdapterBlacklist;
    private MySQLiteOpenHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blacklist);
        btnAdd = (Button) findViewById(R.id.Add_Blacklist_btn);

        listView = (ListView) findViewById(R.id.listview);

        databaseHelper = new MySQLiteOpenHelper(this);

        blacklistModelArrayList = databaseHelper.getAllBlacklist();

        customAdapterBlacklist = new CustomAdapterBlacklist(this,blacklistModelArrayList);
        listView.setAdapter(customAdapterBlacklist);

        /*
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(BlacklistInterface.this, UpdateandDeleteUsers.class);
                intent.putExtra("user", blacklistModelArrayList.get(position));
                startActivity(intent);
            }
        });

         */

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent blacklist = new Intent(getApplicationContext(),Add_to_Blacklist.class);
                startActivity(blacklist);
            }
        });

    }
}

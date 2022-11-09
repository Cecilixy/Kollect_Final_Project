package com.example.kollect_final_project;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;

    public class getAllArtists extends AppCompatActivity {

        private ListView listView;
        private ArrayList<Artist> userModelArrayList;
        private CustomAdapter customAdapter;
        private MySQLiteOpenHelper databaseHelper;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_get_all_artists);

            listView = (ListView) findViewById(R.id.listview);

            databaseHelper = new MySQLiteOpenHelper(this);

            userModelArrayList = databaseHelper.getAllArtists();

            customAdapter = new CustomAdapter(this,userModelArrayList);
            listView.setAdapter(customAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getAllArtists.this, UpdateandDelete.class);
                    intent.putExtra("user", (Serializable) userModelArrayList.get(position));
                    startActivity(intent);
                }
            });

        }
}
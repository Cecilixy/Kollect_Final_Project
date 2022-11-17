package com.example.kollect_final_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class BlacklistInterface extends AppCompatActivity {
    private Button btnAdd, btnGetAll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blacklist);
        btnAdd = (Button) findViewById(R.id.Add_Blacklist_btn);
        btnGetAll = (Button) findViewById(R.id.getAllBlacklist);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent blacklist = new Intent(getApplicationContext(),Add_to_Blacklist.class);
                startActivity(blacklist);
            }
        });

        btnGetAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BlacklistInterface.this, getAllBlacklist.class);
                startActivity(intent);
            }
        });
    }
}

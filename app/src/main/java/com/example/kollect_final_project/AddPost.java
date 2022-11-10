package com.example.kollect_final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddPost extends AppCompatActivity {

    private MySQLiteOpenHelper dbManager;
    private Button btnStore, btnGetall;
    private EditText etsname, etgroups, etprice,etaname,etstatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        dbManager = new MySQLiteOpenHelper(this);


        btnStore = (Button) findViewById(R.id.btnstore);
        btnGetall = (Button) findViewById(R.id.btnget);
        etsname = (EditText) findViewById(R.id.etsname);
        etaname = (EditText) findViewById(R.id.etaname);
        etstatus = (EditText) findViewById(R.id.etstatus);
        etgroups = (EditText) findViewById(R.id.etgroup);
        etprice = (EditText) findViewById(R.id.etprice);

        // add default post
        dbManager.insertPost("admin","Jenny","Blackpink",100,1);
        btnStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbManager.insertPost(etsname.getText().toString(), etaname.getText().toString(), etgroups.getText().toString(), Integer.parseInt(etprice.getText().toString()),Integer.parseInt(etstatus.getText().toString()));
                etsname.setText("");
                etaname.setText("");
                etstatus.setText("");
                etgroups.setText("");
                etprice.setText("");
                Toast.makeText(AddPost.this, "Stored Successfully!", Toast.LENGTH_SHORT).show();
            }
        });

        btnGetall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddPost.this, getAllPosts.class);
                startActivity(intent);
            }
        });
    }

    public void newActivity(View v){
        Intent i = new Intent(AddPost.this, MainActivity.class);
        startActivity(i);
    }
}
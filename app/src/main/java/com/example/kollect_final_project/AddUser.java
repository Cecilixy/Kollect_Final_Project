package com.example.kollect_final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddUser extends AppCompatActivity {

    private MySQLiteOpenHelper dbManager;
    private Button btnStore, btnGetall;
    private EditText etUsername, etGender, etInstagramID, etPassword;
    private FirebaseDatabase myFirebasedata;
    private DatabaseReference userReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        dbManager = new MySQLiteOpenHelper(this);
        myFirebasedata = FirebaseDatabase.getInstance();
        userReference = myFirebasedata.getReference().child("users");


        btnStore = (Button) findViewById(R.id.btnstore);
        btnGetall = (Button) findViewById(R.id.btnget);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etGender = (EditText) findViewById(R.id.etGender);
        etInstagramID = (EditText) findViewById(R.id.etInstagramID);
        etPassword = (EditText) findViewById(R.id.etPassword);

        // add default user

        dbManager.insertUser("admin","123","Male","www.instagram.com");

        btnStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbManager.insertUser(etUsername.getText().toString(), etPassword.getText().toString(), etGender.getText().toString(), etInstagramID.getText().toString());
                User addedUser = new User(etUsername.getText().toString(), etPassword.getText().toString(), etGender.getText().toString(), etInstagramID.getText().toString());
                userReference.push().setValue(addedUser);
                etUsername.setText("");
                etPassword.setText("");
                etGender.setText("");
                etInstagramID.setText("");
                Toast.makeText(AddUser.this, "Stored Successfully!", Toast.LENGTH_SHORT).show();
            }
        });

        btnGetall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddUser.this, getAllUsers.class);
                startActivity(intent);
            }
        });
    }

    public void newActivity(View v){
        Intent i = new Intent(AddUser.this, MainActivity.class);
        startActivity(i);
    }
}
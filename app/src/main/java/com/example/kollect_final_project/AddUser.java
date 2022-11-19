package com.example.kollect_final_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddUser extends AppCompatActivity {

    private MySQLiteOpenHelper dbManager;
    private Button btnStore, btnGetall;
    private TextInputLayout etUsername, etGender, etInstagramID, etPassword;
    private FirebaseDatabase myFirebasedata;
    private DatabaseReference userReference;
    long maxid = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        dbManager = new MySQLiteOpenHelper(this);
        userReference = FirebaseDatabase.getInstance().getReference().child("Users");
        userReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    maxid = (snapshot.getChildrenCount());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        btnStore = (Button) findViewById(R.id.btnstore);
        btnGetall = (Button) findViewById(R.id.btnget);
        etUsername =  findViewById(R.id.etUsername);
        etGender =  findViewById(R.id.etGender);
        etInstagramID =  findViewById(R.id.etInstagramID);
        etPassword =  findViewById(R.id.etPassword);

        // add default user

        // dbManager.insertUser("admin","123","Male","www.instagram.com");

        btnStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = dbManager.getAutoIncrements() + 1;
                String username = etUsername.getEditText().getText().toString();
                String gender = etGender.getEditText().getText().toString();
                String instagramID = etInstagramID.getEditText().getText().toString();
                String password = etPassword.getEditText().getText().toString();
                dbManager.insertUser(username, password, gender, instagramID);
                User addedUser = new User(username, password, gender, instagramID);
                addedUser.setId((int)(maxid + 1));
                userReference.child(username).setValue(addedUser);
                etUsername.getEditText().setText("");
                etPassword.getEditText().setText("");
                etGender.getEditText().setText("");
                etInstagramID.getEditText().setText("");
                Toast.makeText(AddUser.this, "Stored Successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddUser.this, Login.class);
                startActivity(intent);
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
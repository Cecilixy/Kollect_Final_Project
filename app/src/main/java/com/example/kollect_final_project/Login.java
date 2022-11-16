package com.example.kollect_final_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.util.Pair;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class Login extends AppCompatActivity {

    Button callSignUp, login_btn;

    ImageView image;

    TextView logoText, sloganText;

    TextInputLayout username, password;

    public static int USERID = (-1);

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState); //This Line will hide the status bar from the screen

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_login);

        //Hooks

        callSignUp = findViewById(R.id.sign_up);

        image = findViewById(R.id.logo_image);

        logoText = findViewById(R.id.logo_name);

        sloganText = findViewById(R.id.slogan_name);

        username = findViewById(R.id.username);

        password = findViewById(R.id.password);

        login_btn = findViewById(R.id.login_btn);

        callSignUp.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                Intent intent = new Intent(Login.this, AddUser.class);

                Pair[] pairs = new Pair[7];

                pairs[0] = new Pair<View, String>(image, "logo_image");

                pairs[1] = new Pair<View, String>(logoText, "logo_text");

                pairs[2] = new Pair<View, String>(sloganText, "logo_desc");

                pairs[3] = new Pair<View, String>(username, "username_tran");

                pairs[4] = new Pair<View, String>(password, "password_tran");

                pairs[5] = new Pair<View, String>(login_btn, "button_tran");

                pairs[6] = new Pair<View, String>(callSignUp, "login_signup_tran");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {

                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login.this, pairs);

                    startActivity(intent, options.toBundle());

                }

            }

        });

    }

    private Boolean validateUsername() {
        String val = username.getEditText().getText().toString();
        if (val.isEmpty()) {
            username.setError("Field cannot be empty");
            return false;
        } else {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = password.getEditText().getText().toString();
        if (val.isEmpty()) {
            password.setError("Field cannot be empty");
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }

    public void loginUser(View view) {
        //Validate Login Info
        if (!validateUsername() | !validatePassword()) {
            return;
        } else {
            isUser();
        }
    }

    private void isUser(){
        final String userEnteredUsername = username.getEditText().getText().toString().trim();
        final String userEnteredPassword = password.getEditText().getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        Query checkUser = reference.orderByChild("user_name").equalTo(userEnteredUsername);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    username.setError(null);
                    username.setErrorEnabled(false);

                    String passwordFromDB = dataSnapshot.child(userEnteredUsername).child("password").getValue(String.class);

                    if (passwordFromDB.equals(userEnteredPassword)){
                        username.setError(null);

                        username.setErrorEnabled(false);

                        String usernameFromDb = dataSnapshot.child(userEnteredUsername).child("user_name").getValue(String.class);
                        String genderFromDb = dataSnapshot.child(userEnteredUsername).child("gender").getValue(String.class);
                        String instaidFromDb = dataSnapshot.child(userEnteredUsername).child("insta_id").getValue(String.class);
                        USERID = dataSnapshot.child(userEnteredUsername).child("id").getValue(Integer.class);

                        Intent intent = new Intent(getApplicationContext(),Profile.class);
                        intent.putExtra("user_name",usernameFromDb);
                        intent.putExtra("gender",genderFromDb);
                        intent.putExtra("insta_id",instaidFromDb);
                        intent.putExtra("password",passwordFromDB);

                        startActivity(intent);

//                        Intent i = new Intent(Login.this, MainActivity.class);
//                        startActivity(i);

                    }
                    else{
                        password.setError("Wrong Password!");
                        password.requestFocus();
                    }
                }
                else{
                    username.setError("No such user!");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
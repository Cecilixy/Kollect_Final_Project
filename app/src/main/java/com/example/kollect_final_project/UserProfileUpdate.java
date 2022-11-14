package com.example.kollect_final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserProfileUpdate extends AppCompatActivity {

    TextInputLayout fullName, instalink, gender, password;
    TextView fullNameLabel, usernameLabel;

    //Global Variables to hold user data inside this activity
    String _USERNAME, _INSTALINK, _GENDER, _PASSWORD;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_update);

        reference = FirebaseDatabase.getInstance().getReference("Users");

        //Hooks
        fullName = findViewById(R.id.full_name_profile);
        instalink = findViewById(R.id.instaID_profile);
        gender = findViewById(R.id.gender_profile);
        password = findViewById(R.id.password_profile);

        usernameLabel = findViewById(R.id.username);

        //ShowAllData
        showAllUserData();

    }

    private void showAllUserData() {

        Intent intent = getIntent();
        _USERNAME = intent.getStringExtra("user_name");
        _GENDER = intent.getStringExtra("gender");
        _INSTALINK = intent.getStringExtra("insta_id");
        _PASSWORD = intent.getStringExtra("password");



        usernameLabel.setText(_USERNAME);

        fullName.getEditText().setText(_USERNAME);
        instalink.getEditText().setText(_INSTALINK);
        gender.getEditText().setText(_GENDER);
        password.getEditText().setText(_PASSWORD);
    }


    public void update(View view) {

        if (isNameChanged() || isPasswordChanged()) {
            Toast.makeText(this, "Data has been updated", Toast.LENGTH_LONG).show();
        }
        else Toast.makeText(this, "Data is same and can not be updated", Toast.LENGTH_LONG).show();

    }

    private boolean isPasswordChanged() {
        if (!_PASSWORD.equals(password.getEditText().getText().toString())) {
            reference.child(_USERNAME).child("password").setValue(password.getEditText().getText().toString());
            _PASSWORD = password.getEditText().getText().toString();
            return true;
        } else {
            return false;
        }
    }

    private boolean isNameChanged() {

        if (!_USERNAME.equals(fullName.getEditText().getText().toString())) {
            reference.child(_USERNAME).child("user_name").setValue(fullName.getEditText().getText().toString());
            _USERNAME = fullName.getEditText().getText().toString();
            return true;
        } else {
            return false;
        }

    }
}
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

    TextInputLayout fullName, instalink, gender, password, favartist,favgroup;
    TextView fullNameLabel, usernameLabel;

    //Global Variables to hold user data inside this activity
    String _USERNAME, _INSTALINK, _GENDER, _PASSWORD,_FAVARTIST,_FAVGROUP;

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
        favartist = findViewById(R.id.fav_artist_profile);
        favgroup = findViewById(R.id.fav_group_profile);

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
        _FAVARTIST = intent.getStringExtra("fav_artist");
        _FAVGROUP = intent.getStringExtra("fav_group");



        usernameLabel.setText(_USERNAME);

        fullName.getEditText().setText(_USERNAME);
        instalink.getEditText().setText(_INSTALINK);
        gender.getEditText().setText(_GENDER);
        password.getEditText().setText(_PASSWORD);
        favartist.getEditText().setText(_FAVARTIST);
        favgroup.getEditText().setText(_FAVGROUP);
    }


    public void update(View view) {

        if (isNameChanged() || isPasswordChanged() || isInstaLinkChanged() || isGenderChanged() || isFavArtistChanged() || isFavGroupChanged()) {
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

    private boolean isInstaLinkChanged() {

        if (!_INSTALINK.equals(instalink.getEditText().getText().toString())) {
            reference.child(_USERNAME).child("insta_id").setValue(instalink.getEditText().getText().toString());
            _INSTALINK = instalink.getEditText().getText().toString();
            return true;
        } else {
            return false;
        }

    }

    private boolean isGenderChanged() {

        if (!_GENDER.equals(gender.getEditText().getText().toString())) {
            reference.child(_USERNAME).child("gender").setValue(gender.getEditText().getText().toString());
            _GENDER = gender.getEditText().getText().toString();
            return true;
        } else {
            return false;
        }

    }

    private boolean isFavArtistChanged() {

        if (!_FAVARTIST.equals(favartist.getEditText().getText().toString())) {
            reference.child(_USERNAME).child("fav_artist").setValue(favartist.getEditText().getText().toString());
            _FAVARTIST = favartist.getEditText().getText().toString();
            return true;
        } else {
            return false;
        }

    }

    private boolean isFavGroupChanged() {

        if (!_FAVGROUP.equals(favgroup.getEditText().getText().toString())) {
            reference.child(_USERNAME).child("fav_group").setValue(favgroup.getEditText().getText().toString());
            _FAVGROUP = favgroup.getEditText().getText().toString();
            return true;
        } else {
            return false;
        }

    }
}
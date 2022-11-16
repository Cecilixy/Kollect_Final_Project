package com.example.kollect_final_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserProfileUpdate extends AppCompatActivity implements         GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener {
    private GestureDetectorCompat mDetector;
    private static final int thresh = 100;
    private static final int velocity = 100;

    TextInputLayout fullName, instalink, gender, password;
    TextView fullNameLabel, usernameLabel;

    //Global Variables to hold user data inside this activity
    String _USERNAME, _INSTALINK, _GENDER, _PASSWORD;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_update);

        mDetector = new GestureDetectorCompat(this,this);

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

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        boolean result = false;
        try {
            float diffY = e2.getY() - e1.getY();
            float diffX = e2.getX() - e1.getX();
            if (Math.abs(diffX) > Math.abs(diffY)) {
                if (Math.abs(diffX) > thresh && Math.abs(velocityX) > velocity) {
                    if (diffX > 0) {
                        onFlingRight();
                    } else {
                        onFlingLeft();
                    }
                    result = true;
                }
            }
            else if (Math.abs(diffY) > thresh && Math.abs(velocityY) > velocity) {
                if (diffY > 0) {
                    onFlingBottom();
                } else {
                    onFlingTop();
                }
                result = true;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return result;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
        if (this.mDetector.onTouchEvent(event)) {
            return true;
        }
        return super.onTouchEvent(event);
    }
    public void onFlingRight() {
        Intent intentright = new Intent (UserProfileUpdate.this, Profile.class);
        startActivity(intentright);
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





    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }



    public void onFlingLeft() {

    }



    public void onFlingTop() {

    }

    public void onFlingBottom() {

    }
    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        return false;
    }
}

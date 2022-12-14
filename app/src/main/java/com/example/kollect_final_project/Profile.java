package com.example.kollect_final_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Profile extends AppCompatActivity {
    private TextView change_info_txt;
    private ImageView change_info_arr;
    private TextView username;
    private TextView completed_purchase_txt;
    private ImageView completed_purchase_arr;
    private TextView pre_txt;
    private ImageView pre_arr;
    private TextView get_pre_txt;
    private ImageView get_pre_arr;
    private TextView blacklist_txt;
    private ImageView blacklist_arr;
    private Button instaButton;
    private String _USERNAME,_GENDER,_INSTALINK,_PASSWORD,_FAVARTIST,_FAVGROUP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        change_info_txt = (TextView)findViewById(R.id.change_info_txt);
        change_info_arr = (ImageView)findViewById(R.id.change_info_arr);
        username = (TextView) findViewById(R.id.username);
        completed_purchase_txt = (TextView)findViewById(R.id.completed_purchase_txt);
        completed_purchase_arr = (ImageView)findViewById(R.id.completed_purchase_arr);
        pre_txt = (TextView)findViewById(R.id.pre_txt);
        pre_arr = (ImageView)findViewById(R.id.pre_arr);
        get_pre_txt = (TextView)findViewById(R.id.get_pre_txt);
        get_pre_arr = (ImageView)findViewById(R.id.get_pre_arr);
        blacklist_txt = (TextView)findViewById(R.id.blacklist_txt);
        blacklist_arr = (ImageView)findViewById(R.id.blacklist_arr);

        Intent intent = getIntent();

            _USERNAME = intent.getStringExtra("user_name");
            _GENDER = intent.getStringExtra("gender");
            _INSTALINK = intent.getStringExtra("insta_id");
            _PASSWORD = intent.getStringExtra("password");
            _FAVARTIST = intent.getStringExtra("fav_artist");
            _FAVGROUP = intent.getStringExtra("fav_group");

        if (savedInstanceState != null) {
            _USERNAME = savedInstanceState.getString("user_name");

        }

        username.setText(_USERNAME);
        change_info_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent changeIfo = new Intent(getApplicationContext(),UserProfileUpdate.class);
                changeIfo.putExtra("user_name",_USERNAME);
                changeIfo.putExtra("gender",_GENDER);
                changeIfo.putExtra("insta_id",_INSTALINK);
                changeIfo.putExtra("password",_PASSWORD);
                changeIfo.putExtra("fav_artist",_FAVARTIST);
                changeIfo.putExtra("fav_group",_FAVGROUP);
                startActivity(changeIfo);
            }
        });
        change_info_arr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent changeIfo = new Intent(getApplicationContext(),UserProfileUpdate.class);
                changeIfo.putExtra("user_name",_USERNAME);
                changeIfo.putExtra("gender",_GENDER);
                changeIfo.putExtra("insta_id",_INSTALINK);
                changeIfo.putExtra("password",_PASSWORD);
                changeIfo.putExtra("fav_artist",_FAVARTIST);
                changeIfo.putExtra("fav_group",_FAVGROUP);

                startActivity(changeIfo);
            }
        });

        blacklist_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent blacklist = new Intent(getApplicationContext(),BlacklistInterface.class);

                startActivity(blacklist);
            }
        });
        blacklist_arr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent blacklist = new Intent(getApplicationContext(),BlacklistInterface.class);



                startActivity(blacklist);
            }
        });

        pre_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent getPremium = new Intent(getApplicationContext(),GetPremium.class);

                startActivity(getPremium);
            }
        });
        pre_arr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent getPremium = new Intent(getApplicationContext(),GetPremium.class);

                startActivity(getPremium);
            }
        });


        instaButton = findViewById(R.id.instagram_btn);
        instaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Uri uri = Uri.parse("http://instagram.com/_u/"+_INSTALINK);
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);
                likeIng.setPackage("com.instagram.android");

                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://google.com")));
                }
            }
        });
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.profile);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        Intent changeIfo = new Intent(getApplicationContext(),MainActivity.class);
                        changeIfo.putExtra("user_name",_USERNAME);
                        changeIfo.putExtra("gender",_GENDER);
                        changeIfo.putExtra("insta_id",_INSTALINK);
                        changeIfo.putExtra("password",_PASSWORD);
                        changeIfo.putExtra("fav_artist",_FAVARTIST);
                        changeIfo.putExtra("fav_group",_FAVGROUP);

                        startActivity(changeIfo);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.favorite:
                        startActivity(new Intent(getApplicationContext(),Favorite.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.search:
                        startActivity(new Intent(getApplicationContext(),Search.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(),Profile.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
        outState.putString("user_name", username.getText().toString());

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        _USERNAME = savedInstanceState.getString("user_name");

    }

}
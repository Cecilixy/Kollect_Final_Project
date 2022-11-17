package com.example.kollect_final_project;

import static com.example.kollect_final_project.Login.USERID;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class AddPost extends AppCompatActivity {

    private MySQLiteOpenHelper dbManager;
    private Button btnStore, btnGetall;
    private EditText etsname, etgroups, etprice,etaname,etstatus;
    private final int GALLERY_REQUEST_CODE = 1000;
    ImageView imgGallery;
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

        imgGallery = (ImageView) findViewById(R.id.imageUpload);
        imgGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,GALLERY_REQUEST_CODE);
            }
        });

        // add default post
        // dbManager.insertPost("admin","Jenny","Blackpink",100,1);
        btnStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap photo = ((BitmapDrawable)imgGallery.getDrawable()).getBitmap();
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                photo.compress(Bitmap.CompressFormat.PNG, 100, bos);
                byte[] bArray = bos.toByteArray();

                dbManager.insertPost(etsname.getText().toString(), etaname.getText().toString(), etgroups.getText().toString(), Integer.parseInt(etprice.getText().toString()),Integer.parseInt(etstatus.getText().toString()),USERID,bArray);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            if (requestCode == GALLERY_REQUEST_CODE){
                imgGallery.setImageURI(data.getData());
            }
        }
    }

    public void newActivity(View v){
        Intent i = new Intent(AddPost.this, MainActivity.class);
        startActivity(i);
    }
}
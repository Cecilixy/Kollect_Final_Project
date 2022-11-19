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

import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;

public class AddPost extends AppCompatActivity {

    private MySQLiteOpenHelper dbManager;
    private Button btnStore, btnGetall;
    private TextInputLayout etsname, etgroups, etprice,etaname,etstatus;
    private final int GALLERY_REQUEST_CODE = 1000;
    ImageView imgGallery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        dbManager = new MySQLiteOpenHelper(this);


        btnStore = (Button) findViewById(R.id.btnstore);
        btnGetall = (Button) findViewById(R.id.btnget);
        etsname = findViewById(R.id.etsname);
        etaname = findViewById(R.id.etaname);
        etstatus = findViewById(R.id.etstatus);
        etgroups = findViewById(R.id.etgroup);
        etprice = findViewById(R.id.etprice);

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
                photo.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                byte[] bArray = bos.toByteArray();

                String sname = etsname.getEditText().getText().toString();
                String aname = etaname.getEditText().getText().toString();
                int status = Integer.parseInt(etstatus.getEditText().getText().toString());
                String groups = etgroups.getEditText().getText().toString();
                int price = Integer.parseInt(etprice.getEditText().getText().toString());

                dbManager.insertPost(sname, aname, groups, price,status,USERID,bArray);
                etsname.getEditText().setText("");
                etaname.getEditText().setText("");
                etstatus.getEditText().setText("");
                etgroups.getEditText().setText("");
                etprice.getEditText().setText("");
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
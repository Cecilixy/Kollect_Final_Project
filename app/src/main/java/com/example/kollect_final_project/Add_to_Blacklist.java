package com.example.kollect_final_project;

import androidx.annotation.NonNull;
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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;

public class Add_to_Blacklist extends AppCompatActivity {

    private MySQLiteOpenHelper dbManager;
    private Button btnSubmit;
    private EditText etUsername, etPaypalID;
    private FirebaseDatabase myFirebasedata;
    private DatabaseReference blacklistReference;
    long maxid = 0;

    private final int GALLERY_REQUEST_CODE = 1000;
    ImageView imgGallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_blacklist);

        dbManager = new MySQLiteOpenHelper(this);
        blacklistReference = FirebaseDatabase.getInstance().getReference().child("Blacklist");
        blacklistReference.addValueEventListener(new ValueEventListener() {
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

        btnSubmit = (Button) findViewById(R.id.btn_submit);
        etUsername = (EditText) findViewById(R.id.et_username);
        etPaypalID = (EditText) findViewById(R.id.et_paypalID);

        imgGallery = (ImageView) findViewById(R.id.imageUpload);
        imgGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,GALLERY_REQUEST_CODE);
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap photo = ((BitmapDrawable)imgGallery.getDrawable()).getBitmap();
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                photo.compress(Bitmap.CompressFormat.PNG, 100, bos);
                byte[] bArray = bos.toByteArray();


                if (dbManager.ifBlacklistExistsByInstagram(etUsername.getText().toString())){
                    dbManager.updateBlacklist(etUsername.getText().toString(),
                            etPaypalID.getText().toString(), dbManager.checkReportNum(etUsername.getText().toString()) + 1);
                } else {
                    int id = dbManager.getAutoIncrements() + 1;

                    dbManager.insertBlacklist(etUsername.getText().toString(), etPaypalID.getText().toString(), 1, bArray);
                    Blacklist addedUser = new Blacklist(etUsername.getText().toString(), etPaypalID.getText().toString(), 1);
                    addedUser.setId((int) (maxid + 1));
                    blacklistReference.child(etUsername.getText().toString()).setValue(addedUser);
                }
                    etUsername.setText("");
                    etPaypalID.setText("");

                    Toast.makeText(Add_to_Blacklist.this, "Stored Successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Add_to_Blacklist.this, BlacklistInterface.class);
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

}
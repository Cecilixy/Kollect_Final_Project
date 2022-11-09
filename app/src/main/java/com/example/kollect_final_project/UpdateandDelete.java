package com.example.kollect_final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateandDelete extends AppCompatActivity {

    private Post post;
    private EditText etsname, etaname, etgroups, etprice,etstatus;
    private Button btnupdate, btndelete;
    private MySQLiteOpenHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_updel);

        Intent intent = getIntent();
        post = (Post) intent.getSerializableExtra("user");

        databaseHelper = new MySQLiteOpenHelper(this);

        etsname = (EditText) findViewById(R.id.etsname);
        etaname = (EditText) findViewById(R.id.etaname);
        etgroups = (EditText) findViewById(R.id.etgroups);
        etprice = (EditText) findViewById(R.id.etprice);
        etstatus = (EditText) findViewById(R.id.etstatus);
        btndelete = (Button) findViewById(R.id.btndelete);
        btnupdate = (Button) findViewById(R.id.btnupdate);

        etsname.setText(post.getSellerName());
        etaname.setText(post.getArtistName());
        etgroups.setText(post.getGroups());
        etprice.setText(String.valueOf(post.getPrice()));
        etstatus.setText(String.valueOf(post.getStatus()));

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.updatePost(post.getId(),etsname.getText().toString(),etaname.getText().toString(),etgroups.getText().toString(),Integer.parseInt(etprice.getText().toString()),Integer.parseInt(etstatus.getText().toString()));
                Toast.makeText(UpdateandDelete.this, "Updated Successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UpdateandDelete.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.deletePostFromDbByNumber(post.getId());
                Toast.makeText(UpdateandDelete.this, "Deleted Successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UpdateandDelete.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }
}
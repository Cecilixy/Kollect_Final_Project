package com.example.kollect_final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateandDeleteUsers extends AppCompatActivity {

    private User user;
    private EditText etUser_name, etGender, etPassword, etInstagramID;
    private Button btnupdate, btndelete;
    private MySQLiteOpenHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_updel);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");

        databaseHelper = new MySQLiteOpenHelper(this);

        etUser_name = (EditText) findViewById(R.id.etUsername);
        etGender = (EditText) findViewById(R.id.etGender);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etInstagramID = (EditText) findViewById(R.id.etInstagramID);

        btndelete = (Button) findViewById(R.id.btndelete);
        btnupdate = (Button) findViewById(R.id.btnupdate);

        etUser_name.setText(user.getUser_name());
        etGender.setText(user.getGender());
        etPassword.setText(user.getGender());
        etInstagramID.setText(user.getInsta_id());

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.updateUser(user.getId(), etUser_name.getText().toString(), etPassword.getText().toString(), etGender.getText().toString(), etInstagramID.getText().toString());
                Toast.makeText(UpdateandDeleteUsers.this, "Updated Successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UpdateandDeleteUsers.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.deleteUserFromDbByNumber(user.getId());
                Toast.makeText(UpdateandDeleteUsers.this, "Deleted Successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UpdateandDeleteUsers.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }
}
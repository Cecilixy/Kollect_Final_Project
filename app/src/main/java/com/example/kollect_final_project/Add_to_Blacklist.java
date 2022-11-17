package com.example.kollect_final_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Add_to_Blacklist extends AppCompatActivity {

    private MySQLiteOpenHelper dbManager;
    private Button btnSubmit;
    private EditText etUsername, etPaypalID;
    private FirebaseDatabase myFirebasedata;
    private DatabaseReference userReference;
    long maxid = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_blacklist);

        dbManager = new MySQLiteOpenHelper(this);
        userReference = FirebaseDatabase.getInstance().getReference().child("Users");
        userReference.addValueEventListener(new ValueEventListener() {
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

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dbManager.ifBlacklistExistsByInstagram(etUsername.getText().toString())){
                    dbManager.updateBlacklist(etUsername.getText().toString(),
                            etPaypalID.getText().toString(), dbManager.checkReportNum(etUsername.getText().toString()) + 1);
                } else{
                    int id = dbManager.getAutoIncrements() + 1;

                    dbManager.insertBlacklist(etUsername.getText().toString(), etPaypalID.getText().toString(), 1);
                    Blacklist addedUser = new Blacklist(etUsername.getText().toString(), etPaypalID.getText().toString(), 1);
                    addedUser.setId((int)(maxid + 1));
                    userReference.child(etUsername.getText().toString()).setValue(addedUser);
                    etUsername.setText("");
                    etPaypalID.setText("");

                    Toast.makeText(Add_to_Blacklist.this, "Stored Successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Add_to_Blacklist.this, Login.class);
                    startActivity(intent);
                }



            }
        });

    }
}
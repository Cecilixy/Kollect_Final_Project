package com.example.kollect_final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateandDelete extends AppCompatActivity {

    private Artist artist;
    private EditText etname, etgroups, etprice;
    private Button btnupdate, btndelete;
    private MySQLiteOpenHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateand_delete);

        Intent intent = getIntent();
        artist = (Artist) intent.getSerializableExtra("user");

        databaseHelper = new MySQLiteOpenHelper(this);

        etname = (EditText) findViewById(R.id.etname);
        etgroups = (EditText) findViewById(R.id.etgroups);
        etprice = (EditText) findViewById(R.id.etprice);
        btndelete = (Button) findViewById(R.id.btndelete);
        btnupdate = (Button) findViewById(R.id.btnupdate);

        etname.setText(artist.getName());
        etgroups.setText(artist.getGroups());
        etprice.setText(String.valueOf(artist.getPrice()));

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.updateArtist(artist.getId(),etname.getText().toString(),etgroups.getText().toString(),Integer.parseInt(etprice.getText().toString()));
                Toast.makeText(UpdateandDelete.this, "Updated Successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UpdateandDelete.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.deleteArtistFromDbByNumber(artist.getId());
                Toast.makeText(UpdateandDelete.this, "Deleted Successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UpdateandDelete.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }
}
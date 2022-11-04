package com.example.kollect_final_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    RequestQueue requestQueue;

    Connection connect;
    String connectionRes = "";

    private final String Base_Url = "http://10.239.89.53/Kollectdata/getArtist.php";
    Button bt;
    TextView tx1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tx1 = (TextView) findViewById(R.id.textView);
        bt = findViewById(R.id.button);
        bt.setOnClickListener(this);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
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
    public void onClick(View view){
        if (view.getId() == R.id.button){
            StringRequest stringRequest = new StringRequest(Request.Method.POST,Base_Url,
                    response -> tx1.setText(response),
                    error ->  Log.w("Error",error.getMessage())){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError{
                    Map<String, String> params = new HashMap<>();
                    params.put("name", "Jenny");
                    params.put("Groups", "Card");
                    params.put("price", "100");
                    return params;
                }

            };
            requestQueue = Volley.newRequestQueue(MainActivity.this);
            requestQueue.add(stringRequest);

        }
    }
//    public void GetTextFromSql(View v){
//        TextView tx1 = (TextView) findViewById(R.id.texttxt);
//
//        try {
//            SqlConnection conHelper = new SqlConnection();
//            connect = conHelper.connectionClass();
//            if (connect != null){
//                String query = "Select * from username";
//                Statement st = connect.createStatement();
//                ResultSet rs = st.executeQuery(query);
//
//                while (rs.next()){
//                    tx1.setText(rs.getString(1));
//                }
//            }
//            else{
//                connectionRes = "Check Connection";
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//    public void getUser(){
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, Base_Url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            TextView tx1 = (TextView) findViewById(R.id.texttxt);
//                            JSONArray array = new JSONArray(response);
//                            for (int i = 0; i < array.length();i++){
//                                JSONObject object = array.getJSONObject(i);
//
//                                String name = object.getString("name");
//                                String icon = object.getString("icon");
//                                double posts = object.getDouble("posts");
//                                double premium = object.getDouble("premium");
//                                String instalink = object.getString("instalink");
//                                String FavArtist = object.getString("FavArtist");
//                                String FacGroup = object.getString("FavGroup");
//
//
//                                Log.w("Error",name);
//                                tx1.setText(name);
//                            }
//                        }catch (Exception e){
//
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.w("Error",error.getMessage());
//            }
//        });
//        Volley.newRequestQueue(MainActivity.this).add(stringRequest);
//    }


}
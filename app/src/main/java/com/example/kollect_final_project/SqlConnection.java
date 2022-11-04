package com.example.kollect_final_project;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class SqlConnection {
    Connection con;
    String ip,uname,port,database,pass;

    public Connection connectionClass(){
        ip = "127.0.0.1";
        database = "Kollectdata";
        uname = "root";
        port = "3306";
        pass = "";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connect = null;
        String connectUrl = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/Kollectdata","root","");


        }catch (Exception ex){
            Log.w("Error",ex.getMessage());
        }
        return connect;
    }

}

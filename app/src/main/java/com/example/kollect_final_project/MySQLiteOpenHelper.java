package com.example.kollect_final_project;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME="KollectData.db";//数据库名字
    private static final String TABLE_NAME_POSTS="Post";//表的名字
    private static final String TABLE_NAME_USER="User";//表的名字
    private static final String TABLE_NAME_BLACKLIST="Blacklist";//表的名字
    private static final String KEY_ID = "id";

    public MySQLiteOpenHelper(Context context){
        super(context,DB_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {  //onCreate是创建数据库方法
        Log.e("DBOpenHelper", "DBOpenHelperDBOpenHelperDBOpenHelperDBOpenHelper");
        db.execSQL("CREATE TABLE IF NOT EXISTS Post (" +
                "id integer primary key autoincrement, " +
                "seller_name text, " +
                "artist_name text, " +
                "artist_group text, " +
                "price integer, " +
                "status integer, " +
                "user_id integer, " +
                "post_images blob)");

        db.execSQL("CREATE TABLE IF NOT EXISTS User (" +
                "id integer primary key autoincrement, " +
                "username varchar(60) NOT NULL, " +
                "password varchar(60) NOT NULL, " +
                "fav_artist_id integer, " +
                "fav_group_id integer, " +
                "gender varchar(6), " +
                "premium integer DEFAULT 0, " +
                "insta_id varchar(60))");
        db.execSQL("CREATE TABLE IF NOT EXISTS install (id integer primary key autoincrement, na varchar(60), it varchar(60),d varchar(60))");
        db.execSQL("CREATE TABLE IF NOT EXISTS Blacklist (" +
                "id integer primary key autoincrement, " +
                "instagramID varchar(60) not null, " +
                "paypalID varchar(60)," +
                "reportNum integer default 1," +
                "proofImg blob)");
    }

    //这个方法是数据库升级的时候使用到的，因为我没有用到，所以就没有写
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.e("DBOpenHelper", "onUpgradeonUpgradeonUpgradeonUpgrade");
        db.execSQL("DROP TABLE IF EXISTS Post");
        db.execSQL("DROP TABLE IF EXISTS User");
        db.execSQL("DROP TABLE IF EXISTS install");
        db.execSQL("DROP TABLE IF EXISTS smslist");
        onCreate(db);
    }

    public void insertPost(String sname,String aname, String agroup, int price, int status, int userID, byte[] img){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("seller_name",sname);
        values.put("artist_name",aname);
        values.put("artist_group",agroup);
        values.put("price",price);
        values.put("status",status);
        values.put("user_id", userID);
        values.put("post_images", img);

        db.insert(TABLE_NAME_POSTS,null,values);

    }
    //根据学号删除信息 防止有重名的同学
    public void deletePostFromDbByNumber(int number){
        SQLiteDatabase db=getWritableDatabase();
        //返回的是删除的条数
        db.delete(TABLE_NAME_POSTS, KEY_ID + " = ?",new String[]{String.valueOf(number)});
    }
    //修改数据
    public void updatePost(int id,String sname,String aname, String agroup, int price, int status, int userID){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("seller_name",sname);
        values.put("artist_name",aname);
        values.put("artist_group",agroup);
        values.put("price",price);
        values.put("status",status);
        values.put("user_id",userID);
        //依旧是根据学号改
        db.update(TABLE_NAME_POSTS, values, KEY_ID + " = ?", new String[]{String.valueOf(id)});
    }
    //查询数据
    public ArrayList<Post> selectPost(int number){
        SQLiteDatabase db=getWritableDatabase();
        ArrayList<Post> postList =new ArrayList<>();
        Cursor cursor=db.query(TABLE_NAME_POSTS,null,"id like ?",new String[] {String.valueOf(number)},null,null,null);
        if(cursor!=null){
            while (cursor.moveToNext()){
                int sname1=cursor.getColumnIndex("seller_name");
                String sname= cursor.getString(sname1);
                int aname1=cursor.getColumnIndex("artist_name");
                String aname= cursor.getString(aname1);
                int agroup1=cursor.getColumnIndex("artist_group");
                String agroup= cursor.getString(agroup1);
                int price1=cursor.getColumnIndex("price");
                int price= cursor.getInt(price1);
                int status1=cursor.getColumnIndex("status");
                int status= cursor.getInt(status1);
                int userID1=cursor.getColumnIndex("user_id");
                int userID= cursor.getInt(userID1);
                int images1 = cursor.getColumnIndex("post_images");
                byte[] images = cursor.getBlob(images1);


                Post post =new Post();
                post.setSellerName(sname);
                post.setArtistname(aname);
                post.setStatus(status);
                post.setGroups(agroup);
                post.setPrice(price);
                post.setUserID(userID);
                post.setImages(images);


                postList.add(post);
            }
            cursor.close();
        }
        return postList;
    }

    @SuppressLint("Range")
    public ArrayList<Post> getAllPosts( ) {
        String sqlQuery = "select * from " + TABLE_NAME_POSTS;

        SQLiteDatabase db = this.getWritableDatabase( );
        Cursor cursor = db.rawQuery( sqlQuery, null );

        ArrayList<Post> posts = new ArrayList<Post>( );
        while( cursor.moveToNext( ) ) {
            Post post
                    = new Post();
            post.setId(cursor.getInt(cursor.getColumnIndex("id")));
            post.setSellerName(cursor.getString(cursor.getColumnIndex("seller_name")));
            post.setArtistname(cursor.getString(cursor.getColumnIndex("artist_name")));
            post.setGroups(cursor.getString(cursor.getColumnIndex("artist_group")));
            post.setStatus(cursor.getInt(cursor.getColumnIndex("status")));
            post.setPrice(cursor.getInt(cursor.getColumnIndex("price")));
            post.setUserID(cursor.getInt(cursor.getColumnIndex("user_id")));
            post.setImages(cursor.getBlob(cursor.getColumnIndex("post_images")));

            posts.add(post);
        }
        db.close( );
        return posts;
    }

    /*


        -------------------
        USER FUNCTIONALITY
        -------------------



     */


    //添加数据
    public void insertUser(String user_name, String password, String gender, String instagram_id){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", user_name);
        values.put("password", password);
        values.put("gender", gender);
        values.put("insta_id", instagram_id);
        db.insert(TABLE_NAME_USER,null,values);
    }
    //根据学号删除信息 防止有重名的同学
    public void deleteUserFromDbByNumber(int number){
        SQLiteDatabase db=getWritableDatabase();
        //返回的是删除的条数
        db.delete(TABLE_NAME_USER, KEY_ID + " = ?",new String[]{String.valueOf(number)});
    }
    //修改数据
    public void updateUser(int id, String user_name, String password, String gender, String instagram_id){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", user_name);
        values.put("password", password);
        values.put("gender", gender);
        values.put("insta_id", instagram_id);
        //依旧是根据学号改
        db.update(TABLE_NAME_USER, values, KEY_ID + " = ?", new String[]{String.valueOf(id)});
    }
    //查询数据
    public ArrayList<User> selectUser(int number){
        SQLiteDatabase db=getWritableDatabase();
        ArrayList<User> artistList=new ArrayList<>();
        Cursor cursor=db.query(TABLE_NAME_USER,null,"id like ?",new String[] {String.valueOf(number)},null,null,null);
        if(cursor!=null){
            while (cursor.moveToNext()){
                int user_name=cursor.getColumnIndex("username");
                String user_name1 = cursor.getString(user_name);
                int password=cursor.getColumnIndex("password");
                String password1= cursor.getString(password);
                int gender=cursor.getColumnIndex("gender");
                String gender1= cursor.getString(gender);
                int insta_id=cursor.getColumnIndex("insta_id");
                String insta_id1= cursor.getString(insta_id);



                User user=new User();
                user.setInsta_id(insta_id1);
                user.setUser_name(user_name1);
                user.setGender(gender1);
                user.setPassword(password1);


                artistList.add(user);
            }
            cursor.close();
        }
        return artistList;
    }

    @SuppressLint("Range")
    public ArrayList<User> getAllUsers( ) {
        String sqlQuery = "select * from " + TABLE_NAME_USER;

        SQLiteDatabase db = this.getWritableDatabase( );
        Cursor cursor = db.rawQuery( sqlQuery, null );

        ArrayList<User> users = new ArrayList<User>( );
        while( cursor.moveToNext( ) ) {
            User user
                    = new User();
            user.setId(cursor.getInt(cursor.getColumnIndex("id")));
            user.setInsta_id(cursor.getString(cursor.getColumnIndex("insta_id")));
            user.setUser_name(cursor.getString(cursor.getColumnIndex("username")));
            user.setGender(cursor.getString(cursor.getColumnIndex("gender")));
            user.setPassword(cursor.getString(cursor.getColumnIndex("password")));


            users.add(user);
        }
        db.close( );
        return users;
    }

//    public long getNextId(String tableName) {
//        SQLiteDatabase db=getWritableDatabase();
//        Cursor c = null;
//        long seq = 0;
//        try {
//            String sql = "select seq from sqlite_sequence where name=?";
//            c = db.rawQuery(sql, new String[] {tableName});
//            if (c.moveToFirst()) {
//                seq = c.getLong(0);
//            }
//        } finally {
//            if (c != null) {
//                c.close();
//            }
//        }
//        return seq + 1;
//    }

    @SuppressLint("Range")
    public int getAutoIncrements(){
        String query = "SELECT MAX(id) AS max_id from " + TABLE_NAME_USER;
        SQLiteDatabase db = this.getWritableDatabase( );

        Cursor cursor = db.rawQuery(query, null);
        int id = 0;
        if (cursor.moveToFirst())
        {
            do
            {
                id = cursor.getInt(0);
            } while(cursor.moveToNext());
        }

        cursor.close();
        return id;

    }

    /*


        -------------------
        BLACKLIST FUNCTIONALITY
        -------------------



     */


    public void insertBlacklist(String instagramID, String paypalID, int reportNum){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("instagramID", instagramID);
        values.put("paypalID", paypalID);
        values.put("reportNum", reportNum);

        db.insert(TABLE_NAME_BLACKLIST,null,values);
    }

    public void deleteBlacklistFromDbByNumber(int number){
        SQLiteDatabase db=getWritableDatabase();
        db.delete(TABLE_NAME_BLACKLIST, KEY_ID + " = ?",new String[]{String.valueOf(number)});
    }

    public void updateBlacklist(String instagramID, String paypalID, int reportNum){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("instagramID", instagramID);
        values.put("paypalID", paypalID);
        values.put("reportNum", reportNum);

        db.update(TABLE_NAME_BLACKLIST, values, "instagramID = ?", new String[]{String.valueOf(instagramID)});
    }

    public int checkReportNum (String instagramID){
        SQLiteDatabase db=getWritableDatabase();
        ArrayList<Blacklist> blacklistList=new ArrayList<>();
        Cursor cursor=db.query(TABLE_NAME_BLACKLIST,null,"instagramID like ?",new String[] {instagramID},null,null,null);
        int reportNum1 = 0;
        if(cursor!=null) {
            int iID = cursor.getColumnIndex("instagramID");
            String iID1 = cursor.getString(iID);
            int paypalID = cursor.getColumnIndex("paypalID");
            String paypalID1 = cursor.getString(paypalID);
            int reportNum = cursor.getColumnIndex("reportNum");
            reportNum1 = cursor.getInt(reportNum);
            
        }
        return reportNum1;


    }

    public boolean ifBlacklistExistsByInstagram (String instagramID){
        SQLiteDatabase db = getReadableDatabase();
        String queryString = "SELECT 1 FROM " + TABLE_NAME_BLACKLIST + " WHERE " + instagramID + " = ?";
        Cursor c = db.rawQuery(queryString, new String[]{instagramID});
        boolean result = c.getCount() > 0;
        c.close();
        db.close();
        return result;
    }


    public ArrayList<Blacklist> selectBlacklist(int number){
        SQLiteDatabase db=getWritableDatabase();
        ArrayList<Blacklist> blacklistList=new ArrayList<>();
        Cursor cursor=db.query(TABLE_NAME_BLACKLIST,null,"id like ?",new String[] {String.valueOf(number)},null,null,null);
        if(cursor!=null){
            while (cursor.moveToNext()){
                int instagramID=cursor.getColumnIndex("instagramID");
                String instagramID1 = cursor.getString(instagramID);
                int paypalID = cursor.getColumnIndex("paypalID");
                String paypalID1= cursor.getString(paypalID);
                int reportNum = cursor.getColumnIndex("reportNum");
                int reportNum1 = cursor.getInt(reportNum);


                Blacklist blacklist = new Blacklist();
                blacklist.setInstagramID(instagramID1);
                blacklist.setPaypalID(paypalID1);
                blacklist.setReportNum(reportNum1);



                blacklistList.add(blacklist);
            }
            cursor.close();
        }
        return blacklistList;
    }

    @SuppressLint("Range")
    public ArrayList<Blacklist> getAllBlacklist( ) {
        String sqlQuery = "select * from " + TABLE_NAME_BLACKLIST;

        SQLiteDatabase db = this.getWritableDatabase( );
        Cursor cursor = db.rawQuery( sqlQuery, null );

        ArrayList<Blacklist> blacklists = new ArrayList<Blacklist>( );
        while( cursor.moveToNext( ) ) {
            Blacklist blacklist
                    = new Blacklist();
            blacklist.setId(cursor.getInt(cursor.getColumnIndex("id")));
            blacklist.setInstagramID(cursor.getString(cursor.getColumnIndex("InstagramID")));
            blacklist.setPaypalID(cursor.getString(cursor.getColumnIndex("paypalID")));
            blacklist.setReportNum(cursor.getInt(cursor.getColumnIndex("reportNum")));


            blacklists.add(blacklist);
        }
        db.close( );
        return blacklists;
    }



}
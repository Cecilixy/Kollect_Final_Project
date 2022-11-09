package com.example.kollect_final_project;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME="KollectData.db";//数据库名字
    private static final String TABLE_NAME_POSTS="Post";//表的名字
    private static final String TABLE_NAME_USER="User";//表的名字
    private static final String KEY_ID = "id";

    public MySQLiteOpenHelper(Context context){
        super(context,DB_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {  //onCreate是创建数据库方法
        Log.e("DBOpenHelper", "DBOpenHelperDBOpenHelperDBOpenHelperDBOpenHelper");
        db.execSQL("CREATE TABLE IF NOT EXISTS Post (id integer primary key autoincrement, seller_name text, artist_name text, artist_group text, price integer, status integer)");
        db.execSQL("CREATE TABLE IF NOT EXISTS User (" +
                "id integer primary key autoincrement, " +
                "user_name varchar(60) NOT NULL, " +
                "password varchar(60) NOT NULL, " +
                "fav_artist_id integer, " +
                "fav_group_id integer, " +
                "gender varchar(6), " +
                "premium integer DEFAULT 0, " +
                "insta_id varchar(60))");
        db.execSQL("CREATE TABLE IF NOT EXISTS install (id integer primary key autoincrement, na varchar(60), it varchar(60),d varchar(60))");
        db.execSQL("CREATE TABLE IF NOT EXISTS Blacklist (id integer primary key autoincrement, t varchar(60), st varchar(60),n1 varchar(60),n2 varchar(60),n varchar(60),m varchar(60),a varchar(60))");
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

    public void insertPost(String sname,String aname, String agroup, int price, int status){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("seller_name",sname);
        values.put("artist_name",aname);
        values.put("artist_group",agroup);
        values.put("price",price);
        values.put("status",status);
        db.insert(TABLE_NAME_POSTS,null,values);
    }
    //根据学号删除信息 防止有重名的同学
    public void deletePostFromDbByNumber(int number){
        SQLiteDatabase db=getWritableDatabase();
        //返回的是删除的条数
        db.delete(TABLE_NAME_POSTS, KEY_ID + " = ?",new String[]{String.valueOf(number)});
    }
    //修改数据
    public void updatePost(int id,String sname,String aname, String agroup, int price, int status){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("seller_name",sname);
        values.put("artist_name",aname);
        values.put("artist_group",agroup);
        values.put("price",price);
        values.put("status",status);
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


                Post post =new Post();
                post.setSellerName(sname);
                post.setArtistname(aname);
                post.setStatus(status);
                post.setGroups(agroup);
                post.setPrice(price);


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
//    public void insertUser(String user_name, String password, String gender, String instagram_id){
//        SQLiteDatabase db = getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put("user_name", user_name);
//        values.put("password", password);
//        values.put("gender", gender);
//        values.put("insta_id", instagram_id);
//        db.insert(TABLE_NAME_USER,null,values);
//    }
//    //根据学号删除信息 防止有重名的同学
//    public void deleteUserFromDbByNumber(int number){
//        SQLiteDatabase db=getWritableDatabase();
//        //返回的是删除的条数
//        db.delete(TABLE_NAME_USER, KEY_ID + " = ?",new String[]{String.valueOf(number)});
//    }
//    //修改数据
//    public void updateUser(int id, String user_name, String password, String gender, String instagram_id){
//        SQLiteDatabase db=getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put("user_name", user_name);
//        values.put("password", password);
//        values.put("gender", gender);
//        values.put("insta_id", instagram_id);
//        //依旧是根据学号改
//        db.update(TABLE_NAME_USER, values, KEY_ID + " = ?", new String[]{String.valueOf(id)});
//    }
//    //查询数据
//    public ArrayList<Artist> selectUser(int number){
//        SQLiteDatabase db=getWritableDatabase();
//        ArrayList<Artist> artistList=new ArrayList<>();
//        Cursor cursor=db.query(TABLE_NAME_ARTIST,null,"id like ?",new String[] {String.valueOf(number)},null,null,null);
//        if(cursor!=null){
//            while (cursor.moveToNext()){
//                int name1=cursor.getColumnIndex("name");
//                String name= cursor.getString(name1);
//                int groups1=cursor.getColumnIndex("groups");
//                String groups= cursor.getString(groups1);
//                int price1=cursor.getColumnIndex("price");
//                int price= cursor.getInt(price1);
//
//
//                Artist artist=new Artist();
//                artist.setName(name);
//                artist.setGroups(groups);
//                artist.setPrice(price);
//
//
//                artistList.add(artist);
//            }
//            cursor.close();
//        }
//        return artistList;
//    }
//
//    @SuppressLint("Range")
//    public ArrayList<Artist> getAllArtists( ) {
//        String sqlQuery = "select * from " + TABLE_NAME_ARTIST;
//
//        SQLiteDatabase db = this.getWritableDatabase( );
//        Cursor cursor = db.rawQuery( sqlQuery, null );
//
//        ArrayList<Artist> artists = new ArrayList<Artist>( );
//        while( cursor.moveToNext( ) ) {
//            Artist artist
//                    = new Artist();
//            artist.setId(cursor.getInt(cursor.getColumnIndex("id")));
//            artist.setName(cursor.getString(cursor.getColumnIndex("name")));
//            artist.setGroups(cursor.getString(cursor.getColumnIndex("groups")));
//            artist.setPrice(cursor.getInt(cursor.getColumnIndex("price")));
//
//            artists.add(artist);
//        }
//        db.close( );
//        return artists;
//    }


}
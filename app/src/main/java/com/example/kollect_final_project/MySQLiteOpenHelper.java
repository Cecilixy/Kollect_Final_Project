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
    private static final String KEY_ID = "id";
    //创建表
    private static final String CREATE_TABLE_SQL="create table "+TABLE_NAME_POSTS +"(id integer primary key autoincrement,name text,groups text,price integer )";

    public MySQLiteOpenHelper(Context context){
        super(context,DB_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {  //onCreate是创建数据库方法
        Log.e("DBOpenHelper", "DBOpenHelperDBOpenHelperDBOpenHelperDBOpenHelper");
        db.execSQL("CREATE TABLE IF NOT EXISTS Artist (id integer primary key autoincrement, name text,groups text,price integer)");
        db.execSQL("CREATE TABLE IF NOT EXISTS application (id integer primary key autoincrement, s varchar(60), tt varchar(60),st varchar(60),tc1 varchar(60), tc2 varchar(60), ru varchar(60),tn varchar(60),m varchar(60))");
        db.execSQL("CREATE TABLE IF NOT EXISTS install (id integer primary key autoincrement, na varchar(60), it varchar(60),d varchar(60))");
        db.execSQL("CREATE TABLE IF NOT EXISTS smslist (id integer primary key autoincrement, t varchar(60), st varchar(60),n1 varchar(60),n2 varchar(60),n varchar(60),m varchar(60),a varchar(60))");
    }

    //这个方法是数据库升级的时候使用到的，因为我没有用到，所以就没有写
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.e("DBOpenHelper", "onUpgradeonUpgradeonUpgradeonUpgrade");
        db.execSQL("DROP TABLE IF EXISTS Artist");
        db.execSQL("DROP TABLE IF EXISTS application");
        db.execSQL("DROP TABLE IF EXISTS install");
        db.execSQL("DROP TABLE IF EXISTS smslist");
        onCreate(db);
    }

//    public void addArtist(String name, String groups, int price) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        //adding user name in users table
//        ContentValues values = new ContentValues();
//        values.put("name", name);
//        // db.insert(TABLE_USER, null, values);
//        long id = db.insertWithOnConflict(TABLE_NAME_ARTIST, null, values, SQLiteDatabase.CONFLICT_IGNORE);
//
//        //adding user hobby in users_hobby table
//        ContentValues valuesHobby = new ContentValues();
//        valuesHobby.put("id", id);
//        valuesHobby.put("groups", groups);
//        db.insert(TABLE_NAME_ARTIST, null, valuesHobby);
//
//        //adding user city in users_city table
//        ContentValues valuesCity = new ContentValues();
//        valuesCity.put("id", id);
//        valuesCity.put("price", price);
//        db.insert(TABLE_NAME_ARTIST, null, valuesCity);
//    }
//    @SuppressLint("Range")
//    public ArrayList<Artist> getAllArtists() {
//        ArrayList<Artist> userModelArrayList = new ArrayList<Artist>();
//
//        String selectQuery = "SELECT  * FROM " + TABLE_NAME_ARTIST;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(selectQuery, null);
//        // looping through all rows and adding to list
//        if (c.moveToFirst()) {
//            do {
//                Artist userModel = new Artist();
//                userModel.setId(c.getInt(c.getColumnIndex("id")));
//                userModel.setName(c.getString(c.getColumnIndex("name")));
//
//                //getting user hobby where id = id from user_hobby table
//                String selectHobbyQuery = "SELECT  * FROM " + TABLE_NAME_ARTIST + " WHERE " + "id" + " = " + userModel.getId();
//                Log.d("oppp", selectHobbyQuery);
//                //SQLiteDatabase dbhobby = this.getReadableDatabase();
//                Cursor cgroups = db.rawQuery(selectHobbyQuery, null);
//
//                if (cgroups.moveToFirst()) {
//                    do {
//                        userModel.setGroups(cgroups.getString(cgroups.getColumnIndex("groups")));
//                    } while (cgroups.moveToNext());
//                }
//
//                //getting user city where id = id from user_city table
//                String selectCityQuery = "SELECT  * FROM " + TABLE_NAME_ARTIST + " WHERE " + "id" + " = " + userModel.getId();
//                ;
//                //SQLiteDatabase dbCity = this.getReadableDatabase();
//                Cursor cCity = db.rawQuery(selectCityQuery, null);
//
//                if (cCity.moveToFirst()) {
//                    do {
//                        userModel.setPrice(cCity.getInt(cCity.getColumnIndex("price")));
//                    } while (cCity.moveToNext());
//                }
//
//                // adding to Students list
//                userModelArrayList.add(userModel);
//            } while (c.moveToNext());
//        }
//        return userModelArrayList;
//    }
//    public void updateUser(int id, String name, String hobby, String city) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        // updating name in users table
//        ContentValues values = new ContentValues();
//        values.put("name", name);
//        db.update(TABLE_NAME_ARTIST, values, "id" + " = ?", new String[]{String.valueOf(id)});
//
//        // updating hobby in users_hobby table
//        ContentValues valuesHobby = new ContentValues();
//        valuesHobby.put("groups", hobby);
//        db.update(TABLE_NAME_ARTIST, valuesHobby, "id" + " = ?", new String[]{String.valueOf(id)});
//
//        // updating city in users_city table
//        ContentValues valuesCity = new ContentValues();
//        valuesCity.put("price", city);
//        db.update(TABLE_NAME_ARTIST, valuesCity, "id" + " = ?", new String[]{String.valueOf(id)});
//    }
//    public void deleteUSer(int id) {
//
//        // delete row in students table based on id
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        //deleting from users table
//        db.delete(TABLE_USER, KEY_ID + " = ?",new String[]{String.valueOf(id)});
//
//        //deleting from users_hobby table
//        db.delete(TABLE_USER_HOBBY, KEY_ID + " = ?", new String[]{String.valueOf(id)});
//
//        //deleting from users_city table
//        db.delete(TABLE_USER_CITY, KEY_ID + " = ?",new String[]{String.valueOf(id)});
//    }
//

    //添加数据
    public void insertArtist(String name,String groups,int price){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("groups",groups);
        values.put("price",price);
        db.insert(TABLE_NAME_POSTS,null,values);
    }
    //根据学号删除信息 防止有重名的同学
    public void deleteArtistFromDbByNumber(int number){
        SQLiteDatabase db=getWritableDatabase();
        //返回的是删除的条数
        db.delete(TABLE_NAME_POSTS, KEY_ID + " = ?",new String[]{String.valueOf(number)});
    }
    //修改数据
    public void updateArtist(int id,String name,String groups,int price){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("groups",groups);
        values.put("price",price);
        //依旧是根据学号改
        db.update(TABLE_NAME_POSTS, values, KEY_ID + " = ?", new String[]{String.valueOf(id)});
    }
    //查询数据
    public ArrayList<Post> selectArtist(int number){
        SQLiteDatabase db=getWritableDatabase();
        ArrayList<Post> postList =new ArrayList<>();
        Cursor cursor=db.query(TABLE_NAME_POSTS,null,"id like ?",new String[] {String.valueOf(number)},null,null,null);
        if(cursor!=null){
            while (cursor.moveToNext()){
                int name1=cursor.getColumnIndex("name");
                String name= cursor.getString(name1);
                int groups1=cursor.getColumnIndex("groups");
                String groups= cursor.getString(groups1);
                int price1=cursor.getColumnIndex("price");
                int price= cursor.getInt(price1);


                Post post =new Post();
                post.setName(name);
                post.setGroups(groups);
                post.setPrice(price);


                postList.add(post);
            }
            cursor.close();
        }
        return postList;
    }

    @SuppressLint("Range")
    public ArrayList<Post> getAllArtists( ) {
        String sqlQuery = "select * from " + TABLE_NAME_POSTS;

        SQLiteDatabase db = this.getWritableDatabase( );
        Cursor cursor = db.rawQuery( sqlQuery, null );

        ArrayList<Post> posts = new ArrayList<Post>( );
        while( cursor.moveToNext( ) ) {
            Post post
                    = new Post();
            post.setId(cursor.getInt(cursor.getColumnIndex("id")));
            post.setName(cursor.getString(cursor.getColumnIndex("name")));
            post.setGroups(cursor.getString(cursor.getColumnIndex("groups")));
            post.setPrice(cursor.getInt(cursor.getColumnIndex("price")));

            posts.add(post);
        }
        db.close( );
        return posts;
    }
}
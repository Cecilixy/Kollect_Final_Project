package com.example.kollect_final_project;

import android.content.Context;
import android.database.Cursor;

public class DatabaseService {
    private MySQLiteOpenHelper dbOpenHelper;

    public DatabaseService(Context context) {
        dbOpenHelper = new MySQLiteOpenHelper(context);
    }

    public void dropTable(String taleName) {
        dbOpenHelper.getWritableDatabase().execSQL(
                "DROP TABLE IF EXISTS " + taleName);

    }


    public void closeDatabase(String DatabaseName) {
        dbOpenHelper.getWritableDatabase().close();

    }
    public void createArtistTable() {
        String sql = "CREATE TABLE IF NOT EXISTS Artist (id integer primary key autoincrement, name text,groups text,price integer)";
        dbOpenHelper.getWritableDatabase().execSQL(sql);
    }

    public void saveArtistInfo(Post post) {
        dbOpenHelper.getWritableDatabase().execSQL(
                "insert into config (name, groups, price) values(?,?,?)",
                new Object[] { post.getName(), post.getGroups(),
                        post.getPrice()});
    }

    public void updateConfigInfo(Post post) {
        dbOpenHelper.getWritableDatabase().execSQL(
                "update config set name=?, groups=?, price=? where id=?",
                new Object[] { post.getName(), post.getGroups(),
                        post.getPrice() });
    }


    public void deleteItemData(String tableName, Integer id) {
        dbOpenHelper.getWritableDatabase()
                .execSQL("delete from " + tableName + " where id=?",
                        new Object[] { id });
    }
    public Post findArtistInfo(Integer id) {
        Cursor cursor = dbOpenHelper.getWritableDatabase().rawQuery(
                "select id,na,it,d from install where id=?",
                new String[] { String.valueOf(id) });
        if (cursor.moveToNext()) {
            Post post = new Post();


            return post;
        }
        return null;
    }

    public long getDataCount(String tableName) {
        Cursor cursor = dbOpenHelper.getReadableDatabase().rawQuery(
                "select count(*) from " + tableName, null);
        cursor.moveToFirst();
        return cursor.getLong(0);
    }

    public void close() {
        dbOpenHelper.close();
    }
}

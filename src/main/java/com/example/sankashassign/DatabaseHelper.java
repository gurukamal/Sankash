package com.example.sankashassign;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    static String name="database";
    static int version=1;
    String createTable= "CREATE TABLE if not exists 'detail' ( 'username' TEXT  NOT NULL UNIQUE ,'password' TEXT,"+
            " 'type' TEXT )";

    public DatabaseHelper(@Nullable Context context) {
        super(context, name, null, version);
        getWritableDatabase().execSQL(createTable);

    }
    public void insertData(ContentValues contentValues){
        getWritableDatabase().insert("detail","",contentValues);
    }
    public boolean isValid(String username,String password) {
        String sqlQuery = "Select count(*) from detail where username='" + username + "' and password='" + password + "'";
        SQLiteStatement statement = getReadableDatabase().compileStatement(sqlQuery);
        long l = statement.simpleQueryForLong();
        statement.close();
        if (l == 1) {
            return true;
        } else {
            return false;
        }
    }


    public Cursor  viewData(String username,String password,String type){
        String uName='"'+username+'"';
        String pName='"'+password+'"';
        String typee='"'+type+'"';


        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select * from 'detail' where username="+uName,null);
        cursor.getColumnIndex("type");
        return cursor;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

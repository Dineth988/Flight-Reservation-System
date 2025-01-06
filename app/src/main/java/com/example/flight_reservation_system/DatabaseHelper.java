package com.example.flight_reservation_system;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String dbName = "frsDatabase";
    private static final int dbVersion = 1;

    DatabaseHelper (Context context) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE Users (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL, " +
                "email TEXT, " +
                "username TEXT NOT NULL, " +
                "password TEXT NOT NULL)");

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS Users");
        onCreate(db);
    }
    public boolean addUsers(String name, String email, String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("email", email);
        values.put("username", username);
        values.put("password", password);
        long results = db.insert("users",null, values);
        db.close();
        return results != -1;
    }
}

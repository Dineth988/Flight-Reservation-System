package com.example.flight_reservation_system;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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

        db.execSQL("CREATE TABLE HotelInformations (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "hotelName TEXT NOT NULL, " +
                "location TEXT NOT NULL, " +
                "contactNumber TEXT NOT NULL, " +
                "email TEXT, " +
                "rating REAL, " +
                "totalRooms INTEGER NOT NULL, " +
                "description TEXT)");

        db.execSQL("CREATE TABLE FlightBookings (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "flightNumber INTEGER, " +
                "passengerName TEXT NOT NULL, " +
                "passengerContactNumber TEXT, " +
                "passengerEmail TEXT, " +
                "departureDate TEXT NOT NULL, " +
                "arrivalDate TEXT NOT NULL, " +
                "seatClass TEXT, " +
                "seatNumber TEXT, " +
                "totalAmount REAL, " +
                "bookingStatus TEXT)");


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
    public boolean verifyUser(String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Users WHERE username = ? AND password = ?",
                                     new String[]{username,password});
        boolean isValid = cursor.getCount() > 0;
        cursor.close();
        return isValid;
    }
    public Cursor getUserDetail(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM Users WHERE username = ?",
                new String[]{username});

    }
    public void insertHotelInformation(String hotelName, String location, String contactNumber,
                                       String email, double rating, int totalRooms, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("hotelName", hotelName);
        values.put("location", location);
        values.put("contactNumber", contactNumber);
        values.put("email", email);
        values.put("rating", rating);
        values.put("totalRooms", totalRooms);
        values.put("description", description);

        // Insert the data into the table
        long result = db.insert("HotelInformations", null, values);
        db.close();
    }
    public Cursor readHotelData(){
        SQLiteDatabase db = this.getReadableDatabase();

        return db.rawQuery("SELECT * FROM HotelInformations",null);
    }
    public Cursor readBookings(){
        SQLiteDatabase db = this.getReadableDatabase();
        return  db.rawQuery("SELECT * FROM FlightBookings",null);
    }
    public void insertBookingInfo(int flightNumber, String passengerName, String passengerContactNumber,
                                  String passengerEmail, String departureDate, String arrivalDate,
                                  String seatClass, String seatNumber, double totalAmount, String bookingStatus) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("flightNumber", flightNumber);
        values.put("passengerName", passengerName);
        values.put("passengerContactNumber", passengerContactNumber);
        values.put("passengerEmail", passengerEmail);
        values.put("departureDate", departureDate);
        values.put("arrivalDate", arrivalDate);
        values.put("seatClass", seatClass);
        values.put("seatNumber", seatNumber);
        values.put("totalAmount", totalAmount);
        values.put("bookingStatus", bookingStatus);

        // Insert the data into the table
        long result = db.insert("FlightBookings", null, values);
        db.close();
    }

}

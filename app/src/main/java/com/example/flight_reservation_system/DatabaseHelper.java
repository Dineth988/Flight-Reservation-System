package com.example.flight_reservation_system;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String dbName = "frsDatabase";
    private static final int dbVersion = 1;

    private static final String TABLE_FLIGHTS = "Flights";
    private static final String COL_ID = "ID";
    private static final String COL_FROM = "FromCity";
    private static final String COL_TO = "ToCity";
    private static final String COL_DEPARTURE = "DepartureDate";
    private static final String COL_RETURN = "ReturnDate";
    private static final String COL_PRICE = "Price";

    public static final String TABLE_DOCUMENTS = "documents";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_FILE_PATH = "file_path";
    public static final String COLUMN_THUMBNAIL_PATH = "thumbnail_path";

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


        // Create Flights Table
        String createTable = "CREATE TABLE " + TABLE_FLIGHTS + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_FROM + " TEXT, " +
                COL_TO + " TEXT, " +
                COL_DEPARTURE + " TEXT, " +
                COL_RETURN + " TEXT, " +
                COL_PRICE + " REAL)";
        db.execSQL(createTable);

        String createDocumentTable = "CREATE TABLE " + TABLE_DOCUMENTS + " (" +
                COLUMN_ID + " TEXT PRIMARY KEY, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_FILE_PATH + " TEXT, " +
                COLUMN_THUMBNAIL_PATH + " TEXT)";
        db.execSQL(createDocumentTable);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS Users");
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS HotelInformations");
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS FlightBookings");
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS Flights");
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS documents");
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
    public Cursor getFlights(String from, String to, String departureDate) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM Flights WHERE FromCity = ? AND ToCity = ? AND DepartureDate = ?";
        return db.rawQuery(query, new String[]{from, to, departureDate});
    }
    public boolean insertFlightData(String from, String to, String departure, String returnDate, double price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_FROM, from);
        contentValues.put(COL_TO, to);
        contentValues.put(COL_DEPARTURE, departure);
        contentValues.put(COL_RETURN, returnDate);
        contentValues.put(COL_PRICE, price);
        long result = db.insert(TABLE_FLIGHTS, null, contentValues);
        return result != -1;
    }
    public long insertDocument(Document document) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, document.getId());
        values.put(COLUMN_NAME, document.getName());
        values.put(COLUMN_FILE_PATH, document.getFilePath());
        values.put(COLUMN_THUMBNAIL_PATH, document.getThumbnailPath());
        return db.insert(TABLE_DOCUMENTS, null, values);
    }
    public List<Document> getAllDocuments() {
        List<Document> documents = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_DOCUMENTS, null, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String id = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME));
                String filePath = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FILE_PATH));
                String thumbnailPath = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_THUMBNAIL_PATH));

                documents.add(new Document(id, name, filePath, thumbnailPath));
            } while (cursor.moveToNext());
            cursor.close();
        }
        return documents;
    }
}



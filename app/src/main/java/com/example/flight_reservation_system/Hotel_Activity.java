package com.example.flight_reservation_system;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Hotel_Activity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    ArrayList<String> hotelName,location,contactNumber,email,rating,totalRooms,description;
    CustomAdapter customAdapter;
    SQLiteDatabase db;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_hotel);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        databaseHelper = new DatabaseHelper(this);
        databaseHelper.insertHotelInformation("Grand Palace Hotel",
                "123 Main Street, Springfield",
                "123-456-7890",
                "info@grandpalace.com",
                4.7,
                120,
                "A luxurious hotel offering top-notch services.");
        databaseHelper.insertHotelInformation("Ocean View Resort",
                "456 Beach Road, Miami",
                "987-654-3210",
                "info@oceanview.com",
                4.5,
                200,
                "A seaside resort with stunning ocean views and premium amenities.");

        databaseHelper.insertHotelInformation("Mountain Retreat Lodge",
                "789 Hilltop Lane, Denver",
                "456-789-0123",
                "contact@mountainretreat.com",
                4.8,
                75,
                "A peaceful retreat located in the heart of the mountains.");



        displayHotelData();
    }
    public void displayHotelData(){
        databaseHelper = new DatabaseHelper(this);
        hotelName = new ArrayList<>();
        location = new ArrayList<>();
        contactNumber = new ArrayList<>();
        email = new ArrayList<>();
        rating = new ArrayList<>();
        totalRooms = new ArrayList<>();
        description = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);

        customAdapter = new CustomAdapter(this,hotelName,location,contactNumber,email,rating,totalRooms,description);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Cursor cursor = databaseHelper.readHotelData();
        if(cursor.getCount()==0){
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
        else {
            while (cursor.moveToNext()){
                hotelName.add(cursor.getString(0));
                location.add(cursor.getString(1));
                contactNumber.add(cursor.getString(2));
                email.add(cursor.getString(3));
                rating.add(cursor.getString(4));
                totalRooms.add(cursor.getString(5));
                description.add(cursor.getString(6));

            }
        }
    }
    private void filterHotels(String query) {
        ArrayList<String> filteredHotelName = new ArrayList<>();
        ArrayList<String> filteredLocation = new ArrayList<>();
        ArrayList<String> filteredContactNumber = new ArrayList<>();
        ArrayList<String> filteredEmail = new ArrayList<>();
        ArrayList<String> filteredRating = new ArrayList<>();
        ArrayList<String> filteredTotalRooms = new ArrayList<>();
        ArrayList<String> filteredDescription = new ArrayList<>();

        for (int i = 0; i < hotelName.size(); i++) {
            if (location.get(i).toLowerCase().contains(query.toLowerCase())) {
                filteredHotelName.add(hotelName.get(i));
                filteredLocation.add(location.get(i));
                filteredContactNumber.add(contactNumber.get(i));
                filteredEmail.add(email.get(i));
                filteredRating.add(rating.get(i));
                filteredTotalRooms.add(totalRooms.get(i));
                filteredDescription.add(description.get(i));
            }
        }
        customAdapter.updateList(filteredHotelName, filteredLocation, filteredContactNumber, filteredEmail, filteredRating, filteredTotalRooms, filteredDescription);
    }
    }
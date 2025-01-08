

package com.example.flight_reservation_system;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Hotel_Detail_Activity extends AppCompatActivity {

    TextView hotelName, location, contactNumber, email, rating, totalRooms, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_detail);

        hotelName = findViewById(R.id.hotelName);
        location = findViewById(R.id.location);
        contactNumber = findViewById(R.id.contactNumber);
        email = findViewById(R.id.email);
        rating = findViewById(R.id.rating);
        totalRooms = findViewById(R.id.totalRooms);
        description = findViewById(R.id.description);

        // Get the data from the Intent
        String hotelNameValue = getIntent().getStringExtra("hotelName");
        String locationValue = getIntent().getStringExtra("location");
        String contactNumberValue = getIntent().getStringExtra("contactNumber");
        String emailValue = getIntent().getStringExtra("email");
        String ratingValue = getIntent().getStringExtra("rating");
        String totalRoomsValue = getIntent().getStringExtra("totalRooms");
        String descriptionValue = getIntent().getStringExtra("description");

        // Set the data in the TextViews
        hotelName.setText(hotelNameValue);
        location.setText(locationValue);
        contactNumber.setText(contactNumberValue);
        email.setText(emailValue);
        rating.setText(ratingValue);
        totalRooms.setText(totalRoomsValue);
        description.setText(descriptionValue);
    }
}


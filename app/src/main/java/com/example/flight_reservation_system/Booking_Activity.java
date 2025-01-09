package com.example.flight_reservation_system;

import android.database.Cursor;
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

public class Booking_Activity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private ArrayList<String> flightNumber, passengerName, departureDate, bookingStatus;
    private CustomAdapterBooking customAdapterBooking;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_booking);

        // Edge-to-edge handling
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerViewB); // Ensure this matches your RecyclerView's ID in XML
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Insert booking data (for demonstration purposes)
//        insertSampleData();

        // Display booking data
        displayBookingData();
    }

    private void insertSampleData() {
        databaseHelper.insertBookingInfo(
                101,
                "John Doe",
                "123-456-7890",
                "johndoe@example.com",
                "2025-01-15",
                "2025-01-15",
                "Economy",
                "12A",
                250.00,
                "Confirmed"
        );

        databaseHelper.insertBookingInfo(
                102,
                "Jane Smith",
                "987-654-3210",
                "janesmith@example.com",
                "2025-01-20",
                "2025-01-20",
                "Business",
                "5B",
                500.00,
                "Pending"
        );

        databaseHelper.insertBookingInfo(
                103,
                "Mike Johnson",
                "555-123-4567",
                "mikejohnson@example.com",
                "2025-01-25",
                "2025-01-25",
                "First Class",
                "1A",
                1000.00,
                "Cancelled"
        );
    }

    private void displayBookingData() {
        // Initialize data lists
        flightNumber = new ArrayList<>();
        passengerName = new ArrayList<>();
        departureDate = new ArrayList<>();
        bookingStatus = new ArrayList<>();

        // Initialize adapter
        customAdapterBooking = new CustomAdapterBooking(this, flightNumber, passengerName, departureDate, bookingStatus);
        recyclerView.setAdapter(customAdapterBooking);

        // Fetch data from database
        Cursor cursor = databaseHelper.readHotelData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                flightNumber.add(cursor.getString(0)); // Ensure columns match your database schema
                passengerName.add(cursor.getString(1));
                departureDate.add(cursor.getString(2));
                bookingStatus.add(cursor.getString(3));
            }
            customAdapterBooking.notifyDataSetChanged(); // Notify adapter of data changes
        }
    }
}

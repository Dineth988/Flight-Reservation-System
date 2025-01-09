package com.example.flight_reservation_system;

import android.database.Cursor;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Filght extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FlightsAdapter flightsAdapter;
    private List<Flight> flightList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filght);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        flightList = new ArrayList<>();
        flightsAdapter = new FlightsAdapter(flightList);
        recyclerView.setAdapter(flightsAdapter);

        String from = getIntent().getStringExtra("from");
        String to = getIntent().getStringExtra("to");
        String departureDate = getIntent().getStringExtra("departureDate");

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        Cursor cursor = dbHelper.getFlights(from, to, departureDate);

        if (cursor.moveToFirst()) {
            do {
                String returnDate = cursor.getString(cursor.getColumnIndexOrThrow("ReturnDate"));
                double price = cursor.getDouble(cursor.getColumnIndexOrThrow("Price"));
                flightList.add(new Flight(from, to, departureDate, returnDate, price));
            } while (cursor.moveToNext());
        }
        cursor.close();
        flightsAdapter.notifyDataSetChanged();
    }
}
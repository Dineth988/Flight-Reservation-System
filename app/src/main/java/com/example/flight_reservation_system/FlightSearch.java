package com.example.flight_reservation_system;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FlightSearch extends AppCompatActivity {

    private TextView textViewTitle;
    private EditText editTextFrom;
    private EditText editTextTo;
    private EditText editTextDeparture;
    private EditText editTextReturn;
    private ImageView imageViewIcon;
    private Button buttonSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_search);

        // Replace with the actual XML layout file name
        DatabaseHelper dbHelper = new DatabaseHelper(this);

        // Insert sample data into the database
//        populateSampleData(dbHelper);

        // Initialize UI components
        textViewTitle = findViewById(R.id.textView2);
        editTextFrom = findViewById(R.id.editTextText);
        editTextTo = findViewById(R.id.editTextText2);
        editTextDeparture = findViewById(R.id.editTextDate);
        editTextReturn = findViewById(R.id.editTextDate2);
        imageViewIcon = findViewById(R.id.imageView);
        buttonSearch = findViewById(R.id.button);

        // Set up button click listener
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String from = editTextFrom.getText().toString();
                String to = editTextTo.getText().toString();
                String departureDate = editTextDeparture.getText().toString();

                if (from.isEmpty() || to.isEmpty() || departureDate.isEmpty()) {
                    Toast.makeText(FlightSearch.this, "Please fill in all required fields.", Toast.LENGTH_SHORT).show();
                } else {
                    Cursor cursor = dbHelper.getFlights(from, to, departureDate);
                    if (cursor.moveToFirst()) {
                        // Pass flight details to FlightsActivity
                        Intent intent = new Intent(FlightSearch.this, Filght.class);
                        intent.putExtra("from", from);
                        intent.putExtra("to", to);
                        intent.putExtra("departureDate", departureDate);
                        startActivity(intent);
                    } else {
                        Toast.makeText(FlightSearch.this, "No flights found for the given criteria.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



    }

     //Populate the database with sample data
//     private void populateSampleData(DatabaseHelper dbHelper) {
//         dbHelper.insertFlightData("New York", "Los Angeles", "2025-01-15", "2025-01-20", 300.00);
//          dbHelper.insertFlightData("San Francisco", "Seattle", "2025-01-10", "2025-01-15", 200.00);
//        dbHelper.insertFlightData("Boston", "Chicago", "2025-01-12", "2025-01-17", 250.00);
//        dbHelper.insertFlightData("New York", "Los Angeles", "2025-01-15", "2025-01-20", 400.00);
//      }
}
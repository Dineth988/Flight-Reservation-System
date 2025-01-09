package com.example.flight_reservation_system;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Profile_Activity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    TextView textViewUsername,textViewEmail,textViewName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        displayData();
    }
    public void displayData(){
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        textViewUsername = findViewById(R.id.textView8);
        textViewUsername.setText(username);

        databaseHelper = new DatabaseHelper(this);
        Cursor cursor = databaseHelper.getUserDetail(username);

        if (cursor != null && cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));


            textViewEmail = findViewById(R.id.textView16);
            textViewName = findViewById(R.id.textView17);

            textViewName.setText(name);
            textViewEmail.setText(email);

            cursor.close();
        } else {

        }
    }
    public void onClickHotel(View view){
        Intent intent = new Intent(this, Hotel_Activity.class);
        startActivity(intent);
    }
    public void onClickMyBookings(View view){
        Intent intent = new Intent(this, Booking_Activity.class);
        startActivity(intent);
    }
    public void onClickFlights(View view){
        Intent intent = new Intent(this, FlightSearch.class);
        startActivity(intent);
    }
    public void onClickDocument(View view){
        Intent intent = new Intent(this, DocumentStorage.class);
        startActivity(intent);
    }

}
package com.example.flight_reservation_system;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<String> hotelName,location,contactNumber,email,rating,totalRooms,description;
   CustomAdapter(Context context, ArrayList hotelName, ArrayList location, ArrayList contactNumber, ArrayList email, ArrayList rating, ArrayList totalRooms, ArrayList description){
        this.context = context;
        this.hotelName = hotelName;
        this.location = location;
        this.contactNumber = contactNumber;
        this.email = email;
        this.rating = rating;
        this.totalRooms = totalRooms;
        this.description = description;
   }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.myrecycleview, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.hotelNameTxt.setText(String.valueOf(hotelName.get(position)));
        holder.locationTxt.setText(String.valueOf(location.get(position)));

        // Set click listener on each card (item)
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, Hotel_Detail_Activity.class);
            // Pass the hotel details to the next activity using intent extras
            intent.putExtra("hotelName", hotelName.get(position));
            intent.putExtra("location", location.get(position));
            intent.putExtra("contactNumber", contactNumber.get(position));
            intent.putExtra("email", email.get(position));
            intent.putExtra("rating", rating.get(position));
            intent.putExtra("totalRooms", totalRooms.get(position));
            intent.putExtra("description", description.get(position));
            context.startActivity(intent); // Start the HotelDetailActivity
        });
    }

    @Override
    public int getItemCount() {
        return hotelName.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

       TextView hotelNameTxt, locationTxt,contactNumberTxt,emailTxt,ratingTxt,totalRoomsTxt,descriptionTxt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            hotelNameTxt = itemView.findViewById(R.id.tvHotelName);
            locationTxt = itemView.findViewById(R.id.tvHotelLocation);
        }
    }
    public void updateList(ArrayList<String> hotelName, ArrayList<String> location, ArrayList<String> contactNumber,
                           ArrayList<String> email, ArrayList<String> rating, ArrayList<String> totalRooms, ArrayList<String> description) {
        this.hotelName = hotelName;
        this.location = location;
        this.contactNumber = contactNumber;
        this.email = email;
        this.rating = rating;
        this.totalRooms = totalRooms;
        this.description = description;
        notifyDataSetChanged();  // Refresh the RecyclerView
    }


}

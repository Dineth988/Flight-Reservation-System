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

public class CustomAdapterBooking extends RecyclerView.Adapter<CustomAdapterBooking.MyViewHolderBooking> {
   private Context context;
   private ArrayList flightNumber,passengerName,departureDate,bookingStatus;
    CustomAdapterBooking(Context context, ArrayList flightNumber, ArrayList passengerName, ArrayList departureDate, ArrayList bookingStatus){
        this.context = context;
        this.flightNumber = flightNumber;
        this.passengerName = passengerName;
        this.departureDate = departureDate;
        this.bookingStatus = bookingStatus;
    }

    @NonNull
    @Override
    public CustomAdapterBooking.MyViewHolderBooking onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.custom_booking_layout,parent,false);
        return new MyViewHolderBooking(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderBooking holder, int position) {
        holder.flightNumber.setText(String.valueOf(flightNumber.get(position)));
        holder.passengerName.setText(String.valueOf(passengerName.get(position)));
        holder.departureDate.setText(String.valueOf(departureDate.get(position)));
        holder.bookingStatus.setText(String.valueOf(bookingStatus.get(position)));


//        holder.itemView.setOnClickListener(v -> {
//            Intent intent = new Intent(context, Booking_Details_Activity.class);
//
//            // Pass the booking details to the next activity using intent extras
//
//            intent.putExtra("flightNumber", flightNumber.get(position));
//            intent.putExtra("passengerName", passengerName.get(position));
//            intent.putExtra("departureDate", departureDate.get(position));
//            intent.putExtra("bookingStatus", bookingStatus.get(position));
//
//            context.startActivity(intent); // Start the BookingDetailsActivity
//        });

    }


    @Override
    public int getItemCount() {
        return flightNumber.size();
    }

    public class MyViewHolderBooking extends RecyclerView.ViewHolder{
        TextView flightNumber,passengerName,departureDate,bookingStatus;
        public MyViewHolderBooking(@NonNull View itemView) {
            super(itemView);
            flightNumber = itemView.findViewById(R.id.flightNumber);
            passengerName = itemView.findViewById(R.id.passengerName);
            departureDate = itemView.findViewById(R.id.departureDate);
            bookingStatus = itemView.findViewById(R.id.bookingStatus);
        }
    }
}

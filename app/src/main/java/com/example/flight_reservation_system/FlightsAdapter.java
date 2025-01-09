package com.example.flight_reservation_system;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FlightsAdapter extends RecyclerView.Adapter<FlightsAdapter.FlightViewHolder> {

    private List<Flight> flightList;

    public FlightsAdapter(List<Flight> flightList) {
        this.flightList = flightList;
    }

    @NonNull
    @Override
    public FlightViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_flight, parent, false);
        return new FlightViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlightViewHolder holder, int position) {
        Flight flight = flightList.get(position);
        holder.textViewDetails.setText("From: " + flight.getFrom() + "\nTo: " + flight.getTo() +
                "\nDeparture: " + flight.getDepartureDate() + "\nReturn: " + flight.getReturnDate() +
                "\nPrice: $" + flight.getPrice());
    }

    @Override
    public int getItemCount() {
        return flightList.size();
    }

    public static class FlightViewHolder extends RecyclerView.ViewHolder {
        TextView textViewDetails;

        public FlightViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewDetails = itemView.findViewById(R.id.textViewDetails);
        }
    }
}

package com.example.flight_reservation_system;

public class Flight {
    private String from;
    private String to;
    private String departureDate;
    private String returnDate;
    private double price;

    public Flight(String from, String to, String departureDate, String returnDate, double price) {
        this.from = from;
        this.to = to;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.price = price;
    }

    public String getFrom() { return from; }
    public String getTo() { return to; }
    public String getDepartureDate() { return departureDate; }
    public String getReturnDate() { return returnDate; }
    public double getPrice() { return price; }
}

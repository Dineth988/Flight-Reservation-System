package com.example.flight_reservation_system;

public class LocationClass {

    private String name, type, locatedAt;

    public LocationClass(String name, String address){

    }
    public LocationClass(String name, String type, String locatedAt){
        this.name =name;
        this.locatedAt =locatedAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocatedAt() {
        return locatedAt;
    }

    public void setLocatedAt(String locatedAt) {
        this.locatedAt = locatedAt;
    }
}

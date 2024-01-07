package org.example.caballocation;

public class Cab {
    private String id;
    private Location location;

    public Cab(String id, Location location) {
        this.id = id;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public Location getLocation() {
        return location;
    }
}
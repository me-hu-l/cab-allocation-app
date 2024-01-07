package org.example.caballocation;

public class Trip {
    private String id;
    private Location startLocation;
    private Cab assignedCab; // New field to store the assigned cab

    public Trip(String id, Location startLocation) {
        this.id = id;
        this.startLocation = startLocation;
    }

    public String getId() {
        return id;
    }

    public Location getStartLocation() {
        return startLocation;
    }

    public Cab getAssignedCab() {
        return assignedCab;
    }

    public void setAssignedCab(Cab assignedCab) {
        this.assignedCab = assignedCab;
    }
}

package org.example.locationtracking;
import org.example.caballocation.Location;

import java.util.HashMap;
import java.util.Map;

public class GPSLocationTracker implements LocationTracker {
    private Map<String, Location> locations;

    public GPSLocationTracker() {
        this.locations = new HashMap<>();
    }
    @Override
    public void updateLocation(String entityId, Location location) {
        locations.put(entityId, location);
    }

    @Override
    public Location trackLocation(String entityId) {
        return locations.get(entityId);
    }
}
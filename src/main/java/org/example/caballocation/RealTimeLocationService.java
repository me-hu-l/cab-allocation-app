package org.example.caballocation;
import org.example.locationtracking.LocationTracker;

import java.util.Map;

public class RealTimeLocationService {
    private LocationTracker locationTracker;

    private Map<String, Location> cabLocations;
    private Map<String, Location> tripLocations;

    public RealTimeLocationService(LocationTracker locationTracker, Map<String, Location> cabLocations, Map<String, Location> tripLocations) {
        this.locationTracker = locationTracker;
        this.cabLocations = cabLocations;
        this.tripLocations = tripLocations;
    }

    public Location getCabLocation(String cabId) {
        cabLocations.put(cabId, locationTracker.trackLocation(cabId));
        return cabLocations.get(cabId);
    }

    public Location getTripLocation(String tripId) {
        cabLocations.put(tripId, locationTracker.trackLocation(tripId));
        return tripLocations.get(tripId);
    }
}
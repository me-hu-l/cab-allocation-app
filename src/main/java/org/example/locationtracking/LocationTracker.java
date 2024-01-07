package org.example.locationtracking;

import org.example.caballocation.Location;

public interface LocationTracker {
    public void updateLocation(String entityId, Location location);
    Location trackLocation(String entityId);
}


package org.example.locationtracking;

import org.example.caballocation.Location;
import org.junit.Test;

import static org.junit.Assert.*;

public class GPSLocationTrackerTest {
    @Test
    public void testTrackLocation() {
        // Arrange
        GPSLocationTracker locationTracker = new GPSLocationTracker();
        Location expectedLocation = new Location(37.7749, -122.4194);

        // Act
        locationTracker.updateLocation("cab1", expectedLocation);
        Location trackedLocation = locationTracker.trackLocation("cab1");

        // Assert
        assertEquals(expectedLocation, trackedLocation);
    }

    @Test
    public void testTrackLocation_NotFound() {
        // Arrange
        GPSLocationTracker locationTracker = new GPSLocationTracker();

        // Act
        Location trackedLocation = locationTracker.trackLocation("unknownCab");

        // Assert
        assertEquals(null, trackedLocation);
    }
}
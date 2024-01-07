package org.example.cabsearch;

import org.example.caballocation.Cab;
import org.example.caballocation.Location;
import org.example.caballocation.RealTimeLocationService;
import org.example.caballocation.Trip;
import org.example.locationtracking.GPSLocationTracker;
import org.example.locationtracking.LocationTracker;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.*;

import static org.junit.Assert.*;

public class CabSearchServiceTest {
    private CabSearchService cabSearchService;

    @Test
    public void suggestNearbyCabs() {
        // Sample data
        Location employeeLocation = new Location(1.0, 2.0);
        Cab cab1 = new Cab("C1", new Location(1.1, 2.1));
        Cab cab2 = new Cab("C2", new Location(1.2, 2.2));
        Cab cab3 = new Cab("C3", new Location(1.3, 2.3));

        List<Cab> availableCabs = Arrays.asList(cab1, cab2, cab3);

        Trip trip1 = new Trip("T1", new Location(1.4, 2.4));
        Trip trip2 = new Trip("T2", new Location(1.5, 2.5));
        trip1.setAssignedCab(cab1);
        trip2.setAssignedCab(cab2);

        List<Trip> ongoingTrips = Arrays.asList(trip1, trip2);

        Map<String, Location> cabLocations = new HashMap<>();
        cabLocations.put(cab1.getId(), cab1.getLocation());
        cabLocations.put(cab2.getId(), cab2.getLocation());
        cabLocations.put(cab3.getId(), cab3.getLocation());

        Map<String, Location> tripLocations = new HashMap<>();
        tripLocations.put(trip1.getId(), trip1.getStartLocation());
        tripLocations.put(trip2.getId(), trip2.getStartLocation());

        LocationTracker locationTracker = new GPSLocationTracker();

        locationTracker.updateLocation(trip1.getId(), trip1.getStartLocation());
        locationTracker.updateLocation(trip2.getId(), trip2.getStartLocation());
        locationTracker.updateLocation(cab1.getId(), cab1.getLocation());
        locationTracker.updateLocation(cab2.getId(), cab2.getLocation());
        locationTracker.updateLocation(cab3.getId(), cab3.getLocation());

        RealTimeLocationService locationService = new RealTimeLocationService(locationTracker, cabLocations, tripLocations);
        cabSearchService = new CabSearchService(locationService);

        // Test the method
        List<Cab> suggestedCabs = cabSearchService.suggestNearbyCabs(employeeLocation, availableCabs, ongoingTrips);

        // Expected result: cab3 is nearby and available (not engaged in any trip)
        List<Cab> expectedResult = Collections.singletonList(cab3);

        // Assert the result
        assertEquals(expectedResult, suggestedCabs);
    }
}
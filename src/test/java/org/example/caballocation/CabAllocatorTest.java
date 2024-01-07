package org.example.caballocation;

import org.example.locationtracking.GPSLocationTracker;
import org.example.locationtracking.LocationTracker;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CabAllocatorTest {

    @Test
    public void allocateCab() {
        // Create example data for RealTimeLocationService
        Location source = new Location(0, 0);
        Location destination1 = new Location(1, 1);
        Location destination2 = new Location(2, 2);

        // Create a trip and a list of available cabs
        Trip trip = new Trip("trip-1", source);
        Cab cab1 = new Cab("cab-1", destination1);
        Cab cab2 = new Cab("cab-2", destination2);
        List<Cab> availableCabs = List.of(cab1, cab2);

        Map<String, Location> cabLocations = new HashMap<>();
        cabLocations.put(cab1.getId(), destination1);
        cabLocations.put(cab2.getId(), destination2);

        Map<String, Location> tripLocations = new HashMap<>();
        tripLocations.put(trip.getId(), source);

        LocationTracker locationTracker = new GPSLocationTracker();

        locationTracker.updateLocation(trip.getId(), trip.getStartLocation());
        locationTracker.updateLocation(cab1.getId(), cab1.getLocation());
        locationTracker.updateLocation(cab2.getId(), cab2.getLocation());

        RealTimeLocationService locationService = new RealTimeLocationService(locationTracker, cabLocations, tripLocations);

        // Create example data for RoadNetwork

        RoadNetwork roadNetwork = new RoadNetwork();
        roadNetwork.addRoad(source, destination1, 1.0);
        roadNetwork.addRoad(destination1, destination2, 1.0);

        // Create an instance of the CabAllocator with the example data
        CabAllocator cabAllocator = new CabAllocator(locationService, roadNetwork);



        // Call the allocateCab method
        Cab allocatedCab = cabAllocator.allocateCab(trip, availableCabs);

        // Assert that the allocatedCab is not null (meaning a cab was allocated)
        System.out.println(allocatedCab.getLocation().getLatitude() + " " + allocatedCab.getLocation().getLongitude());
        assertNotNull(allocatedCab);

        // You can add more specific assertions based on your example data and expectations
    }
}
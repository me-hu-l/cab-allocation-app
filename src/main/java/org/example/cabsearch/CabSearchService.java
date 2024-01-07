package org.example.cabsearch;

import org.example.caballocation.Cab;
import org.example.caballocation.Location;
import org.example.caballocation.RealTimeLocationService;
import org.example.caballocation.Trip;

import java.util.List;
import java.util.stream.Collectors;

public class CabSearchService {
    private RealTimeLocationService locationService;

    public CabSearchService(RealTimeLocationService locationService) {
        this.locationService = locationService;
    }

    public List<Cab> suggestNearbyCabs(Location employeeLocation, List<Cab> availableCabs, List<Trip> ongoingTrips) {
        // Get real-time locations of available cabs
        List<Location> cabLocations = availableCabs.stream()
                .map(cab -> locationService.getCabLocation(cab.getId()))
                .collect(Collectors.toList());

        // Filter cabs based on proximity to the employee's location and availability (not engaged in trips)
        return availableCabs.stream()
                .filter(cab -> isCabNearbyAndAvailable(employeeLocation, cabLocations, cab, ongoingTrips))
                .collect(Collectors.toList());
    }

    private boolean isCabNearbyAndAvailable(Location employeeLocation, List<Location> cabLocations, Cab cab, List<Trip> ongoingTrips) {
        Location cabLocation = locationService.getCabLocation(cab.getId());

        // Check if the cab is nearby
        boolean isNearby = cabLocations.contains(cabLocation);

        // Check if the cab is not engaged in a trip (available)
        boolean isAvailable = ongoingTrips.stream().noneMatch(trip -> trip.getAssignedCab().equals(cab));

        return isNearby && isAvailable;
    }
}

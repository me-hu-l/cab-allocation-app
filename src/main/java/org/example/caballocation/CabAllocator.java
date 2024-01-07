package org.example.caballocation;

import java.util.List;
import java.util.Map;

public class CabAllocator implements AllocationAlgorithm {
    private RealTimeLocationService locationService;
    private RoadNetwork roadNetwork;

    public CabAllocator(RealTimeLocationService locationService, RoadNetwork roadNetwork) {
        this.locationService = locationService;
        this.roadNetwork = roadNetwork;
    }

    @Override
    public Cab allocateCab(Trip trip, List<Cab> availableCabs) {
        // Get the start location of the trip
        Location tripStartLocation = trip.getStartLocation();

        // Calculate the shortest path distances from the trip start location to all other locations
        Map<Location, Double> distances = roadNetwork.calculateShortestPaths(tripStartLocation);


        // Find the cab with the minimum distance
        Cab bestCab = null;
        double minDistance = Double.MAX_VALUE;

        for (Cab cab : availableCabs) {
            Location cabLocation = locationService.getCabLocation(cab.getId());
            // Retrieve the distance from the map
            Double distance = distances.get(cabLocation);
            // Check if distance is not null and smaller than the current minimum
            if (distance != null && distance < minDistance) {
                minDistance = distance;
                bestCab = cab;
            }
        }
        trip.setAssignedCab(bestCab);

        return bestCab;
    }
}

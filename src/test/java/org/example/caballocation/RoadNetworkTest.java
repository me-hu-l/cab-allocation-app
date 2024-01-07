package org.example.caballocation;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.Map;
import java.util.Optional;

public class RoadNetworkTest {

    @Test
    public void testcalculateShortestPaths() {
        RoadNetwork roadNetwork = new RoadNetwork();

        Location source = new Location(0, 0);
        Location destination1 = new Location(1, 1);
        Location destination2 = new Location(2, 2);

        roadNetwork.addRoad(source, destination1, 5);
        roadNetwork.addRoad(source, destination2, 10);

        Map<Location, Double> distances = roadNetwork.calculateShortestPaths(source);

        assertEquals(0.0, distances.get(source), 0.0001);
        assertEquals(5.0, distances.get(destination1), 0.0001);
        assertEquals(10.0, distances.get(destination2), 0.0001);
    }
}
package org.example.caballocation;

import java.util.*;

// RoadNetwork.java
public class RoadNetwork {
    private Map<Location, List<Road>> adjacencyList;

    public RoadNetwork() {
        this.adjacencyList = new HashMap<>();
    }

    public void addRoad(Location source, Location destination, double distance) {
        // Add a road between source and destination with the given distance
        Road roadForward = new Road(destination, distance);
        Road roadBackward = new Road(source, distance);
        adjacencyList.computeIfAbsent(source, k -> new ArrayList<>()).add(roadForward);
        adjacencyList.computeIfAbsent(destination, k -> new ArrayList<>()).add(roadBackward);
    }

    public Map<Location, Double> calculateShortestPaths(Location source) {
        // Djikstra's algorithm to calculate shortest paths from the source

        Map<Location, Double> distances = new HashMap<>();
        PriorityQueue<NodeDistancePair> priorityQueue = new PriorityQueue<>(Comparator.comparingDouble(pair -> pair.distance));

        distances.put(source, 0.0);
        priorityQueue.offer(new NodeDistancePair(source, 0.0));

        while (!priorityQueue.isEmpty()) {
            Location currentLocation = priorityQueue.poll().location;

            for (Road road : adjacencyList.getOrDefault(currentLocation, Collections.emptyList())) {
                Location neighbor = road.destination;
                double newDistance = distances.get(currentLocation) + road.distance;

                if (!distances.containsKey(neighbor) || newDistance < distances.get(neighbor)) {
                    distances.put(neighbor, newDistance);
                    priorityQueue.offer(new NodeDistancePair(neighbor, newDistance));
                }
            }
        }

        return distances;
    }

    private static class Road {
        Location destination;
        double distance;

        public Road(Location destination, double distance) {
            this.destination = destination;
            this.distance = distance;
        }
    }

    private static class NodeDistancePair {
        Location location;
        double distance;

        public NodeDistancePair(Location location, double distance) {
            this.location = location;
            this.distance = distance;
        }
    }
}
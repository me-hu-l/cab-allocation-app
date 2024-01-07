package org.example.caballocation;

import java.util.List;

public interface AllocationAlgorithm {
    Cab allocateCab(Trip trip, List<Cab> availableCabs);

}
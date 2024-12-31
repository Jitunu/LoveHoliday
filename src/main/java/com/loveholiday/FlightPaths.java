package com.loveholiday;

import java.util.*;

public class FlightPaths {

    private final int[][] costArr;
    private final Map<String, Integer> locationMap;

    public FlightPaths(int[][] costArr, Map<String, Integer> locationMap) {
        if (costArr.length != locationMap.size()) {
            throw new IllegalArgumentException("Costs size must match number of locations.");
        }
        this.costArr = costArr;
        this.locationMap = locationMap;
    }

    public List<String> findFlightCosts(String startLocation, String endLocation) {
        if (!locationMap.containsKey(startLocation) || !locationMap.containsKey(endLocation)) {
            System.err.println("Invalid locations. Please use valid locations: Castle Black, Winterfell, Riverrun, King's Landing.");
            System.exit(1);
        }
        int start = locationMap.get(startLocation);
        int end = locationMap.get(endLocation);
        List<String> paths = new ArrayList<>();
        findPaths(start, end, new ArrayList<>(), 0, paths);
        return paths;
    }

    private void findPaths(int current, int destination, List<String> path, int cost, List<String> results) {
        path.add(getLocationName(current));
        if (current == destination) {
            results.add(String.join(" -> ", path) + ": " + cost);
        } else {
            for (int next = current + 1; next < costArr.length; next++) {
                if (costArr[current][next] > 0) {
                    findPaths(next, destination, new ArrayList<>(path), cost + costArr[current][next], results);
                }
            }
        }
    }

    private String getLocationName(int index) {
        return locationMap.entrySet().stream()
                .filter(entry -> entry.getValue() == index)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }
}

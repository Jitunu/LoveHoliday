package com.loveholiday;

import java.util.*;

/**
 * The {@code FlightPaths} class provides functionality to calculate all possible flight paths and their costs
 * between two locations, based on a flight cost adjacency matrix and a map of location names to indices.
 *
 * <p>This class supports only north-to-south flight paths, as indicated by the adjacency matrix, where a
 * positive cost represents a valid direct flight between locations.</p>
 */
public class FlightPaths {

    private final int[][] costArr;
    private final Map<String, Integer> locationMap;

    /**
     * Constructs a new {@code FlightPaths} instance with the specified cost matrix and location map.
     *
     * @param costArr      a 2D adjacency matrix representing flight costs between locations.
     *                     {@code costArr[i][j]} represents the cost of flying from location {@code i} to {@code j}.
     *                     A value of {@code 0} indicates no direct flight.
     * @param locationMap  a mapping of location names to their indices in the cost matrix.
     * @throws IllegalArgumentException if the size of {@code costArr} does not match the number of locations in {@code locationMap}.
     */
    public FlightPaths(int[][] costArr, Map<String, Integer> locationMap) {
        if (costArr.length != locationMap.size()) {
            throw new IllegalArgumentException("Costs size must match number of locations.");
        }
        this.costArr = costArr;
        this.locationMap = locationMap;
    }

    /**
     * Finds all possible flight paths and their costs between the given start and end locations.
     *
     * @param startLocation the name of the starting location.
     * @param endLocation   the name of the destination location.
     * @return a list of strings, where each string represents a valid flight path in the format:
     *         {@code "Location1 -> Location2 -> ... -> Destination: Cost"}.
     * @throws NullPointerException if either {@code startLocation} or {@code endLocation} is not found in the {@code locationMap}.
     */
    public List<String> findFlightCosts(String startLocation, String endLocation) {

        int start = locationMap.get(startLocation);
        int end = locationMap.get(endLocation);
        List<String> paths = new ArrayList<>();
        findPaths(start, end, new ArrayList<>(), 0, paths);
        return paths;
    }

    /**
     * Recursive helper method to explore all valid flight paths from the current location to the destination.
     *
     * @param current    the index of the current location in the cost matrix.
     * @param destination the index of the destination location in the cost matrix.
     * @param path       a list of location names representing the current path.
     * @param cost       the accumulated cost of the current path.
     * @param results    a list to store all valid paths and their costs.
     */
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

    /**
     * Retrieves the name of the location corresponding to the given index in the cost matrix.
     *
     * @param index the index of the location in the cost matrix.
     * @return the name of the location, or {@code null} if the index is not mapped to any location.
     */
    private String getLocationName(int index) {
        return locationMap.entrySet().stream()
                .filter(entry -> entry.getValue() == index)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }
}

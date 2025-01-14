package com.loveholiday.flight;

import java.util.*;

public class ShortestFlightPaths {

    private final int[][] costArr;
    private final Map<String, Integer> locationMap;

    public ShortestFlightPaths(int[][] costArr, Map<String, Integer> locationMap) {
        if (costArr.length != locationMap.size()) {
            throw new IllegalArgumentException("Costs size must match number of locations.");
        }
        this.costArr = costArr;
        this.locationMap = locationMap;
    }

    /**
     * Finds the shortest flight path and its cost between the given start and end locations.
     *
     * @param startLocation the name of the starting location.
     * @param endLocation   the name of the destination location.
     * @return a string representing the shortest flight path in the format:
     *         {@code "Location1 -> Location2 -> ... -> Destination: Cost"}.
     */
    public String findShortestFlightCost(String startLocation, String endLocation) {

        int start = locationMap.get(startLocation);
        int end = locationMap.get(endLocation);

        // Priority queue for Dijkstra's algorithm
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.cost));
        pq.add(new Node(start, 0, new ArrayList<>()));

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            // Add the current location to the path
            current.path.add(getLocationName(current.index));
//            System.out.println(current.path +" : "+current.cost);
            // If the destination is reached, return the path and cost
            if (current.index == end) {
                return String.join(" -> ", current.path) + ": " + current.cost;
            }

            // Explore neighbors
            for (int next = current.index + 1; next < costArr.length; next++) {
                if (costArr[current.index][next] > 0) {
                    List<String> newPath = new ArrayList<>(current.path);
                    pq.add(new Node(next, current.cost + costArr[current.index][next], newPath));
                }
            }
        }

        // If no path is found
        return "No valid flight paths found.";
    }

    /**
     * Retrieves the name of the location corresponding to the given index in the cost matrix.
     */
    private String getLocationName(int index) {
        return locationMap.entrySet().stream()
                .filter(entry -> entry.getValue() == index)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }

    // Helper class to represent nodes in the priority queue
    private static class Node {
        int index;
        int cost;
        List<String> path;

        Node(int index, int cost, List<String> path) {
            this.index = index;
            this.cost = cost;
            this.path = path;
        }
    }

    public static void main(String[] args) {
        int[][] COSTS = {
                {0, 15, 80, 90},  // Castle Black
                {15, 0, 40, 50},  // Winterfell
                {80, 40, 0, 70},  // Riverrun
                {90, 50, 70, 0}   // King's Landing
        };
        Map<String, Integer> LOCATIONS = new HashMap<>() {{
            put("Castle Black", 0);
            put("Winterfell", 1);
            put("Riverrun", 2);
            put("King's Landing", 3);
        }};
        ShortestFlightPaths flightPaths = new ShortestFlightPaths(COSTS, LOCATIONS);
        System.out.println(flightPaths.findShortestFlightCost("Castle Black", "King's Landing"));
    }
}

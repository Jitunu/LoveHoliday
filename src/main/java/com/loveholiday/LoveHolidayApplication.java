package com.loveholiday;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoveHolidayApplication {

    private static final Map<String, Integer> LOCATIONS = new HashMap<>() {{
        put("Castle Black", 0);
        put("Winterfell", 1);
        put("Riverrun", 2);
        put("King's Landing", 3);
    }};

    private static final int[][] COSTS = {
            {0, 15, 80, 90},
            {0, 0, 40, 50},
            {0, 0, 0, 70},
            {0, 0, 0, 0}
    };

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Please provide Source and Destination location");
            System.exit(1);
        }
        String startLocation = args[0];
        String endLocation = args[1];

        FlightPaths flightPaths = new FlightPaths(COSTS, LOCATIONS);

        List<String> paths = flightPaths.findFlightCosts(startLocation, endLocation);
        if (paths.isEmpty()) {
            System.out.println("No valid flight paths found.");
        } else {
            paths.forEach(System.out::println);
        }
    }
}

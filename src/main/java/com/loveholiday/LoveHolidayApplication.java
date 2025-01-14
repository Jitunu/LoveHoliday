package com.loveholiday;

import com.loveholiday.flight.ShortestFlightPaths;

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
/*

    private static final int[][] COSTS = {
            {0, 15, 80, 90},
            {0, 0, 40, 50},
            {0, 0, 0, 70},
            {0, 0, 0, 0}
    };
*/

    private static final int[][] COSTS = {
            {0, 15, 80, 90},  // Castle Black
            {15, 0, 40, 50},  // Winterfell
            {80, 40, 0, 70},  // Riverrun
            {90, 50, 70, 0}   // King's Landing
    };

    public static void main(String[] args) {
        if(InputValidation.isValidNumOfArgs(args) ) {
            String startLocation = args[0];
            String endLocation = args[1];
            if (InputValidation.isValidInputLocation(startLocation, endLocation, LOCATIONS)) {
                FlightPaths flightPaths = new FlightPaths(COSTS, LOCATIONS);
                List<String> paths = flightPaths.findFlightCosts(startLocation, endLocation);
                if (paths.isEmpty()) {
                    System.out.println("No valid flight paths found.");
                } else {
                    paths.forEach(System.out::println);
                }
            }
        }
    }
}

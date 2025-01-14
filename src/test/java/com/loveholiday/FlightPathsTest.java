package com.loveholiday;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlightPathsTest {
    private static final int[][] costsArr;
    private static final Map<String, Integer> locationMap = new HashMap<>();
    private static final FlightPaths flightPaths;

    static {
        locationMap.put("Castle Black", 0);
        locationMap.put("Winterfell", 1);
        locationMap.put("Riverrun", 2);
        locationMap.put("King's Landing", 3);
        costsArr = new int[][]{
                {0, 15, 80, 90},
                {0, 0, 40, 50},
                {0, 0, 0, 70},
                {0, 0, 0, 0}
        };
        flightPaths = new FlightPaths(costsArr, locationMap);
    }

    @Test
    @DisplayName("Source: Castle Black and Destination: Riverrun")
    public void testFindPaths() {
        List<String> paths = flightPaths.findFlightCosts("Castle Black", "Riverrun");
        assertTrue(paths.contains("Castle Black -> Winterfell -> Riverrun: 55"));
        assertTrue(paths.contains("Castle Black -> Riverrun: 80"));
    }

    @Test
    @DisplayName("Source: Castle Black and Destination: King's Landing")
    public void testFindFlightPaths() {

        List<String> paths = flightPaths.findFlightCosts("Castle Black", "King's Landing");
        assertTrue(paths.contains("Castle Black -> Winterfell -> Riverrun -> King's Landing: 125"));
        assertTrue(paths.contains("Castle Black -> Winterfell -> King's Landing: 65"));
        assertTrue(paths.contains("Castle Black -> Riverrun -> King's Landing: 150"));
        assertTrue(paths.contains("Castle Black -> King's Landing: 90"));
    }

    @Test
    @DisplayName("Source: King's Landing and Destination: Castle Black")
    public void testNoValidFlightPaths() {

        List<String> paths = flightPaths.findFlightCosts("King's Landing", "Castle Black");
        assertEquals(0, paths.size());
    }

    @Test
    @DisplayName("Source: Castle Black and Destination: Castle Black")
    public void testSameSourceAndDestinationFlightPaths() {

        List<String> paths = flightPaths.findFlightCosts("Castle Black", "Castle Black");
        assertTrue(paths.contains("Castle Black: 0"));
    }

}

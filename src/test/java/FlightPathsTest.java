import com.loveholiday.FlightPaths;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlightPathsTest {
    int[][] costsArr;
    Map<String, Integer> locationMap;
    FlightPaths flightPaths;

    @BeforeEach
    public void init() {
        costsArr = new int[][]{
                {0, 15, 80, 90},
                {0, 0, 40, 50},
                {0, 0, 0, 70},
                {0, 0, 0, 0}
        };

        locationMap = new HashMap<>() {{
            put("Castle Black", 0);
            put("Winterfell", 1);
            put("Riverrun", 2);
            put("King's Landing", 3);
        }};
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
    public void invalidInput() {

        List<String> paths = flightPaths.findFlightCosts("Castle Black", "King's Landing");
        assertTrue(paths.contains("Castle Black -> Winterfell -> Riverrun -> King's Landing: 125"));
        assertTrue(paths.contains("Castle Black -> Winterfell -> King's Landing: 65"));
        assertTrue(paths.contains("Castle Black -> Riverrun -> King's Landing: 150"));
        assertTrue(paths.contains("Castle Black -> King's Landing: 90"));
    }
}

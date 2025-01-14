package com.loveholiday;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class InputValidationTest {

    private static final Map<String, Integer> LOCATIONS = new HashMap<>();

    static {
        LOCATIONS.put("Castle Black", 0);
        LOCATIONS.put("Winterfell", 1);
        LOCATIONS.put("Riverrun", 2);
        LOCATIONS.put("King's Landing", 3);
    }

    @Test
    void testIsValidNumOfArgsWithValidArgs() {
        String[] args = {"Castle Black", "Winterfell"};
        assertTrue(InputValidation.isValidNumOfArgs(args));
    }

    @Test
    void testIsValidInputLocationWithValidLocations() {
        assertTrue(InputValidation.isValidInputLocation("Castle Black", "Winterfell", LOCATIONS));
    }

}

package com.loveholiday;

import java.util.Map;

/**
 * The {@code InputValidation} class provides utility methods for validating user inputs
 * when working with flight paths. It ensures the correct number of arguments are passed
 * and verifies that the specified locations are valid.
 */
public class InputValidation {

    /**
     * Validates the number of arguments provided to the program.
     *
     * <p>The method checks that exactly two arguments are provided (source and destination).
     * If the number of arguments is incorrect, it prints an error message to {@code System.err}
     * and terminates the program with an exit code of {@code 1}.</p>
     *
     * @param args an array of strings representing the input arguments.
     * @return {@code true} if the number of arguments is exactly two.
     */
    public static boolean isValidNumOfArgs(String[] args) {
        if (args.length != 2) {
            System.err.println("Please provide only 2 location Source and Destination. You have provided: "+args.length+" location");
            System.exit(1);
        }
        return true;
    }


    /**
     * Validates that the provided source and destination locations exist in the known locations map.
     *
     * <p>This method checks whether the provided {@code startLocation} and {@code endLocation} keys exist
     * in the {@code LOCATIONS} map. If either location is invalid, it prints an error message to
     * {@code System.err} and terminates the program with an exit code of {@code 1}.</p>
     *
     * @param startLocation the name of the starting location to validate.
     * @param endLocation   the name of the destination location to validate.
     * @param LOCATIONS     a {@code Map} containing valid location names as keys and their indices as values.
     * @return {@code true} if both locations are valid.
     */
    public static boolean isValidInputLocation(String startLocation, String endLocation, Map<String, Integer> LOCATIONS) {
        if (!LOCATIONS.containsKey(startLocation) || !LOCATIONS.containsKey(endLocation)) {
            System.err.println("Invalid locations. Please use valid locations: Castle Black, Winterfell, Riverrun, King's Landing.");
            System.exit(1);
        }
        return true;
    }

}

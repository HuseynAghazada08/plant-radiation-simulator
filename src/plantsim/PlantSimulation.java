package plantsim;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlantSimulation {

    /**
     * Runs the simulation and prints the surviving plants.
     *
     * @param args optional input file path
     */
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        try {
            if (args.length > 1) {
                throw new IllegalArgumentException(
                        "Usage: PlantSimulation [input-file]");
            }

            String fileName;
            if (args.length == 1) {
                fileName = args[0].trim();
            } else {
                System.out.print("Enter the name of the input file: ");
                fileName = nextLine(keyboard, "file name").trim();
            }

            if (fileName.isEmpty()) {
                throw new IllegalArgumentException("File name cannot be empty.");
            }

            Planet planet = readPlanet(new File(fileName));
            planet.simulate();

            System.out.println("\nSURVIVORS");
            List<String> survivors = planet.getSurvivors();

            if (survivors.isEmpty()) {
                System.out.println("There are no survivors.");
            } else {
                for (String name : survivors) {
                    System.out.println(name);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File was not found.");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid data: " + e.getMessage());
        }
    }

    /**
     * Reads the planet data from a file.
     *
     * @param file the input file
     * @return the initialized planet
     * @throws FileNotFoundException if the file cannot be opened
     * @throws IllegalArgumentException if the data is invalid
     */
    private static Planet readPlanet(File file)
            throws FileNotFoundException {

        try (Scanner input = new Scanner(file)) {
            int count = positiveInt(
                    nextLine(input, "plant count"), "Plant count");

            List<Plant> plants = new ArrayList<>();

            for (int i = 0; i < count; i++) {
                String[] fields = nextLine(input, "plant " + (i + 1))
                        .trim().split("\\s+");

                if (fields.length != 3) {
                    throw new IllegalArgumentException(
                            "Plant " + (i + 1)
                            + " must contain a name, type, and nutrient level.");
                }

                String name = fields[0];
                String type = fields[1];
                int nutrients = positiveInt(
                        fields[2], "Plant " + (i + 1) + " nutrients");

                switch (type) {
                    case "p":
                        plants.add(new Puffs(name, nutrients));
                        break;
                    case "d":
                        plants.add(new Deltatree(name, nutrients));
                        break;
                    case "b":
                        plants.add(new Parabush(name, nutrients));
                        break;
                    default:
                        throw new IllegalArgumentException(
                                "Invalid plant type: " + type);
                }
            }

            int days = positiveInt(
                    nextLine(input, "day count"), "Day count");

            while (input.hasNextLine()) {
                if (!input.nextLine().trim().isEmpty()) {
                    throw new IllegalArgumentException(
                            "Unexpected data after the day count.");
                }
            }

            return new Planet(plants, days);
        }
    }

    /**
     * Reads the next available line.
     *
     * @param input the scanner to read from
     * @param description the expected data's description
     * @return the next line
     * @throws IllegalArgumentException if the line is missing
     */
    private static String nextLine(Scanner input, String description) {
        if (!input.hasNextLine()) {
            throw new IllegalArgumentException(
                    "Missing " + description + ".");
        }

        return input.nextLine();
    }

    /**
     * Parses a positive integer.
     *
     * @param value the text to parse
     * @param description the value's description
     * @return the positive integer
     * @throws IllegalArgumentException if the value is invalid or not positive
     */
    private static int positiveInt(String value, String description) {
        int result;

        try {
            result = Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    description + " must be a positive integer.", e);
        }

        if (result <= 0) {
            throw new IllegalArgumentException(
                    description + " must be a positive integer.");
        }

        return result;
    }
}

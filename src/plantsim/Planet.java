package plantsim;

import java.util.ArrayList;
import java.util.List;

public class Planet {
    private final List<Plant> plants;
    private final int numberOfDays;
    private Radiation radiation = Radiation.NONE;

    /**
     * Creates a planet with the given plants and simulation length.
     *
     * @param initialPlants the starting plants
     * @param numberOfDays the number of days to simulate
     * @throws IllegalArgumentException if the list is null or the days are not positive
     */
    public Planet(List<Plant> initialPlants, int numberOfDays) {
        if (initialPlants == null) {
            throw new IllegalArgumentException("Plant list cannot be null.");
        }

        if (numberOfDays <= 0) {
            throw new IllegalArgumentException(
                    "Number of days must be greater than 0.");
        }

        this.numberOfDays = numberOfDays;
        this.plants = new ArrayList<>(initialPlants);
    }

    /**
     * Adds a plant to the planet.
     *
     * @param plant the plant to add
     * @throws IllegalArgumentException if the plant is null
     */
    public void addPlant(Plant plant) {
        if (plant == null) {
            throw new IllegalArgumentException("Plant cannot be null.");
        }
        plants.add(plant);
    }

    /**
     * Runs the simulation and prints each day's plant status.
     * Surviving plants determine the next day's radiation.
     */
    public void simulate() {
        for (int day = 1; day <= numberOfDays; day++) {
            System.out.println(
                    "\nDAY " + day + " - Radiation: " + radiation);

            int alphaNeed = 0;
            int deltaNeed = 0;

            for (Plant plant : plants) {
                if (plant.isLiving()) {
                    switch (radiation) {
                        case ALPHA:
                            plant.alpha();
                            break;
                        case DELTA:
                            plant.delta();
                            break;
                        case NONE:
                            plant.noRadiation();
                            break;
                        default:
                            throw new IllegalStateException(
                                    "Unknown radiation.");
                    }

                    // Only surviving plants influence tomorrow's radiation.
                    if (plant.isLiving()) {
                        alphaNeed += plant.getAlphaNeed();
                        deltaNeed += plant.getDeltaNeed();
                    }
                }

                System.out.println(plant);
            }

            if (alphaNeed >= deltaNeed + 3) {
                radiation = Radiation.ALPHA;
            } else if (deltaNeed >= alphaNeed + 3) {
                radiation = Radiation.DELTA;
            } else {
                radiation = Radiation.NONE;
            }
        }
    }

    /**
     * Collects the names of the plants still alive.
     *
     * @return a list of surviving plant names
     */
    public List<String> getSurvivors() {
        List<String> survivors = new ArrayList<>();

        for (Plant plant : plants) {
            if (plant.isLiving()) {
                survivors.add(plant.getName());
            }
        }

        return survivors;
    }
}

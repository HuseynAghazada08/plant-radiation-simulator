package plantsim;

public abstract class Plant {
    private final String name;
    protected int nutrients;
    protected boolean living = true;

    /**
     * Creates a plant with a name and starting nutrients.
     *
     * @param name the plant's name
     * @param nutrients its starting nutrient level
     * @throws IllegalArgumentException if the name is null or blank,
     *         or the nutrients are not positive
     */
    protected Plant(String name, int nutrients) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Plant name cannot be empty.");
        }

        if (nutrients <= 0) {
            throw new IllegalArgumentException(
                    "Initial nutrients must be greater than 0.");
        }

        this.name = name;
        this.nutrients = nutrients;
    }

    /**
     * Applies the effects of alpha radiation to the plant.
     */
    public abstract void alpha();

    /**
     * Applies the effects of delta radiation to the plant.
     */
    public abstract void delta();

    /**
     * Applies the effects of a day without radiation.
     */
    public abstract void noRadiation();

    /**
     * Gets the plant's need for alpha radiation.
     *
     * @return the amount of alpha radiation requested
     */
    public abstract int getAlphaNeed();

    /**
     * Gets the plant's need for delta radiation.
     *
     * @return the amount of delta radiation requested
     */
    public abstract int getDeltaNeed();

    /**
     * Marks the plant as dead if its nutrients reach zero or below.
     */
    protected void checkLife() {
        if (nutrients <= 0) {
            living = false;
        }
    }

    /**
     * Gets the plant's name.
     *
     * @return the plant's name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the plant's current nutrient level.
     *
     * @return the remaining nutrients
     */
    public int getNutrients() {
        return nutrients;
    }

    /**
     * Checks whether the plant is still alive.
     *
     * @return true if the plant is alive, false otherwise
     */
    public boolean isLiving() {
        return living;
    }

    /**
     * Returns the plant's name, nutrients, and living status.
     *
     * @return a description of the plant
     */
    @Override
    public String toString() {
        return "Name: " + name
                + ", nutrients: " + nutrients
                + ", living: " + living;
    }
}

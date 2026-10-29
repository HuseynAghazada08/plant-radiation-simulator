package plantsim;

public class Puffs extends Plant {

    /**
     * Creates a Puffs plant and checks if it can survive.
     *
     * @param name the plant's name
     * @param nutrients its starting nutrient level
     * @throws IllegalArgumentException if the name is null or blank,
     *         or the nutrients are not positive
     */
    public Puffs(String name, int nutrients) {
        super(name, nutrients);
        checkLife();
    }

    /**
     * Adds 2 nutrients after alpha radiation and checks if the plant survives.
     */
    @Override
    public void alpha() {
        nutrients += 2;
        checkLife();
    }

    /**
     * Removes 2 nutrients after delta radiation and checks if the plant survives.
     */
    @Override
    public void delta() {
        nutrients -= 2;
        checkLife();
    }

    /**
     * Removes 1 nutrient without radiation and checks if the plant survives.
     */
    @Override
    public void noRadiation() {
        nutrients -= 1;
        checkLife();
    }

    /**
     * Marks the plant as dead if its nutrients fall outside 1 to 10.
     */
    @Override
    protected void checkLife() {
        if (nutrients <= 0 || nutrients > 10) {
            living = false;
        }
    }

    /**
     * Gets the plant's need for alpha radiation.
     *
     * @return 10 minus its nutrients if alive, or 0 if dead
     */
    @Override
    public int getAlphaNeed() {
        return living ? 10 - nutrients : 0;
    }

    /**
     * Returns zero because Puffs do not request delta radiation.
     *
     * @return 0
     */
    @Override
    public int getDeltaNeed() {
        return 0;
    }
}

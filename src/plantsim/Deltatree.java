package plantsim;

public class Deltatree extends Plant {

    /**
     * Creates a Deltatree with the given name and nutrients.
     *
     * @param name the plant's name
     * @param nutrients its starting nutrient level
     */
    public Deltatree(String name, int nutrients) {
        super(name, nutrients);
    }

    /**
     * Removes 3 nutrients after alpha radiation and checks if the plant survives.
     */
    @Override
    public void alpha() {
        nutrients -= 3;
        checkLife();
    }

    /**
     * Adds 4 nutrients after delta radiation and checks if the plant survives.
     */
    @Override
    public void delta() {
        nutrients += 4;
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
     * Returns zero because Deltatrees do not need alpha radiation.
     *
     * @return 0
     */
    @Override
    public int getAlphaNeed() {
        return 0;
    }

    /**
     * Gets the plant's need for delta radiation based on its nutrients.
     *
     * @return 4 below 5 nutrients, 1 from 5 to 10, or 0 otherwise or if dead
     */
    @Override
    public int getDeltaNeed() {
        if (!living) {
            return 0;
        }

        if (nutrients < 5) {
            return 4;
        }

        if (nutrients <= 10) {
            return 1;
        }

        return 0;
    }
}

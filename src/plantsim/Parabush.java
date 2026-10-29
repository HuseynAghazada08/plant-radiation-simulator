package plantsim;

public class Parabush extends Plant {

    /**
     * Creates a Parabush with the given name and nutrients.
     *
     * @param name the plant's name
     * @param nutrients its starting nutrient level
     */
    public Parabush(String name, int nutrients) {
        super(name, nutrients);
    }

    /**
     * Adds 1 nutrient after alpha radiation and checks if the plant survives.
     */
    @Override
    public void alpha() {
        nutrients += 1;
        checkLife();
    }

    /**
     * Adds 1 nutrient after delta radiation and checks if the plant survives.
     */
    @Override
    public void delta() {
        nutrients += 1;
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
     * Returns zero because Parabushes do not request alpha radiation.
     *
     * @return 0
     */
    @Override
    public int getAlphaNeed() {
        return 0;
    }

    /**
     * Returns zero because Parabushes do not request delta radiation.
     *
     * @return 0
     */
    @Override
    public int getDeltaNeed() {
        return 0;
    }
}

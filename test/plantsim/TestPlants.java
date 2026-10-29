package plantsim;

public class TestPlants {

    /**
     * Runs the tests for all three plant types.
     *
     * @param args command-line arguments; not used
     */
    public static void main(String[] args) {
        testPuffs();
        testDeltatree();
        testParabush();
    }

    /**
     * Tests Puffs' radiation effects, radiation needs, and survival limits.
     */
    private static void testPuffs() {
        System.out.println("\nPUFFS TESTS");

        Puffs p = new Puffs("P", 7);

        p.alpha();
        check("Alpha radiation", p.getNutrients() == 9);

        p.delta();
        check("Delta radiation", p.getNutrients() == 7);

        p.noRadiation();
        check("No radiation", p.getNutrients() == 6);

        check("Alpha need", p.getAlphaNeed() == 4);
        check("Delta need", p.getDeltaNeed() == 0);

        Puffs limit = new Puffs("Limit", 10);
        check("Alive with 10 nutrients", limit.isLiving());

        limit.alpha();
        check("Dies above 10 nutrients",
                limit.getNutrients() == 12 && !limit.isLiving());
        check("Dead plant has no alpha need",
                limit.getAlphaNeed() == 0);

        Puffs low = new Puffs("Low", 2);
        low.delta();
        check("Dies at zero nutrients",
                low.getNutrients() == 0 && !low.isLiving());

        Puffs negative = new Puffs("Negative", 1);
        negative.delta();
        check("Dies below zero",
                negative.getNutrients() == -1 && !negative.isLiving());
    }

    /**
     * Tests Deltatree's radiation effects, radiation needs, and survival.
     */
    private static void testDeltatree() {
        System.out.println("\nDELTATREE TESTS");

        Deltatree d = new Deltatree("D", 8);

        d.alpha();
        check("Alpha radiation", d.getNutrients() == 5);

        d.delta();
        check("Delta radiation", d.getNutrients() == 9);

        d.noRadiation();
        check("No radiation", d.getNutrients() == 8);

        check("Alpha need", d.getAlphaNeed() == 0);

        Deltatree four = new Deltatree("Four", 4);
        Deltatree five = new Deltatree("Five", 5);
        Deltatree ten = new Deltatree("Ten", 10);
        Deltatree eleven = new Deltatree("Eleven", 11);

        check("Delta need with 4 nutrients", four.getDeltaNeed() == 4);
        check("Delta need with 5 nutrients", five.getDeltaNeed() == 1);
        check("Delta need with 10 nutrients", ten.getDeltaNeed() == 1);
        check("Delta need with 11 nutrients", eleven.getDeltaNeed() == 0);

        Deltatree low = new Deltatree("Low", 3);
        low.alpha();
        check("Dies at zero nutrients",
                low.getNutrients() == 0 && !low.isLiving());
        check("Dead plant has no delta need",
                low.getDeltaNeed() == 0);

        Deltatree negative = new Deltatree("Negative", 2);
        negative.alpha();
        check("Dies below zero",
                negative.getNutrients() == -1 && !negative.isLiving());
    }

    /**
     * Tests Parabush's radiation effects, radiation needs, and survival.
     */
    private static void testParabush() {
        System.out.println("\nPARABUSH TESTS");

        Parabush b = new Parabush("B", 2);

        b.alpha();
        check("Alpha radiation", b.getNutrients() == 3);

        b.delta();
        check("Delta radiation", b.getNutrients() == 4);

        b.noRadiation();
        check("No radiation", b.getNutrients() == 3);

        check("Alpha need", b.getAlphaNeed() == 0);
        check("Delta need", b.getDeltaNeed() == 0);

        Parabush low = new Parabush("Low", 1);
        low.noRadiation();
        check("Dies at zero nutrients",
                low.getNutrients() == 0 && !low.isLiving());
    }

    /**
     * Prints a passing check or throws an assertion error on failure.
     *
     * @param name the test's name
     * @param correct whether the result matches the expected value
     */
    private static void check(String name, boolean correct) {
        if (correct) {
            System.out.println(name + ": PASS");
        } else {
            throw new AssertionError(name + ": FAIL");
        }
    }
}


public class Tester {

    private Person[] people;
    private double hits;
    private int iterations;

    public Tester(int people, int iterations) {
        this.people = new Person[people];
        this.iterations = iterations;
    }

    /**
     * Runs a simulation this.iterations number of times. In each simulation,
     * this.people.length people are created, each of which have a birthday (an
     * int between 1 and 365, inclusive), and then the people are checked to see
     * if there is at least one pair of people that have the same birthday.
     */
    public void simulate() {
        for (int i = 0; i < this.iterations; i++) {

            for (int j = 0; j < this.people.length; j++) {
                this.people[j] = new Person();
            }
            this.checkMatches();

        }
    }

    /**
     * Checks if there are at least two people in this.people with the same
     * birthday. If there is at least one match, this.hits is incremented and
     * the method stops executing.
     */
    public void checkMatches() {
        for (int i = 0; i < this.people.length - 1; i++) {
            for (int j = i + 1; j < this.people.length; j++) {
                if (this.people[i].getBirthday() == this.people[j].getBirthday()) {
                    this.hits++;
                    return;
                }
            }
        }
    }

    public double chanceOfMatch() {
        return this.hits / this.iterations;
    }
}

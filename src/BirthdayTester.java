
public class BirthdayTester {

    public static void main(String[] args) {
        /* Our class has 22 people */
        int people = 22;
        int iterations = 10000;
        Tester tester = new Tester(people, iterations);
        tester.simulate();
        System.out.println("Chance of match with " + people + " people and " + iterations + " iterations: " + tester.chanceOfMatch());

    }

}

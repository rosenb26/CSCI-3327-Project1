
public class BirthdayProblemTester {

    public static void main(String[] args) {
        /* 22 people in our class */
        for (int i = 2; i <= 22; i++) {
            BirthdayProblem b = new BirthdayProblem(i);
            System.out.println("probability with " + b.getPeople() + " people: " + b.probabilityOfSameBirthday());

        }
    }
}

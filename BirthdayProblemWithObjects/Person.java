
import java.util.Random;

public class Person {

    /* A birthday is an int between 1 and 365, inclusive. */
    private int birthday;

    /**
     * Creates a person with a random birthday.
     */
    public Person() {
        Random rng = new Random();
        this.birthday = rng.nextInt(365) + 1;
    }

    public int getBirthday() {
        return this.birthday;
    }
}

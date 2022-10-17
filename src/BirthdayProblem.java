
import java.math.BigDecimal;
import java.math.MathContext;

/**
 * TEST
 * @author rosenb26
 */
public class BirthdayProblem {

    private int numberOfPeople;

    public BirthdayProblem(int n) {
        this.numberOfPeople = n;
    }

    /**
     * Computes the probability of at least two people having the same birthday.
     * Note that n/d is the probability of all people having different
     * birthdays.
     *
     * @return The probability that there are at least two people with the same
     * birthday, given by 1 - (the probability of all people having different
     * birthdays).
     */
    public BigDecimal probabilityOfSameBirthday() {
        BigDecimal n = this.numerator(this.numberOfPeople);
        BigDecimal d = this.denominator(this.numberOfPeople);

        return BigDecimal.ONE.subtract(n.divide(d, new MathContext(4)));
    }

    /**
     * Computes the number of ways for n people to all have different birthdays.
     *
     * @param n The number of people
     * @return The number of ways, i.e. 365 *3 64 *...* 365 - n + 1.
     */
    public BigDecimal numerator(int n) {
        BigDecimal product = BigDecimal.ONE;
        for (int i = 365; i >= 365 - n + 1; i--) {
            product = product.multiply(BigDecimal.valueOf(i));
        }
        return product;
    }

    /**
     * Computes the total number of different birthdays possible among n people.
     *
     * @param n The number of people.
     * @return The number of possible assignments of birthdays to people, i.e.
     * 365^n.
     */
    public BigDecimal denominator(int n) {
        return BigDecimal.valueOf(365).pow(n);
    }

    public int getPeople() {
        return this.numberOfPeople;
    }

    public static void main(String[] args) {
        /* 22 people in our class */
        for (int i = 2; i <= 22; i++) {
            BirthdayProblem b = new BirthdayProblem(i);
            System.out.println("probability with " + b.getPeople() + " people: " + b.probabilityOfSameBirthday());

        }
    }

}

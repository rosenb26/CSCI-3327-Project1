
import java.math.BigDecimal;
import java.math.MathContext;

public class BirthdayProblem {

    private int numberOfPeople;

    public BirthdayProblem(int n) {
        this.numberOfPeople = n;
    }

    /**
     * Computes the probability of at least two people among a group of
     * this.numberOfPeople having the same birthday. Note that n/d below is the
     * probability of all people having different birthdays.
     *
     * @return The probability (to four decimal places) that there are at least
     * two people with the same birthday, given by 1 - (the probability of all
     * people having different birthdays).
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
     * @return The number of ways, i.e. 365 * 364 *...* (365 - n + 1).
     */
    public BigDecimal numerator(int n) {
        BigDecimal product = BigDecimal.ONE;
        for (int i = 365; i >= 365 - n + 1; i--) {
            product = product.multiply(BigDecimal.valueOf(i));
        }
        return product;
    }

    /**
     * Computes the total number of different birthdays possible among n people,
     * i.e. the number of ordered n-tuples where 1 <= n_i <= 365.
     *
     * @param n The number of people.
     * @return The number of possible n-tuples, i.e. 365^n.
     *
     */
    public BigDecimal denominator(int n) {
        return BigDecimal.valueOf(365).pow(n);
    }

    public int getPeople() {
        return this.numberOfPeople;
    }
}

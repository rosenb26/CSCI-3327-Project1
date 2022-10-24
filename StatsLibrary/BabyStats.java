
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * A class with a few methods to compute basic statistics and probabilities.
 */
public class BabyStats {

    /**
     * Computes the mean of a list of integers.
     *
     * @param values The list of integers whose mean is desired.
     * @return The mean of the list of integers.
     */
    public double mean(int[] values) {
        double sum = 0;
        for (int x : values) {
            sum += x;
        }
        return sum / values.length;
    }

    /*
     * This method implements an extremely inefficient algorithm for finding the
     * mode (if it exists) in a list of integers.
     *
     * @param values The data from which the mode is to be extracted.
     * @return A String representing the mode or a message that the mode does
     * not exist.
     */
    public String mode(int[] values) {
        int[] frequencies = new int[values.length];

        /*
         * Count the number of times each data point appears in values, i.e.
         * frequencies[j] gives the number of times that values[j] appears in
         * values.
         */
        for (int i = 0; i < values.length; i++) {
            int frequency = 0;
            for (int j = 0; j < values.length; j++) {
                if (values[j] == values[i]) {
                    frequency++;
                }
            }
            frequencies[i] = frequency;
        }

        /*
         * Find the largest frequency and record the index of its first
         * appearance.
         */
        int maxFrequency = frequencies[0];
        int index = 0;
        for (int i = 1; i < frequencies.length; i++) {
            if (frequencies[i] > maxFrequency) {
                maxFrequency = frequencies[i];
                index = i;
            }
        }

        /*
         * Find the mode, if it exists. If there is a mode in values[], it cannot
         * appear in any position smaller than index, which is why we initialize
         * i to index + 1 in the following loop.
         */
        int modeCandidate = values[index];
        for (int i = index + 1; i < frequencies.length; i++) {
            if (frequencies[i] == maxFrequency) {
                if (modeCandidate != values[i]) {
                    /**
                     * This means that there are two distinct data points that
                     * appear the same number of times, and no other data point
                     * appears as many times. By definition, there is no mode.
                     */
                    return "No mode";
                }
            }
        }

        /*
         * If program execution makes it to this point, then there is a single
         * data point that occurs (strictly) more than any other, and therefore
         * must be the mode.
         */
        return "" + values[index];

    }

    /**
     * Computes the median of a set of integers.
     *
     * @param values The set of integers.
     * @return The median of the set of integers.
     */
    public double median(int[] values) {
        /*
         * The values first need to be sorted. We use an inefficient sorting
         * algorithm for simplicity.
         */

        for (int i = 0; i < values.length - 1; i++) {
            for (int j = i + 1; j < values.length; j++) {
                if (values[j] < values[i]) {
                    int temp = values[i];
                    values[i] = values[j];
                    values[j] = temp;
                }
            }
        }

        /*
         * If the size of the data set is odd then there is a unique middle
         * value.
         */
        if (values.length % 2 == 1) {
            return values[values.length / 2];
        }

        /*
         * If the size of the data set is even, then get the average of the two
         * middle values.
         */
        return .5 * (values[values.length / 2 - 1] + values[values.length / 2]);
    }

    /**
     * Find the variance of a list of numbers (here integers) by applying the
     * definition: sum the squares of values[i] - mean, for each i.
     *
     * @param values The list of numbers.
     * @return The variance.
     */
    public double variance(int[] values) {
        double mean = this.mean(values);

        double sum = 0;
        for (int x : values) {
            sum += (x - mean) * (x - mean);
        }
        return sum / (values.length - 1);

    }

    /**
     * Finds the standard deviation of a list of numbers.
     *
     * @param values The list whose standard deviation is desired.
     * @return The standard deviation (as the square root of the variance).
     */
    public double standardDeviation(int[] values) {
        return Math.sqrt(this.variance(values));
    }

    /**
     * Computes the factorial of a given integer.
     *
     * Note that 12! is the largest factorial that fits in an int, and 20! is
     * the largest factorial that fits in a long. Hence the use of BigInteger.
     *
     * @param n The number whose factorial is desired.
     * @return The factorial of n.
     */
    public BigInteger factorial(int n) {
        BigInteger factorial = BigInteger.ONE;
        for (int i = n; i >= 2; i--) {
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }
        return factorial;
    }

    /**
     * Computes the number of ways to order r items from a set of n.
     *
     * @param n The number of items in the set.
     * @param r The number of items to be placed in order.
     * @return The number of permutations, i.e. nPr.
     */
    public BigInteger permutations(int n, int r) {
        BigInteger product = BigInteger.ONE;
        for (int i = n; i >= n - r + 1; i--) {
            product = product.multiply(BigInteger.valueOf(i));
        }
        return product;
    }

    /**
     * Computes the number of subsets (combinations) of size r present in a set
     * of n.
     *
     * @param n The size of the set
     * @param r The number of subsets.
     * @return The number of combinations, i.e. nCr.
     */
    public BigInteger combinations(int n, int r) {
        return this.permutations(n, r).divide(this.factorial(r));
    }

    /**
     * Computes a multinomial coefficient.
     *
     * @param n The size of the set (the number of distinct objects).
     * @param partition A partition of n, i.e. a set of k numbers, n_1, n_2,...
     * n_k (k >= 1 and k <= n) such that the sum of the k numbers is n.
     *
     * @return The multinomial coefficient (n !/(n_1! * ... * n_k!)).
     */
    public BigInteger multinomialCoefficient(int n, int... partition) {
        BigInteger numerator = this.factorial(n);
        BigInteger denominator = BigInteger.ONE;
        for (int i = 0; i < partition.length; i++) {
            denominator = denominator.multiply(this.factorial(partition[i]));
        }
        return numerator.divide(denominator);
    }

    /**
     * Computes the probability of exactly k successes in n trials, where each
     * trial is independent of the others and the probability of a success is p.
     *
     * @param p The probability of success.
     * @param n The number of trials.
     * @param k The number of successes.
     * @return The probability of exactly k successes.
     */
    public BigDecimal binomialProbability(double p, int n, int k) {
        BigDecimal coefficient = new BigDecimal(this.combinations(n, k));
        BigDecimal pCopy = BigDecimal.valueOf(p);

        // The first argument in setScale can be altered to give as many decimal places as desired.
        return coefficient.multiply(pCopy.pow(k)).multiply(BigDecimal.ONE.subtract(pCopy).pow(n - k)).setScale(7, RoundingMode.HALF_UP);
    }

    /**
     * The mean of a binomial distribution.
     *
     * @param p The probability of success.
     * @param n The number of trials.
     * @return The mean (expected value).
     */
    public double binomialMean(double p, int n) {
        return n * p;
    }

    /**
     * The variance of a binomial distribution.
     *
     * @param p The probability of success.
     * @param n The number of trials.
     * @return The variance.
     */
    public double binomialVariance(double p, int n) {
        return n * p * (1 - p);
    }

    /**
     * Computes the probability of the first successful outcome occurring on the
     * nth trial.
     *
     * @param p The probability of a successful outcome in any given single
     * trial.
     * @param n The number of trials.
     *
     * @return The probability of the first success occurring on exactly the nth
     * trial.
     */
    public BigDecimal geometricProbability(double p, int n) {
        BigDecimal chanceOfSuccess = BigDecimal.valueOf(p);
        return BigDecimal.ONE.subtract(chanceOfSuccess).pow(n - 1).multiply(chanceOfSuccess);
    }

    /**
     * The mean of a geometric distribution.
     *
     * @param p The probability of success.
     * @return The geometric mean, i.e. 1/p.
     */
    public double geometricMean(double p) {
        return 1 / p;
    }

    /**
     * The variance of a geometric distribution.
     *
     * @param p The probability of success.
     * @return The geometric variance.
     */
    public double geometricVariance(double p) {
        return (1 - p) / (p * p);
    }

    /**
     * Computes a hypergeometric probability. Given a set of size N that
     * contains r items of type I and N - r items of type II (and nothing else),
     * compute the probability of selecting exactly y items of type I and n - y
     * items of type II.
     *
     * @param N The number of items in the set.
     * @param n The total number of items selected.
     * @param r The number of items of type I present in the set.
     * @param y The number of type I items selected.
     * @return The hypergeometric probability.
     */
    public BigDecimal hypergeometricProbability(int N, int n, int r, int y) {
        BigDecimal numerator = new BigDecimal(this.combinations(r, y).multiply(this.combinations(N - r, n - y)));
        BigDecimal denominator = new BigDecimal(this.combinations(N, n));

        return numerator.divide(denominator, 4, RoundingMode.HALF_UP);
    }

    /**
     * The hypergeometric mean.
     *
     * @param N The number of items in the set.
     * @param n The number of items selected.
     * @param r The number of items of type I.
     * @return The hypergeometric mean.
     */
    public BigDecimal hypergeometricMean(int N, int n, int r) {
        BigDecimal capitalNCopy = BigDecimal.valueOf(N);
        BigDecimal lowerNcopy = BigDecimal.valueOf(n);
        BigDecimal rCopy = BigDecimal.valueOf(r);
        return (lowerNcopy.multiply(rCopy)).divide(capitalNCopy);
    }

    /**
     * The hypergeometric variance.
     * @param N The number of items in the set.
     * @param n The number of items selected.
     * @param r The number of items of type I.
     * @return The hypergeometric variance.
     */
    public BigDecimal hypergeometricVariance(int N, int n, int r) {
        BigDecimal capitalNCopy = BigDecimal.valueOf(N);
        BigDecimal lowerNcopy = BigDecimal.valueOf(n);
        BigDecimal rCopy = BigDecimal.valueOf(r);

        return lowerNcopy.multiply(rCopy.divide(capitalNCopy)).multiply(capitalNCopy.subtract(rCopy).divide(capitalNCopy))
                .multiply(capitalNCopy.subtract(lowerNcopy).divide(capitalNCopy.subtract(BigDecimal.ONE), 5, RoundingMode.HALF_UP));
    }

    /**
     * Computes the probability of a Poisson distribution for a given value of
     * lambda.
     *
     * @param y The number of occurrences whose probability is desired.
     * @param lambda The average number of occurrences per unit time.
     * @return The probability of observing exactly y occurrences.
     */
    public BigDecimal poissonProbabilityExact(int y, double lambda) {
        BigDecimal eToTheLambda = BigDecimal.valueOf(Math.pow(Math.E, lambda));
        BigDecimal denominator = new BigDecimal(this.factorial(y)).multiply(eToTheLambda);

        return BigDecimal.valueOf(lambda).pow(y).divide(denominator, 4, RoundingMode.HALF_UP);
    }
    
    /**
     * Computes the probability of a Poisson distribution where the number of
     * occurrences is strictly smaller than a given number.
     *
     * @param y The upper bound on the number of occurrences.
     * @param lambda The average number of occurrences per unit time.
     * @return The probability of observing x occurrences, where x is a
     * nonnegative integer strictly smaller than y.
     */
    public BigDecimal poissonProbabilityLessThan(int y, double lambda) {
        BigDecimal sum = BigDecimal.ZERO;
        for (int i = 0; i < y; i++) {
            sum = sum.add(this.poissonProbabilityExact(i, lambda));
        }
        return sum;
    }

    /**
     * Computes the probability of a Poisson distribution where the number of
     * occurrences is strictly bigger than a given number.
     *
     * @param y The lower bound on the number of occurrences.
     * @param lambda The average number of occurrences per unit time.
     * @return The probability of observing x occurrences, where x is a
     * nonnegative integer strictly larger than y.
     */
    public BigDecimal poissonProbabilityGreaterThan(int y, double lambda) {
        BigDecimal sum = this.poissonProbabilityLessThan(y, lambda);
        sum = sum.add(this.poissonProbabilityExact(y, lambda));

        return BigDecimal.ONE.subtract(sum);
    }

    /**
     * Computes |sin(5x)| for some number of points, which are equally
     * distributed in some interval.
     *
     * @param start The start of the interval.
     * @param end The end of the interval.
     * @param numberOfPoints The number of points to compute.
     */
    public void writeFunctionValuesToFile(double start, double end, int numberOfPoints) throws Exception {
        PrintWriter writer = new PrintWriter("output.csv");
        writer.println("x, |sin(5x)|");

        double step = Math.abs(end - start) / numberOfPoints;
        for (int i = 1; i <= numberOfPoints; i++) {
            writer.println(start + i * step + ", " + this.abs5Sinx(start + i * step));
        }
        writer.close();
    }

    /**
     * Salts a file by distorting the y-values with some randomness.
     *
     * @param fileName The file whose y-values are to be distorted.
     */
    public void saltFile(String fileName) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter("salted.csv");
        Random rng = new Random();
        Scanner reader = new Scanner(new File(fileName));

        // skip the header of the input file
        reader.nextLine();

        // write the header for the output file
        writer.println("x, salted");

        while (reader.hasNextLine()) {
            String[] line = reader.nextLine().split(",");

            int plusMinus = rng.nextInt(2);

            // how far to shift up or down (a number between 0 and 1)
            double offset = rng.nextDouble();

            /*randomly (with probability .5) determine to shift the value
            up or down 
             */
            if (plusMinus == 1) {
                offset *= -1;
            }

            // write the old x value and the new y value 
            writer.println(line[0] + ", " + (Double.valueOf(line[1]) + offset));
        }
        writer.close();
        reader.close();
    }

    /**
     * Smooths y-values in a list of x, y pairs starting with the second value
     * and ending at the penultimate value. For each y-value, an average of
     * neighboring y-values is computed and the y-value is replaced with this
     * average. The number of neighboring y-values used in the average for each
     * y-value is determined as follows: the window supplied by the user is the
     * first choice. If this window is bigger than the distance to the nearest
     * edge (either the beginning or end), then the distance to the nearest edge
     * is used. For example, if there are 1000 points and the user specifies a
     * window of 20, then all y-values i from 0 to 19 will have a window of i
     * and all y-values i from 980 to 998 will have window 999 - i. All other
     * y-values will have a window of 20.
     *
     * @param fileName The file whose data are to be smoothed.
     * @param window The preferred window size to use for each y-value.
     */
    public void smoothFile(String fileName, int window) throws FileNotFoundException {
        Scanner reader = new Scanner(new File(fileName));

        /*
        Parallel arrays are frowned upon, and a better way to do this would be
        using maps. But since we are assumed to have minimal programming
        knowledge, I decided to do this as naively as possible.
         */
        ArrayList<Double> xValues = new ArrayList<>();
        ArrayList<Double> yValues = new ArrayList<>();

        // skip the header line
        reader.nextLine();

        while (reader.hasNextLine()) {
            String[] temp = reader.nextLine().split(",");
            xValues.add(Double.valueOf(temp[0]));
            yValues.add(Double.valueOf(temp[1]));
        }

        PrintWriter writer = new PrintWriter("smoothed.csv");

        // write the header
        writer.println("x, smoothed");

        /* The first value cannot be smoothed (because it has
        no points on the left), so remains as is.
         */
        writer.println(xValues.get(0) + ", " + yValues.get(0));

        for (int i = 1; i < xValues.size() - 1; i++) {
            int actualWindow = Math.min(i, Math.min(xValues.size() - 1 - i, window));
            double sum = 0;

            for (int j = i - actualWindow; j <= i + actualWindow; j++) {
                // we want points to the left and right, only, and not the actual point
                if (j == i) {
                    continue;
                }
                sum += yValues.get(j);
            }
            double average = sum / (2 * actualWindow);
            writer.println(xValues.get(i) + ", " + average);
        }

        /* The last value cannot be smoothed (because it has
        no points on the right), so remains as is.
         */
        writer.println(xValues.get(xValues.size() - 1) + ", " + yValues.get(yValues.size() - 1));

        reader.close();
        writer.close();

    }

    /**
     * A custom function: |sin(5x)|.
     *
     * @param number The input for the function.
     * @return The absolute value of sin(5*number).
     */
    public double abs5Sinx(double number) {
        return Math.abs(Math.sin(5 * number));
    }

}

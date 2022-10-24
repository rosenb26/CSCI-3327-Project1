
import java.math.BigDecimal;

public class BabyStatsTester {

    public static void main(String[] args) throws Exception {
        BabyStats tester = new BabyStats();

        /*
        Test the mean, mode, median, variance, and standard deviation methods.
        Feel free to test them with any other exotic data sets.
         */
        int[] array = {1, 2, 3, 4, 5, 5, 6, 7, 8, 9};
        System.out.println("mean: " + tester.mean(array));
        System.out.println("mode: " + tester.mode(array));
        System.out.println("median: " + tester.median(array));
        System.out.println("variance: " + tester.variance(array));
        System.out.println("standard deviation: " + tester.standardDeviation(array));


        /*
        Binomial distribution. Example 3.9, page 106. 
         */
        BigDecimal sum = BigDecimal.ZERO;
        for (int i = 4; i <= 20; i++) {
            sum = sum.add(tester.binomialProbability(.05, 20, i));
        }
        System.out.println("Example 3.9: " + sum);

        /*
        Binomial distribution. Problem 3.41, page 111. 
        Note the book's answer of 0.000 which is very misleading. After all,
        is it really impossible that the student gets 10 or more correct by
        guessing randomly? No, it is quite possible.
         */
        sum = BigDecimal.ZERO;
        for (int i = 10; i <= 15; i++) {
            sum = sum.add(tester.binomialProbability(.2, 15, i));
        }
        System.out.println("Problem 3.41: " + sum);

        /*
        Binomial distribution. Problem 3.42 (a), page 111. 
         */
        sum = BigDecimal.ZERO;
        for (int i = 10; i <= 15; i++) {
            sum = sum.add(tester.binomialProbability(.25, 15, i));
        }
        System.out.println("Problem 3.42 (a): " + sum);

        /*
        Binomial distribution. Problem 3.42 (b), page 111. 
         */
        sum = BigDecimal.ZERO;
        for (int i = 10; i <= 15; i++) {
            sum = sum.add(tester.binomialProbability(1.0 / 3, 15, i));
        }
        System.out.println("Problem 3.42 (b): " + sum);
        
        /*
        Geometric distribution. Problem 3.67, page 119.
        */
        System.out.println("Problem 3.67: " + tester.geometricProbability(.3, 5));

        /*
        Hypergeometric distribution. Example 3.16, page 126;
         */
        System.out.println("Example 3.16 " + tester.hypergeometricProbability(20, 10, 5, 5));

        /*
        Poisson distribution (exact). Problem 3.19, page 132.
         */
        System.out.println("Example 3.19 " + tester.poissonProbabilityExact(2, 1));

        /*
        Poisson distribution (less than). Example 3.21, page 134.
         */
        System.out.println("Example 3.21 " + tester.poissonProbabilityLessThan(4, 2));

        /*
        Poisson distribution (greater than). Example 3.21, page 134.
         */
        System.out.println("Example 3.21 " + tester.poissonProbabilityGreaterThan(0, 1));

        /*
        Test the methods that compute and write data to a file, 
        salt data, and smooth data. Here we demonstrate with 1000 points 
        between 0 and 1. 
        Note that due to the randomness inherent in the salter method,
        the appearance of the salted (and consequently the smoothed) data
        will vary slightly each time.
         */
        tester.writeFunctionValuesToFile(0, 1, 1000);
        tester.saltFile("output.csv");
        tester.smoothFile("salted.csv", 20);

    }

}

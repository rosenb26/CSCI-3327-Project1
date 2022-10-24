
import java.text.DecimalFormat;

public class Seafood {

    private double weight;
    private double pricePerPound;
    private DecimalFormat formatter;

    public Seafood(double weight, double pricePerPound) {

        this.weight = weight;
        this.pricePerPound = pricePerPound;
        this.formatter = new DecimalFormat("#.00");
    }

    public double getWeight() {
        return Double.valueOf(this.formatter.format(this.weight));
    }

    public double getPrice() {
        return Double.valueOf(this.formatter.format(this.weight * this.pricePerPound));
    }

}

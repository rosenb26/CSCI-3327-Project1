
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class FishMarket {

    private Seafood[] items;
    private Seafood[] seafoodFromCSV;

    /**
     * Constructs a FishMarket that can hold a given number of Seafood items.
     * @param n The number of Seafood items the FishMarket can hold.
     */
    public FishMarket(int n) {
        this.items = new Seafood[n];
        this.seafoodFromCSV = new Seafood[n];
    }

    /**
     * Generates random Seafood objects (Fish, Crab, Scallop, Shrimp) and stores
     * them in this.items.
     *
     * @param n The relative "weight" given to Fish, to increase the chance of a
     * Fish object being chosen at random. For example, n = 10 will make it 10
     * times more likely that a Fish is selected than any of the other Seafood
     * objects.
     */
    public void generateSeafood(int n) {

        Random rng = new Random();

        for (int i = 0; i < this.items.length; i++) {

            double weight = rng.nextDouble();
            int seafoodChoice = rng.nextInt(n + 3);

            if (seafoodChoice < n) {
                this.items[i] = new Fish(weight * 5);
            }
            else if (seafoodChoice == n) {
                this.items[i] = new Shrimp(weight * 2);
            }
            else if (seafoodChoice == n + 1) {
                this.items[i] = new Scallop(weight);
            }
            else {
                this.items[i] = new Crab(weight * 10);
            }
        }
    }

    /**
     * Writes the Seafood items in this.items to a CSV file.
     */
    public void writeItemsToFile(String fileName) {
        try {
            PrintWriter writer = new PrintWriter(fileName);
            
            // write the header
            writer.println("Index, Type, Weight, Price");

            for (int i = 0; i < this.items.length; i++) {
                writer.println(i + ", " + this.items[i].getClass().getSimpleName() + ", " + this.items[i].getWeight() + ", " + this.items[i].getPrice());
            }
            writer.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Couldn't write to the specified file.");
        }
    }

    /**
     *
     * 
     */
    public void readItemsFromFile() {
        try {
            Scanner reader = new Scanner(new File("seafood.csv"));
            
             //skip the header
            reader.nextLine();
            
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] temp = line.split(",");
                double weight = Double.valueOf(temp[2]);
                int index = Integer.valueOf(temp[0]);

                if (temp[1].equals("Fish")) {
                    this.seafoodFromCSV[index] = new Fish(weight);
                }
                else if (temp[1].equals("Shrimp")) {
                    this.seafoodFromCSV[index] = new Shrimp(weight);
                }
                else if (temp[1].equals("Scallop")) {
                    this.seafoodFromCSV[index] = new Scallop(weight);
                }
                else {
                    this.seafoodFromCSV[index] = new Crab(weight);
                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Couldn't read from the specified file.");
        }
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < this.seafoodFromCSV.length; i++) {
            result += "Index: " + i + "; Type: " + this.items[i].getClass().getSimpleName()
                    + "; Weight: " + this.items[i].getWeight() + "; Price: " + this.items[i].getPrice() + "\n";
        }
        return result;
    }

}


public class FishMarketTester {

    
    public static void main(String[] args) throws Exception {
        
        // create a FishMarket with 1000 Seafood items
        FishMarket market = new FishMarket(1000);
        
        /* Each Seafood item has equal weight, i.e. each type of Seafood item 
        is equally likely to be chosen.*/
        market.generateSeafood(1);
        market.writeItemsToFile("fairSelection.csv");
        
        /* Make Fish 10 times more likely to be chosen. */
        market.generateSeafood(10);
        market.writeItemsToFile("weightedSelection.csv");
        
        // load the data which were written to the the first file
        market.readItemsFromFile("fairSelection.csv");
        
        // demonstrate the toString() method for funsies
        System.out.println(market);
        
    }


}

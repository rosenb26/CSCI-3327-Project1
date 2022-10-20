
public class MontyHallTester {

    public static void main(String[] args) {
        /**
         * The original problem had three doors, but the gimmick becomes
         * increasingly obvious as the number of doors increases.
         */
        MontyHall m = new MontyHall(3, 1000);
        m.noSwitch();
        System.out.println("Win % without switching: " + m.getHits() / m.getTrials());
        m.resetHits();
        m.yesSwitch();
        System.out.println("Win % ith switching: " + m.getHits() / m.getTrials());
    }
}

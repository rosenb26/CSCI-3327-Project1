
import java.util.Random;

/**
 * A class that simulates the Monty Hall problem with any number of doors.
 * Simulates the probability of winning the prize by switching and by not
 * switching.
 */
public class MontyHall {

    private Random rng;
    private double hits;
    private int doors;
    private int trials;

    /**
     *
     * @param doors The number of doors in the game.
     * @param trials The number of trials in the simulation.
     */
    public MontyHall(int doors, int trials) {
        this.rng = new Random();
        this.doors = doors;
        this.trials = trials;
    }

    /**
     * Simulate the player choosing a door randomly and not switching to a
     * different door after the host reveals all but one door. If the player
     * correctly chooses the door with the prize (car), increment the number of
     * hits.
     */
    public void noSwitch() {
        for (int i = 0; i < this.trials; i++) {
            int carDoor = this.rng.nextInt(this.doors) + 1;
            int playerChoice = this.rng.nextInt(this.doors) + 1;
            if (carDoor == playerChoice) {
                this.hits++;
            }

        }
    }

    /**
     * Simulate the player choosing a door randomly and switching to a different
     * door after the host reveals all but one door. If the player correctly
     * chooses the door with the prize (after switching, increment the number
     * of hits.
     */
    public void yesSwitch() {
        for (int i = 0; i < this.trials; i++) {
            int carDoor = this.rng.nextInt(this.doors) + 1;
            int playerChoice = this.rng.nextInt(this.doors) + 1;

            /**
             * If the player miraculously chose the door with the prize, then
             * the host chooses any other door (all of which contain a dud) and
             * the player switches.
             */
            if (carDoor == playerChoice) {
                /**
                 * The purpose of this if/else block sequence is to make sure
                 * that the player does not win the prize after switching.
                 */
                if (carDoor == 0) {
                    playerChoice = 1;
                }
                else {
                    playerChoice = 0;
                }
            }

            /**
             * The player switches, and always ends up choosing the door with
             * the prize, since that's the only door the host leaves unrevealed.
             */
            else {
                playerChoice = carDoor;
            }

            if (carDoor == playerChoice) {
                this.hits++;
            }
        }
    }

    public double getHits() {
        return this.hits;
    }

    public int getTrials() {
        return this.trials;
    }

    public void resetHits() {
        this.hits = 0;
    }

}

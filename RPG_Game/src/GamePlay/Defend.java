/** *
 *
 * The Defend class represents a defend action that a character (RPGmodel) can perform.
 * The execute method defines the logic for the defense action, adjusting the defender's stats and displaying the updated stats.
 * The comments added to each line or section help elucidate the functionality and purpose of the code.
 * Name: Chi Yan Cheung
 * SID: 15950216
 *
 * COMP603
 * Project 2 - RPG_Game
 *
 *
 * */
package Gameplay;

// Import the RPGmodel class for character properties and methods
import CharactorModel.RPGmodel;

// Define the Defend class
public class Defend {

    // Declare a private member variable for the defending character
    private RPGmodel defender;

    // Constructor for the Defend class that takes in the defending character
    public Defend(RPGmodel defender) {
        // Initialize the defender member variable with the provided character
        this.defender = defender;
    }

    // The execute method that performs the defend operation.
    public void execute() {
        // When defending, you can modify stats as needed:
        // Example: Increase defender's defence and recover some health
        defender.setDefence(defender.getDefence() + 3);
        defender.setHealth(defender.getHealth() + 5);

        // Print out the current stats of the defender after performing the defend action
        System.out.println(defender.getName() + " stats after defending:");
        System.out.println("Health: " + defender.getHealth());
        System.out.println("Attack Power: " + defender.getAttackPower());
        System.out.println("Defence: " + defender.getDefence());
    }
}

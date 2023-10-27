/** *
 *
 * The Attack class represents an attack action that a character (RPGmodel) can perform.
 * The execute method defines the logic for the attack, modifying the attacker's stats and printing out the updated stats.
 * The comments added to each line or section help explain the functionality and purpose of the code.
 * Name: Chi Yan Cheung
 * SID: 15950216
 *
 * COMP603
 * Project 2 - RPG_Game
 *
 *
 * */
package GamePlay;

// Import the RPGmodel class for character properties and methods
import CharactorModel.RPGmodel;

// Define the Attack class
public class Attack {

    // Declare a private member variable for the attacking character
    private RPGmodel attacker;

    // Constructor for the Attack class that takes in the attacking character
    public Attack(RPGmodel attacker) {
        // Initialize the attacker member variable with the provided character
        this.attacker = attacker;
    }

    // The execute method that performs the attack operation
    public void execute() {
        // After the attack, you can modify stats as needed:
        // Example: Increase attacker's attackPower, decrease its defence and decrease health
        attacker.setAttackPower(attacker.getAttackPower() + 2);
        attacker.setDefence(attacker.getDefence() - 1);
        attacker.setHealth(attacker.getHealth() - 2);

        // Print out the current stats of the attacker after performing the attack
        System.out.println(attacker.getName() + " stats after attack:");
        System.out.println("Health: " + attacker.getHealth());
        System.out.println("Attack Power: " + attacker.getAttackPower());
        System.out.println("Defence: " + attacker.getDefence());
    }
}

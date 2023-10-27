/** *
 *
 * The Warrior class, derived from the RPGmodel class, represents a specialized RPG character with predefined attributes for health, attack power, and defence.
 * It possesses a unique attack method that deducts the target's health based on its attack power and provides a console output narrating the attack details.
 *
 * Name: Chi Yan Cheung
 * SID: 15950216
 *
 * COMP603
 * Project 2 - RPG_Game
 *
 * */
package CharactorModel;

// Define the Warrior class which inherits from the RPGmodel class
class Warrior extends RPGmodel {

    // Constructor for the Warrior class with parameters for name and gender
    public Warrior(String name, String gender) {
        // Call the constructor of the parent class (RPGmodel) with default values for health, attack power, and speed
        super(name, gender, 100, 15, 5);
    }

    // Override the attack method from the parent class to provide specific implementation for the Warrior
    @Override
    public void attack(RPGmodel target) {
        // Reduce the target's health by the Warrior's attack power
        target.receiveDamage(attackPower);
        // Print a message to the console indicating the Warrior's attack action
        System.out.println(name + " attacked " + target.name + " with a sword for " + attackPower + " damage.");
    }
}

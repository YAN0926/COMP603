/** *
 * The Mage class, a subclass of RPGmodel, represents a specialized RPG character type.
 * When instantiated, it sets default attributes for health, attack power, and defence.
 * The class has its distinct attack method, where it diminishes the target's health based on the Mage's attack power.
 * After performing the attack, a console message narrates the Mage's action, specifying the target and the inflicted damage.
 *
 * Name: Chi Yan Cheung
 * SID: 15950216
 * Ranger Ng
 * SID: 20124370
 * 
 * COMP603
 * Project 2 - RPG_Game
 *
 ** */
package CharactorModel;

// Constructor for the Mage class with parameters for name and gender
class Mage extends RPGmodel {

    // Call the constructor of the parent class (RPGmodel) with default values for health, attack power, and speed
    public Mage(String name, String gender) {
        super(name, gender, 70, 25, 2);
    }

    // Override the attack method from the parent class to provide specific implementation for the Mage
    @Override
    public void attack(RPGmodel target) {
        // Reduce the target's health by the Mage's attack power
        target.receiveDamage(attackPower);
        // Print a message to the console indicating the Mage's attack action
        System.out.println(name + " cast a spell on " + target.name + " for " + attackPower + " damage.");
    }
}

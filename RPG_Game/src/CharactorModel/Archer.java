/** *
 *
 * The Archer class, derived from the RPGmodel, depicts a specific RPG character type. Upon creation,
 * it assigns default values for health, attack power, and defence.
 * The Archer has a unique attack method that deducts health from a target based on the Archer's attack power.
 * After the attack, a message is displayed, detailing the Archer's action and the damage dealt to the target.
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

// Define the Archer class which inherits from the RPGmodel class
class Archer extends RPGmodel {

    // Constructor for the Archer class with parameters for name and gender
    public Archer(String name, String gender) {
        // Call the constructor of the parent class (RPGmodel) with default values for health, attack power, and speed
        super(name, gender, 80, 20, 3);
    }

    // Override the attack method from the parent class to provide specific implementation for the Archer
    @Override
    public void attack(RPGmodel target) {
        // Reduce the target's health by the Archer's attack power
        target.receiveDamage(attackPower);
        // Print a message to the console indicating the Archer's attack action
        System.out.println(name + " shot an arrow at " + target.name + " for " + attackPower + " damage.");
    }
}

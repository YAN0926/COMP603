/**
 *
 * The Enemy class is a specialized version of the Character class, representing adversaries in the game.
 * The constructor initializes enemy attributes by invoking the superclass (Character) constructor.
 * The overridden reset method allows for resetting both common character attributes, using the reset logic, and any enemy-specific attributes if present.
 *
 * Name: Chi Yan CHEUNG SID: 15905216
 * Name: Renger NG SID: 20124370
 *
 * COMP603 Software Development Project 2
 *
 */
package rpg_game;

public class Enemy extends Character {

    //Constructor to create an enemy character
    public Enemy(String name, int health, int attack, int defense) {
        // Call the superclass (Character) constructor to initialize enemy attributes
        super(name, health, attack, defense);
    }

    //Reset method to reset enemy-specific attributes
    @Override
    public void reset() {
        // Reset enemy-specific attributes here, if any
        super.reset(); // Call the superclass's reset method to reset common character attributes
        // Reset enemy-specific attributes here, if any
    }
}

/** *
 *
 * The UsePotion class represents the action of a character (RPGmodel) using a potion.
 * The execute method defines the logic for using the potion, altering the character's stats and presenting the updated stats.
 * The comments added to each line or section help clarify the functionality and purpose of the code.
 * 
 * Name: Chi Yan Cheung
 * SID: 15950216
 * Ranger Ng
 * SID: 20124370
 *
 * COMP603
 * Project 2 - RPG_Game
 *
 *
 * */
package Gameplay;

import CharactorModel.RPGmodel;

public class UsePotion {

    private RPGmodel character;

    public UsePotion(RPGmodel character) {
        this.character = character;
    }

    public void execute() {
        // When using a potion, you can modify stats as needed:
        // Example: Recover a significant amount of health and a bit of defence
        character.setHealth(character.getHealth() + 20);
        character.setDefence(character.getDefence() + 2);

        // Printout the current character stats after using the potion
        System.out.println(character.getName() + " stats after using a potion:");
        System.out.println("Health: " + character.getHealth());
        System.out.println("Attack Power: " + character.getAttackPower());
        System.out.println("Defence: " + character.getDefence());
    }
}

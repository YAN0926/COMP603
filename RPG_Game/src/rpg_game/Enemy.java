/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Renger NG
 * @author Chi Yan CHEUNG
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
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

public class Character {
    private String name;
    private int level;
    private int experience;
    private int health;
    private int initialHealth;
    private int attack;
    private int defense;
    private Inventory inventory;

    // Constructor
    public Character(String name, int health, int attack, int defense) {
        this.name = name;
        this.level = 1;
        this.experience = 0;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.initialHealth = health;
    }

    // Getter and Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int health) {
        this.health = health;
        if(health<0) {
        	this.health = 0;
        }
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    // Method to deduct health when taking damage
    public void takeDamage(int damage) {
 
		this.health -= damage;
		//when character is dead, ignore negative health
	    if(this.health<0) {
	    	this.health = 0;
	    }
  
    }

    // Method to calculate damage inflicted on a target character
    public int calculateDamage(Character target) {
        return Math.max(0, attack - target.defense);
    }

    // Method to check if the character is alive
    public boolean isAlive() {
        return this.health > 0;
    }

    public Inventory getInventory() {
        return inventory;
    }
    
    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExperience() {
        return experience;
    }

    // Method to gain experience
    public void setExperience(int experience) {
        this.experience = experience;
    }
    
    public void gainExperience(int amount) {
        experience += amount;
    }
    
    // Method to calculate and return the maximum health value
    public int getMaxHealth() {
        // Return the maximum health value based on the player's level or any other criteria
        return 100 + (level - 1) * 20;
    }
    
    public String getStats() {
    return "Name: " + name + ", Level: " + level + ", Health: " + health + ", Attack: " + attack + ", Defense: " + defense;
}
    
    // Method to reset character attributes
    public void reset() {
        // Reset character attributes
        this.health = initialHealth;
    }
}
/**
 *
 * The Character class models an RPG game character with attributes like name, level, health, attack, defense, and inventory.
 * It provides methods to initialize attributes, deal and take damage, check if the character is alive, and modify or access various attributes.
 * The character's maximum health is determined by their level, and a reset method reverts the health to its starting value.
 *
 * Name: Chi Yan CHEUNG SID: 15905216
 * Name: Renger NG SID: 20124370
 *
 * COMP603 Software Development Project 2
 *
 */
package rpg_game;

// This class represents a character in an RPG game
public class Character {

    // Instance variables for the character's attributes
    private String name; // Character's name
    private int level; // Character's level
    private int experience; // Experience points acquired by the character
    private int health; // Current health of the character
    private int initialHealth; // Initial health of the character when created
    private int attack; // Attack value of the character
    private int defense; // Defense value of the character
    private Inventory inventory; // Inventory object to store character's items

    // Constructor to initialize the character's attributes
    public Character(String name, int health, int attack, int defense) {
        this.name = name;
        this.level = 1; // Start at level 1
        this.experience = 0; // Start with 0 experience
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.initialHealth = health; // Store initial health value
    }

    // Getters and Setters for the character's attributes
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
        if (health < 0) { // Ensure the health doesn't drop below 
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

    // Reduces the character's health based on the damage received
    public void takeDamage(int damage) {
        this.health -= damage;
        // Ensure the health doesn't drop below 0
        if (this.health < 0) {
            this.health = 0;
        }

    }

    // Method to calculate damage inflicted on a target character
    public int calculateDamage(Character target) {
        return Math.max(0, attack - target.defense); // Ensure damage isn't negative
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

    // Set experience for the character
    public void setExperience(int experience) {
        this.experience = experience;
    }

    // Add experience to the character
    public void gainExperience(int amount) {
        experience += amount;
    }

    // Method to calculate and return the maximum health value
    public int getMaxHealth() {
        // Return the maximum health value based on the player's level or any other criteria
        return 100 + (level - 1) * 20;
    }

    // Return a string with the character's stats
    public String getStats() {
        return "Name: " + name + ", Level: " + level + ", Health: " + health + ", Attack: " + attack + ", Defense: " + defense;
    }

    // Method to reset character attributes
    public void reset() {
        // Reset character attributes
        this.health = initialHealth;
    }
}

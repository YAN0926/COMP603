/**
 *
 * The Item class represents game items with attributes like name, type, attack bonus, and defense bonus.
 * There are two constructors: one that takes individual attributes to create an item and another that constructs an item from a comma-separated string.
 * The class provides getter methods to access item attributes and an overridden toString method to generate a descriptive string representation.
 * Items can be of various types, such as swords or armor, each potentially providing bonuses to a player's attack or defense statistics.
 *
 * Name: Chi Yan CHEUNG SID: 15905216
 * Name: Renger NG SID: 20124370
 *
 * COMP603 Software Development Project 2
 *
 */
package rpg_game;

public class Item {

    private String name;
    private ItemType type;
    private int attackBonus; // Bonus attack points (for swords)
    private int defenseBonus; //Bonus defense points (for armor)

    // Constructor for creating an item from separate attributes
    public Item(String name, ItemType type, int attackBonus, int defenseBonus) {
        this.name = name;
        this.type = type;
        this.attackBonus = attackBonus;
        this.defenseBonus = defenseBonus;
    }

    // Constructor for creating an item from a string representation
    public Item(String itemString) {
        // Parse the itemString to initialize the item attributes
        String[] parts = itemString.split(","); // Assuming items are comma-separated

        if (parts.length == 4) {
            this.name = parts[0];
            this.type = ItemType.valueOf(parts[1]);
            this.attackBonus = Integer.parseInt(parts[2]);
            this.defenseBonus = Integer.parseInt(parts[3]);
        } else {
            // Handle invalid input data
            throw new IllegalArgumentException("Invalid item data: " + itemString);
        }
    }

    //Get the name of the item
    public String getName() {
        return name;
    }

    //Get the type of the item
    public ItemType getType() {
        return type;
    }

    //Get the bonus attack points provided by the item (for swords)
    public int getAttackBonus() {
        return attackBonus;
    }

    //Get the bonus defense points provided by the item (for armor)
    public int getDefenseBonus() {
        return defenseBonus;
    }

    //Override the toString method to provide a string representation of the item
    @Override
    public String toString() {
        return name + " (" + type + ")";
    }
}

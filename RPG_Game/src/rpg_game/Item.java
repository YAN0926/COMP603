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
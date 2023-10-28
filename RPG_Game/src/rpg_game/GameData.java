/**
 *
 * The GameData class encapsulates the core data components of the RPG game: the player's character and their associated inventory.
 * It offers a constructor for initializing these components and provides getter and setter methods to access and modify them.
 * This design ensures that the game's primary data elements are neatly bundled together, allowing for straightforward access and management.
 *
 * Name: Chi Yan CHEUNG SID: 15905216
 * Name: Renger NG SID: 20124370
 *
 * COMP603 Software Development Project 2
 *
 */
package rpg_game;

// This class represents the main game data, including the player's character and their inventory
public class GameData {

    // Member variables for storing the player's character and their inventory
    private Character player;
    private Inventory inventory;

    // Constructor to initialize the game data with a player and inventory
    public GameData(Character player, Inventory inventory) {
        this.player = player;
        this.inventory = inventory;
    }

    // Getter method to retrieve the player's character
    public Character getPlayer() {
        return player;
    }

    // Setter method to update the player's character
    public void setPlayer(Character player) {
        this.player = player;
    }

    // Getter method to retrieve the player's inventory
    public Inventory getInventory() {
        return inventory;
    }

    // Setter method to update the player's inventory
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}

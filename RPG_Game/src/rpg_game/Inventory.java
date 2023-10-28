/**
 *
 * The Inventory class represents a player's collection of game items. This class manages a list of items, When adding an item, the method ensures that no duplicates (based on the item's name) are added.
 * The design emphasizes encapsulation, as direct modifications to the internal item list are prevented, and interactions are managed through provided methods.
 *
 * Name: Chi Yan CHEUNG SID: 15905216
 * Name: Renger NG SID: 20124370
 *
 * COMP603 Software Development Project 2
 *
 */
package rpg_game;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

    private List<Item> items;

    public Inventory() {
        items = new ArrayList<>();

    }
    //Add an item to the inventory if it doesn't already exist

    public void addItem(Item item) {
        if (item != null) {
            // Check if the item already exists in the inventory by name
            boolean itemExists = items.stream().anyMatch(existingItem -> existingItem.getName().equals(item.getName()));

            if (!itemExists) {
                items.add(item);
            }
        }
    }

    public void addItems(List<Item> newItems) {
        for (Item newItem : newItems) {
            addItem(newItem);
        }
    }

    //Remove an item from the inventory
    public void removeItem(Item item) {
        items.remove(item);
    }

    //Get a list of items in the inventory
    public List<Item> getItems() {
        return items;
    }

    // Clear all items from the inventory
    public void clearItems() {
        items.clear();
    }
}

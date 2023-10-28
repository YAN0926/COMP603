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
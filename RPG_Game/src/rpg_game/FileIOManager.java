package rpg_game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileIOManager {
    private static final String SAVE_DIRECTORY_PATH = "./resources/saves";
    private static final String SAVE_FILE_NAME = "game_save.txt";
    private static final String SAVE_FILE_PATH = SAVE_DIRECTORY_PATH + File.separator + SAVE_FILE_NAME;

    public static void saveGame(GameData gameData) {
        try {
            File directory = new File(SAVE_DIRECTORY_PATH);
            if (!directory.exists()) {
                directory.mkdirs(); // Create the directory if it doesn't exist
            }

            // Create a FileWriter to write game data to the text file
            FileWriter writer = new FileWriter(SAVE_FILE_PATH);

            // Write game data to the file
            writer.write("Player:\n");
            writer.write("Name: " + gameData.getPlayer().getName() + "\n");
            writer.write("Level: " + gameData.getPlayer().getLevel() + "\n");
            writer.write("Experience: " + gameData.getPlayer().getExperience() + "\n");
            writer.write("Health: " + gameData.getPlayer().getHealth() + "\n");
            writer.write("Attack: " + gameData.getPlayer().getAttack() + "\n");
            writer.write("Defense: " + gameData.getPlayer().getDefense() + "\n");

            writer.write("Inventory Items:\n");
            for (Item item : gameData.getInventory().getItems()) {
                writer.write(item.getName() + "," + item.getType() + "," + item.getAttackBonus() + "," + item.getDefenseBonus() + "\n");
            }
            writer.write("End Inventory Items\n");

            // Close the writer
            writer.close();
        } catch (IOException e) {
            System.err.println("Error saving game data: " + e.getMessage());
        }
    }

    public static GameData loadGame() {
        Character player = null;
        Inventory inventory = new Inventory();

        
        try {
            // Create a FileReader to read game data from the text file
            FileReader reader = new FileReader(SAVE_FILE_PATH);
            BufferedReader bufferedReader = new BufferedReader(reader);

            // Initialize variables to store the loaded data
            String line;
            String name = null;
            int level = 0;
            int experience = 0;
            int health = 0;
            int attack = 0;
            int defense = 0;
            List<Item> inventoryItems = new ArrayList<>(); // Create a list of items

            // Read game data from the file and parse the attributes
            while ((line = bufferedReader.readLine()) != null) {
                if (line.startsWith("Name: ")) {
                    name = line.substring("Name: ".length());
                } else if (line.startsWith("Level: ")) {
                    level = Integer.parseInt(line.substring("Level: ".length()));
                } else if (line.startsWith("Experience: ")) {
                    experience = Integer.parseInt(line.substring("Experience: ".length()));
                } else if (line.startsWith("Health: ")) {
                    health = Integer.parseInt(line.substring("Health: ".length()));
                } else if (line.startsWith("Attack: ")) {
                    attack = Integer.parseInt(line.substring("Attack: ".length()));
                } else if (line.startsWith("Defense: ")) {
                    defense = Integer.parseInt(line.substring("Defense: ".length()));
                } else if (line.equals("Inventory Items:")) {
                    // Read inventory items by parsing item attributes
                    while (!(line = bufferedReader.readLine()).equals("End Inventory Items")) {
                        String[] itemAttributes = line.split(",");
                        if (itemAttributes.length == 4) {
                            String itemName = itemAttributes[0]; // Change the variable name here
                            ItemType type = ItemType.valueOf(itemAttributes[1]);
                            int attackBonus = Integer.parseInt(itemAttributes[2]);
                            int defenseBonus = Integer.parseInt(itemAttributes[3]);
                            inventoryItems.add(new Item(itemName, type, attackBonus, defenseBonus)); // Use the new variable name
                        } else {
                            System.err.println("Invalid item data: " + line);
                        }
                    }
                }
            }


            // Close the reader
            bufferedReader.close();

            // Create the player and set the loaded data
            player = new Character("null", 0, 0, 0);
            player.setName(name);
            player.setLevel(level);
            player.setExperience(experience);
            player.setHealth(health);
            player.setAttack(attack);
            player.setDefense(defense);

            inventory.clearItems();

            // Add items to inventoryItems...

            inventory.addItems(inventoryItems); // Add the list of items to the inventory

        } catch (IOException e) {
            System.err.println("Error loading game data: " + e.getMessage());
        }

        return new GameData(player, inventory);
    }
}

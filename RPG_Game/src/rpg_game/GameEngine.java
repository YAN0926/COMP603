/**
 *
 * The GameEngine class serves as the main logic controller for the RPG game.
 * It manages the game's quests, the player's stats, inventory, and interactions with enemies.
 * The class is initialized with a reference to the game's GUI, ensuring that game play can be represented visually to the user.
 * Among its responsibilities, the GameEngine initializes available quests and items, allows starting a new game, and displays player stats and inventory.
 * Additionally, it offers functionality to save and load game states, handle player decisions, simulate battles with enemies, and manage player level-ups.
 * The engine also interacts with the GUI to update player statistics and handle various game play events.
 *
 * Name: Chi Yan CHEUNG SID: 1590521
 * Name: Renger NG SID: 20124370
 *
 * COMP603 Software Development Project 2
 *
 */
package rpg_game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

// This class serves as the game's core logic engine, managing quests, items, player stats, and battles.
public class GameEngine {

    // Class member variables
    private List<Quest> quests; // List to store all available quests in the game
    public Character player; // The main player character
    private Enemy currentEnemy; // The current enemy the player is battling
    public Inventory myInventory; // The player's inventory of items
    private List<Item> availableItems; // Items that can be discovered during the game
    public boolean gameFinished; // Flag to check if the game is over
    private static Random random; // Random generator for probabilistic events
    private int totalBonusAttack; // Total attack bonus from equipped items
    private int totalBonusDefense; // Total defense bonus from equipped items
    private RPGGameGUI gui; // Reference to the game's GUI
    private boolean isGameStarted = false; // Flag to check if the game has starte

    // Constructor that initializes the game engine and ties it to the game's GUI
    public GameEngine(RPGGameGUI gui) {
        this.gui = gui;
        quests = new ArrayList<>();
        myInventory = new Inventory();
        initializeQuests();
        initializeItems();

    }

    //-------------------------new added 2
    // Method to initialize available items in the game
    private void initializeItems() {
        // Initialize items
        availableItems = new ArrayList<>();
        availableItems.add(new Item("Sword of Power", ItemType.SWORD, 10, 0));
        availableItems.add(new Item("Iron Armor", ItemType.ARMOR, 0, 5));
        availableItems.add(new Item("Magic Wand", ItemType.STAFF, 5, 0));
        availableItems.add(new Item("Leather Boots", ItemType.BOOTS, 0, 2));
    }

    // Method to start a new game with a provided player name
    public void startNewGame(String playerName) {
        if (playerName != null && !playerName.trim().isEmpty()) {
            // Valid player name provided
            isGameStarted = true; // Set the game as started
            player = new Character(playerName, 100, 20, 10);
            gameFinished = false;
        } else {
            // Empty or null player name
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    gui.displayMessage("Please enter a valid player name.");
                }
            });
        }
    }

    // Method to display the player's character stats
    public void viewCharacterStats() {
        totalBonusAttack = 0;
        for (Item item : myInventory.getItems()) {
            if (item != null) {
                totalBonusAttack += item.getAttackBonus();
            }
        }

        totalBonusDefense = 0;
        for (Item item : myInventory.getItems()) {
            if (item != null) {
                totalBonusDefense += item.getDefenseBonus();
            }
        }

        // Implement logic to display character stats
        if (isGameStarted && player != null) {
            // Build the character stats message
            StringBuilder statsBuilder = new StringBuilder();
            statsBuilder.append("Character Stats:\n");
            statsBuilder.append("Name: ").append(player.getName()).append("\n");
            statsBuilder.append("Level: ").append(player.getLevel()).append("\n");
            statsBuilder.append("Experience: ").append(player.getExperience()).append("\n");
            int experienceNeededForNextLevel = (player.getLevel() * 100) - player.getExperience();
            statsBuilder.append("Experience Needed for Next Level: ").append(experienceNeededForNextLevel).append("\n");
            statsBuilder.append("Health: ").append(player.getHealth()).append("\n");
            statsBuilder.append("Attack: ").append(player.getAttack() + totalBonusAttack).append("\n");
            statsBuilder.append("Defense: ").append(player.getDefense() + totalBonusDefense).append("\n");
            // Add other character stats here
            // Display the message in the GUI on the EDT
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    gui.displayMessage(statsBuilder.toString());
                }
            });
        }

    }

    // Method to display the player's inventory of items
    public void viewInventory() {
        // Implement logic to display the player's inventory
        if (isGameStarted && player != null) {
            // Build the inventory message
            StringBuilder message = new StringBuilder();
            message.append("Inventory:\n");
            for (Item item : myInventory.getItems()) {
                if (item != null) {
                    message.append(item.getName()).append(" (").append(item.getType()).append(") ");
                    message.append("Attack Bonus: ").append(item.getAttackBonus()).append(" ");
                    message.append("Defense Bonus: ").append(item.getDefenseBonus()).append("\n");
                }
            }

            // Display the message in the GUI
            gui.displayMessage(message.toString());
        }

    }

    // Method to save the current game state
    public void saveGame() {
        if (isGameStarted && player != null) {
            // Create a GameData object with the current player and inventory data
            GameData gameData = new GameData(player, myInventory);

            // Use the FileIOManager to save the game data
            FileIOManager.saveGame(gameData);

            JOptionPane.showMessageDialog(gui.getFrame(), "Game data saved successfully");
        }
    }

    // Method to load a previously saved game state
    public void loadGame() {
        isGameStarted = true; // Set the game as started

        // Use the FileIOManager to load the game data
        GameData gameData = FileIOManager.loadGame();

        if (gameData != null) {
            player = gameData.getPlayer();
            myInventory.clearItems(); // Clear the current inventory
            myInventory.addItems(gameData.getInventory().getItems()); // Add items from loaded data to the inventory

            JOptionPane.showMessageDialog(gui.getFrame(), "Game data loaded successfully");
        }

        updatePlayerInfo(player.getHealth(), player.getAttack(), player.getDefense());

        gameFinished = false;
    }

    // Method to handle the user's choice when an option button is clicked
    public void handleOptionChoice(String option) {
        if (isGameStarted) {
            switch (option) {
                case "1":
                    viewCharacterStats();
                    break;
                case "2":
                    viewInventory();
                    break;
                case "3":
                    chooseQuestAndStartBattle(gui.getFrame());
                    break;
                case "4":
                    saveGame();
                    break;
                case "5":
                    loadGame();
                    break;
                default:
                    // Handle other options as needed
                    break;
            }
        }
    }

    // Method to initialize available quests in the game
    private void initializeQuests() {
        // Initialize enemies for each quest
        Enemy easyEnemy = new Enemy("Easy Enemy", 100, 15, 5);
        Enemy mediumEnemy = new Enemy("Medium Enemy", 200, 20, 10);
        Enemy hardEnemy = new Enemy("Hard Enemy", 300, 25, 15);
        Enemy veryHardEnemy = new Enemy("Very Hard Enemy", 400, 30, 20);
        Enemy extremeEnemy = new Enemy("Extreme Enemy", 500, 35, 25);

        quests.add(new Quest("Defeat the easy enemy.", Difficulty.EASY, easyEnemy));
        quests.add(new Quest("Defeat the medium enemy.", Difficulty.MEDIUM, mediumEnemy));
        quests.add(new Quest("Defeat the hard enemy.", Difficulty.HARD, hardEnemy));
        quests.add(new Quest("Defeat the challenging enemy.", Difficulty.VERY_HARD, veryHardEnemy));
        quests.add(new Quest("Defeat the ultimate enemy.", Difficulty.EXTREME, extremeEnemy));
    }

    // Increase the player's level and adjust their attributess
    private void levelUpPlayer() {
        player.setExperience(player.getExperience() - player.getLevel() * 100); // Deduct current needed exp for leveling up
        player.setLevel(player.getLevel() + 1);

        // Adjust player's attributes as they level up
        player.setHealth(player.getHealth() + 20);
        player.setAttack(player.getAttack() + 5);
        player.setDefense(player.getDefense() + 3);
        player.setHealth(player.getMaxHealth()); // Reset player's health when level up

        StringBuilder leveUpMessage = new StringBuilder();
        leveUpMessage.append("Congratulations! ").append(player.getName()).append(" has leveled up to level ").append(player.getLevel()).append("!\n");
        gui.displayMessage(leveUpMessage.toString());
    }

    // Simulate a battle between the player and the enemy in the quest
    private void battleEnemy(Quest quest) {
        currentEnemy = quest.getEnemy();
        currentEnemy.reset();

        while (player.isAlive() && currentEnemy.isAlive()) {
            gui.displayMessage(CombatSystem.battle(player, currentEnemy, totalBonusAttack, totalBonusDefense));

        }

        if (!player.isAlive()) {

            this.gameFinished = true;
            int choice = gui.showDefeatDialog();
            if (choice == 0) {
                // Start a new game
                startNewGame(player.getName());
            } else if (choice == 1) {
                // Load a saved game
                loadGame();
            }
            updatePlayerInfo(player.getHealth(), player.getAttack(), player.getDefense());

        } else {
            quest.complete();

            int probabilityThreshold = 0;
            switch (quest.getDifficulty()) {
                case EASY:
                    probabilityThreshold = 10;
                    break;
                case MEDIUM:
                    probabilityThreshold = 30;
                    break;
                case HARD:
                    probabilityThreshold = 50;
                    break;
                case VERY_HARD:
                    probabilityThreshold = 70;
                    break;
                case EXTREME:
                    probabilityThreshold = 90;
                    break;
            }

            // Generate a random number between 1 and 100
            random = new Random();
            int randomNumber = random.nextInt(100) + 1;

            // Check if the random number falls within the specified probability range
            if (randomNumber <= probabilityThreshold) {
                // Grant a random item from item pool
                myInventory.addItem(getRandomItem());
            }

            player.gainExperience(50 * quest.getDifficulty().getIntValue());
            // Award experience for completing the quest

            if (player.getExperience() >= player.getLevel() * 100) {
                levelUpPlayer();

            }
            updatePlayerInfo(player.getHealth(), player.getAttack() + totalBonusAttack, player.getDefense() + totalBonusDefense);
        }
    }

    // Return a random item from the availableItems list
    public Item getRandomItem() {
        random = new Random();
        if (availableItems.isEmpty()) {
            return null; // No items available
        }

        // Generate a random index to select an item from availableItems
        int randomIndex = random.nextInt(availableItems.size());
        Item selectedItem = availableItems.get(randomIndex);

        JOptionPane.showMessageDialog(gui.getFrame(), "Congratulations! You got an item " + availableItems.get(randomIndex).getName());
        // Remove the selected item from availableItems
        availableItems.remove(randomIndex);

        // Return the randomly selected item
        return selectedItem;
    }

    // Method to choose a quest and start a battle
    public void chooseQuestAndStartBattle(JFrame frame) {
        // Implement your quest selection logic here (e.g., using a dialog or GUI input)
        // For simplicity, let's assume a default quest is chosen
        String[] options = {
            "Defeat a Skeleton (1) Easy",
            "Defeat a Goblin (2) Medium",
            "Defeat a Elves (3) Hard",
            "Defeat a Orcs (4) Very hard",
            "Defeat a Dragons (5) Extreme"
        };

        String selectedOption = (String) JOptionPane.showInputDialog(
                frame,
                "Select a quest difficulty:",
                "Quest Selection",
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]
        );

        if (selectedOption != null) {
            // A selection was made (not canceled)
            int selectedValue = Integer.parseInt(selectedOption.split("\\(")[1].split("\\)")[0]);
            Quest selectedQuest = quests.get(selectedValue - 1);
            // Start the battle with the selected quest
            battleEnemy(selectedQuest);

        }
    }

    // Method to update player's displayed stats in the GUI
    public void updatePlayerInfo(int hp, int attack, int defense) {
        gui.updatePlayerInfo(hp, attack, defense);
    }

}

/**
 *
 * The DBManager class facilitates database operations for an RPG game.
 * It connects to a Derby database, setting up tables for characters, items, and their relationships if they don't exist.
 * The class provides methods to save and load a character's state and inventory.
 * Helper methods manage the intricacies of database interactions, such as updating or inserting records as needed.
 *
 * Name: Chi Yan CHEUNG SID: 15905216
 * Name: Renger NG SID: 20124370
 *
 * COMP603 Software Development Project 2
 *
 */
package rpg_game;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

// This class manages the database operations for the RPG game, including saving and loading game states
public class DBManager {

    // Database URL for the embedded Derby database
    private static final String URL = "jdbc:derby:RPG_db;create=true";

    // Database connection object
    private static Connection conn;

    // Constructor initializes the database connection and ensures the necessary tables exist
    public DBManager() {
        try {
            conn = DriverManager.getConnection(URL);
            if (conn != null) {
                createTablesIfNotExist();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Create the game-related tables if they don't already exist
    private void createTablesIfNotExist() {
        try ( Statement stmt = conn.createStatement()) {
            // Create table for characters
            stmt.execute("CREATE TABLE IF NOT EXISTS CharacterTable ("
                    + "character_id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                    + "name VARCHAR(255),"
                    + "level INT,"
                    + "experience INT,"
                    + "health INT,"
                    + "attack INT,"
                    + "defense INT)");

            // Create table for items
            stmt.execute("CREATE TABLE IF NOT EXISTS ItemTable ("
                    + "item_id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                    + "name VARCHAR(255),"
                    + "type VARCHAR(255),"
                    + "attackBonus INT,"
                    + "defenseBonus INT)");

            // Create table linking characters and their inventory items
            stmt.execute("CREATE TABLE IF NOT EXISTS InventoryTable ("
                    + "character_id INTEGER REFERENCES CharacterTable(character_id),"
                    + "item_id INTEGER REFERENCES ItemTable(item_id))");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Save the state of the game, including character and inventory details
    public void saveGame(Character character) {
        try {
            saveCharacter(character); // Save character's main details
            saveInventory(character); // Save character's inventory
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Save character's details to the database
    private void saveCharacter(Character character) throws SQLException {
        try ( Statement stmt = conn.createStatement()) {
            // Check if character already exists
            ResultSet rs = stmt.executeQuery("SELECT * FROM CharacterTable WHERE name = '" + character.getName() + "'");
            if (rs.next()) {
                // Update character record
                stmt.executeUpdate("UPDATE CharacterTable SET "
                        + "level = " + character.getLevel() + ", "
                        + "experience = " + character.getExperience() + ", "
                        + "health = " + character.getHealth() + ", "
                        + "attack = " + character.getAttack() + ", "
                        + "defense = " + character.getDefense()
                        + " WHERE name = '" + character.getName() + "'");
            } else {
                // Insert new character record
                stmt.executeUpdate("INSERT INTO CharacterTable (name, level, experience, health, initialHealth, attack, defense) VALUES ('"
                        + character.getName() + "', "
                        + character.getLevel() + ", "
                        + character.getExperience() + ", "
                        + character.getHealth() + ", "
                        + character.getAttack() + ", "
                        + character.getDefense() + ")");
            }
        }
    }

    // Save character's inventory to the database
    private void saveInventory(Character character) throws SQLException {
        try ( Statement stmt = conn.createStatement()) {
            // Retrieve character_id from CharacterTable based on the character's name
            ResultSet charRS = stmt.executeQuery("SELECT character_id FROM CharacterTable WHERE name = '" + character.getName() + "'");
            int characterId = -1;
            if (charRS.next()) {
                characterId = charRS.getInt("character_id");
            }

            // Iterate through character's inventory items
            for (Item item : character.getInventory().getItems()) {
                // Check if item already exists
                ResultSet rs = stmt.executeQuery("SELECT * FROM ItemTable WHERE name = '" + item.getName() + "'");
                if (!rs.next()) {
                    // Insert new item record
                    stmt.executeUpdate("INSERT INTO ItemTable (name, type, attackBonus, defenseBonus) VALUES ('"
                            + item.getName() + "', '"
                            + item.getType().toString() + "', "
                            + item.getAttackBonus() + ", "
                            + item.getDefenseBonus() + ")");

                    // Fetch the newly inserted item's ID
                    rs = stmt.executeQuery("SELECT * FROM ItemTable WHERE name = '" + item.getName() + "'");
                    rs.next();
                }

                int itemId = rs.getInt("item_id");
                // Insert into InventoryTable
                stmt.executeUpdate("INSERT INTO InventoryTable (character_id, item_id) VALUES ("
                        + characterId + ", " + itemId + ")");
            }
        }
    }

    // Load the game state for a given character from the database
    public Character loadGame(String characterName) {
        try {
            Character character = loadCharacter(characterName); // Load character details
            loadInventory(character);  // Load character's inventory
            return character;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Load character details from the database
    private Character loadCharacter(String characterName) throws SQLException {
        try ( Statement stmt = conn.createStatement()) {
            // Query character details
            ResultSet rs = stmt.executeQuery("SELECT * FROM CharacterTable WHERE name = '" + characterName + "'");
            if (rs.next()) {
                // Create and return a Character object based on fetched data
                Character character = new Character(rs.getString("name"), rs.getInt("health"), rs.getInt("attack"), rs.getInt("defense"));
                character.setLevel(rs.getInt("level"));
                character.setExperience(rs.getInt("experience"));
                return character;
            } else {
                return null;
            }
        }
    }

// Load character's inventory from the database
    private void loadInventory(Character character) throws SQLException {
        try ( Statement stmt = conn.createStatement()) {
            // Retrieve character_id from CharacterTable based on the character's name
            ResultSet charRS = stmt.executeQuery("SELECT character_id FROM CharacterTable WHERE name = '" + character.getName() + "'");
            int characterId = -1;
            if (charRS.next()) {
                characterId = charRS.getInt("character_id");
            }

            // Fetch associated items for the character
            ResultSet rs = stmt.executeQuery("SELECT * FROM InventoryTable JOIN ItemTable ON InventoryTable.item_id = ItemTable.item_id WHERE character_id = " + characterId);
            while (rs.next()) {
                Item item = new Item(rs.getString("name"), ItemType.valueOf(rs.getString("type")), rs.getInt("attackBonus"), rs.getInt("defenseBonus"));
                character.getInventory().addItem(item);
            }
        }
    }

}

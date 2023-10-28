package rpg_game;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBManager {

    private static final String URL = "jdbc:derby:RPG_db;create=true";
    private static Connection conn;

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

    private void createTablesIfNotExist() {
        try ( Statement stmt = conn.createStatement()) {
            // Create CharacterTable
            stmt.execute("CREATE TABLE IF NOT EXISTS CharacterTable ("
                    + "character_id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                    + "name VARCHAR(255),"
                    + "level INT,"
                    + "experience INT,"
                    + "health INT,"
                    + "attack INT,"
                    + "defense INT)");

            // Create ItemTable
            stmt.execute("CREATE TABLE IF NOT EXISTS ItemTable ("
                    + "item_id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                    + "name VARCHAR(255),"
                    + "type VARCHAR(255),"
                    + "attackBonus INT,"
                    + "defenseBonus INT)");

            // Create InventoryTable
            stmt.execute("CREATE TABLE IF NOT EXISTS InventoryTable ("
                    + "character_id INTEGER REFERENCES CharacterTable(character_id),"
                    + "item_id INTEGER REFERENCES ItemTable(item_id))");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to save the game state
    public void saveGame(Character character) {
        try {
            // Save character details
            saveCharacter(character);

            // Save character's inventory
            saveInventory(character);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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

  private void saveInventory(Character character) throws SQLException {
    try (Statement stmt = conn.createStatement()) {
        // Retrieve character_id from CharacterTable based on the character's name
        ResultSet charRS = stmt.executeQuery("SELECT character_id FROM CharacterTable WHERE name = '" + character.getName() + "'");
        int characterId = -1;
        if (charRS.next()) {
            characterId = charRS.getInt("character_id");
        }
        
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


    // Method to load the game state
    public Character loadGame(String characterName) {
        try {
            // Load character details
            Character character = loadCharacter(characterName);

            // Load character's inventory
            loadInventory(character);

            return character;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Character loadCharacter(String characterName) throws SQLException {
        try ( Statement stmt = conn.createStatement()) {
            // Query character details
            ResultSet rs = stmt.executeQuery("SELECT * FROM CharacterTable WHERE name = '" + characterName + "'");
            if (rs.next()) {
                // TODO: This requires the Character class to have a suitable constructor or setters to initialize
                // Creating a new Character object based on the fetched data
                Character character = new Character(rs.getString("name"), rs.getInt("health"), rs.getInt("attack"), rs.getInt("defense"));
                character.setLevel(rs.getInt("level"));
                character.setExperience(rs.getInt("experience"));
                // ... and so on for other fields

                return character;
            } else {
                return null;
            }
        }
    }

private void loadInventory(Character character) throws SQLException {
    try (Statement stmt = conn.createStatement()) {
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

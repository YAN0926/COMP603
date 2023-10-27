/** *
 *
 * The RPGcontroller class provides methods for managing character data in a database,
 * such as creating tables, checking table existence, updating character points, retrieving points,
 * deleting character data, and closing the database connection.
 * The comments added to each line or section help explain the functionality and purpose of the code.
 *
 * Name: Chi Yan Cheung
 * SID: 15950216
 * Ranger Ng
 * SID: 20124370
 *
 * COMP603
 * Project 2 - RPG_Game
 *
 *
 * */
package Controller;

// Import necessary classes for database operations
import DataBase.DBManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;

// Define the RPGcontroller class
public class RPGcontroller {

    // Declare private member variables for database management
    DBManager dbManager;
    private Connection conn;
    private Statement statement;

    // Constructor for the RPGcontroller class
    public RPGcontroller() {
        // Instantiate the DBManager object.
        dbManager = new DBManager();
        // Get a connection to the database
        conn = dbManager.getConnection();
        try {
            // Create a statement object for executing SQL queries
            statement = conn.createStatement();
        } catch (SQLException ex) {
            // Print the stack trace if there's an exception
            ex.printStackTrace();
        }
    }

    // Method to create a table and insert character status 
    public void createStatusTable(String character, int points) {
        try {
            // Check if the table exists, if not, create it
            if (!isTableExists()) {
                statement.addBatch("CREATE TABLE CharacterAchievements (\"character\" VARCHAR(100), points INT)");
            }
            // Insert the character and points into the table
            statement.addBatch("INSERT INTO CharacterAchievements (\"character\", points) VALUES ('" + character + "', " + points + ")");
            statement.executeBatch();
            System.out.println("Data inserted successfully!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    // Method to check if the table already exists in the database
    public boolean isTableExists() {
        try {
            // Use metadata to check for the table's existence
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null, null, "CharacterAchievements", null);
            if (tables.next()) {
                System.out.println("The table already exists.");
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    // Method to update character points
    public void updateStatus(String character, int newPoints) {
        String query = "UPDATE CharacterAchievements SET points = ? WHERE \"character\" = ?";
        try ( PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, newPoints);
            stmt.setString(2, character);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    // Method to get points of a character
    public int getCharacterPoints(String character) {
        String query = "SELECT points FROM CharacterAchievements WHERE \"character\" = ?";
        try ( PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, character);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("points");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return -1;  // Return -1 or some value to indicate character not found.
    }

    // Method to delete character status
    public void deleteStatus(String character) {
        String query = "DELETE FROM CharacterAchievements WHERE \"character\" = ?";
        try ( PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, character);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Successfully deleted record for: " + character);
            } else {
                System.out.println(character + " not found in the table.");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    // Method to close the database connection 
    public void closeConnection() {
        this.dbManager.closeConnection();
    }

}

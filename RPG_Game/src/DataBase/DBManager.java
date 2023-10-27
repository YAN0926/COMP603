/** *
 *
 * The DBManager class provides methods for managing a database connection, specifically for the RPG_Ebd database.
 * The class allows for establishing a connection, retrieving the connection object, and closing the connection.
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
package DataBase;

// Import necessary classes for database operations
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Define the DBManager class
public class DBManager {

    // Define constants for database username, password, and URL
    private static final String USER_NAME = "pdc";
    private static final String PASSWORD = "pdc";
    private static final String URL = "jdbc:derby:RPG_Ebd; create=true";

    // Declare a Connection object to manage the database connection
    Connection conn;

    // Constructor for the DBManager class that establishes a connection upon instantiation
    public DBManager() {
        establishConnection();
    }

    // Main method for testing purposes
    public static void main(String[] args) {
        DBManager dbManager = new DBManager();
        System.out.println(dbManager.getConnection());
    }

    // Method to return the database connection 
    public Connection getConnection() {
        return this.conn;
    }

    // Private method to establish a connection to the database
    private void establishConnection() {
        // If there's no existing connection, try to establish one
        if (this.conn == null) {
            try {
                // Use DriverManager to get a connection to the database using the specified URL, username, and password
                conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
                System.out.println("RPG_Ebd is connected please!");
            } catch (SQLException ex) {
                // Print any SQLException messages to the console
                System.out.println(ex.getMessage());
            }
        }
    }

    // Method to close the database connection
    public void closeConnection() {
        // If there's an existing connection, try to close it
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                // Print any SQLException messages to the console
                System.out.println(ex.getMessage());
            }
        }
    }
}

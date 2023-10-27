/** *
 *
 * The PointManager class provides a GUI interface for managing character points in the RPG game. It allows users to create or insert characters,
 * update their points, delete characters, and view their current points.
 * The comments added to each line or section help explain the functionality and purpose of the code.
 * Name: Chi Yan Cheung
 * SID: 15950216
 *
 * COMP603
 * Project 2 - RPG_Game
 *
 *
 * */
package GUIview;

// Import necessary classes for GUI and controller
import javax.swing.*;
import java.awt.*;
import Controller.RPGcontroller;

// Define the PointManager class which extends JFrame
public class PointManager extends JFrame {

    // Declare member variables for GUI components and the RPG controller
    private JTextField nameField;
    private JTextField pointsField;
    private JTextArea outputArea;
    private RPGcontroller achievementManager;
    JButton currentPointButton = new JButton("Current Point");

    // Constructor for the PointManager class
    public PointManager() {
        // Initialize the RPG controller
        achievementManager = new RPGcontroller();

        // Set the window title and size
        setTitle("Point Manager");
        setSize(400, 300);
        // Set the layout manager
        setLayout(new FlowLayout());
        
        setLocationRelativeTo(null); // Center the JFrame on the screen

        // Initialize GUI components
        nameField = new JTextField(10);
        pointsField = new JTextField(10);
        outputArea = new JTextArea(10, 20);
        outputArea.setEditable(false);  // Prevent manual editing of the output area
        // Create a scroll pane for the output area
        JScrollPane scrollPane = new JScrollPane(outputArea);
        // Create buttons for various actions.
        JButton createButton = new JButton("Create/Insert Character");
        JButton updateButton = new JButton("Update Points");
        JButton deleteButton = new JButton("Delete Character");

        // Add components to the JFrame
        add(new JLabel("Character Name:"));
        add(nameField);
        add(new JLabel("Points:"));
        add(pointsField);
        add(createButton);
        add(updateButton);
        add(deleteButton);
        add(scrollPane);  // Add the scroll pane (which contains the text area)
        add(currentPointButton);

        // Add action listeners to the buttons
        createButton.addActionListener(e -> createCharacter());
        updateButton.addActionListener(e -> updatePoints());
        deleteButton.addActionListener(e -> deleteCharacter());
        currentPointButton.addActionListener(e -> showCurrentPoint());

        // Set the default close operation and make the JFrame visible
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Method to create or insert a character into the database
    private void createCharacter() {
        String character = nameField.getText().trim();
        try {
            int points = Integer.parseInt(pointsField.getText().trim());
            achievementManager.createStatusTable(character, points);
            outputArea.setText(character + " created/inserted with " + points + " points.");
        } catch (NumberFormatException ex) {
            outputArea.setText("Error: Invalid number format for points.");
        }
    }

    // Method to display the current points of a character
    private void showCurrentPoint() {
        String character = nameField.getText().trim();
        int points = achievementManager.getCharacterPoints(character);
        if (points != -1) {
            outputArea.setText(character + " has " + points + " points.");
        } else {
            outputArea.setText(character + " not found.");
        }
    }

    // Method to update the points of a character in the database
    private void updatePoints() {
        String character = nameField.getText().trim();
        try {
            int points = Integer.parseInt(pointsField.getText().trim());
            achievementManager.updateStatus(character, points);
            outputArea.setText(character + " updated with " + points + " points.");
        } catch (NumberFormatException ex) {
            outputArea.setText("Error: Invalid number format for points.");
        }
    }

    // Method to delete a character from the database
    private void deleteCharacter() {
        String character = nameField.getText().trim();
        achievementManager.deleteStatus(character);
        outputArea.setText(character + " deleted from the database.");
    }

    // The main method to run the PointManager application
    public static void main(String[] args) {
        new PointManager();
    }
}

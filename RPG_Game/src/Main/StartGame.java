/** *
 *
 * The StartGame class is the entry point of the game.
 * It initializes the graphical user interface of the RPG game (RPGview) and sets it to be visible, effectively starting the game.
 * The comments added to each line or section help to clarify the functionality and purpose of the code.
 * Name: Chi Yan Cheung
 * SID: 15950216
 *
 * COMP603
 * Project 2 - RPG_Game
 *
 *
 * */
package Main;

// Import the RPGview class which provides the game's graphical user interface.
import GUIview.RPGview;

// Define the StartGame class, which initializes and starts the RPG game
public class StartGame {

    // The main method that serves as the entry point of the game
    public static void main(String[] args) {
        // Create an instance of the RPGview class to represent the game's view
        RPGview view = new RPGview();
        // Start the RPG game by making the view visible
        startRPGGame(view);
    }

    // A method to make the RPG game view visible
    private static void startRPGGame(RPGview view) {
        // Set the visibility of the view to true, thus showing the game window
        view.setVisible(true);
    }
}

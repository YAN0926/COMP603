package unitTest;

import org.junit.Before;
import org.junit.Test;

import rpg_game.GameEngine;
import rpg_game.RPGGameGUI;

import static org.junit.Assert.*;

public class GameEngineTest {

    private GameEngine gameEngine;
    private RPGGameGUI gui;

    @Before
    public void setUp() {
        // Create an instance of RPGGameGUI (not null)
        gui = new RPGGameGUI(); // You may need to provide any required constructor arguments

        // Create the game engine using the GUI instance
        gameEngine = new GameEngine(gui);
    }

    @Test
    public void testStartNewGame() {
        // Test starting a new game with a valid player name
        gameEngine.startNewGame("TestPlayer");
        assertNotNull(gameEngine.player); // Player should not be null
        assertFalse(gameEngine.gameFinished); // Game should not be finished
        // Add more assertions as needed
    }

    @Test
    public void testStartNewGameWithInvalidName() {
        // Test starting a new game with an invalid (empty) player name
        gameEngine.startNewGame("");
        assertNull(gameEngine.player); // Player should be null
        assertFalse(gameEngine.gameFinished); // Game should not be finished
        // Add more assertions as needed
    }

    @Test
    public void testViewInventory() {
        // Test viewing the player's inventory after starting a game
        gameEngine.startNewGame("TestPlayer");
        // Assuming you have a way to add items to the player's inventory
        //gameEngine.myInventory.addItem(new Item("Test Item", ItemType.SWORD, 5, 2));
        // Use assertions to check if the displayed inventory is correct
        // Add more assertions as needed
    }

}

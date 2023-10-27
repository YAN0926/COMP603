/** *
 *
 * The GameIntro class provides a method to get the introduction text for the RPG game.
 * The method getIntroduction returns a string with details about the game and the available character choices.
 * The comments added to each line or section help explain the purpose and content of the code
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
package GUIview;

// Define the GameIntro class
public class GameIntro {
    
    // Define a static method that returns the game introduction
    public static String getIntroduction() {
        return "Welcome to our RPG game!\n\n"
                + // Welcome message
                "In this game, you can choose from three different characters:\n"
                + // Introduction to character choices
                "- Archer\n"
                + // Archer character
                "- Mage\n"
                + // Mage character
                "- Warrior\n\n"
                + // Warrior character
                "Each character has its unique abilities and characteristics. Choose wisely and embark on your journey!"; // Closing statement
    }
}

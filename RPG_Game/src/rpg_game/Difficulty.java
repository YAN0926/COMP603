/**
 *
 * The Difficulty enum in the RPG game categorizes difficulty levels, each associated with a distinct integer.
 * For example, EASY is linked to the integer 1. A private constructor initializes the integer value for each level, and a public method allows for its retrieval.
 * This design provides a consistent method to reference difficulty levels through both a descriptive label and a numerical value.
 *
 * Name: Chi Yan CHEUNG SID: 15905216
 * Name: Renger NG SID: 20124370
 *
 * COMP603 Software Development Project 2
 *
 */
package rpg_game;

// This enum defines different difficulty levels for the RPG game
public enum Difficulty {
    // Define different difficulty levels along with their associated integer values
    EASY(1),
    MEDIUM(2),
    HARD(3),
    VERY_HARD(4),
    EXTREME(5);

    // Private field to store the integer value associated with each difficulty level
    private final int intValue;

    // Constructor for Difficulty enum, associates an integer value with each difficulty level
    private Difficulty(int intValue) {
        this.intValue = intValue;
    }

    // Getter method to retrieve the integer value associated with a difficulty level
    public int getIntValue() {
        return intValue;
    }
}

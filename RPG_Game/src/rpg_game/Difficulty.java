/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Renger NG
 * @author Chi Yan CHEUNG
 */

package rpg_game;

public enum Difficulty {
    // Define different difficulty levels along with their associated integer values
    EASY(1),
    MEDIUM(2),
    HARD(3),
    VERY_HARD(4),
    EXTREME(5);

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
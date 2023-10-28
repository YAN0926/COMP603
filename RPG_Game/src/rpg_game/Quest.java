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

public class Quest {
    private String description; // Description of the quest
    private boolean isCompleted; // Completion status of the quest
    private Difficulty difficulty; // Difficulty level of the quest
    private Enemy enemy; // The enemy associated with the quest

    public Quest(String description, Difficulty difficulty, Enemy enemy) {
        this.description = description;
        this.isCompleted = false;
        this.difficulty = difficulty;
        this.enemy = enemy; // Set the enemy property
    }

    //Get the description of the quest
    public String getDescription() {
        return description;
    }
    
    //Get the difficulty level of the quest
    public Difficulty getDifficulty() {
        return difficulty;
    }
    
    //Get the enemy associated with the quest
    public Enemy getEnemy() {
        return enemy;
    }

    //Check if the quest is completed
    public boolean isCompleted() {
        return isCompleted;
    }

    //Mark the quest as completed
    public void complete() {
        isCompleted = true;
    }
}
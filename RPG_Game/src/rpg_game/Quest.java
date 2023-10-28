/**
 *
 * The Quest class represents game quests, each with a description, completion status, difficulty level, and an associated enemy.
 * Upon initialization, quests are marked as incomplete.
 * The class provides getter methods to access its attributes and a complete method to mark a quest as finished.
 * The difficulty and enemy for each quest are essential for determining the challenge a player faces during that quest.
 *
 * Name: Chi Yan CHEUNG SID: 15905216
 * Name: Renger NG SID: 20124370
 *
 * COMP603 Software Development Project 2
 *
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

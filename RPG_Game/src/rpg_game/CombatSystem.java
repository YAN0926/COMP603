/**
 *
 * The CombatSystem class orchestrates battles between a player and an enemy in an RPG game.
 * It calculates damage dealt by each participant, factoring in random variations and bonuses.
 * The battle persists until one character is defeated. Throughout the fight, a StringBuilder accumulates a log detailing each event, and at the end, the method announces the winner and returns the complete battle narrative.
 *
 * Name: Chi Yan CHEUNG SID: 15905216
 * Name: Renger NG SID: 20124370
 *
 * COMP603 Software Development Project 2
 *
 */
package rpg_game;

import java.util.Random;

// This class handles the combat system for battles between a player and an enemy
public class CombatSystem {

    // Random number generator for generating random damage values
    private static Random random = new Random();

    // Conducts a battle between a player and an enemy, taking into account bonus attack and defense values
    public static String battle(Character player, Enemy enemy, int totalBonusAttack, int totalBonusDefense) {

        // StringBuilder to accumulate battle information and display to the user
        StringBuilder battleInfo = new StringBuilder();

        // Initial battle message indicating the participants
        battleInfo.append("A battle has started between ").append(player.getName()).append(" and ").append(enemy.getName()).append("\n");

        // Battle continues as long as both player and enemy are alive
        while (player.isAlive() && enemy.isAlive()) {

            // Calculate player's damage, add a random value and apply bonus attack
            int playerDamage = player.calculateDamage(enemy) + random.nextInt(11) + totalBonusAttack;
            // Calculate enemy's damage, add a random value and subtract bonus defense
            int enemyDamage = enemy.calculateDamage(player) + random.nextInt(6) - totalBonusDefense;

            // Ensure that damage values don't drop below zero
            if (playerDamage <= 0) {
                playerDamage = 0;
            }
            if (enemyDamage <= 0) {
                enemyDamage = 0;
            }

            // Inflict damage on the characters and display battle information
            enemy.takeDamage(playerDamage);
            player.takeDamage(enemyDamage);

            // Append the damage information to the battle message
            battleInfo.append(player.getName()).append(" hits ").append(enemy.getName()).append(" for ").append(playerDamage).append(" damage.").append("\n");
            battleInfo.append(enemy.getName()).append(" hits ").append(player.getName()).append(" for ").append(playerDamage).append(" damage.").append("\n");

            // Update the health status of the battling characters
            battleInfo.append(player.getName()).append(" current health: ").append(player.getHealth()).append("; ").append(enemy.getName()).append(" current health: ").append(enemy.getHealth()).append("\n");
        }

        // Determine the winner and append the result to the battle message
        if (player.isAlive()) {
            battleInfo.append(player.getName()).append(" wins!");
        } else {
            battleInfo.append(enemy.getName()).append(" wins!");
        }

        // Return the accumulated battle information
        return battleInfo.toString();
    }
}

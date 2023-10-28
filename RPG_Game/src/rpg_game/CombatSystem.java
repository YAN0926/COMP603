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

import java.util.Random;


public class CombatSystem {
    private static Random random = new Random();
  
    public static String battle(Character player, Enemy enemy, int totalBonusAttack, int totalBonusDefense) {
    	
        // Build the character stats message
        StringBuilder battleInfo = new StringBuilder();
        battleInfo.append("A battle has started between ").append(player.getName()).append(" and ").append(enemy.getName()).append("\n");

        while (player.isAlive() && enemy.isAlive()) {
            
            // Calculate player's damage, add a random value and apply bonus attack
            int playerDamage = player.calculateDamage(enemy)+random.nextInt(11)+totalBonusAttack;
            // Calculate enemy's damage, add a random value and subtract bonus defense
            int enemyDamage = enemy.calculateDamage(player)+random.nextInt(6)-totalBonusDefense;
            
            if(playerDamage <= 0) {
            	playerDamage = 0;
            }
            if(enemyDamage <= 0) {
            	enemyDamage = 0;
            }
           
            // Inflict damage on the characters and display battle information
            enemy.takeDamage(playerDamage);
            player.takeDamage(enemyDamage);
            battleInfo.append(player.getName()).append(" hits ").append(enemy.getName()).append(" for ").append(playerDamage).append(" damage.").append("\n");
            battleInfo.append(enemy.getName()).append(" hits ").append(player.getName()).append(" for ").append(playerDamage).append(" damage.").append("\n");
            battleInfo.append(player.getName()).append(" current health: ").append(player.getHealth()).append("; ").append(enemy.getName()).append(" current health: ").append(enemy.getHealth()).append("\n");
        }

        // Determine the winner of the battle
        if (player.isAlive()) {
        	battleInfo.append(player.getName()).append(" wins!");
        } else {
        	battleInfo.append(enemy.getName()).append(" wins!");
        }
        return battleInfo.toString();
    }
}
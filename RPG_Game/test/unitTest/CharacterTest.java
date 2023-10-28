package unitTest;

import org.junit.Test;
import rpg_game.Character;

import org.junit.Before;
import static org.junit.Assert.*;

public class CharacterTest {

    private Character character;

    @Before
    public void setUp() {
        character = new Character("Test Character", 100, 20, 10);
    }

    @Test
    public void testTakeDamage() {
        int initialHealth = character.getHealth();
        character.takeDamage(30);
        assertEquals(initialHealth - 30, character.getHealth());
    }

    @Test
    public void testCalculateDamage() {
        Character target = new Character("Target", 100, 5, 15);
        int damage = character.calculateDamage(target);
        assertEquals(5, damage);
    }

    @Test
    public void testIsAlive() {
        assertTrue(character.isAlive());
        character.setHealth(0);
        assertFalse(character.isAlive());
    }

    @Test
    public void testGainExperience() {
        int initialExperience = character.getExperience();
        character.gainExperience(50);
        assertEquals(initialExperience + 50, character.getExperience());
    }
}

/**
 * The Actionable interface provides a contract for character actions.
 * Classes implementing this interface must provide specific implementations for the declared methods.
 *
 * Name: Chi Yan Cheung
 * SID: 15950216
 *
 * COMP603
 * Project 2 - RPG_Game
 *
 */
package CharactorModel;

public interface Actionable {
    void castSpell();
    void useWeapon();
    void dodge();
}
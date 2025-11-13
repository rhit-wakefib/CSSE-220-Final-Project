package game;

/**
 * Manages attack mode state.
 * Encapsulates attack logic for better separation of concerns.
 * 
 * @author Braden Wakefield, Terrel Doxie
 * Help Citation: used claude for refactoring
 */
public class AttackController {
    private boolean attackMode = false;
    
    public void activateAttack() {
        attackMode = true;
    }
    
    public void deactivateAttack() {
        attackMode = false;
    }
    
    public boolean isAttackActive() {
        return attackMode;
    }
}
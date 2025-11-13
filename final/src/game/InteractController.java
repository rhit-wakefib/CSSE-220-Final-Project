package game;

/**
 * Manages attack mode state.
 * Encapsulates attack logic for better separation of concerns.
 * 
 * @author Braden Wakefield, Terrel Doxie
 * Help Citation: used claude for refactoring
 */
public class InteractController {
    private boolean interactMode  = false;
    
    public void activateInteract() {
    	interactMode = true;
    }
    
    public void deactivateInteract() {
    	interactMode  = false;
    }
    
    public boolean isInteractActive() {
        return interactMode ;
    }
}
package game;

import javax.swing.Timer;
import Entities.Player;

/**
 * Handles jump timing and cooldown logic.
 * Separates jump mechanics from input handling for better cohesion.
 * 
 * @author Braden Wakefield, Terrel Doxie
 */
public class JumpController {
    private static final int JUMP_COOLDOWN = 700;
    private static final int JUMP_DURATION = 500;
    
    private boolean canJump = true;
    private Player player;
    
    public JumpController(Player player) {
        this.player = player;
    }
    
    public void initiateJump() {
        if (!canJump) {
            return;
        }
        
        canJump = false;
        player.startJump();
        
        // Timer to end the jump after duration
        Timer jumpTimer = new Timer(JUMP_DURATION, e -> {
            player.endJump();
            ((Timer) e.getSource()).stop();
        });
        jumpTimer.start();
        
        // Timer to allow next jump after cooldown
        Timer cooldownTimer = new Timer(JUMP_COOLDOWN, e -> {
            canJump = true;
            ((Timer) e.getSource()).stop();
        });
        cooldownTimer.start();
    }
    
    public boolean canJump() {
        return canJump;
    }
}
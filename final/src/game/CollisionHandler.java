package game;

import java.util.ArrayList;
import java.util.List;
import Entities.Entity;
import Entities.Platform;

/**
 * Handles all collision detection and response logic.
 * Separates collision logic from game component for better cohesion.
 * 
 * @author Braden Wakefield, Terrel Doxie
 * Help Citation: used claude for refactoring
 */
public class CollisionHandler {
    private GameComponent gameComponent;
    private HudModel hud;
    private GamePanel gamePanel;
    
    private static final double PLAYER_COLLECTION_RADIUS = 30.0;
    
    public CollisionHandler(GameComponent gameComponent, HudModel hud) {
        this.gameComponent = gameComponent;
        this.hud = hud;
    }
    
    public void setGamePanel(GamePanel panel) {
        this.gamePanel = panel;
    }
    
    public void handleAllCollisions() {
        handleEnemyCollisions();
        handleBlockCollisions();
        handlePlatformCollisions();
        cleanupRemovedObjects();
    }
    
    private void handleEnemyCollisions() {
        List<Entity> enemiesToRemove = new ArrayList<>();
        List<Entity> enemies = gameComponent.getEnemies();
        List<Platform> platforms = gameComponent.getPlatforms();
        
        for (Entity enemy : enemies) {
            // Enemy-platform collisions
            for (Platform platform : platforms) {
                if (enemy.overlaps(platform)) {
                    enemy.collideWithPlatform(platform);
                }
            }
            
            // Enemy-player collisions
            if (enemy.overlaps(gameComponent.p)) {
                enemiesToRemove.add(enemy);
                hud.loseHealth1();
            }
        }
        
        enemies.removeAll(enemiesToRemove);
    }
    
    private void handleBlockCollisions() {
        if (gamePanel == null || !gamePanel.isAttackActive()) {
            return;
        }
        
        List<Block> blocksToRemove = new ArrayList<>();
        List<Block> blocks = gameComponent.getBlocks();
        
        for (Block block : blocks) {
            if (block.isNearby(gameComponent.p, PLAYER_COLLECTION_RADIUS)) {
                blocksToRemove.add(block);
                hud.addCoin(block.getValue());
            }
        }
        
        blocks.removeAll(blocksToRemove);
        gamePanel.deactivateAttack();
    }
    
    private void handlePlatformCollisions() {
        List<Platform> platforms = gameComponent.getPlatforms();
        
        // Platform-platform collisions
        for (int i = 0; i < platforms.size(); i++) {
            for (int j = i + 1; j < platforms.size(); j++) {
                Platform p1 = platforms.get(i);
                Platform p2 = platforms.get(j);
                if (p1.overlaps(p2)) {
                    p1.collideWithPlatform(p2);
                }
            }
        }
        
        // Player-platform collisions
        for (Platform platform : platforms) {
            if (gameComponent.p.overlaps(platform)) {
                gameComponent.p.collideWithPlatform(platform);
            }
        }
    }
    
    private void cleanupRemovedObjects() {
        List<Platform> platforms = gameComponent.getPlatforms();
        List<Platform> toRemove = new ArrayList<>();
        
        for (Platform platform : platforms) {
            if (platform.shouldRemove()) {
                toRemove.add(platform);
            }
        }
        
        platforms.removeAll(toRemove);
    }
}
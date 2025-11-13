package game;

import java.util.ArrayList;
import java.util.List;
import Entities.Entity;
import platforms.AbstractPlatform;

/**
 * Handles all collision detection and response logic.
 * Separates collision logic from game component for better cohesion.
 * 
 * @author Braden Wakefield, Terrel Doxie
 */
public class CollisionHandler {
    private GameComponent gameComponent;
    private HudModel hud;
    private GamePanel gamePanel;
    
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
        List<AbstractPlatform> platforms = gameComponent.getPlatforms();
        
        for (Entity enemy : enemies) {
            // Enemy-platform collisions
            for (AbstractPlatform platform : platforms) {
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
            if (isPlayerNearBlock(block)) {
                blocksToRemove.add(block);
                hud.addCoin(1);
            }
        }
        
        blocks.removeAll(blocksToRemove);
        gamePanel.deactivateAttack();
    }
    
    private boolean isPlayerNearBlock(Block block) {
        double dx = block.x - gameComponent.p.x;
        double dy = block.y - gameComponent.p.y;
        int radiusSum = block.radius + 30;
        return dx * dx + dy * dy <= radiusSum * radiusSum;
    }
    
    private void handlePlatformCollisions() {
        List<AbstractPlatform> platforms = gameComponent.getPlatforms();
        
        // Platform-platform collisions
        for (int i = 0; i < platforms.size(); i++) {
            for (int j = i + 1; j < platforms.size(); j++) {
                AbstractPlatform p1 = platforms.get(i);
                AbstractPlatform p2 = platforms.get(j);
                if (p1.overlaps(p2)) {
                    p1.collideWithPlatform(p2);
                }
            }
        }
        
        // Player-platform collisions
        for (AbstractPlatform platform : platforms) {
            if (gameComponent.p.overlaps(platform)) {
                gameComponent.p.collideWithPlatform(platform);
            }
        }
    }
    
    private void cleanupRemovedObjects() {
        List<AbstractPlatform> platforms = gameComponent.getPlatforms();
        List<AbstractPlatform> toRemove = new ArrayList<>();
        
        for (AbstractPlatform platform : platforms) {
            if (platform.shouldRemove()) {
                toRemove.add(platform);
            }
        }
        
        platforms.removeAll(toRemove);
    }
}
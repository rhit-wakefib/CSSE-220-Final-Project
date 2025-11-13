package game;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import Entities.Platform;

/**
 * Represents a collectible block (coin) in the game.
 * 
 * @author Charlie Kofahl
 **************************************************************************************** 
 *         REQUIRED HELP CITATION
 *         Used Claude for refactoring
 *************************************************************************************** 
 */
public class Block extends GameObject {
    
    private int value;
    private BufferedImage sprite;
    private boolean spriteLoaded = false;
    
    private static final double BLOCK_SIZE = 60; // diameter (radius * 2)
    private static final double COLLISION_RADIUS = 30; // radius for collision detection
    
    public Block(GameComponent gameComponent, double x, double y, int value) {
        super(gameComponent, x - COLLISION_RADIUS, y - COLLISION_RADIUS, 0, 0, BLOCK_SIZE, BLOCK_SIZE);
        this.value = value;
        loadSprite("US_One_Cent_Obv.png");
    }
    
    private void loadSprite(String imagePath) {
        try {
            sprite = ImageIO.read(Block.class.getResource(imagePath));
            spriteLoaded = true;
        } catch (IOException e) {
            System.err.println("Error loading block sprite: " + imagePath);
            e.printStackTrace();
            spriteLoaded = false;
        }
    }
    
    @Override
    public void drawOn(Graphics2D g2) {
        if (spriteLoaded) {
            g2.drawImage(sprite, (int)x, (int)y, (int)getWidth(), (int)getHeight(), null);
        } else {
            // Fallback: draw circle if sprite fails to load
            g2.fillOval((int)x, (int)y, (int)getWidth(), (int)getHeight());
        }
    }
    
    @Override
    public void collideWithPlatform(Platform otherPlatform) {
        // Blocks don't interact with platforms - they're static collectibles
    }
    
    /**
     * Checks if this block is within collection range of a game object.
     * Uses center-to-center distance with combined radii.
     */
    public boolean isNearby(GameObject other, double collectionRadius) {
        double thisCenterX = x + COLLISION_RADIUS;
        double thisCenterY = y + COLLISION_RADIUS;
        double otherCenterX = other.getX() + other.getWidth() / 2;
        double otherCenterY = other.getY() + other.getHeight() / 2;
        
        double dx = thisCenterX - otherCenterX;
        double dy = thisCenterY - otherCenterY;
        double radiusSum = COLLISION_RADIUS + collectionRadius;
        
        return dx * dx + dy * dy <= radiusSum * radiusSum;
    }
    
    public int getValue() {
        return value;
    }
    
    public static double getCollisionRadius() {
        return COLLISION_RADIUS;
    }
}
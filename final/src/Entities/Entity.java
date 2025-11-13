package Entities;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import game.GameComponent;
import game.GameObject;

/**
 * @author Braden Wakefield, Terrel Doxie
 * Help Citation: used CSSE220 materials
 */
public abstract class Entity extends GameObject {
    protected int health = 3;
    protected int damage;
    protected BufferedImage sprite;
    protected boolean spriteLoaded = false;
    
    private static final int GRAVITY = 3;
    
    public Entity(int x, int y, int xVelocity, int yVelocity, 
                  GameComponent gameComponent, int width, int height) {
        super(gameComponent, x, y, xVelocity, yVelocity, width, height);
    }
    
    protected void takeDamage() {
        health -= damage;
    }
    
    @Override 
    public void update() {
        applyGravity();
        updatePosition();
        enforceGroundConstraint();
    }
    
    protected void applyGravity() {
        this.y += GRAVITY;
    }
    
    protected abstract void updatePosition();
    
    protected void enforceGroundConstraint() {
        if (this.y >= gameComponent.getGroundY()) {
            this.y = gameComponent.getGroundY();
        }
    }
    
    /**
     * Loads a sprite from the given resource path.
     * Template method to be used by subclasses.
     * Returns the loaded sprite or null if loading fails.
     */
    protected BufferedImage loadSpriteFromResource(String resourcePath) {
        try {
            if (getClass().getResource(resourcePath) != null) {
                return ImageIO.read(getClass().getResource(resourcePath));
            } else {
                System.err.println("Sprite file not found: " + resourcePath);
                return null;
            }
        } catch (IOException e) {
            System.err.println("Error loading sprite: " + resourcePath);
            return null;
        }
    }
}
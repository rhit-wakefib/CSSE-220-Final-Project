package Entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import game.GameComponent;
import platforms.AbstractPlatform;

/**
 * @author Braden Wakefield, Terrel Doxie
 *Help Citation: used claude for refactoring
 * Player sprite: https://cl.pinterest.com/pin/785244885024471618/
 */
public class Player extends Entity {
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;
    
    private boolean isJumping = false;
    private String currentDirection = "left";
    
    // Cached sprites - loaded once at construction
    private BufferedImage leftSprite;
    private BufferedImage rightSprite;
    private BufferedImage jumpSprite;
    
    public Player(int x, int y, int xVelocity, int yVelocity, 
                  GameComponent gameComponent) {
        super(x, y, xVelocity, yVelocity, gameComponent, WIDTH, HEIGHT);
        loadAllSprites();
        sprite = leftSprite; // Set initial sprite
        spriteLoaded = (sprite != null);
    }
    
    /**
     * Load all sprites once during initialization
     */
    private void loadAllSprites() {
        leftSprite = loadSpriteFromResource("epicplayerss.png");
        rightSprite = loadSpriteFromResource("epicPRight.png");
        jumpSprite = loadSpriteFromResource("pDown.png");
    }
    
    public void moveLeft() {
        this.x -= xVelocity;
        currentDirection = "left";
        updateSprite();
    }
    
    public void moveRight() {
        this.x += xVelocity;
        currentDirection = "right";
        updateSprite();
    }
    
    public void startJump() {
        isJumping = true;
        currentDirection = "up";
        updateSprite();
    }
    
    public void performJump() {
        if (isJumping) {
            this.y -= yVelocity;
        }
    }
    
    public void endJump() {
        isJumping = false;
    }
    
    public boolean isJumping() {
        return isJumping;
    }
    
    @Override
    protected void applyGravity() {
        if (!isJumping) {
            super.applyGravity();
        }
    }
    
    @Override
    protected void updatePosition() {
        performJump();
    }
    
    /**
     * Switch to the appropriate cached sprite based on current direction
     */
    private void updateSprite() {
        if (currentDirection.equals("left")) {
            sprite = leftSprite;
        } else if (currentDirection.equals("right")) {
            sprite = rightSprite;
        } else if (currentDirection.equals("up")) {
            sprite = jumpSprite;
        }
        spriteLoaded = (sprite != null);
    }
    
    @Override
    public void drawOn(Graphics2D g2) {
        if (spriteLoaded) {
            int drawX = (int) (this.x - WIDTH);
            int drawY = (int) (this.y - HEIGHT);
            g2.drawImage(sprite, drawX, drawY, WIDTH, WIDTH, null);
        }
    }
    
    @Override
    public void collideWithPlatform(AbstractPlatform platform) {
        if (!isJumping) {
            this.y = platform.getY() - HEIGHT;
        }
    }
}
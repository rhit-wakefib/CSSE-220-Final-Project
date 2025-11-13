package Entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import game.GameComponent;
import platforms.AbstractPlatform;

/**
 * @author Braden Wakefield, Terrel Doxie
 * Help Citation: used claude for refactoring
 * https://favpng.com/png_view/minecraft-creeper-minecraft-creeper-character-png/XfFXQzHD
 */
public class Enemy extends Entity {
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;
    
    private boolean movingRight;
    
    // Cached sprites - loaded once at construction
    private BufferedImage leftSprite;
    private BufferedImage rightSprite;
    
    public Enemy(int x, int y, int xVelocity, int yVelocity, 
                 GameComponent gameComponent) {
        super(x, y, xVelocity, yVelocity, gameComponent, WIDTH, HEIGHT);
        this.movingRight = (xVelocity >= 0);
        loadAllSprites();
        updateSprite();
    }
    
    /**
     * Load all sprites once during initialization
     */
    private void loadAllSprites() {
        leftSprite = loadSpriteFromResource("eleft.png");
        rightSprite = loadSpriteFromResource("eright.png");
    }
    
    @Override
    protected void updatePosition() {
        this.x += this.xVelocity;
        
        if (isOffScreen()) {
            reverseDirection();
            movingRight = !movingRight;
            updateSprite();
        }
    }
    
    /**
     * Switch to the appropriate cached sprite based on movement direction
     */
    private void updateSprite() {
        if (movingRight) {
            sprite = rightSprite;
        } else {
            sprite = leftSprite;
        }
        spriteLoaded = (sprite != null);
    }
    
    @Override
    public void drawOn(Graphics2D g2) {
        if (spriteLoaded) {
            int drawX = (int) this.x - WIDTH;
            int drawY = (int) this.y - WIDTH;
            g2.drawImage(sprite, drawX, drawY, WIDTH, WIDTH, null);
        }
    }
    
    @Override
    public void collideWithPlatform(AbstractPlatform platform) {
        this.yVelocity = 0;
        this.y = platform.getY() - HEIGHT;
    }
}
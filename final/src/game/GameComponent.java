package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import Entities.Enemy;
import Entities.Entity;
import Entities.Player;
import platforms.AbstractPlatform;
import platforms.Platform;

/**
 * @author Braden Wakefield, Terrel Doxie, Charlie Kofahl
 * Help Citation: used CSSE220 materials
 */
public class GameComponent extends JComponent {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;
    public static final Color BG = Color.CYAN;
    public static final Color FG = Color.GREEN;
    
    private final int groundY = HEIGHT - (HEIGHT / 4);
    private final HudModel hud;
    
    private final List<AbstractPlatform> platforms = new ArrayList<>();
    private final List<Entity> enemies = new ArrayList<>();
    private final List<Block> blocks = new ArrayList<>();
    
    CollisionHandler collisionHandler;
    
    public Player p;
    
    public GameComponent(HudModel hud) {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.hud = hud;
        setOpaque(true);
        initializeGameObjects();
        this.collisionHandler = new CollisionHandler(this, hud);
    }
    
    private void initializeGameObjects() {
        this.p = new Player(400, groundY, 5, 10, this);
        
        platforms.add(new Platform(400, 300, 0, 0, this));
        platforms.add(new Platform(200, 350, 0, 0, this));
        
        enemies.add(new Enemy(300, 168, 10, 0, this));
        enemies.add(new Enemy(215, 170, 0, 5, this));
        enemies.add(new Enemy(225, 150, 2, 2, this));
        enemies.add(new Enemy(350, 170, -6, 5, this));
        
        blocks.add(new Block(600, groundY, 1));
        blocks.add(new Block(500, groundY, 1));
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        drawGround(g2);
        drawGameObjects(g2);
    }
    
    private void drawGround(Graphics2D g2) {
        g2.setColor(FG);
        Rectangle ground = new Rectangle(0, groundY, WIDTH, HEIGHT);
        g2.draw(ground);
        g2.fill(ground);
    }
    
    private void drawGameObjects(Graphics2D g2) {
        for (AbstractPlatform platform : platforms) {
            platform.drawOn(g2);
        }
        for (Entity enemy : enemies) {
            enemy.drawOn(g2);
        }
        for (Block block : blocks) {
            block.drawOn(g2);
        }
        p.drawOn(g2);
    }
    
    public int getGroundY() {
        return groundY;
    }
    
    public List<AbstractPlatform> getPlatforms() {
        return platforms;
    }
    
    public List<Entity> getEnemies() {
        return enemies;
    }
    
    public List<Block> getBlocks() {
        return blocks;
    }
    
    public void updateState() {
        hud.updateAll(WIDTH, HEIGHT);
        updateGameObjects();
        collisionHandler.handleAllCollisions();
        p.update();
    }
    
    private void updateGameObjects() {
        for (AbstractPlatform platform : platforms) {
            platform.update();
        }
        for (Entity enemy : enemies) {
            enemy.update();
        }
    }
}
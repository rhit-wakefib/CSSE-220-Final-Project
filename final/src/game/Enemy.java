package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Enemy extends Entity {

	int x, y;
    int dx = 3, dy = 2;
    int radius = 15;
    Color color = Color.RED;
    private BufferedImage sprite;
    private boolean spriteLoaded = false;

    public Enemy(int x, int y) {
        this.x = x;
        this.y = y;
        try {
			sprite = ImageIO.read(Enemy.class.getResource("tennis.png"));
			spriteLoaded = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			spriteLoaded = false;
			//e.printStackTrace();
		}
    }


    public void draw(Graphics2D g2) {
    	
    	if (spriteLoaded) {
    		int drawX = x - radius;
    		int drawY = y - radius;
    		int size = radius *2;
    		g2.drawImage(sprite, drawX, drawY, size, size, null);
    	} else {
    	
        g2.setColor(color);
        g2.fillOval(x - radius, y - radius, radius * 2, radius * 2);
        
    	}
    }

    public void move(int width, int height) {
        x += dx;
        y += dy;
        // wrap around the edges
        
        // Left/right
        if (x - radius < 0) { x = radius; dx = -dx; }
        else if (x + radius > width) { x = width - radius; dx = -dx; }

        // Top/bottom
        if (y - radius < 0) { y = radius; dy = -dy; }
        else if (y + radius > height) { y = height - radius; dy = -dy; }
    }

    
}

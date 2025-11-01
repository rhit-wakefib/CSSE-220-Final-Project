package Entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author's Braden Wakefield
 * 
 **************************************************************************************** 

 *         REQUIRED HELP CITATION

 * 

 *         DONE: "only used CSSE220 materials"

 *************************************************************************************** 
 *
 */

public class Enemy extends Entity {

	

    Color color = Color.RED;
    private BufferedImage sprite;
    private boolean spriteLoaded = false;

    public Enemy(int x, int y) {

    	super(x,y);
    	
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
    
}

package Entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.GameComponent;
import platforms.AbstractPlatform;

/**
 * @author's Braden Wakefield
 * 
 **************************************************************************************** 

 *         REQUIRED HELP CITATION

 *         DONE: "only used CSSE220 materials"

 *************************************************************************************** 
 *
 */

public class Enemy extends Entity {

	private static final int WIDTH = 80;
	private static final int HEIGHT = 20;

    Color color = Color.RED;
    private BufferedImage sprite;
    private boolean spriteLoaded = false;

    public Enemy(int x, int y, int xVelocity, int yVelocity, GameComponent gameComponent) {

		super(x, y, xVelocity, yVelocity, gameComponent, WIDTH, HEIGHT);    	
        try {
			sprite = ImageIO.read(Enemy.class.getResource("tennis.png"));
			spriteLoaded = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			spriteLoaded = false;
			//e.printStackTrace();
		}
    }


	@Override
	public void drawOn(Graphics2D g2) {
		if (spriteLoaded) {
    		int drawX = (int) this.getX() - radius;
    		int drawY = (int) this.getY() - radius;
    		int size = radius *2;
    		g2.drawImage(sprite, drawX, drawY, size, size, null);
    	} else {
    	
        g2.setColor(color);
        g2.fillOval((int)this.getX() - radius, (int)this.getY() - radius, radius * 2, radius * 2);		
    	}
	}
	
	@Override
	public void update() {
		super.update();
		if (isOffScreen()  ) {
			this.reverseDirection();
		}
	}
	
	@Override
	public void collideWithPlatform(AbstractPlatform otherPlatform) {
		this.reverseDirection();
		this.update();
//		bounced =true;
	}


    

}
    


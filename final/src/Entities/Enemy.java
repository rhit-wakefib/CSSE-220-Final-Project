package Entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.GameComponent;
import platforms.AbstractPlatform;

/**
 * @author's Braden Wakefield Terrel Doxie
 * 
 **************************************************************************************** 

 *         REQUIRED HELP CITATION

 *         DONE: "only used CSSE220 materials"

 *************************************************************************************** 
 *
 */

public class Enemy extends Entity {

	private static final int WIDTH = 20;
	private static final int HEIGHT = 20;

    Color color = Color.RED;


    public Enemy(int x, int y, int xVelocity, int yVelocity, GameComponent gameComponent) {

		super(x, y, xVelocity, yVelocity, gameComponent, WIDTH, HEIGHT);    	
		isPlayer = false;
		try {
        	if (Player.class.getResource("eleft.png") != null) {
				sprite = ImageIO.read(Player.class.getResource("eleft.png"));
				spriteLoaded = true;
	        	} else {
	        		System.out.println("no file could be found");
	        	}
		} catch (IOException e) {

			spriteLoaded = false;
			//e.printStackTrace();
			
		}
    }


	@Override
	public void drawOn(Graphics2D g2) {
		if (spriteLoaded) {
    		int drawX = (int) this.getX() - WIDTH;
    		int drawY = (int) this.getY() - WIDTH;
    		int size = WIDTH *2;
    		g2.drawImage(sprite, drawX, drawY, size, size, null);
    	} else {
    	
//        g2.setColor(color);
//        g2.fillOval((int)this.getX() - radius, (int)this.getY() - radius, radius * 2, radius * 2);		
    	}
	}
	
	@Override
	public void update() {
		super.update();
		if (isOffScreen()  ) {
			this.reverseDirection();
			if(this.getX() <= 0) {
				eRight = true;
			} else {
				eLeft = true;
			}
		}
	}
	
	@Override
	public void collideWithPlatform(AbstractPlatform plat) {
		this.yVelocity = 0;
		this.y = plat.getY() - HEIGHT;
		if(!spriteLoaded) eLeft = true;
		this.update();
//		bounced =true;
	}


    

}
    


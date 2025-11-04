package Entities;

import java.awt.Color;
import java.awt.Graphics2D;


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

public class Player extends Entity {
	
	private static final int WIDTH = 30;
	private static final int HEIGHT = 30;


    Color color = Color.RED;
//    private BufferedImage sprite;
//    private boolean spriteLoaded = false;

    public Player(int x, int y, int xVelocity, int yVelocity, GameComponent gameComponent) {
		super(x, y, xVelocity, yVelocity, gameComponent, WIDTH,HEIGHT);
		isPlayer = true;
        loadSprite();
    }


	@Override
	public void drawOn(Graphics2D g2) {
		if (spriteLoaded) {
    		int drawX = (int) this.getX() - WIDTH;
    		int drawY = (int) this.getY() - HEIGHT;
    		int size = WIDTH*2;
    		g2.drawImage(sprite, drawX, drawY, size, size, null);
    	} else {
    	
    		
  
    	}
	}
	
	@Override
	public void collideWithPlatform(AbstractPlatform platform) {


		this.y = platform.getY()-30;
		if(up == false) {

		if(up == false||this.y == platform.getY()-30) {
			this.y = platform.getY()-35;
			this.update();
		}

		this.update();
		}
	}
	
}

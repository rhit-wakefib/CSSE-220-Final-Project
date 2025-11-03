package Entities;

import java.awt.Color;
import java.awt.Graphics2D;
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

//		if (this.y > platform.getY()) {
//				this.y = platform.getY();
//			} else {
//			this.y = platform.getY();
////			reverseDirection(); // delete later just for test
//			}
//		this.x = this.x + (platform.getX() - this.x);
		//this.y = platform.getY()-30;
		if(up == false||this.y == platform.getY()-30) {
			this.y = platform.getY()-35;
			this.update();
		}
//		if (this.x > platform.getX() + platform.getWidth()) {
//			this.x = this.WIDTH + platform.getX() + platform.getWidth();
//		} 
//		else  if (this.x < platform.getX() + platform.getWidth()) {
//		this.x = platform.getX();
////		reverseDirection(); // delete later just for test
//		}
		this.update();
//		bounced =true;
	}

}

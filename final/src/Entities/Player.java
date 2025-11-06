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

public class Player extends Entity {
	
	private static final int WIDTH = 50;
	private static final int HEIGHT = 50;
	private int Health;


    Color color = Color.RED;
//    private BufferedImage sprite;
//    private boolean spriteLoaded = false;

    public Player(int x, int y, int xVelocity, int yVelocity, GameComponent gameComponent) {
		super(x, y, xVelocity, yVelocity, gameComponent, WIDTH,HEIGHT);
		isPlayer = true;
		this.Health = 3;
		try {
		if (Player.class.getResource("epicplayerss.png") != null) {
			sprite = ImageIO.read(Player.class.getResource("epicplayerss.png"));
			spriteLoaded = true;
        	} else {
        		System.out.println("no file could be found");
        	}
		} catch (IOException e) {

			spriteLoaded = false;
			//e.printStackTrace();
		}
    }

    
    public int getHealth() {
    	return Health;
    }
    public int loseHealth() {
    	return Health-=1;
    }
    

	@Override
	public void drawOn(Graphics2D g2) {
		if (spriteLoaded) {
    		int drawX = (int) (this.getX() - WIDTH);
    		int drawY = (int) (this.getY() - HEIGHT);
    		int size = WIDTH;
//    		g2.drawImage(sprite, drawX, drawY, size, size, null);
    		g2.drawRect(drawX, drawY, size, size);
    	}
	}
	
	
	@Override
	public void collideWithPlatform(AbstractPlatform platform) {


		this.y = platform.getY()-HEIGHT;
		if(up == false) {

		if(up == false||this.y == platform.getY()-30) {
			this.y = platform.getY()-HEIGHT;
			this.update();
		}

		this.update();
		}
	}
	
}

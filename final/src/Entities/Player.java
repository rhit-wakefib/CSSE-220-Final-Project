package Entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.GameComponent;
import platforms.AbstractPlatform;

public class Player extends Entity {
	
	private static final int WIDTH = 80;
	private static final int HEIGHT = 20;


    Color color = Color.RED;
    private BufferedImage sprite;
    private boolean spriteLoaded = false;
	
    public Player(int x, int y, int xVelocity, int yVelocity, GameComponent gameComponent) {
		super(x, y, xVelocity, yVelocity, gameComponent, WIDTH,HEIGHT);
		isPlayer = true;
        try {
			sprite = ImageIO.read(Enemy.class.getResource("epicplayerss.png"));
			spriteLoaded = true;
		} catch (IOException e) {

			spriteLoaded = false;
			//e.printStackTrace();
		}
    }


	@Override
	public void drawOn(Graphics2D g2) {
		if (spriteLoaded) {
    		int drawX = (int) this.getX() - 30;
    		int drawY = (int) this.getY() - 70;
    		int size = radius *7;
    		g2.drawImage(sprite, drawX, drawY, size, size, null);
    	} else {
    	
  
    	}
	}

}

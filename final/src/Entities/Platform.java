package Entities;

import java.awt.Color;
import java.awt.Graphics2D;
import game.GameComponent;
import game.GameObject;

/**
 * A platform that moves around the screen.
 * Bounces off other platforms and edges of the screen.
 */
public class Platform extends GameObject {
	private static final int WIDTH = 120;
	private static final int HEIGHT = 20;
	private static final Color PLATFORM_COLOR = Color.BLACK;
	
	public Platform(int x, int y, int xVelocity, int yVelocity, GameComponent gameComponent) {
		super(gameComponent, x, y, xVelocity, yVelocity, WIDTH, HEIGHT);
	}


	@Override
	public void drawOn(Graphics2D g) {
		int drawX = (int) (getX() - WIDTH / 2);
		int drawY = (int) (getY() - HEIGHT * 2);
		g.setColor(PLATFORM_COLOR);
		g.fillRect(drawX, drawY, WIDTH, HEIGHT);
	}


	@Override
	public void collideWithPlatform(Platform otherPlatform) {
	
	}
}
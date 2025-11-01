package game;

import java.awt.Color;
import java.awt.Graphics2D;

import Entities.Entity;
import platforms.AbstractPlatform;

public class Player extends Entity {
	
    private static GameComponent gameComponent;
	protected int x, y;
    protected int dx = 3, dy = 2;
    protected int radius = 15;
    private Color color = Color.RED;
    
    public Player(int x, int y) {
    	super(y, y, y, y, gameComponent, y, y);
    	this.x = x;
    	this.y = y;
    }

	
    public void draw(Graphics2D g2) {
        g2.setColor(color);
        g2.fillOval(x - radius, y - radius, radius * 2, radius * 2);
    }


	@Override
	public void drawOn(Graphics2D g2) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void collideWithPlatform(AbstractPlatform otherPlatform) {
		// TODO Auto-generated method stub
		
	}
}

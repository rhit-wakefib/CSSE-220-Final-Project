package Entities;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D.Double;

import game.GameComponent;
import game.GameObject;
import platforms.AbstractPlatform;

public abstract class Entity extends GameObject {
//	protected double x;
//	protected double y;
//	protected double dx, dy;
	protected int health = 3;
	protected int damage;
	
	private static final int MOVE_SPEED = 5;
	
	
	//movement
	protected boolean left = false;
	protected boolean right = false;
	protected boolean up = false;
	protected boolean down = false;
	protected boolean isPlayer = false;

    int radius = 15;
	
	public Entity(int x, int y, int xVelocity, int yVelocity, GameComponent gameComponent, int width, int height) {
		super(gameComponent,x,y,xVelocity,yVelocity, width,height);
	}
	
	protected void takeDamage() {
		health += (-damage);
	}
	
	public void collideWithFloor() {
		this.update();
	}
	
	@Override 
	public void update() {
		if(isPlayer) {
			
			if (left) {
				this.x -= MOVE_SPEED;
				left = false;
			}
			else if (right) {
				this.x += MOVE_SPEED;
				right = false;
			}
			else if (up) {
				this.y -= MOVE_SPEED;
			}
			else if (down) {
				this.y += MOVE_SPEED;
			}
		} else {
			this.x += this.xVelocity;
			this.y += this.yVelocity;
			
			}
		
		if (this.y > gameComponent.getGroundY()) {
			this.y = gameComponent.getGroundY();
			reverseDirection(); // delete later just for test
		}
	}
		
	
	
//    public void move(int width, int height) {
//        x += dx;
//        y += dy;
//        // wrap around the edges
//        
//        // Left/right
//        if (x - radius < 0) { x = radius; dx = -dx; }
//        else if (x + radius > width) { x = width - radius; dx = -dx; }
//
//        // Top/bottom
//        if (y - radius < 0) { y = radius; dy = -dy; }
//        else if (y + radius > height) { y = height - radius; dy = -dy; }
//    }
	
}

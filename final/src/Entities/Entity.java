package Entities;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.GameComponent;
import game.GameObject;
import platforms.AbstractPlatform;

public abstract class Entity extends GameObject {
	protected int health = 3;
	protected int damage;
	
    protected BufferedImage sprite;
    protected boolean spriteLoaded = false;
	
	private static final double MOVE_SPEED = 5;
	
	
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
	public void collideWithPlatform(AbstractPlatform otherPlatform) {}
	
	@Override 
	public void update() {
		if(isPlayer) {
			
			if (left) {
				this.x -= MOVE_SPEED;
				loadSprite();
				left = false;
			}
			else if (right) {
				this.x += MOVE_SPEED;
				loadSprite();
				right = false;
			}
			else if (up) {
				this.y -= MOVE_SPEED;
				loadSprite();
				up = false;
			}
			else if (down) {
				this.y += MOVE_SPEED;
				loadSprite();
				down = false;
			}
			
			this.y += 3;
			
		} else {
			this.x += this.xVelocity;
			this.y += this.yVelocity;
			
			}
		
		if (this.y >= gameComponent.getGroundY()) {
			if(isPlayer) {
				this.y = gameComponent.getGroundY();
			} else {
			this.y = gameComponent.getGroundY();
//			reverseDirection(); // delete later just for test
			}
		}
	}
	
	public void loadSprite() {
		
		if(isPlayer) {
			if(left) {
		        try {
					sprite = ImageIO.read(Player.class.getResource("epicplayerss.png"));
					spriteLoaded = true;
				} catch (IOException e) {
		
					spriteLoaded = false;
					//e.printStackTrace();
				}
			} else if (right) {
		        	try {
						sprite = ImageIO.read(Player.class.getResource("epicPRight.png"));
						spriteLoaded = true;
					} catch (IOException e) {
			
						spriteLoaded = false;
						//e.printStackTrace();
					}
		        }
			else if (up) {
	        	try {
					sprite = ImageIO.read(Player.class.getResource("pDown.png"));
					spriteLoaded = true;
				} catch (IOException e) {
		
					spriteLoaded = false;
					//e.printStackTrace();
				}
	        }
			else if (down) {
	        	try {
					sprite = ImageIO.read(Player.class.getResource("epicplayerss.png"));
					spriteLoaded = true;
				} catch (IOException e) {
		
					spriteLoaded = false;
					//e.printStackTrace();
				}
	        } else {
		        try {
					sprite = ImageIO.read(Player.class.getResource("epicplayerss.png"));
					spriteLoaded = true;
				} catch (IOException e) {
		
					spriteLoaded = false;
					//e.printStackTrace();
				}
	        }
		} else if (!isPlayer){
	        try {
				sprite = ImageIO.read(Enemy.class.getResource("tennis.png"));
				spriteLoaded = true;
			} catch (IOException e) {

				spriteLoaded = false;
				//e.printStackTrace();
			}
		}
	}
	
	public void moveLeft() {
		left = true;
	}
	public void moveRight() {
		right = true;
	}
	public void moveUp() {
		up = true;
	}
	
	public void moveDown() {
		down = true;
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

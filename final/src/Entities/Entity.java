package Entities;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import game.GameComponent;
import game.GameObject;
import platforms.AbstractPlatform;

/**
 * @author Braden Wakefield, Terrel Doxie
 * 
 *  * Help Citation
 * used CSSE220 materials
 * player sprite is from https://cl.pinterest.com/pin/785244885024471618/
 * enemy sprite https://favpng.com/png_view/minecraft-creeper-minecraft-creeper-character-png/XfFXQzHD
 */

public abstract class Entity extends GameObject {
	protected int health = 3;
	protected int damage;
	
    protected BufferedImage sprite;
    protected boolean spriteLoaded = false;
    
	
	
	
	
	//movement
	protected boolean left = false;
	protected boolean right = false;
	protected boolean up = false;
	protected boolean down = false;
	protected boolean isPlayer = false;
	
	
	protected boolean eRight = false;
	protected boolean eLeft = false;

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
	public void collideWithPlatform(AbstractPlatform otherPlatform) {
		this.update();
	}
	
	@Override 
	public void update() {
		if(isPlayer) {
			
			if (left) {
				this.x -= xVelocity;
				loadSprite();
				left = false;
			}
			else if (right) {
				this.x += xVelocity;
				loadSprite();
				right = false;
			}
			else if (up) {
				this.y -= yVelocity;
				loadSprite();
				up = false;
			}
			else if (down) {
				this.y += yVelocity;
				loadSprite();
				down = false;
			}
			
			this.y += 3;
			
		} else {
			
			if(eRight) {
				loadSprite();
				eRight = false;
			} else if (eLeft){
				loadSprite();
				eLeft = false;
			}
			
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

				}
			} else if (right) {
		        	try {
						sprite = ImageIO.read(Player.class.getResource("epicPRight.png"));
						spriteLoaded = true;
					} catch (IOException e) {
			
						spriteLoaded = false;
						JOptionPane.showMessageDialog(null, "Wrong Input", "beep boop", JOptionPane.WARNING_MESSAGE);
						System.out.println("Error" + e.getMessage());
						//e.printStackTrace();
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
				if(eRight) {
			        try {
						sprite = ImageIO.read(Enemy.class.getResource("eright.png"));
						spriteLoaded = true;
					} catch (IOException e) {
		
						spriteLoaded = false;
						//e.printStackTrace();
					}
			        
				} else if (eLeft) {
					try {
						sprite = ImageIO.read(Enemy.class.getResource("eleft.png"));
						spriteLoaded = true;
						eLeft = false;
					} catch (IOException e) {
	
						spriteLoaded = false;
						//e.printStackTrace();
						
					}
				} else {
					try {
						sprite = ImageIO.read(Enemy.class.getResource("eleft.png"));
						spriteLoaded = true;
						eLeft = false;
					} catch (IOException e) {
	
						spriteLoaded = false;
						//e.printStackTrace();
						
					}
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

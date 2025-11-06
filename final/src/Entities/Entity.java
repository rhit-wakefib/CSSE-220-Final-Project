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
    
    // player directional sprite loaded
    protected boolean leftSpriteLoaded = false;
    protected boolean rightSpriteLoaded = false;
    protected boolean upSpriteLoaded = false;
    protected boolean downSpriteLoaded = false;
    
    // enemy directional sprite loaded
    protected boolean eLeftSpriteLoaded = false;
    protected boolean eRightSpriteLoaded = false;
	
	//movement
	public boolean left = false;
	public boolean right = false;
	public boolean up = false;
	public boolean down = false;
	protected boolean isPlayer = false;
	
	// enemy movement
	protected boolean eRight = false;
	protected boolean eLeft = false;

	
	public Entity(int x, int y, int xVelocity, int yVelocity, GameComponent gameComponent, int width, int height) {
		super(gameComponent,x,y,xVelocity,yVelocity, width,height);
		
	}
	
	protected void takeDamage() {
		health += (-damage);
	}
	
	
	@Override 
	public void update() {
		
		this.y += 3;
			
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
			
		} else {
			
			if(eRight) {
				eLeftSpriteLoaded = false;
				loadSprite();
				eRight = false;
			} else if (eLeft){
				eRightSpriteLoaded = false;
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
			
			if(left && !leftSpriteLoaded) {
		        try {
		        	if (Player.class.getResource("epicplayerss.png") != null) {
						sprite = ImageIO.read(Player.class.getResource("epicplayerss.png"));
						spriteLoaded = true;
						leftSpriteLoaded = true;
						rightSpriteLoaded = false;
						upSpriteLoaded = false;
						downSpriteLoaded = false;
			        	} else {
			        		System.out.println("no file could be found");
			        	}
				} catch (IOException e) {
		
					
					spriteLoaded = false;

				}
			} 
			else if (right && !rightSpriteLoaded) {
		        	try {
			        	if (Player.class.getResource("epicPRight.png") != null) {
							sprite = ImageIO.read(Player.class.getResource("epicPRight.png"));
							spriteLoaded = true;
							leftSpriteLoaded = false;
							rightSpriteLoaded = true;
							upSpriteLoaded = false;
							downSpriteLoaded = false;
							
				        	} else {
				        		System.out.println("no file could be found");
				        	}
					} catch (IOException e) {
			
						spriteLoaded = false;

					}
//		        	spriteLoaded = false;

		    }
			else if (up && !upSpriteLoaded) {
	        	try {
		        	if (Player.class.getResource("pDown.png") != null) {
					sprite = ImageIO.read(Player.class.getResource("pDown.png"));
					spriteLoaded = true;
					leftSpriteLoaded = false;
					rightSpriteLoaded = false;
					upSpriteLoaded = true;
					downSpriteLoaded = false;
		        	} else {
		        		System.out.println("no file could be found");
		        	}
				} catch (IOException e) {
		
					spriteLoaded = false;
					//e.printStackTrace();
				}
	        }
			else if (down && !downSpriteLoaded) {
	        	try {
		        	if (Player.class.getResource("epicplayerss.png") != null) {
					sprite = ImageIO.read(Player.class.getResource("epicplayerss.png"));
					spriteLoaded = true;
					leftSpriteLoaded = false;
					rightSpriteLoaded = false;
					upSpriteLoaded = false;
					downSpriteLoaded = true;
		        	} else {
		        		System.out.println("no file could be found");
		        	}
				} catch (IOException e) {
		
					spriteLoaded = false;
					//e.printStackTrace();
				}
	        } 
			
//			else {
//		        try {
//		        	if (Player.class.getResource("epicplayerss.png") != null) {
//					sprite = ImageIO.read(Player.class.getResource("epicplayerss.png"));
//					spriteLoaded = true;
//		        	} else {
//		        		System.out.println("no file could be found");
//		        	}
//				} catch (IOException e) {
//		
//					spriteLoaded = false;
//					//e.printStackTrace();
//				}
//	        		}
			} 
		
			else if (!isPlayer) {
				if(eRight && !eRightSpriteLoaded) {
			        try {
			        	if (Player.class.getResource("eright.png") != null) {
							sprite = ImageIO.read(Player.class.getResource("eright.png"));
							spriteLoaded = true;
						    eLeftSpriteLoaded = false;
						    eRightSpriteLoaded = true;
				        	} else {
				        		System.out.println("no file could be found");
				        	}
			        	
					} catch (IOException e) {
		
						spriteLoaded = false;
						//e.printStackTrace();
					}
			        
				} else if (eLeft && !eLeftSpriteLoaded) {
					try {
			        	if (Player.class.getResource("eleft.png") != null) {
							sprite = ImageIO.read(Player.class.getResource("eleft.png"));
							spriteLoaded = true;
						    eLeftSpriteLoaded = true;
						    eRightSpriteLoaded = false;
				        	} else {
				        		System.out.println("no file could be found");
				        	}
			        	
					} catch (IOException e) {
	
						spriteLoaded = false;
						//e.printStackTrace();
						
					}
//				} else {
//					try {
//			        	if (Player.class.getResource("eleft.png") != null) {
//							sprite = ImageIO.read(Player.class.getResource("eleft.png"));
//							spriteLoaded = true;
//				        	} else {
//				        		System.out.println("no file could be found");
//				        	}
//					} catch (IOException e) {
//	
//						spriteLoaded = false;
//						//e.printStackTrace();
//						
//					}
				}
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

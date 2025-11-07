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
    
	protected boolean rightLoaded = false;
	protected boolean leftLoaded = false;
	protected boolean upLoaded = false;
	protected boolean downLoaded = false;
	
	protected boolean eRightLoaded = false;
	protected boolean eLeftLoaded = false;
	
	//movement
	public boolean left = false;
	public boolean right = false;
	public boolean up = false;
	public boolean down = false;
	protected boolean isPlayer = false;
	
	
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
	    
	    if(isPlayer) {
	        // Only apply gravity if not actively jumping
	        if (!up) {
	            this.y += 3;  // Apply gravity when not jumping
	        }
	        
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
	        }
	        
	        else if (down) {
	            this.y += yVelocity;
	            loadSprite();
	            down = false;
	        }
	        
	    } else {
	        
	        if(eRight) {
	          
	            loadSprite();
	            eRight = false;

	        } else if (eLeft){
	           
	            loadSprite();
	            eLeft = false;

	        }
 
	        this.x += this.xVelocity;
//	        this.y += this.yVelocity;
	        this.y += 3;
	    }
	    
	    if (this.y >= gameComponent.getGroundY()) {
	        if(isPlayer) {
	            this.y = gameComponent.getGroundY();
	        } else {
	            this.y = gameComponent.getGroundY();
	        }
	    }
	}
	
	public void loadSprite() {
		
		if(isPlayer) {
			
			if(left && leftLoaded) {
		        try {
		        	if (Player.class.getResource("epicplayerss.png") != null) {
						sprite = ImageIO.read(Player.class.getResource("epicplayerss.png"));
						spriteLoaded = true;
			        	} else {
			        		System.out.println("no file could be found");
			        	}
				} catch (IOException e) {
		
					
					spriteLoaded = false;
 
				}
		        leftLoaded = true;
		        rightLoaded = false;
		        upLoaded = false;
		        downLoaded = false;
			}
			else if (right && !rightLoaded) {
		        	try {
			        	if (Player.class.getResource("epicPRight.png") != null) {
							sprite = ImageIO.read(Player.class.getResource("epicPRight.png"));
							spriteLoaded = true;
				        	} else {
				        		System.out.println("no file could be found");
				        	}
					} catch (IOException e) {
			
						spriteLoaded = false;
 
					}
//		        	spriteLoaded = false;
		        	
			        leftLoaded = false;
			        rightLoaded = true;
			        upLoaded = false;
			        downLoaded = false;
 
		    }
			else if (up && !upLoaded) {
	        	try {
		        	if (Player.class.getResource("pDown.png") != null) {
					sprite = ImageIO.read(Player.class.getResource("pDown.png"));
					spriteLoaded = true;
		        	} else {
		        		System.out.println("no file could be found");
		        	}
				} catch (IOException e) {
		
					spriteLoaded = false;
					//e.printStackTrace();
				}
	        	
		        leftLoaded = false;
		        rightLoaded = false;
		        upLoaded = true;
		        downLoaded = false;
	        }
			else if (down && !downLoaded) {
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
	        	
		        leftLoaded = false;
		        rightLoaded = false;
		        upLoaded = false;
		        downLoaded = true;
	        }
						
		
			}  else if (!isPlayer) {
				if(eRight) {
			        try {
			        	if (Player.class.getResource("eright.png") != null) {
							sprite = ImageIO.read(Player.class.getResource("eright.png"));
							spriteLoaded = true;
				        	} else {
				        		System.out.println("no file could be found");
				        	}
			        	
					} catch (IOException e) {
		
						spriteLoaded = false;
						//e.printStackTrace();
					}
			        

				} else if (eLeft) {
					try {
			        	if (Player.class.getResource("eleft.png") != null) {
							sprite = ImageIO.read(Player.class.getResource("eleft.png"));
							spriteLoaded = true;
				        	} else {
				        		System.out.println("no file could be found");
				        	}
			        	
					} catch (IOException e) {
	
						spriteLoaded = false;
						//e.printStackTrace();
						
					}


				}
			}
				
		}
		
	}
	

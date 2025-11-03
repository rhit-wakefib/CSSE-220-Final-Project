package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D.Double;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.Timer;

import Entities.Enemy;
import Entities.Entity;
import Entities.Player;

//import drops.AbstractDrop;
//import drops.DamagingDrop;
//import drops.HealingDrop;
//import drops.InvincibilityDrop;
import platforms.AbstractPlatform;
import platforms.Platform;


/**
 * @author's Braden Wakefield Terrel Doxie Charlie Kofahl
 * 
 **************************************************************************************** 

 *         REQUIRED HELP CITATION

 *         DONE: "only used CSSE220 materials"

 *************************************************************************************** 
 *
 */

public class GameComponent extends JComponent {


	public static final int WIDTH = 1000;
	public static final int HEIGHT = 700;
	public static final Color BG = Color.CYAN;
	public static final Color FG = Color.GREEN;
	private int x1 = WIDTH/2, x2 = WIDTH/2;
	private int y1 = HEIGHT/8, y2 = HEIGHT/8 *7;
	private int groundStartX = 0, groundEndX = WIDTH;
	private int groundY = HEIGHT - (HEIGHT/4);

	private final HudModel hud;
	
	private List<AbstractPlatform> platforms = new ArrayList<>();
	private List<Entity> enemies = new ArrayList<>();
	private List<Block> blocks = new ArrayList<>();
	
	private Player p;
	
	Timer timer;
	
	public GameComponent(HudModel hud) {
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		//  seed a couple so something is visible immediately

	    this.hud = hud;
	    setOpaque(true);

	    setOpaque(true);		
	    
		this.p = new Player(400, 100, 2, 0, this);

		this.platforms.add(new Platform(200, 100, 0, 0, this));
		this.platforms.add(new Platform(30,  100, 0, 0, this));
		this.platforms.add(new Platform(130, 150, 0, 0, this));
		this.platforms.add(new Platform(300, 300, 0, 0, this));
		
  //Models
        
		this.enemies.add(new Enemy(300, 168, 3, 0, this));
		this.enemies.add(new Enemy(350, 170, 0, 5, this));
		
		this.blocks.add(new Block(500,200,1));

	
	}


		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(FG);
			
			Rectangle r = new Rectangle(groundStartX, groundY, WIDTH, HEIGHT);
			
			g2.draw(r);
			g2.fill(r);
			
			for (AbstractPlatform platform : this.platforms) {
				platform.drawOn(g2);
			}
			
			for (Entity e : this.enemies) {
				e.drawOn(g2);
			}
			
			p.drawOn(g2);
			
			for (Block b : this.blocks) {
				b.drawOn(g2);
			}
			
			p.drawOn(g2);
			
		}
		
		    
		public int getGroundY() {
			return groundY;
		}
		
		public void updateState() {
			// Each is big enough to be in a helper method.
			hud.updateAll(WIDTH, HEIGHT);
			updatePlatforms();
			updateEnemies();
			handleCollisions();
			p.update();
			
		}
		
		
		
		private void handleCollisions() {
			List<GameObject> allObjects = new ArrayList<>();
//			allObjects.addAll( this.drops);
			allObjects.addAll( this.platforms);
			allObjects.addAll( this.enemies);
			
			
			//drop and platform collisions
//			for(AbstractDrop r: drops){
//				for(AbstractPlatform p: platforms){
//					if( !r.shouldRemove() && !p.shouldRemove()) {
//						if (r.overlaps(p)) {
//							r.collideWithPlatform(p);
//						}
//					}
//				}
//			}
			
			for( Entity e: enemies){
				for( AbstractPlatform p2: platforms){
						if (e.overlaps(p2)) {
							e.collideWithPlatform(p2);
						}
					}
				}
			
			List<Block> toRemove = new ArrayList<>();
			for (Block b : blocks) {
			    double dx = b.x - p.x, dy = b.y - p.y;
			    int rSum = b.radius + 30;
			    if (dx*dx + dy*dy <= rSum*rSum) {
			    	toRemove.add(b);
			    	hud.addCoin(1);
			    	//ADD SOMETHING TO UPDATE SCORE HERE SINCE U TOUCHED A COIN 
			    }
			}
			blocks.removeAll(toRemove);
			
			for( AbstractPlatform p1: platforms){
				for( AbstractPlatform p2: platforms){
					if (p1 != p2) {
						if (p1.overlaps(p2)) {
							p1.collideWithPlatform(p2);
						}
					}
				}
			}
			
			// player collision with platform
			for( AbstractPlatform p1: platforms){
				if (p.overlaps(p1)) {
//					p.y = p.y +(p1.y - p.y);
					p.collideWithPlatform(p1);
				}
			}
			
			
			
			List<GameObject> shouldRemove = new ArrayList<>();
			
			for(GameObject object: allObjects){
				if(object.shouldRemove()){
					shouldRemove.add(object);
				}
			}
			
			for(GameObject object: shouldRemove){
//				this.drops.remove(object);
				this.platforms.remove(object);
//				object.onRemove();
			}
		}



		private void updatePlatforms() {
			for (AbstractPlatform platform : this.platforms) {
				platform.update();
			}
		}
		
		private void updateEnemies() {
			for (Entity e : this.enemies) {
				e.update();
			}
		}
		
		public void moveLeft() {
			this.p.moveLeft();
		}
		
		public void moveRight() {
			this.p.moveRight();
		}
		
		public void moveUp() {
			this.p.moveUp();
		}
		
		public void moveDown() {
			this.p.moveDown();
		}
		

//
//		public void createRainDrop(Double boundingBox) {
//			this.drops.add(new DamagingDrop(boundingBox, this));
//		}

}

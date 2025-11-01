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




public class GameComponent extends JComponent {


	public static final int WIDTH = 600;
	public static final int HEIGHT = 300;
	public static final Color BG = Color.CYAN;
	public static final Color FG = Color.GREEN;
	private int x1 = WIDTH/2, x2 = WIDTH/2;
	private int y1 = HEIGHT/8, y2 = HEIGHT/8 *7;
	private int groundStartX = 0, groundEndX = WIDTH;
	private int groundY = HEIGHT - (HEIGHT/4);

	private final HudModel hud;
	
	private List<AbstractPlatform> platforms = new ArrayList<>();
	private List<Entity> enemies = new ArrayList<>();
	
	private Player p;
	Timer timer;
	
	public GameComponent(HudModel hud) {
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		//  seed a couple so something is visible immediately

	    this.hud = hud;
	    setOpaque(true);
//		model.addBall(100, 100);
//	    model.addBall(200, 60);
	    timer = new Timer(30, e-> {
	        	p.update();
	        	repaint();
	        });
	    setOpaque(true);		

		this.platforms.add(new Platform(200, 100, 0, 0, this));
		this.platforms.add(new Platform(30,  100, 0, 0, this));
		this.platforms.add(new Platform(130, 150, 0, 0, this));
		this.platforms.add(new Platform(230, 200, 0, 0, this));
		
  //Models
        
		this.enemies.add(new Enemy(300, 170, 0, 5, this));
		this.enemies.add(new Enemy(350, 170, 0, 5, this));
//        ballModel.addBall(230, 200, 0, 5, this);
		
		this.p = new Player(400, groundY, 0, 5, this);
//		
    buildKeys();
	}
	private void buildKeys() {
		this.setFocusable(true);
		this.requestFocusInWindow();
		
		this.addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT -> p.moveLeft();
                case KeyEvent.VK_RIGHT -> p.moveRight();
               
			}
		}
			@Override
			public void keyReleased(KeyEvent e) {
				  switch (e.getKeyCode()) {
                  case KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT -> p.stopMoving();
			}
			}
	});
	}

	public void start() { timer.start(); }     // NEW
	public void stop()  { timer.stop(); } 
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
			
		}
		
		public void moveDelta(int dx) {
	        x1 += dx; x2 += dx;
	        repaint(); 
		}
		
		public void setX(int i) {
			x1 = i;
			x2 = i;
		    repaint();
		}	

		    
		public int getGroundY() {
			return groundY;
		}
		
		public void updateState() {
			// Each is big enough to be in a helper method.
//	    	ballModel.updateAll(WIDTH, groundY); // or canvas.getWidth(), Height()
			hud.updateAll(WIDTH, HEIGHT);
			updatePlatforms();
			updateEnemies();
			handleCollisions();
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
			
			
			for( AbstractPlatform p1: platforms){
				for( AbstractPlatform p2: platforms){
					if (p1 != p2) {
						if (p1.overlaps(p2)) {
							p1.collideWithPlatform(p2);
						}
					}
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
		
		
			
//		public void toggleBoxDirection() {
//			this.userPlatform.reverseDirection();
//		}
//
//		public void createRainDrop(Double boundingBox) {
//			this.drops.add(new DamagingDrop(boundingBox, this));
//		}

}

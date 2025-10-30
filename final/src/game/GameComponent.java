package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.Timer;



public class GameComponent extends JComponent {


	public static final int WIDTH = 600;
	public static final int HEIGHT = 300;
	public static final Color BG = Color.CYAN;
	public static final Color FG = Color.GREEN;
	private int x1 = WIDTH/2, x2 = WIDTH/2;
	private int y1 = HEIGHT/8, y2 = HEIGHT/8 *7;
	private int groundStartX = 0, groundEndX = WIDTH;
	private int groundY = HEIGHT - (HEIGHT/3);
	private final EnemyModel model;
	private final HudModel hud;

	
	
	public GameComponent(EnemyModel model, HudModel hud) {
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		//  seed a couple so something is visible immediately
	    this.model = model;
	    this.hud = hud;
	    setOpaque(true);
//		model.addBall(100, 100);
//	    model.addBall(200, 60);
//	    timer = new Timer(30, e-> {
//	        	model.updateAll(WIDTH, HEIGHT);
//	        	repaint();
//	        });
    
	}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			
			Rectangle r = new Rectangle(groundStartX,groundY, WIDTH, HEIGHT);
			
			g2.setColor(FG);
			g2.drawLine(x1, y1, x2, y2);
			
			g2.draw(r);
			g2.fill(r);
			
//			g2.drawLine(groundStartX, groundY, groundEndX, groundY);
	        for (Enemy b : model.getBalls()) {
	            b.draw(g2);
	        }
			
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

		    
		    // helper to add more balls at runtime (used below)
		    public void addBall(int x, int y) {
		       model.addBall(x,y);
		    	 repaint();
		    }

}

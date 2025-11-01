package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;
import javax.swing.Timer;



public class GameComponent extends JComponent {


	public static final int WIDTH = 400;
	public static final int HEIGHT = 150;
	public static final Color BG = Color.CYAN;
	public static final Color FG = Color.BLACK;
	private int x1 = WIDTH/2, x2 = WIDTH/2;
	private int y1 = HEIGHT/8, y2 = HEIGHT/8 *7;
	private final EnemyModel model;
	private final HudModel hud;
	Timer timer;

	
	
	public GameComponent(EnemyModel model, HudModel hud) {
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		//  seed a couple so something is visible immediately
	    this.model = model;
	    this.hud = hud;
	    setOpaque(true);
//		model.addBall(100, 100);
//	    model.addBall(200, 60);
	   
	    timer = new Timer(30, e-> {
	        	model.updateAll(WIDTH, HEIGHT);
	        	repaint();
	        });
    
	}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(FG);
			g2.drawLine(x1, y1, x2, y2);
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

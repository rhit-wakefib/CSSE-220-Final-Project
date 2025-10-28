package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.Timer;

import javax.swing.JComponent;

/**
 * @author Braden Wakefield
 * 
 * Help Citation
 * Only used CSSE220 materials
 */
public class GameComponent extends JComponent{ // Model class following MVC holds data
	
	public static final int WIDTH  = 400;
    public static final int HEIGHT = 140;
    public static final Color BG   = Color.CYAN;
    public static final Color FG   = Color.BLACK;
    
    // sets x and y to middle
    private int x1 = WIDTH/2, x2 = WIDTH/2;
    private int y1 = HEIGHT/8, y2 = HEIGHT/8*7; // the denominator represents the gap
    
    public final Timer timer;                       // NEW
//    private final ArrayList<Ball> balls = new ArrayList<>();   // NEW
    private Player p;
    
    public GameComponent() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
       this.p = new Player(80, 80);
//        balls.add(new Ball( 80,  80));
//        balls.add(new Ball(180, 140));
//        balls.add(new Ball(300, 220));
//        balls.get(0).dx = 3; balls.get(0).dy = 2;
//        balls.get(1).dx = 1; balls.get(1).dy = 3;
//        balls.get(2).dx = 4; balls.get(2).dy = -1;
        
        // NEW: ~33 fps; on each tick, move the ball and repaint
        this.timer = new Timer(30, e -> {
//            this.ball.move(getWidth(), getHeight());
//        	for (Ball b : balls) {
//                b.move(getWidth(), getHeight());
//            }
            repaint();
        });
      }
    
    public void start() { timer.start(); }     // NEW
    public void stop()  { timer.stop(); }      // NEW

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(FG);
		g2.drawLine(x1, y1, x2, y2);
		
//		for(Ball b : balls) {
//			b.draw(g2);
//		}
		
        this.p.draw(g2);
	}
	
	public void addBall(int x, int y) {
//        balls.add(new Ball(x, y));
    }
	
    public void moveDelta(int dx) {
        x1 += dx; x2 += dx;
        repaint();    //common mistake is not requesting repaint to refresh screen. Need to always add this to refresh screen                 
      }
    
    public void setX(int i) {
		x1 = i;
		x2 = i;
	    repaint(); // must request paint() swing to put in cue to be repainted
	}

}

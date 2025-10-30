package game;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;
import javax.swing.Timer;

/**
 * Controller class for the game.
 * Handles user input (buttons + keys) and controls the main Timer loop.
 */

public class GamePanel extends JPanel {
	
    // models
	private final BallModel ballModel = new BallModel();;
	private final HudModel hudModel = new HudModel();;
	// views
	private final GameComponent canvas = new GameComponent(ballModel,hudModel);
	private final HudViewer hudView = new HudViewer();
	
	// loop
	private final Timer timer;
	
    /**
     * Constructs the main game panel with controls and keyboard support.
     */
    
    public GamePanel() {
    	JPanel layered = new JPanel();
        layered.setLayout(new OverlayLayout(layered));
        layered.setOpaque(false);  // Make layered panel transparent
        // view
        canvas.setOpaque(false); // panels sets the background if you want it from canvas, set it to true
        layered.add(hudView); 
    	layered.add(canvas);
    	layered.add(canvas);

    	hudView.setOpaque(false);         // Transparent so no gray background
    	hudView.setAlignmentX(0f);        // Left edge
    	hudView.setAlignmentY(0f);        // Top edge
    	layered.add(hudView);             // Add after canvas → goes on top
       
    	// PANEL LAYOUT
    	this.setLayout(new BorderLayout());
    	this.add(layered, BorderLayout.CENTER);
    	this.setBackground(canvas.BG);
    	this.add(buildControls(), BorderLayout.SOUTH);
    	
    
        this.buildKeys();
        //Models
        
        ballModel.addBall(100, 100); // just to have initial balls
        ballModel.addBall(200, 60);
        // game loop
        this.timer = new Timer(30, e -> tick());
    	
    }
    
    // control animation
    private void tick() {
    	ballModel.updateAll(canvas.getHeight(), canvas.getWidth()); // or canvas.getWidth(), Height()
    	hudModel.setBallCount(ballModel.getBallCount());
    	hudView.refresh(hudModel);
    	canvas.repaint();
    }
    
    
    private JComponent buildControls() {
		JPanel controls = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 6));
        JButton center  = new JButton("Center");
        JButton left = new JButton("Left");
        JButton right = new JButton("Right");

        left.addActionListener(e -> { canvas.moveDelta(-10); requestFocusInWindow(); });
        right.addActionListener(e -> { canvas.moveDelta(+10); requestFocusInWindow(); });
        center.addActionListener(e -> { canvas.setX(canvas.WIDTH/2); requestFocusInWindow(); });

        controls.add(left); 
        controls.add(right);
        controls.add(center);
        return controls;
    }
    
    private void addBall(int x, int y ) {
        ballModel.addBall(x, y);
        hudModel.addScore(5);  // example scoring rule on add
        hudModel.setBallCount(ballModel.getBallCount());
        canvas.repaint();
    }
    
    public void startGame() { timer.start(); }
    public void stopGame()  { timer.stop();  }
    
private void buildKeys() {
		
		this.setFocusable(true);
		this.requestFocusInWindow();
		
		this.addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyPressed(KeyEvent e) {
	            switch (e.getKeyCode()) {
	                case KeyEvent.VK_LEFT -> canvas.moveDelta(-10);
	                case KeyEvent.VK_RIGHT -> canvas.moveDelta(10);
	                case KeyEvent.VK_SPACE -> {
	                    if (timer.isRunning()) timer.stop();
	                    else timer.start();
	                }
	                case KeyEvent.VK_B -> {
	                    int x = (int)(Math.random() * canvas.getWidth());
	                    int y = (int)(Math.random() * canvas.getHeight());
	                    canvas.addBall(x, y);
	                }
	                
	                case KeyEvent.VK_S -> {
	                    ScoreManager.save(hudModel.getScore(), hudModel.getBallCount());
	                    // quick feedback (optional)
	                    System.out.println("Saved: score=" + hudModel.getScore() + ", balls=" + hudModel.getBallCount());
	                }
	            }
	        }
	    });
		
	}

}

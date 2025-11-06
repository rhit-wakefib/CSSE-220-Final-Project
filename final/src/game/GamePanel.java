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
* @author's Braden Wakefield Terrell Doxie
*
****************************************************************************************
 
*         REQUIRED HELP CITATION
 
*         DONE: "only used CSSE220 materials"
 
***************************************************************************************
*
*/
 
/**
* Controller class for the game.
* Handles user input (buttons + keys) and controls the main Timer loop.
*/
 
public class GamePanel extends JPanel {
	
    // models
 
	private final HudModel hudModel = new HudModel();
	// views
	private final GameComponent canvas = new GameComponent(hudModel);
	private final HudViewer hudView = new HudViewer();
	// loop
	private final Timer timer;
	private boolean canPress = true;
	private final int Cooldown = 700;
	//private final int moveAmount = 10;
	private int upDuration = 500;
 
	
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
    	hudView.refresh(hudModel);
    	// PANEL LAYOUT
    	this.setLayout(new BorderLayout());
    	this.add(layered, BorderLayout.CENTER);
    	this.setBackground(canvas.BG);
    	this.add(buildControls(), BorderLayout.SOUTH);
    	
    
        this.buildKeys();
 
        // game loop
        this.timer = new Timer(30, e -> tick());
    	
    }
    
    // control animation
    private void tick() {
 
    	hudView.refresh(hudModel);
    	canvas.updateState();
    	canvas.repaint();
    }
    
    
    private JComponent buildControls() {
		JPanel controls = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 6));
//        JButton center  = new JButton("Center");
//        JButton left = new JButton("Left");
//        JButton right = new JButton("Right");
//
//        left.addActionListener(e -> { canvas.moveDelta(-10); requestFocusInWindow(); });
//        right.addActionListener(e -> { canvas.moveDelta(+10); requestFocusInWindow(); });
//        center.addActionListener(e -> { canvas.setX(canvas.WIDTH/2); requestFocusInWindow(); });
//
//        controls.add(left);
//        controls.add(right);
//        controls.add(center);
        return controls;
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
	                case KeyEvent.VK_LEFT -> canvas.p.left = true;
	                case KeyEvent.VK_RIGHT -> canvas.p.right =true;
//	                case KeyEvent.VK_UP -> canvas.upPressed();
	                case KeyEvent.VK_DOWN -> canvas.p.down = true;
                    //claude below CHANGE THIS
	                case KeyEvent.VK_UP -> {
	                    if(!canPress) {
	                        return;
	                    }
	                    canPress = false;
	                    
	                    // Set up to true and let it stay true for the duration
	                    canvas.p.up = true;
	                    
	                    // After the jump duration, set it back to false
	                    new javax.swing.Timer(upDuration, ev -> {
	                        canvas.p.up = false;
	                        ((javax.swing.Timer) ev.getSource()).stop();
	                    }).start();
	                    
	                    // Cooldown timer before next jump
	                    new javax.swing.Timer(Cooldown, ev -> {
	                        canPress = true;
	                        ((javax.swing.Timer) ev.getSource()).stop();
	                    }).start();
	                }
//	                case KeyEvent.VK_LEFT -> canvas.moveLeft();
//	                case KeyEvent.VK_RIGHT -> canvas.moveRight();
//	                case KeyEvent.VK_UP -> canvas.moveUp();
//	                case KeyEvent.VK_DOWN -> canvas.moveDown();
//	                case KeyEvent.VK_UP && KeyEvent.VK_RIGHT -> canvas.moveUpRight();
 
	                case KeyEvent.VK_SPACE -> {
	                    if (timer.isRunning()) timer.stop();
	                    else timer.start();
	                }
	                
//	                case KeyEvent.VK_B -> {
//	                    int x = (int)(Math.random() * canvas.getWidth());
//	                    int y = (int)(Math.random() * canvas.getHeight());
////	                    canvas.addBall(x, y);
//	                }
	                
 
//	                case KeyEvent.VK_S -> {
//	                    ScoreManager.save(hudModel.getScore(), hudModel.getBallCount());
//	                    // quick feedback (optional)
//	                    System.out.println("Saved: score=" + hudModel.getScore() + ", balls=" + hudModel.getBallCount());
//	                }
 
	                case KeyEvent.VK_S -> {
	                    ScoreManager.save(hudModel.getHealth1(), hudModel.getcoinCount());
	                    // quick feedback (optional)
	                    System.out.println("Saved: score=" + hudModel.getHealth1() + ", balls=" + hudModel.getcoinCount());
	                }
	                
	                default -> {
	                	timer.stop();
	                    javax.swing.JOptionPane.showMessageDialog(
	                        null,
	                        "Invalid key! Use arrow keys, or SPACE.",
	                        "Invalid Key",
	                        javax.swing.JOptionPane.WARNING_MESSAGE
	                        
	                    );
	                    timer.start();}
	            }
	        }
	    });
	}
 
}
 
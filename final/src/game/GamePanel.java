package game;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * @author Braden Wakefield
 * 
 * Help Citation
 * Only used CSSE220 materials
 */
public class GamePanel extends JPanel{ // the controller class following MVC

    private final GameComponent canvas = new GameComponent();
	
	public GamePanel() {
		this.setLayout(new BorderLayout(8,8));
		this.setBackground(GameComponent.BG);
		this.add(canvas, BorderLayout.CENTER); // control where we add the canvas to
		
		setFocusable(true);          // must be focusable to get keys
		requestFocusInWindow();      // ask for focus. allows you to switch focus. MUST HAVE
		
		this.add(buildControls(), BorderLayout.SOUTH);
		
		this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
        	    switch (e.getKeyCode()) {
        	    
        	    	case KeyEvent.VK_B -> {
        	        int x = (int)(Math.random() * canvas.getWidth());
        	        int y = (int)(Math.random() * canvas.getHeight());
        	        canvas.addBall(x, y);
        	    	}
        	        case KeyEvent.VK_LEFT  -> canvas.moveDelta(-10);
        	        case KeyEvent.VK_RIGHT -> canvas.moveDelta(10);
        	        case KeyEvent.VK_SPACE -> {
        	            if (canvas.timer.isRunning()) canvas.stop();
        	            else canvas.start();
        	        }
        	        
        	    }
                canvas.repaint(); // need to refresh the screen
            }
        });
	}
	
	private JComponent buildControls() {
        JPanel controls = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 6));
        JButton left   = new JButton("◀ Left");
        JButton right  = new JButton("Right ▶");
        JButton center = new JButton("Center");



        left.addActionListener(e -> { canvas.moveDelta(-10); requestFocusInWindow(); }); // focus in window gives focus back to panel
        right.addActionListener(e -> { canvas.moveDelta(+10); requestFocusInWindow(); });
        center.addActionListener(e -> { canvas.setX(canvas.WIDTH/2); requestFocusInWindow(); }); // setX centers that

        controls.add(left); 
        controls.add(right);
        controls.add(center);
        return controls;
    }
	
	
}

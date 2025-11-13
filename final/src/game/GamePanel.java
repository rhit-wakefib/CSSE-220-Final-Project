package game;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;
import javax.swing.Timer;

/**
 * @author Braden Wakefield, Terrell Doxie
 * Help Citation: used claude for refactoring
 */
public class GamePanel extends JPanel {
    private final HudModel hudModel = new HudModel();
    private final GameComponent canvas = new GameComponent(hudModel);
    private final HudViewer hudView = new HudViewer();
    private final Timer timer;
    
    private JumpController jumpController;
    private InteractController attackController;
    
    public GamePanel() {
        setupLayout();
        this.jumpController = new JumpController(canvas.p);
        this.attackController = new InteractController();
        canvas.collisionHandler.setGamePanel(this); // Link collision handler to panel
        buildKeys();
        this.timer = new Timer(30, e -> tick());
    }
    
    private void setupLayout() {
        JPanel layered = new JPanel();
        layered.setLayout(new OverlayLayout(layered));
        layered.setOpaque(false);
        
        canvas.setOpaque(false);
        hudView.setOpaque(false);
        hudView.setAlignmentX(0f);
        hudView.setAlignmentY(0f);
        
        layered.add(hudView);
        layered.add(canvas);
        
        hudView.refresh(hudModel);
        
        this.setLayout(new BorderLayout());
        this.add(layered, BorderLayout.CENTER);
        this.setBackground(canvas.BG);
    }
    
    private void tick() {
        hudView.refresh(hudModel);
        canvas.updateState();
        canvas.repaint();
    }
    
    public void startGame() { 
        timer.start(); 
    }
    
    public void stopGame() { 
        timer.stop(); 
    }
    
    public boolean isAttackActive() {
        return attackController.isInteractActive();
    }
    
    public void deactivateAttack() {
        attackController.deactivateInteract();
    }
    
    private void buildKeys() {
        this.setFocusable(true);
        this.requestFocusInWindow();
        
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e.getKeyCode());
            }
        });
    }
    
    private void handleKeyPress(int keyCode) {
        if (keyCode == KeyEvent.VK_LEFT) {
            canvas.p.moveLeft();
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            canvas.p.moveRight();
        } else if (keyCode == KeyEvent.VK_DOWN) {
            attackController.activateInteract();
        } else if (keyCode == KeyEvent.VK_UP) {
            jumpController.initiateJump();
        } else if (keyCode == KeyEvent.VK_SPACE) {
            togglePause();
        } else if (keyCode == KeyEvent.VK_S) {
            saveGame();
        } else {
            showInvalidKeyWarning();
        }
    }
    
    private void togglePause() {
        if (timer.isRunning()) {
            timer.stop();
        } else {
            timer.start();
        }
    }
    
    private void saveGame() {
        System.out.println("Saved: health=" + hudModel.getHealth1() + 
                         ", coins=" + hudModel.getcoinCount());
    }
    
    private void showInvalidKeyWarning() {
        timer.stop();
        javax.swing.JOptionPane.showMessageDialog(
            null,
            "Invalid key! Use arrow keys or SPACE.",
            "Invalid Key",
            javax.swing.JOptionPane.WARNING_MESSAGE
        );
        timer.start();
    }
}
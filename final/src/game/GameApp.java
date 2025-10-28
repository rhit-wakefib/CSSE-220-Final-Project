package game;

import javax.swing.JFrame;

public class GameApp {


    private final JFrame frame = new JFrame("Moving Object — Key Listener");
    private GamePanel panel = new GamePanel();
    
    public GameApp() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // kills all threads
//        frame.add(panel); // don't want to use this because it will only dedicate a portion of the screen to this.
        frame.setContentPane(panel);   // add our game panel. sets the entire screen instead of just a portion (add)
        frame.pack();                            // size to panel's preferred size
        frame.setLocationRelativeTo(null);       // centered placement. null means whatever your devices relative center is
    }

    public void show() { // starts the game
        frame.setVisible(true); // makes the frame visible
    }
    
}



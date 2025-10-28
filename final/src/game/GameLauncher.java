package game;

import javax.swing.SwingUtilities;
/**
 * @author Braden Wakefield
 * 
 * Help Citation
 * Only used CSSE220 materials
 */

public class GameLauncher {

	public static void main(String[] args) {

//		new GameApp().show(); // starts the game but doesn't take care of the threads which will cause issues
			
		// must always use this now on for swing projects
		SwingUtilities.invokeLater(() -> new GameApp().show()); // handles the threads safe now. () -> is a function parameter
		// makes safe thread
	}

}

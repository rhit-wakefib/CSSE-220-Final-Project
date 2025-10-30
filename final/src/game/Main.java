package game;

import javax.swing.SwingUtilities;

/**
 * Entry point for the Ball Animation game application.
 * <p>
 * The {@code main} method simply schedules the creation of the {@link GameApp}
 * on the Swing event-dispatch thread (EDT) to ensure thread-safe GUI initialization.
 * </p>
 * 
 * <h2>Responsibilities:</h2>
 * <ul>
 *   <li>Start the Swing application</li>
 *   <li>Ensure all UI code runs on the Event Dispatch Thread</li>
 * 		<li>Construct the {@link GameApp} and call its {@code show()} method</li>
 * </ul>
 * */


public class Main {

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(()-> new GameApp().show());

	}

}

package game;

import java.awt.Color;
import java.awt.Font;
import java.util.Timer;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * @author's Braden Wakefield Terrel Doxie
 * 
 **************************************************************************************** 

 *         REQUIRED HELP CITATION

 *         DONE: "only used CSSE220 materials"

 *************************************************************************************** 
 *
 */

public class HudViewer extends JLabel {
	Timer timer;
	
	public HudViewer() {
		setOpaque(false);
		setForeground(new Color(0, 0, 0));
        setFont(getFont().deriveFont(Font.BOLD, 13f));
	}
	
	public void refresh(HudModel hud) {
        StringBuilder html = new StringBuilder(
            "<html><h2 style='margin:0;padding:0;'>Score</h2><ol>"
        );

//        html.append("<li>Current: ").append(hud.getScore()).append("</li>");

        html.append("<li>Health: ").append(hud.getHealth1()).append("</li>");
        if(hud.getHealth1() == 0) {
        	//timer.stop();
            javax.swing.JOptionPane.showMessageDialog(
                null,
                "You lost all of your lives!",
                "Game Over",
                javax.swing.JOptionPane.PLAIN_MESSAGE);
            hud.health = 3;
            
        }
        

        html.append("<li>Coins: ").append(hud.getcoinCount()).append("</li>");

        html.append("<li>Points: ").append(hud.getScore()).append("</li>");

 //       html.append("<li>Score: ").append(hud.getScore()).append("</li>");

        html.append("</ol></html>");
        setText(html.toString());
    }

}

package game;

import java.awt.Color;
import java.awt.Font;

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
	
	public HudViewer() {
		setOpaque(false);
		setForeground(new Color(0, 0, 0));
        setFont(getFont().deriveFont(Font.BOLD, 13f));
	}
	
	public void refresh(HudModel hud) {
        StringBuilder html = new StringBuilder(
            "<html><h2 style='margin:0;padding:0;'>Score</h2><ol>"
        );
        html.append("<li>Health: ").append(hud.getHealth1()).append("</li>");
        html.append("<li>Coins: ").append(hud.getcoinCount()).append("</li>");
        html.append("<li>Score: ").append(hud.getScore()).append("</li>");
        html.append("</ol></html>");
        setText(html.toString());
    }

}

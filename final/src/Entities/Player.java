package Entities;

import java.awt.Color;
import java.awt.Graphics2D;

public class Player extends Entity {
	
    private int x, y;
    private int dx = 3, dy = 2;
    private int radius = 15;
    private Color color = Color.RED;
    
    public Player(int x, int y) {
    	super(x,y);

    }

	
    public void draw(Graphics2D g2) {
        g2.setColor(color);
        g2.fillOval(x - radius, y - radius, radius * 2, radius * 2);
    }
}

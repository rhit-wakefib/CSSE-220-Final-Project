package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;



public abstract class Item {
	protected int x;
	protected int y;
	protected Color color;
	protected int radius;
	protected BufferedImage sprite; 
	protected boolean spriteLoaded = false;
	protected String Image;
	
	
	public Item() {
		  
	        try {
				sprite = ImageIO.read(Item.class.getResource(Image));
				spriteLoaded = true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				spriteLoaded=false;
			} 
	        
	 }
	
	 public Item(int x, int y) {
		  this.x = x;
	        this.y = y;
	        try {
				sprite = ImageIO.read(Item.class.getResource(Image));
				spriteLoaded = true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				spriteLoaded=false;
			} 
	        
	 }
	
	
	
	
	
	 public void draw(Graphics2D g2) {
			if (spriteLoaded) {
	    		int drawX= x-radius;
	    		int drawY= y-radius;
	    		int size = radius*2;
	    		g2.drawImage(sprite, drawX, drawY, size, size, null);
	    	}else {
	        g2.setColor(color);
	        g2.fillOval(x - radius, y - radius, radius * 2, radius * 2);
	    }}
}

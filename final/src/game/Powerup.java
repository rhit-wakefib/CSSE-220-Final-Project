package game;


import java.io.IOException;

import javax.imageio.ImageIO;

public class Powerup extends Item {
	 private int radius = 8;
	 private String image = "US_One_Cent_Obv.png";
	 // implement some sort 
	 
	 public Powerup(int x, int y) {
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

	 
	
}

package game;


import java.io.IOException;

import javax.imageio.ImageIO;

public class SpecialBlock extends Item {
	 private int radius = 8;
	 private String image = "US_One_Cent_Obv.png";
	 // implement some sort 
	 
	 public SpecialBlock(int x, int y) {
		 	super(x,y);
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

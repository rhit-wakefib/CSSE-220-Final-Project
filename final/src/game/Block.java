package game;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Block extends Item {
	 private int radius = 8;
	 private int value;
	 private String image = "US_One_Cent_Obv.png";
	 
	 public Block(int x, int y,int value) {
		 super(x,y);
		    this.x = x;
	        this.y = y;
	        this.value=value;
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

package game;


import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author's Charlie Kofahl
 * 
 **************************************************************************************** 

 *         REQUIRED HELP CITATION

 *         DONE: "only used CSSE220 materials"

 *************************************************************************************** 
 *
 */
public class SpecialBlock extends Item {

	 private int radius = 8;
	 private static String image = "US_One_Cent_Obv.png";
	 // implement some sort 
	 
	 public SpecialBlock(int x, int y) {
		 	super(x,y,image);
		    this.x = x;
	        this.y = y;
	        try {
				sprite = ImageIO.read(Item.class.getResource(image));
				spriteLoaded = true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				spriteLoaded=false;
			} 
	        
	}

	 
	
}

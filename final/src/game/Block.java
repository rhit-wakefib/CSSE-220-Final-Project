package game;


/**
 * @author's Charlie Kofahl
 * 
 **************************************************************************************** 

 *         REQUIRED HELP CITATION

 *         DONE: "only used CSSE220 materials"

 *************************************************************************************** 
 *
 */
public class Block extends Item {
	 
	 private int value;

	
	 public Block(int x, int y,int value)  {
		 super(x,y,"US_One_Cent_Obv.png");
		    this.x = x;
	        this.y = y;
	        this.radius = 30;
	      
	        this.value=value;
}
}

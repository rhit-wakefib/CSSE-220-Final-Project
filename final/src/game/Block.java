package game;



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

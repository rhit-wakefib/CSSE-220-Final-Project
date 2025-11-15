package game;

import Entities.Player;

/**
 * @author's Braden Wakefield Terrel Doxie
 * 
 **************************************************************************************** 

 *         REQUIRED HELP CITATION

 *         DONE: "only used CSSE220 materials"

 *************************************************************************************** 
 *
 */
public class HudModel {
	public Player player;

	

	protected int scoreCount = 0;

	protected int health = 3;

    protected int coinCount = 0;
    private int score;


    public int getScore() { return scoreCount; }

    public int getHealth1() { return health; }

    public int getcoinCount() { return coinCount; }
      
    public void loseHealth1() { 
    	this.health-=1;
    	}
   
    public void addCoin(int toadd) { 
    	this.coinCount +=toadd; 
    	this.scoreCount = coinCount * 10; 
    	}
    

    public void updateAll(int width, int height) {
    }
}
    


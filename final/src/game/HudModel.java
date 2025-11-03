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

	
	private int score = 0;
    private int coinCount = 0;

    public int getScore() { return score; }
    public int getcoinCount() { return coinCount; }
    
    public void addScore(int delta) { 
    	this.score +=delta; 
    	}
    public void addCoin(int toadd) { 
    	this.coinCount +=toadd; 
    	}
    public void setcoinCount(int count) { 
    	this.coinCount = count; 
    	}
    public void updateAll(int width, int height) {
    	//removig this for now since its only use is a tick counter / displayer it has no actual use
    }
}
    


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

	
	private int scoreCount = 0;
    private int coinCount = 0;

    public int getScore() { return scoreCount; }
    public int getcoinCount() { return coinCount; }
    
    public void addScore(int delta) { 
    	this.scoreCount = coinCount * 10; 
    	}
    public void addCoin(int toadd) { 
    	this.coinCount +=toadd; 
    	this.scoreCount = coinCount * 10; 
    	}
    public void setcoinCount(int count) { 
    	this.coinCount = count; 
    	}
    public void setScore(int score) {
    	this.scoreCount = score;
    }
    public void updateAll(int width, int height) {
    	//removig this for now since its only use is a tick counter / displayer it has no actual use
    }
}
    


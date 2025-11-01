package game;

import Entities.Player;


public class HudModel {
	public Player player;

	
	private int score = 0;
    private int ballCount = 0;

    public int getScore() { return score; }
    public int getBallCount() { return ballCount; }
    
    public void addScore(int delta) { 
    	this.score +=delta; 
    	}
    public void setBallCount(int count) { 
    	this.ballCount = count; 
    	}
    public void updateAll(int width, int height) {
}
}
    


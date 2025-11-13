package platforms;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import game.GameComponent;

/**
 * A Platform move around the screen and collects RainDrops.
 * 
 * A Platform can fill with Raindrops; when it does, it EXPLODES 
 * into a shower of RainDrops. 
 *
 */
public class Platform extends AbstractPlatform {
	private static final int WIDTH = 120;
	private static final int HEIGHT = 20;
//	private static final int RED_START = 222, GREEN_START=184, BLUE_START=135, WATER_INCREMENT=5;
//	private static final int DROPS_ON_EXPLODE = 50;
//	private int rainDropsCollected = 0;
//	private int invincibleCount = 0;
//	private boolean bounced;
	
	public Platform(int x, int y, int xVelocity, int yVelocity, GameComponent gameComponent) {
		super(x, y, xVelocity, yVelocity, gameComponent, WIDTH, HEIGHT);
	}

	public void collideWithPlatform(AbstractPlatform otherPlatform ) {
		this.reverseDirection();
		this.update();
//		bounced =true;
	}

	@Override
	public void update() {
		super.update();
		if ( isOffScreen()  ) {
//			this.reverseDirection();
		}
//		invincibleCount--;
//		bounced =false;
	}

	public void drawOn(Graphics2D g) {

		int drawX = (int) (getX() - (WIDTH/2));
	    int drawY = (int) (getY() - (HEIGHT*2));
	    g.setColor(Color.BLACK);
	    g.fillRect(drawX, drawY, WIDTH, HEIGHT);
	}

	

//	@Override
//	public void onRemove() {
//		for (int i = 0; i < DROPS_ON_EXPLODE; i++) {
//			this.gameComponent.createRainDrop(this.getBoundingBox());
//		}
//	}
	

	
	
	//Methods shared with UserControlledPlatform but not with GameObject
//	@Override
//	public void removeDrop() {
//		if (this.invincibleCount <= 0) {
//			this.rainDropsCollected = this.rainDropsCollected - WATER_INCREMENT;
//			if ( this.rainDropsCollected< 0 ) {
//				 this.rainDropsCollected = 0;
//			}
//		}
//	}
	
	@Override
	public void addDrop() {
//		if (this.invincibleCount <= 0) {
//			this.rainDropsCollected = this.rainDropsCollected + WATER_INCREMENT;
//		}
	}
	
	@Override
	public void makeInvinciple() {
//		this.invincibleCount = 50;
	}
	
	

}

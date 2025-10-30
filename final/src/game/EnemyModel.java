package game;

import java.util.ArrayList;
import java.util.List;


public class EnemyModel {
	
	private List<Enemy> balls = new ArrayList<>();
	
	
	 public void addBall(int x, int y) {
	        balls.add(new Enemy(x, y));
	    }
	 
// Called each tick from the timer */
 	public void updateAll(int width, int height) {
 		for (Enemy b: balls) {
 		b.move(width, height);
 		handleCollisions();
 		
 	} 
}

 	// Add getter to count balls for score HUD
 	public int getBallCount() {
 		return balls.size();
 	}
 	
 	public List<Enemy> getBalls() {
 		return balls;
 	}
 	
 	private void handleCollisions() {
 	    for (int i = 0; i < balls.size(); i++) {
 	        for (int j = i + 1; j < balls.size(); j++) {
 	            Enemy a = balls.get(i), b = balls.get(j);

 	            int dx = a.x - b.x, dy = a.y - b.y;
 	            int rSum = a.radius + b.radius;

 	            if (dx*dx + dy*dy <= rSum*rSum) {
 	                // 1) swap velocities (equal-mass cartoon bounce)
 	                    a.dx = -a.dx; a.dy = -a.dy;
 	                     b.dx = -b.dx; b.dy = -b.dy;

 	                // 2) nudge apart one step along their new directions
 	                a.x += Integer.signum(a.dx); a.y += Integer.signum(a.dy);
 	                b.x += Integer.signum(b.dx); b.y += Integer.signum(b.dy);
 	            }
 	        }
 	    }
 	}

}

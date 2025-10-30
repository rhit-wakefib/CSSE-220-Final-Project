package game;

public abstract class Entity {

	protected int health = 3;
	protected int damage;
	
	int x, y;
    int dx = 3, dy = 2;
    int radius = 15;
	
	public Entity(int x, int y) {

		this.x = x;
		this.y = y;
	}
	
	protected void takeDamage() {
		health += (-damage);
	}
	
    public void move(int width, int height) {
        x += dx;
        y += dy;
        // wrap around the edges
        
        // Left/right
        if (x - radius < 0) { x = radius; dx = -dx; }
        else if (x + radius > width) { x = width - radius; dx = -dx; }

        // Top/bottom
        if (y - radius < 0) { y = radius; dy = -dy; }
        else if (y + radius > height) { y = height - radius; dy = -dy; }
    }
	
}

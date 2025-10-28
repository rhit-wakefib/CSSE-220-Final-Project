package game;

public abstract class Entity {

	protected int health = 3;
	protected int damage;
	
	public Entity() {
		this.health = health;
		this.damage = damage;
	}
	
	protected void takeDamage() {
		health += (-damage);
	}
	

}

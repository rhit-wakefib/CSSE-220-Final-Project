package game;

import java.util.ArrayList;
import java.util.List;

public class ItemModel {
	private ArrayList<Item> Items = new ArrayList<>();
	public void addItem(Item item) {
		Items.add(item);
	}
//	 private void handleCollisions(Player player) {
//		 for (int i = 0; i < Items.size(); i++) {
//			 Player a = player;
//			 Item b = Items.get(i);
//			 int dx = a.x - b.x, dy = a.y - b.y;
//	         int rSum = a.radius + b.radius;
//	         if (dx*dx + dy*dy <= rSum*rSum) {
//	        	 //Implement a method to keep track of some sort of score/coin value if the item being collided with is either a coin or a powerup
//	        	 
//	         }
//	 }
}


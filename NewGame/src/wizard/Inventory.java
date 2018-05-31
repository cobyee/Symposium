package wizard;

import java.util.ArrayList;

public class Inventory {

	
	static ArrayList<Item> inventory = new ArrayList<Item>();
	
	public Inventory() {
		Item smallPotion = new HpItem("Small Potion", 5, 10);
		Item mediumPotion = new HpItem("Medium Potion", 1, 20);
		inventory.add(smallPotion);
		inventory.add(mediumPotion);
	}
	
	public static void main(String[] args) {

	}
	
	public static ArrayList<Item> getInventory(){
		return inventory;
	}
	
}

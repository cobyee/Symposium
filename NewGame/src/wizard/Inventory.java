package wizard;

import java.util.ArrayList;

public class Inventory {

	
	static ArrayList<Item> inventory = new ArrayList<Item>();
	
	public Inventory() {
		HpItem smallPotion = new HpItem("Small Potion", 5, 10, "resources/smallPotion.png","Restore 20 HP");
		HpItem mediumPotion = new HpItem("Medium Potion", 1, 20, "resources/mediumPotion.png", "Restore 50 HP");
		inventory.add(smallPotion);
		inventory.add(mediumPotion);
	}
	
	public static void main(String[] args) {

	}
	
	public static ArrayList<Item> getInventory(){
		return inventory;
	}
	
}

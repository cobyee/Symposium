package wizard;

import java.util.ArrayList;

public class Inventory {

	
	static ArrayList<Item> inventory = new ArrayList<Item>();
	
	public Inventory() {
		HpItem smallPotion = new HpItem("Small Potion", 5, 20, "resources/smallPotion.png","Restore 20 HP");
		HpItem mediumPotion = new HpItem("Medium Potion", 1, 50, "resources/mediumPotion.png", "Restore 50 HP");
		ManaItem smallMana = new ManaItem("Small Mana Potion",3, 20,"resources/smallMana.png", "Restore 20 mana");
		ManaItem mediumMana = new ManaItem("Medium Mana Potion",1,30,"resources/mediumMana.png", "Restore 30 mana");
		inventory.add(smallPotion);
		inventory.add(mediumPotion);
		inventory.add(smallMana);
		inventory.add(mediumMana);
	}
	
	public static void main(String[] args) {
	}
	
	public static ArrayList<Item> getInventory(){
		return inventory;
	}
	
}

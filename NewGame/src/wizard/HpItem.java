package wizard;

public class HpItem extends Item {

	private int healthRestored;
	
	public HpItem(String name, int amt, int health) {
		super(name,amt);
		healthRestored = health;
	}
	
	public int getRestoration() {
		return healthRestored;
	}
	
}

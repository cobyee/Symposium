package wizard;

public class HpItem extends Item {

	private int healthRestored;
	
	public HpItem(String name, int amt, int health, String source, String description) {
		super(name,amt,source,description);
		healthRestored = health;
	}
	
	public int getRestoration() {
		return healthRestored;
	}
	
}

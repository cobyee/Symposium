package wizard;

public class ManaItem extends Item {

	private int manaRestored;
	
	public ManaItem(String name, int amt, int health, String source, String description) {
		super(name,amt,source,description);
		manaRestored = health;
	}
	
	public int getRestoration() {
		return manaRestored;
	}
	
}
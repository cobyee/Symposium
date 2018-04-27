package wizard;

public class Tile {

	boolean isBlocked;
	boolean isOccupied;
	Characters character;
	
	public Tile(boolean isBlocked, Characters character) {
		this.isBlocked = isBlocked;
		this.character = character;
	}
	
	public static void main(String[] arguments) {
		
	}
	
	public void setBlocked() {
		isBlocked = true;
	}
	public boolean getBlocked() {
		return isBlocked;
	}
}

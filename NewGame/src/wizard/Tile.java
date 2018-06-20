package wizard;

public class Tile {

	boolean isBlocked;
	Characters character = null;
	
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
		if (character != null) {
		return true;
		}
		return isBlocked;
	}
	
	public void placeCharacter(Characters character) {
		this.character = character;
	}
	
	public boolean isOccupied() {
		if(character == null) {
			return false;
		}
		return true;
	}
	public Characters getCharacter() {
		return this.character;
	}
}

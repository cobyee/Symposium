package wizard;

public class Tile {

	boolean isBlocked;
	boolean isOccupied;
	
	public Tile(boolean isBlocked, boolean isOccupied) {
		this.isBlocked = isBlocked;
		this.isOccupied = isOccupied;
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

package wizard;

public class Characters {

	private String name;
	private int hp;
	private int attack;
	private String picSource;
	private boolean ally;
	private boolean canMove;
	private int distance;
	private int xCoordinate;
	private int yCoordinate;
	private boolean didAttack;
	
	public Characters(String name, int hp, int attack, String picSource, boolean ally, boolean canMove, int distance, int xCoord, int yCoord, boolean didattack) {
		this.name = name;
		this.hp = hp;
		this.attack = attack;
		this.picSource = picSource;
		this.ally = ally;
		this.canMove = canMove;
		this.distance = distance;
		xCoordinate = xCoord;
		yCoordinate = yCoord;
		this.didAttack = didattack;
	}
	public int getHp() {
		return hp;
	}
	public int getAtk() {
		return attack;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public String getPic() {
		return picSource;
	}
	public boolean isAlly() {
		return ally;
	}
	public boolean getcanMove() {
		return canMove;
	}
	public int getDistance() {
		return distance;
	}
	public int getX() {
		return xCoordinate;
	}
	public int getY() {
		return yCoordinate;
	}
	public void setX(int n) {
		xCoordinate = n;
	}
	public void setY(int n) {
		yCoordinate = n;
	}
	public boolean getDidAttack() {
		return this.didAttack;
	}
	public void setDidAttack(boolean set) {
		this.didAttack = set;
	}
	
	
	
}

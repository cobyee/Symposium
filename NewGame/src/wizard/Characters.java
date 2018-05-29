package wizard;

public class Characters {

	private String name;
	private double hp;
	private double attack;
	private String picSource;
	private boolean ally;
	private boolean canMove;
	private int distance;
	private int xCoordinate;
	private int yCoordinate;
	private boolean didAttack;
	private double currentHp;
	
	public Characters(String name, double hp, double attack, String picSource, boolean ally, boolean canMove, int distance, int xCoord, int yCoord, boolean didattack) {
		this.name = name;
		this.hp = hp;
		this.currentHp = hp;
		this.attack = attack;
		this.picSource = picSource;
		this.ally = ally;
		this.canMove = canMove;
		this.distance = distance;
		xCoordinate = xCoord;
		yCoordinate = yCoord;
		this.didAttack = didattack;
	}
	public double getHp() {
		return currentHp;
	}
	public double getAtk() {
		return attack;
	}
	public void setHp(double hp) {
		this.currentHp = hp;
	}
	public double getMaxHp() {
		return this.hp;
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

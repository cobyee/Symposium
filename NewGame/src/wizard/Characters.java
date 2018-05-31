package wizard;

public class Characters {

	private String name;
	private double hp;
	private double attack;
	private String picSourceU;
	private String picSourceR;
	private String picSourceL;
	private String picSourceD;
	private boolean ally;
	private boolean canMove;
	private int distance;
	private int xCoordinate;
	private int yCoordinate;
	private boolean didAttack;
	private double currentHp;
	private int face;
	private String[] skills = new String[4];
	
	public Characters(String name, double hp, double attack, String picSourceU, String picSourceR, String picSourceL, String picSourceD, int face, boolean ally, boolean canMove, int distance, int xCoord, int yCoord, boolean didattack, String skill1, String skill2, String skill3, String skill4) {
		this.name = name;
		this.hp = hp;
		this.currentHp = hp;
		this.attack = attack;
		this.picSourceU = picSourceU;
		this.picSourceR = picSourceR;
		this.picSourceL = picSourceL;
		this.picSourceD = picSourceD;
		this.ally = ally;
		this.canMove = canMove;
		this.distance = distance;
		xCoordinate = xCoord;
		yCoordinate = yCoord;
		this.didAttack = didattack;
		this.face = face;
		skills[0] = skill1;
		skills[1] = skill2;
		skills[2] = skill3;
		skills[3] = skill4;
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
	public String getPicU() {
		return picSourceU;
	}
	public String getPicR() {
		return picSourceR;
	}
	public String getPicL() {
		return picSourceL;
	}
	public String getPicD() {
		return picSourceD;
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
	public int getFace() {
		return face;
	}
	public void setFace(int face) {
		this.face = face;
	}
	public String[] getSkill() {
		return skills;
	}
	
}

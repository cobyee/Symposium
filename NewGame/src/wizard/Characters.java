package wizard;

public class Characters {

	private String name;
	private int hp;
	private int attack;
	private String picSource;
	private boolean ally;
	private boolean canMove;
	
	public Characters(String name, int hp, int attack, String picSource, boolean ally, boolean canMove) {
		this.name = name;
		this.hp = hp;
		this.attack = attack;
		this.picSource = picSource;
		this.ally = ally;
		this.canMove = canMove;
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
}

package wizard;

public class Characters {

	private String name;
	private int hp;
	private int attack;
	private String picSource;
	
	public Characters(String name, int hp, int attack, String picSource) {
		this.name = name;
		this.hp = hp;
		this.attack = attack;
		this.picSource = picSource;
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
}

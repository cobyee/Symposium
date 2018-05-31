package wizard;

public class Item {

	String name;
	int amt;
	
	public Item(String name, int amt) {
		this.name=name;
	}
	
	public int getAmt() {
		return amt;
	}
	
	public void setAmt(int amt) {
		this.amt = amt;
	}
	
	public String getName() {
		return name;
	}
	
}

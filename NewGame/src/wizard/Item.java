package wizard;

public class Item {

	String name;
	int amt;
	String source;
	String desc;
	
	public Item(String name, int amt, String source, String desc) {
		this.name=name;
		this.source = source;
		this.desc = desc;
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
	
	public String getSource() {
		return source;
	}

	public String getDescription() {
		return desc;
	}
	
}

package wizard;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

public class SelectionScreen extends BasicGameState {

	private Inventory inventory = new Inventory();
	private static ArrayList<Item> items = new ArrayList<Item>();
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		for(Item i: Inventory.getInventory()) {
			items.add(i);
		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		g.setColor(Color.white);
		g.drawString("Level 1",50,50);
		g.drawRect(50,50, 65, 24);
		g.drawString("Level 2",50,75);
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int arg2) throws SlickException {
		Input input = container.getInput();
		if (input.isKeyDown(Input.KEY_ENTER) && !Application.justSwapped()) {
			sbg.enterState(3);
  		 }
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 2;
	}
	
	public static ArrayList<Item> getInventory(){
		return items;
	}

}
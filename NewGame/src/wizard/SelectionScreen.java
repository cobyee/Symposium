package wizard;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

public class SelectionScreen extends BasicGameState {

	private Inventory inventory = new Inventory();
	private static ArrayList<Item> items = new ArrayList<Item>();
	private static int levelSelected = 1;
	private static String[] levels = {"One","Two","Three"};
	private static boolean canMove = true;
	private static int highestLevelUnlocked = 1;
	private Image lock;
	
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
		for(int i = 0; i < levels.length; i++) {
			g.drawString("Level " + (i+1), 50, 50 + (25 * i));
			if(i+1 > highestLevelUnlocked) {
				lock = new Image("resources/LevelLocked.png");
				lock.draw(50,50+(25*i));
			}
		}
		g.drawRect(50,50+(25*(levelSelected-1)), 65, 24);
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int arg2) throws SlickException {
		Input input = container.getInput();
		if (input.isKeyDown(Input.KEY_ENTER) && !Application.justSwapped()) {
			Application.switchScreen();
			sbg.enterState(3);
  		}
		if(input.isKeyDown(Input.KEY_W) && canMove && levelSelected > 1) {
			canMove = false;
			levelSelected--;
	    	Thread thread = new Thread(){
	    		public void run(){
	    			try {
						Thread.sleep(300);
						canMove = true;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
	    			
	    		}
	    	};
	    	thread.start();
		}
		if(input.isKeyDown(Input.KEY_S) && canMove && levelSelected < levels.length) {
			canMove = false;
			levelSelected++;
	    	Thread thread = new Thread(){
	    		public void run(){
	    			try {
						Thread.sleep(300);
						canMove = true;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
	    			
	    		}
	    	};
	    	thread.start();
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
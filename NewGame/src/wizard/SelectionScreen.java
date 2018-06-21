package wizard;

import java.awt.Font;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

public class SelectionScreen extends BasicGameState {

	private Inventory inventory = new Inventory();
	private static ArrayList<Item> items = new ArrayList<Item>();
	private IntroSound intros;
	private static int levelSelected = 1;
	private static String[] levels = {"One","Two","Three"};
	private static boolean canMove = true;
	private Image background;
	private static int highestLevelUnlocked = 1;
	private Image lock;
	private Image shownImage;
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		background = new Image("resources/selectionBackground.png");
		for(Item i: Inventory.getInventory()) {
			items.add(i);
		}
		intros= new IntroSound();
		intros.playSound();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		background.draw(0,0);
		// TODO Auto-generated method stub
		g.setColor(Color.black);
		g.fillRect(50,90+(55*(levelSelected-1)), 150, 50);
		for(int i = 0; i < levels.length; i++) {
			if(i+1 == levelSelected) {
				TrueTypeFont levelName = new TrueTypeFont(new Font("Verdana", Font.ITALIC , 40),true);
				levelName.drawString(50.0f, (90 + (55 * i)), "Level " + (i+1), Color.white);
			} else {
				TrueTypeFont levelName = new TrueTypeFont(new Font("Verdana", Font.ITALIC , 40),true);
				levelName.drawString(50.0f, (90 + (55 * i)), "Level " + (i+1), Color.black);
			}
			if(i+1 > highestLevelUnlocked) {
				lock = new Image("resources/LevelLocked.png");
				lock.draw(50,90+(55*i));
			}
		}
		if(levelSelected == 1) {
			shownImage = new Image("resources/smallmap1.png");
			shownImage.draw(300,100);
		}
		if(levelSelected == 2) {
			shownImage = new Image("resources/smallmap2.png");
			shownImage.draw(300,100);
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int arg2) throws SlickException {
		Input input = container.getInput();
		if (input.isKeyDown(Input.KEY_ENTER) && !Application.justSwapped()) {
			Application.switchScreen();
			sbg.enterState(3);
			intros.stopSound(); 
  		}
		if(input.isKeyDown(Input.KEY_W) && canMove && levelSelected > 1 && levelSelected-1 < highestLevelUnlocked) {
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
		if(input.isKeyDown(Input.KEY_S) && canMove && levelSelected < levels.length && levelSelected < highestLevelUnlocked) {
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
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
	private static int gold = 200;
	private static IntroSound intros;
	private static int levelSelected = 1;
	private static int levelPlaceHolder;
	private static String[] levels = {"One","Two","Three"};
	private static boolean canMove = true;
	private Image background;
	private static int highestLevelUnlocked = 2;
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
		if(levelSelected != 0) {
			g.fillRect(50,90+(55*(levelSelected-1)), 150, 50);
		} else {
			g.fillRect(465,500, 110, 50);
		}
		for(int i = 0; i < levels.length; i++) {
			if(i+1 == levelSelected) {
				TrueTypeFont levelName = new TrueTypeFont(new Font("Verdana", Font.PLAIN , 40),true);
				levelName.drawString(50.0f, (90 + (55 * i)), "Level " + (i+1), Color.white);
			} else {
				TrueTypeFont levelName = new TrueTypeFont(new Font("Verdana", Font.PLAIN , 40),true);
				levelName.drawString(50.0f, (90 + (55 * i)), "Level " + (i+1), Color.black);
			}
			if(i+1 > highestLevelUnlocked) {
				lock = new Image("resources/LevelLocked.png");
				lock.draw(50,90+(55*i));
			}
		}
		if(levelSelected == 0) {
			TrueTypeFont levelName = new TrueTypeFont(new Font("Verdana", Font.PLAIN , 40),true);
			levelName.drawString(470.0f, 500.0f, "Shop", Color.white);
		} else {
			TrueTypeFont levelName = new TrueTypeFont(new Font("Verdana", Font.PLAIN , 40),true);
			levelName.drawString(470.0f, 500.0f, "Shop", Color.black);
		}
		if(levelSelected == 1) {
			shownImage = new Image("resources/smallmap1.png");
		}
		if(levelSelected == 2) {
			shownImage = new Image("resources/smallmap2.png");
		}
		shownImage.draw(300,100);
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int arg2) throws SlickException {
		Input input = container.getInput();
		if(!Application.justSwapped()) {
			if (input.isKeyDown(Input.KEY_ENTER) && !Application.justSwapped()) {
				if(levelSelected == 0) {
					Application.switchScreen();
					sbg.enterState(4);
				}
				if(levelSelected == 1) {
					Application.switchScreen();
					sbg.enterState(3);
					intros.stopSound();
				}
				if(levelSelected == 2) {
					Application.switchScreen();
					sbg.enterState(5);
					intros.stopSound();
				}
			}
			if(input.isKeyDown(Input.KEY_W) && canMove && levelSelected > 1 && levelSelected-1 < highestLevelUnlocked) {
				canMove = false;
				levelSelected--;
				threadMethod();
			}
			if(input.isKeyDown(Input.KEY_S) && canMove && levelSelected < levels.length && levelSelected < highestLevelUnlocked) {
				canMove = false;
				levelSelected++;
				threadMethod();
			}
			if(input.isKeyDown(Input.KEY_D) && canMove) {
				canMove = false;
				levelPlaceHolder = levelSelected;
				levelSelected = 0;
				threadMethod();
			}
			if(input.isKeyDown(Input.KEY_A) && canMove) {
				canMove = false;
				levelSelected = levelPlaceHolder;
				levelPlaceHolder = 0;
				threadMethod();
			}
			if(input.isKeyDown(Input.KEY_ESCAPE)) {
				Application.switchScreen();
				sbg.enterState(1);
			}
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

	private static void threadMethod() {
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
	
	public static int getGold() {
		return gold;
	}
	
	public static void setGold(int g) {
		gold = g;
	}
	
	public static void addLevel() {
		highestLevelUnlocked++;
	}
	public static void queueMusic() {
		intros.startSound();
		intros.playSound();
	
	}
}
package wizard;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainMenu extends BasicGameState {

	private boolean selectingOption = false;
	private static boolean canMove = true;
	private Color color1 = new Color(184,219,252);
	private Color color2 = new Color(249,246,250);
	private int optionPos = 0;
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		if(selectingOption) {
			g.setColor(Color.white);
			g.drawRect(99, 99, 101, 61);
			g.setColor(color1);
			g.fillRect(100, 100, 100, 60);
			g.setColor(color2);
			g.fillRect(105, 102+(optionPos*20), 90, 18);
			g.setColor(Color.black);
			g.drawString("New Game",106,104);
			g.drawString("Load Game",106,124);
			g.drawString("Quit",106,144);
		} else {
			g.setColor(Color.white);
			g.drawString("Press Enter", 50, 50);
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int arg2) throws SlickException {
		Input input = container.getInput();
		if(selectingOption) {
			if (input.isKeyDown(Input.KEY_ENTER)) {
				if(optionPos == 0) {
					Application.switchScreen();
					sbg.enterState(2);
				}
			}
			if(input.isKeyDown(Input.KEY_W) && canMove) {
				if(optionPos >= 0) {
					canMove = false;
					optionPos--;
					MyTimerTask timer = new MyTimerTask();
			        timer.completeTask(4);
				}
			}
			if(input.isKeyDown(input.KEY_S) && canMove) {
				if(optionPos <= 2) {
					canMove = false;
					optionPos++;
					MyTimerTask timer = new MyTimerTask();
			        timer.completeTask(4);
				}
			}
		} else {
			if (input.isKeyDown(Input.KEY_ENTER)) {
				selectingOption = true;
				MyTimerTask timer = new MyTimerTask();
		        timer.completeTask(4);
	  		 }
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 1;
	}

	public static void setMovable() {
		canMove = true;
	}

}

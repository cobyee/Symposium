package wizard;

import java.awt.Font;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

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

public class MainMenu extends BasicGameState {

	private boolean selectingOption = false;
	private static boolean canMove = true;
	private Color color1 = new Color(184,219,252);
	private Color color2 = new Color(249,246,250);
	private int optionPos = 0;
	private Image background;
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		background = new Image("resources/Oof The Game.png");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		background.draw(0,0);
		if(selectingOption) {
			g.setColor(Color.white);
			//99,99,101,61
			g.drawRect(269, 479, 101, 41);
			g.setColor(color1);
			g.fillRect(270, 480, 100, 40);
			g.setColor(color2);
			g.fillRect(275, 482+(optionPos*20), 90, 18);
			g.setColor(Color.black);
			g.drawString("New Game",276,484);
			g.drawString("Back",276,504);
		} else {
			g.setColor(Color.white);
			g.drawString("Press Enter", 280, 480);
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int arg2) throws SlickException {
		Input input = container.getInput();
		if(selectingOption) {
			if (input.isKeyDown(Input.KEY_ENTER)&& canMove) {
				if(optionPos == 0) {
					Application.switchScreen();
					sbg.enterState(2);
				}
				if(optionPos == 1) {
					selectingOption = false;
					MyTimerTask timer = new MyTimerTask();
			        timer.completeTask(4);
			        optionPos = 0;
				}
			}
			if(input.isKeyDown(Input.KEY_W) && canMove) {
				if(optionPos >= 1) {
					canMove = false;
					optionPos--;
					MyTimerTask timer = new MyTimerTask();
			        timer.completeTask(4);
				}
			}
			if(input.isKeyDown(input.KEY_S) && canMove) {
				if(optionPos <= 0) {
					canMove = false;
					optionPos++;
					MyTimerTask timer = new MyTimerTask();
			        timer.completeTask(4);
				}
			}
		} else {
			if (input.isKeyDown(Input.KEY_ENTER) && canMove) {
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

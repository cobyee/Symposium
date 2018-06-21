package wizard;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Shop extends BasicGameState {

	private static Image background;
	private static Image potion1;
	private static Image potion2;
	private static Image potion3;
	private static Image potion4;
	private static Image cursor;
	private int currentItem = 1;
	private static boolean canMove = true;
	
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		// TODO Auto-generated method stub
		background = new Image("resources/shop.png");
		potion1 = new Image("resources/smallPotion.png");
		potion2 = new Image("resources/mediumPotion.png");
		potion3 = new Image("resources/smallMana.png");
		potion4 = new Image("resources/mediumMana.png");
		cursor = new Image("resources/whitecursor.png");
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		background.draw(0,0);
		cursor.draw(1+((currentItem-1)*128),104);
		potion1.draw(17,120);
		potion2.draw(145,120);
		potion3.draw(273,120);
		potion4.draw(401,120);
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int arg2) throws SlickException {
		Input input = container.getInput();
		if(!Application.justSwapped()) {
			if(input.isKeyDown(Input.KEY_ESCAPE)) {
				Application.switchScreen();
				sbg.enterState(2);
			}
			if(input.isKeyDown(Input.KEY_A) && canMove && currentItem > 1) {
				canMove = false;
				currentItem--;
				threadMethod();
			}
			if(input.isKeyDown(Input.KEY_D) && canMove && currentItem < 4) {
				canMove = false;
				currentItem++;
				threadMethod();
			}
		}
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
	
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 4;
	}

}

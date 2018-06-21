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
	private int currentItem = 0;
	private static boolean canMove = true;
	private static int[] costs = {75,150,100,175};
	
	
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
		cursor.draw(1+((currentItem)*128),104);
		potion1.draw(17,120);
		potion2.draw(145,120);
		potion3.draw(273,120);
		potion4.draw(401,120);
		TrueTypeFont title = new TrueTypeFont(new Font("Verdana", Font.PLAIN , 30),true);
		title.drawString(22.0f, 44.0f, "Shop", Color.white);
		TrueTypeFont name = new TrueTypeFont(new Font("Verdana", Font.PLAIN , 30),true);
		name.drawString(20.0f, 610.0f, SelectionScreen.getInventory().get(currentItem).getName(), Color.white);
		TrueTypeFont description = new TrueTypeFont(new Font("Verdana", Font.PLAIN , 14),true);
		description.drawString(20.0f, 650.0f, SelectionScreen.getInventory().get(currentItem).getDescription(), Color.white);
		TrueTypeFont cost = new TrueTypeFont(new Font("Verdana", Font.PLAIN , 14),true);
		cost.drawString(20.0f, 670.0f, "Cost: " + costs[currentItem], Color.white);
		TrueTypeFont gold = new TrueTypeFont(new Font("Verdana", Font.PLAIN , 20),true);
		gold.drawString(520.0f, 30.0f, "Gold: " + SelectionScreen.getGold(), Color.white);
		TrueTypeFont amt1 = new TrueTypeFont(new Font("Verdana", Font.PLAIN , 10),true);
		amt1.drawString(110, 210.0f, Integer.toString(SelectionScreen.getInventory().get(0).getAmt()), Color.white); 
		TrueTypeFont amt2 = new TrueTypeFont(new Font("Verdana", Font.PLAIN , 10),true);
		amt2.drawString(238, 210.0f, Integer.toString(SelectionScreen.getInventory().get(1).getAmt()), Color.white); 
		TrueTypeFont amt3 = new TrueTypeFont(new Font("Verdana", Font.PLAIN , 10),true);
		amt3.drawString(366, 210.0f, Integer.toString(SelectionScreen.getInventory().get(2).getAmt()), Color.white); 
		TrueTypeFont amt4 = new TrueTypeFont(new Font("Verdana", Font.PLAIN , 10),true);
		amt4.drawString(494, 210.0f, Integer.toString(SelectionScreen.getInventory().get(3).getAmt()), Color.white); 
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int arg2) throws SlickException {
		Input input = container.getInput();
		if(!Application.justSwapped()) {
			if(input.isKeyDown(Input.KEY_ESCAPE)) {
				Application.switchScreen();
				sbg.enterState(2);
			}
			if(input.isKeyDown(Input.KEY_A) && canMove && currentItem > 0) {
				canMove = false;
				currentItem--;
				threadMethod(100);
			}
			if(input.isKeyDown(Input.KEY_D) && canMove && currentItem < 3) {
				canMove = false;
				currentItem++;
				threadMethod(100);
			}
			if(input.isKeyDown(Input.KEY_ENTER) && canMove) {
				if(SelectionScreen.getGold() >= costs[currentItem]) {
					SelectionScreen.getInventory().get(currentItem).setAmt(SelectionScreen.getInventory().get(currentItem).getAmt()+1);
					SelectionScreen.setGold(SelectionScreen.getGold() - costs[currentItem]);
					canMove = false;
					threadMethod(200);
				}
			}
		}
	}


	private static void threadMethod(int n) {
    	Thread thread = new Thread(){
    		public void run(){
    			try {
					Thread.sleep(n);
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

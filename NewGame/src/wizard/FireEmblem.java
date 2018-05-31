package wizard;

import java.awt.Button;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.tiled.TiledMap;

import sun.java2d.loops.DrawRect;
 /**
 * @author panos
 */

//-Djava.library.path=C:\Users\BT_1N3_27\git\Symposium\NewGame\lib\slick
public class FireEmblem extends BasicGame {
	
	private Inventory inventory = new Inventory();
	private ArrayList<Item> items = new ArrayList<Item>();
	
	private static Characters test1;
	private static ArrayList<Characters> turns = new ArrayList<Characters>();
	private int turnLoc = 0;
	private Characters currentTurn = turns.get(turnLoc);
	private static Characters cursor;
	
	private boolean pickingItem = false;
	private int tileAmt = 0;
	private Animation sprite, up, down, left, right, allyTest,Csprite;
	private Animation[] images = {sprite, allyTest};
	
	private Animation enemys, eDown;
	private static Image button;
	private static Image button2;
	private static Image button3;
	private static Image button4;
	private static Image button5;
	private static Image map;
	private static Image itemScreen;
	private static boolean canMove = false;
	private static boolean chooseOption = true;
	private static Characters enemy1;
	private static boolean cursormode;
	
	private static Tile[][] grid = new Tile[10][10];
	
	private static Image[] shownOptions = new Image[5];
	private static Image[] menuOptions = new Image[5];
	private static Image[] menuOptions2 = new Image[5];
	
	private static double currentPer;
	
	Font font = new Font("Verdana", Font.BOLD, 8);
	TrueTypeFont trueTypeFont;
	TrueTypeFont currentMoves;
	TrueTypeFont smallPotion;

	public static int OptionPos = 0;
	public static int ItemPos = 0;
	
	private static Characters main;
	
	
    public FireEmblem() {
    	super("Fire Emblem game");
    } 

    public static void main(String[] arguments) {	
    	populateGrid();
    	for(int i = 0; i < 10; i++) {
    		grid[i][0].setBlocked();
    	}
    	for(int i = 3; i < 10; i++) {
    	    for(int j = 7; j < 10; j++) {
    			grid[j][i].setBlocked();
    		}
    	}
    	for(int i = 3; i < 5; i++) {
    		for(int j = 0; j < 5; j++) {
    			grid[j][i].setBlocked();
    		}
    	}
    	for(int i = 5; i < 10; i++) {
    		for(int j = 0; j < 2; j++) {
    			grid[j][i].setBlocked();
    		}
    	}
    	enemy1 = new Characters("Enemy", 10, 5, "resources/asdf.png","resources/asdf.png","resources/asdf.png","resources/asdf.png",3, false, false, 0, 1, 2, false);
    	grid[1][2].placeCharacter(enemy1);
    	grid[1][2].setBlocked();
    	main = new Characters("Joe", 10, 6, "resources/spriteUp.png","resources/spriteLeft.png", "resources/spriteRight.png", "resources/SpriteFront.png",3, false, false,5,1,1, false);
    	turns.add(main);
    	test1 = new Characters("Ally", 10, 6, "resources/asdf.png","resources/asdf.png","resources/asdf.png","resources/asdf.png",3, false, false,1,4,4,false);
    	turns.add(enemy1);
    	turns.add(test1);
    	
        try
        {
            AppGameContainer app = new AppGameContainer(new FireEmblem());
            app.setDisplayMode(640, 704, false);
            app.start();
        }
        catch (SlickException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void init(GameContainer container) throws SlickException {
    	//grassMap = new TiledMap("resources/map1.tmx");
    	map = new Image("resources/FE Map.png");
    	for(Item i : Inventory.getInventory()) {
    		items.add(i);
    	}
    	itemScreen = new Image("resources/scroll.png");
    	Image [] movementUp = {new Image("resources/spriteUp.png"), new Image("resources/spriteUp.png")};
    	Image [] movementDown = {new Image("resources/spriteFront.png"), new Image("resources/spriteFront.png")};
    	Image [] movementLeft = {new Image("resources/spriteLeft.png"), new Image("resources/spriteLeft.png")};
    	Image [] movementRight = {new Image("resources/spriteRight.png"), new Image("resources/spriteRight.png")};
    	Image[] cursord = {new Image("resources/cursor.png"), new Image("resources/cursor.png")};
    	Image [] test = {new Image("resources/asdf.png"), new Image("resources/asdf.png")};
    	int [] duration = {300, 300}; 
    	
    	Image [] enemyDown = {new Image(enemy1.getPicU()), new Image(enemy1.getPicU())};
    	
    	eDown = new Animation(enemyDown, duration, false);
    	
    	up = new Animation(movementUp, duration, false);
    	down = new Animation(movementDown, duration, false);
    	left = new Animation(movementLeft, duration, false);
    	right = new Animation(movementRight, duration, false); 
    	allyTest = new Animation(test, duration, false);
    	
    	sprite = down; 
    	Csprite = new Animation(cursord, duration, false);
    	enemys = eDown;
    	cursormode = false;
    	
    	menuOptions[0] = new Image("resources/attack.png");
        menuOptions[1] = new Image("resources/items.png");
        menuOptions[2] = new Image("resources/magic.png");
        menuOptions[3] = new Image("resources/move.png");
        menuOptions[4] = new Image("resources/end.png");
        menuOptions2[0] = new Image("resources/attackselected.png");
        menuOptions2[1] = new Image("resources/itemsselected.png");
        menuOptions2[2] = new Image("resources/magicselected.png");
        menuOptions2[3] = new Image("resources/moveselected.png");
        menuOptions2[4] = new Image("resources/endselected.png");
        
        shownOptions[0] = new Image("resources/attackselected.png");
        shownOptions[1] = new Image("resources/items.png");
        shownOptions[2] = new Image("resources/magic.png");
        shownOptions[3] = new Image("resources/move.png");
        shownOptions[4] = new Image("resources/end.png");
        
        cursor = new Characters("cursor", 20, 20, "resources/cursor.png","resources/cursor.png","resources/cursor.png","resources/cursor.png", 3, false, true, 9999, 20, 20, true);
        
        updateButtons();
        
     // initialise the font
        font = new Font("Verdana", Font.BOLD, 8);
        trueTypeFont = new TrueTypeFont(font, true);
        currentMoves = new TrueTypeFont(font,true);

        
        // render some text to the screen
        updateHealth();
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
    	 if(tileAmt > 0) {
    		 chooseOption = false;
    	 }else {
    		 chooseOption = true;
    	 }
    	 Input input = container.getInput();
    	 if(cursormode == false) {
    		 if(input.isKeyDown(Input.KEY_UP) && movable(2) && currentTurn.getY() != 0 && tileAmt != 0) { 
    			 if(currentTurn == turns.get(0)) {
    				 currentTurn.setFace(0);
    				 sprite.update(delta);
    			 }
    			 // The lower the delta the slowest the sprite will animate.
    			 currentTurn.setY(currentTurn.getY()-1);
    			 moveHelper();
    		 }
    		 if(input.isKeyDown(Input.KEY_DOWN) && movable(4) && currentTurn.getY() != 9 && tileAmt != 0) {
    			 if(currentTurn == turns.get(0)) {
    				 currentTurn.setFace(3);
    				 sprite.update(delta);
    			 }
    			 currentTurn.setY(currentTurn.getY()+1);
    			 moveHelper();
    		 }
    		 if (input.isKeyDown(Input.KEY_LEFT)&& movable(3) && currentTurn.getX() != 0 && tileAmt != 0) { 
    			 if(currentTurn == turns.get(0)) {
    				 currentTurn.setFace(1);
    				 sprite.update(delta);
    			 }
    			 currentTurn.setX(currentTurn.getX()-1);
             	moveHelper();
    		 }
    		 if (input.isKeyDown(Input.KEY_RIGHT)&& movable(1) && currentTurn.getX() != 9 && tileAmt != 0) {
    			 if(currentTurn == turns.get(0)) {
    				 currentTurn.setFace(2);
    				 sprite.update(delta);
    			 }
    			 currentTurn.setX(currentTurn.getX()+1);
    			 moveHelper();
    		 }
    		 if (input.isKeyDown(Input.KEY_A) && chooseOption) {
    			 OptionLeft();
    			 optionHelper();
    		 }
    		 if (input.isKeyDown(Input.KEY_D) && chooseOption) {
    			 OptionRight();
    			 optionHelper();
    		 }
    		 if (input.isKeyDown(Input.KEY_ENTER) && OptionPos == 0 && currentTurn.getDidAttack()==false && canAttack()) {
    			 System.out.println("i attacked");
    			 AttackSelection(currentTurn);
    			 MyTimerTask timer = new MyTimerTask();
    			 timer.completeTask(0);
    			 currentTurn.setDidAttack(true);
    			 updateHealth();
    		 }
    		 if(input.isKeyDown(Input.KEY_ENTER) && OptionPos == 1) {
    			 pickingItem = true;
    		 }
    		 if (input.isKeyDown(Input.KEY_ENTER) && OptionPos == 3 ){
    			 tileAmt = currentTurn.getDistance();
    			 canMove = true;
    			 chooseOption = false;
    			 if(tileAmt == 0) {
    				 chooseOption = true;
    			 }
    		 }
    		 if(input.isKeyDown(Input.KEY_ENTER) && OptionPos == 4 && chooseOption) {
    			 currentTurn.setDidAttack(false);
    			 System.out.println(turnLoc);
    			 turnLoc++;
    			 if(turnLoc > turns.size()-1) {
    				 turnLoc = 0;
    			 }
    			 currentTurn = turns.get(turnLoc);
    			 MyTimerTask timer = new MyTimerTask();
    			 timer.completeTask(0);
    			 OptionPos = 0;
    			 resetButtons();
    			 updateButtons();
    			 updateHealth();
    		 }
    	 }
    	 if (cursormode == true){
    		 if (input.isKeyDown(Input.KEY_UP) && cursor.getY() != 0) { 
                 // The lower the delta the slowest the sprite will animate.
                 cursor.setY(cursor.getY()-1);
                 cursorHelper(delta);
             }
             if (input.isKeyDown(Input.KEY_DOWN) && cursor.getY() != 9) {
                 cursor.setY(cursor.getY()+1);
                 cursorHelper(delta);
             }
             if (input.isKeyDown(Input.KEY_LEFT) && cursor.getX() != 0) { 
                 cursor.setX(cursor.getX()-1);
                 cursorHelper(delta);
             }
             if (input.isKeyDown(Input.KEY_RIGHT) && cursor.getX() != 9)  {
                 cursor.setX(cursor.getX()+1);
                 cursorHelper(delta);
             }
             if(input.isKeyDown(Input.KEY_ENTER) && Attackable(currentTurn)) {
            	 System.out.println("hi");
            	 Fighting(currentTurn, grid[cursor.getX()][cursor.getY()].getCharacter());
            	 MyTimerTask timer = new MyTimerTask();
                 timer.completeTask(0);
                 updateHealth();
             }
    	 }
    }

    public void render(GameContainer container, Graphics g) throws SlickException {
    	System.out.println(turns.size());
    	map.draw(0,0);
    	//grassMap.render(0,0);
    	button.draw((int)20, (int)640);
    	button2.draw((int)84, (int)640);
    	button3.draw(148, (int)640);
    	button4.draw(212, (int)640);
    	button5.draw(276, (int)640);
    	trueTypeFont.drawString(600.0f, 10.0f, Double.toString(currentTurn.getHp()), Color.black);
    	currentMoves.drawString(600.0f, 30.0f, Integer.toString(tileAmt), Color.black);
    	
    	
    	if(turns.size()>0) {
    		for (Characters d: turns) {
    			Image[] asdf = {new Image(d.getPicU()), new Image(d.getPicU())};
    			if (d.getFace() == 0) {
    				Image[] u = {new Image(d.getPicU()), new Image(d.getPicU())};
    				asdf = u;
    			}
    			if (d.getFace() == 1) {
    				Image[] r = {new Image(d.getPicR()), new Image(d.getPicR())};	
    				asdf = r;
    			}
    			if(d.getFace()==2) {
    				Image[] l = {new Image(d.getPicL()), new Image(d.getPicL())};
    				asdf = l;
    			}
    			if(d.getFace()==3) {
    				Image[] fda = {new Image(d.getPicD()), new Image(d.getPicD())};
    				asdf = fda;
    			}
    			int [] duration = {300, 300}; 
    			Animation df = new Animation(asdf,duration, false);
    			df.draw(d.getX()*64, d.getY()*64);
    		}
    	} else {
    		System.out.println("game over");
    	}
    	for(int i = 0; i < turns.size(); i++) {
    		if(specificPerc(turns.get(i)) > 0) {
    			g.drawRect(turns.get(i).getX() * 64 + 10, turns.get(i).getY() * 64, 44, 2);
        		g.fillRect(turns.get(i).getX() * 64 + 10, turns.get(i).getY() * 64, (int)(specificPerc(turns.get(i))*44), 2);
        		g.setColor(Color.red);
    		}
    	}
    	Csprite.draw(cursor.getX()*64, cursor.getY()*64);
    	if(pickingItem) {
    		itemScreen.draw(50,50);
    		int y = 70;
    		for(int i = 0; i < items.size(); i++) {
    	        TrueTypeFont words = new TrueTypeFont(new Font("Verdana", Font.BOLD, 8),true);
    	        if(i == ItemPos) {
    	        	words.drawString(70.0f,y, items.get(i).getName(), Color.blue);
    	        } else {
    	        	words.drawString(70.0f,y, items.get(i).getName(), Color.black);
    	        }
    	        y+=30;
    		}
    	}
    }
    
    public void cursorHelper(int delta) {
    	Csprite.update(delta);
        canMove = false;
        MyTimerTask timer = new MyTimerTask();
        timer.completeTask(0);
    }
    public void moveHelper() {
    	canMove = false;
        MyTimerTask timer = new MyTimerTask();
        timer.completeTask(0);
        tileAmt--;
    }
    public void optionHelper() {
    	updateButtons();
   	 	canMove = false;
   	 	MyTimerTask timer = new MyTimerTask();
   	 	timer.completeTask(1);
    }
    public boolean movable(int direction) {
    	if(canMove) {
    		//1 right, 2 up, 3 left, 4 down
    		if(direction == 1 && currentTurn.getX() != 9) {
    			if(grid[currentTurn.getX()+1][currentTurn.getY()].getBlocked()) {
    				return false;
    			}
    		}
    		if(direction == 2 && currentTurn.getY() != 0) {
    			if(grid[currentTurn.getX()][currentTurn.getY()-1].getBlocked()) {
    				return false;
    			}
    		}
    		if(direction == 3 && currentTurn.getX() != 0) {
    			if(grid[currentTurn.getX()-1][currentTurn.getY()].getBlocked()) {
    				return false;
    			}
    		}
    		if(direction == 4 && currentTurn.getY() != 9) {
    			if(grid[currentTurn.getX()][currentTurn.getY()+1].getBlocked()) {
    				return false;
    			}
    		}
    		return true;
    	}
    	return false;
    }
    
    public boolean canAttack() {
    	if(currentTurn.getX() == 0 || currentTurn.getX() == 9 || currentTurn.getY() == 0 || currentTurn.getY() == 9) {
    		if(currentTurn.getX() == 0) {
    			if(grid[currentTurn.getX()][currentTurn.getY() - 1].isOccupied() || grid[currentTurn.getX()][currentTurn.getY() + 1].isOccupied() || 
    					grid[currentTurn.getX()+1][currentTurn.getY()].isOccupied()) {
    				return true;
    			}
    		}
    		if(currentTurn.getX() == 9) {
    			if(grid[currentTurn.getX()][currentTurn.getY() - 1].isOccupied() || grid[currentTurn.getX()][currentTurn.getY() + 1].isOccupied() || 
    					grid[currentTurn.getX()-1][currentTurn.getY()].isOccupied()) {
    				return true;
    			}
    		}
    		if(currentTurn.getY() == 9) {
    			if(grid[currentTurn.getX()][currentTurn.getY() - 1].isOccupied() || grid[currentTurn.getX()-1][currentTurn.getY()].isOccupied() || 
    					grid[currentTurn.getX()+1][currentTurn.getY()].isOccupied()) {
    				return true;
    			}
    		}
    		if(currentTurn.getY() == 0) {
    			if(grid[currentTurn.getX()][currentTurn.getY() + 1].isOccupied() || grid[currentTurn.getX()-1][currentTurn.getY()].isOccupied() || 
    					grid[currentTurn.getX()+1][currentTurn.getY()].isOccupied()) {
    				return true;
    			}
    		}
    	} else {
    		if(grid[currentTurn.getX()][currentTurn.getY() - 1].isOccupied() || grid[currentTurn.getX()][currentTurn.getY() + 1].isOccupied() || grid[currentTurn.getX()-1][currentTurn.getY()].isOccupied() || 
    				grid[currentTurn.getX()+1][currentTurn.getY()].isOccupied()) {
    			System.out.println("attacked");
    			return true;
    		}
    	}
    	return false;
    }
    
    public static void populateGrid() {
    	for(int i = 0; i < 10; i++) {
    		for(int j = 0; j < 10; j ++) {
    			grid[i][j] = new Tile(false, null);
    		}
    	}
    }
    
    public static void OptionLeft() {
    	if(OptionPos != 0) {
    		shownOptions[OptionPos] = menuOptions[OptionPos];
    		OptionPos--;
    		shownOptions[OptionPos] = menuOptions2[OptionPos];
    	}
    }
    public static void OptionRight() {
    	if(OptionPos != 4) {
    		shownOptions[OptionPos] = menuOptions[OptionPos];
    		OptionPos++;
    		shownOptions[OptionPos] = menuOptions2[OptionPos];
    	}
    }
    public static void updateButtons() {
    	button = shownOptions[0];
    	button2 = shownOptions[1];
    	button3 = shownOptions[2];
    	button4 = shownOptions[3];
    	button5 = shownOptions[4];
    }
    
    public static void resetButtons() throws SlickException {
        shownOptions[0] = new Image("resources/attackselected.png");
        shownOptions[1] = new Image("resources/items.png");
        shownOptions[2] = new Image("resources/magic.png");
        shownOptions[3] = new Image("resources/move.png");
        shownOptions[4] = new Image("resources/end.png");
    }
    
    public static boolean getMovable() {
    	return canMove;
    }
    
    public static void setMovable(boolean b) {
    	canMove = b;
    }

	public static void setOptionMovable(boolean b) {
		chooseOption = b;
	}
	
	public static void AttackSelection(Characters current) {
		cursor.setX(current.getX());
		cursor.setY(current.getY());
		cursormode = true;
	}
	
	public static boolean Attackable(Characters current) {
		if((cursor.getX()==current.getX()-1 || cursor.getX()==current.getX()+1)&&grid[cursor.getX()][cursor.getY()].isOccupied()) {
			return true;
		} 
		if((cursor.getY()==current.getY()-1||cursor.getY()==current.getY()+1)&&grid[cursor.getX()][cursor.getY()].isOccupied()) {
			return true;
		}
		return false;
	}
	public void Fighting(Characters us, Characters them) {
		us.setHp(us.getHp()-them.getAtk());
		them.setHp(them.getHp()-us.getAtk());
		System.out.println(us.getHp()+" "+them.getHp());
		cursormode = false;
		cursor.setX(20);
		cursor.setY(20);
	}
	public void updateHealth() {
		currentPer=currentTurn.getHp()/currentTurn.getMaxHp();
		if(turns.size() >0) {
			checkDead();
		}
	}
	public void checkDead() {
		if (currentPer <= 0) {
			turns.remove(turnLoc);
			currentTurn = turns.get(turnLoc);
			updateHealth();
		}
		
	}
	public double specificPerc(Characters c) {
		return c.getHp()/c.getMaxHp();
	}
	
}


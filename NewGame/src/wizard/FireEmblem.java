package wizard;

import java.awt.Button;
import java.awt.Font;

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
import org.newdawn.slick.tiled.TiledMap;
/**
 * @author panos
 */

//-Djava.library.path=C:\Users\BT_1N3_27\git\Symposium\NewGame\lib\slick
public class FireEmblem extends BasicGame
{
	private static Characters test1;
	private Characters[] turns = {main, test1};
	private int turnLoc = 0;
	private Characters currentTurn = turns[turnLoc];
	
	private int tileAmt = 0;
	private TiledMap grassMap;
	private Animation sprite, up, down, left, right, pikaTest, allyTest;
	private Animation enemys, eDown;
	private static Image button;
	private static Image button2;
	private static Image button3;
	private static Image button4;
	private static Image button5;
	private float x = 64f, y = 64f;
	private boolean[][] blocked;
	private static boolean canMove = false;
	private static boolean chooseOption = true;
	private int buttonX = 20;
	private int buttonY = 640;
	private static Characters enemy1;
	
	private static Tile[][] grid = new Tile[10][10];
	private int characterX = 1;
	private int characterY = 1;
	
	private static Image[] shownOptions = new Image[5];
	private static Image[] menuOptions = new Image[5];
	private static Image[] menuOptions2 = new Image[5];
	
	Font font = new Font("Verdana", Font.BOLD, 8);
	TrueTypeFont trueTypeFont;
	TrueTypeFont currentMoves;

	public static int OptionPos = 0;
	
	static int ourlife = 10;
	static int ouratk = 1;
	
	private static Characters main;
	
	
    public FireEmblem()
    {
        super("Fire Emblem game");
    } 

    public static void main(String[] arguments)
    {	
    	populateGrid();
    	grid[2][2].setBlocked();
    	enemy1 = new Characters("Enemy", 10, 5, "resources/rpgTile101.png", false, false, 0, 1, 2);
    	grid[1][2].placeCharacter(enemy1);
    	grid[1][2].setBlocked();
    	main = new Characters("Joe", 10, 6, "resources/asdf.png", false, false,5,1,1);
    	test1 = new Characters("Ally", 10, 6, "resouces/asdf.png", false, false,1,4,4);
    	
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
    public void init(GameContainer container) throws SlickException
    {
    	grassMap = new TiledMap("resources/map.tmx");
    	
    	Image [] movementUp = {new Image("resources/cursor.png"), new Image("resources/cursor.png")};
    	Image [] movementDown = {new Image("resources/cursor.png"), new Image("resources/cursor.png")};
    	Image [] movementLeft = {new Image("resources/cursor.png"), new Image("resources/cursor.png")};
    	Image [] movementRight = {new Image("resources/cursor.png"), new Image("resources/cursor.png")};
    	Image [] test = {new Image("resources/asdf.png"), new Image("resources/asdf.png")};
    	int [] duration = {300, 300}; 
    	
    	Image [] enemyDown = {new Image(enemy1.getPic()), new Image(enemy1.getPic())};
    	
    	eDown = new Animation(enemyDown, duration, false);
    	
    	up = new Animation(movementUp, duration, false);
    	down = new Animation(movementDown, duration, false);
    	left = new Animation(movementLeft, duration, false);
    	right = new Animation(movementRight, duration, false); 
    	pikaTest = new Animation(test, duration, false);
    	allyTest = new Animation(test, duration, false);

    	sprite = right; 
    	enemys = eDown;
    	
    	// build a collision map based on tile properties in the TileD map
    	blocked = new boolean[grassMap.getWidth()][grassMap.getHeight()];
    	
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
        
        updateButtons();
        
     // initialise the font
        font = new Font("Verdana", Font.BOLD, 8);
        trueTypeFont = new TrueTypeFont(font, true);
        currentMoves = new TrueTypeFont(font,true);

        // render some text to the screen
      
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException
    {
    	 //change later
    	 if(tileAmt > 0) {
    		 chooseOption = false;
    	 }else {
    		 chooseOption = true;
    	 }
    	 Input input = container.getInput();
         if (input.isKeyDown(Input.KEY_UP) && movable(2) && currentTurn.getY() != 0 && tileAmt != 0)
         { 
             //sprite = up;
             //sprite.update(delta);
             // The lower the delta the slowest the sprite will animate.
             canMove = false;
             currentTurn.setY(currentTurn.getY()-1);
             MyTimerTask timer = new MyTimerTask();
             timer.completeTask(0);
             tileAmt--;
         }
         if (input.isKeyDown(Input.KEY_DOWN) && movable(4) && currentTurn.getY() != 9 && tileAmt != 0)
         {
        	 System.out.println("dsf");
             canMove = false;
             currentTurn.setY(currentTurn.getY()+1);
             MyTimerTask timer = new MyTimerTask();
             timer.completeTask(0);
             tileAmt--;
         }
         if (input.isKeyDown(Input.KEY_LEFT)&& movable(3) && currentTurn.getX() != 0 && tileAmt != 0)
         { 
             canMove = false;
             currentTurn.setX(currentTurn.getX()-1);
             MyTimerTask timer = new MyTimerTask();
             timer.completeTask(0);
             tileAmt--;
         }
         if (input.isKeyDown(Input.KEY_RIGHT)&& movable(1) && currentTurn.getX() != 9 && tileAmt != 0)
         {
             currentTurn.setX(currentTurn.getX()+1);
             canMove = false;
             MyTimerTask timer = new MyTimerTask();
             timer.completeTask(0);
             tileAmt--;
         }
         if (input.isKeyDown(Input.KEY_A) && chooseOption) {
        	 OptionLeft();
       	  	 updateButtons();
        	 canMove = false;
        	 MyTimerTask timer = new MyTimerTask();
        	 timer.completeTask(1);
         }
         if (input.isKeyDown(Input.KEY_D) && chooseOption) {
        	 OptionRight();
        	 updateButtons();
        	 canMove = false;
        	 MyTimerTask timer = new MyTimerTask();
        	 timer.completeTask(1);
         }
         if (input.isKeyDown(Input.KEY_ENTER) && OptionPos == 0 && canMove && canAttack()) {
        	 attack(grid[1][2].getCharacter());
        	 System.out.println("i attacked");
        	 System.out.println(grid[1][2].getCharacter().getHp());
        	 canMove = false;
      	     MyTimerTask timer = new MyTimerTask();
      	     timer.completeTask(0);
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
        	System.out.println(turnLoc);
            turnLoc++;
             if(turnLoc > turns.length-1) {
            		turnLoc = 0;
        	 }
        	 currentTurn = turns[turnLoc];
      	     MyTimerTask timer = new MyTimerTask();
      	     timer.completeTask(0);
         }
    }

    public void render(GameContainer container, Graphics g) throws SlickException
    {
    	grassMap.render(0,0);
    //	sprite.draw(main.getX(), main.getY()); 
    	button.draw((int)buttonX, (int)buttonY);
    	button2.draw((int)84, (int)buttonY);
    	button3.draw(148, (int)buttonY);
    	button4.draw(212, (int)buttonY);
    	button5.draw(276, (int)buttonY);
    	trueTypeFont.drawString(600.0f, 10.0f, "20/20", Color.black);
    	currentMoves.drawString(600.0f, 30.0f, Integer.toString(tileAmt), Color.black);
    	enemys.draw(64,128);
    	pikaTest.draw(main.getX()*64, main.getY()*64);
    	allyTest.draw(test1.getX()*64,test1.getY()*64);
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
    	if(canMove) {
    		if(characterX == 0 || characterX == 9 || characterY == 0 || characterY == 9) {
    			if(characterX == 0) {
    				if(grid[characterX][characterY - 1].isOccupied() || grid[characterX][characterY + 1].isOccupied() || 
    						grid[characterX+1][characterY].isOccupied()) {
    					return true;
    				}
    			}
    			if(characterX == 9) {
    				if(grid[characterX][characterY - 1].isOccupied() || grid[characterX][characterY + 1].isOccupied() || 
    						grid[characterX-1][characterY].isOccupied()) {
    					return true;
    				}
    			}
    			if(characterY == 9) {
    				if(grid[characterX][characterY - 1].isOccupied() || grid[characterX-1][characterY].isOccupied() || 
    						grid[characterX+1][characterY].isOccupied()) {
    					return true;
    				}
    			}
    			if(characterY == 0) {
    				if(grid[characterX][characterY + 1].isOccupied() || grid[characterX-1][characterY].isOccupied() || 
    						grid[characterX+1][characterY].isOccupied()) {
    					return true;
    				}
    			}
    		} else {
    			if(grid[characterX][characterY - 1].isOccupied() || grid[characterX][characterY + 1].isOccupied() || grid[characterX-1][characterY].isOccupied() || 
    					grid[characterX+1][characterY].isOccupied()) {
    				return true;
    			}
    		}
    		return false;
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
    
    public static boolean getMovable() {
    	return canMove;
    }
    
    public static void setMovable(boolean b) {
    	canMove = b;
    }
    public void attack(Characters enemya) {
    	ourlife = enemya.getAtk() - ourlife;
    	enemya.setHp(enemya.getHp()- ouratk);
    }
    public void createOptions() {
    	
    	
    }

	public static void setOptionMovable(boolean b) {
		chooseOption = b;
	}
	public static void moving() {
		
	}
}

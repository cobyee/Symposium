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
	private static Characters cursor;
	
	private int tileAmt = 0;
	private TiledMap grassMap;
	private Animation sprite, up, down, left, right, pikaTest, allyTest,Csprite;
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
	private static boolean cursormode;
	
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
    	enemy1 = new Characters("Enemy", 10, 5, "resources/asdf.png", false, false, 0, 1, 2, false);
    	grid[1][2].placeCharacter(enemy1);
    	grid[1][2].setBlocked();
    	main = new Characters("Joe", 10, 6, "resources/spriteFront.png", false, false,5,1,1, false);
    	test1 = new Characters("Ally", 10, 6, "resouces/asdf.png", false, false,1,4,4,false);
    	
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
    	grassMap = new TiledMap("resources/map1.tmx");
    	
    	Image [] movementUp = {new Image("resources/spriteUp.png"), new Image("resources/spriteUp.png")};
    	Image [] movementDown = {new Image("resources/spriteFront.png"), new Image("resources/spriteFront.png")};
    	Image [] movementLeft = {new Image("resources/spriteLeft.png"), new Image("resources/spriteLeft.png")};
    	Image [] movementRight = {new Image("resources/spriteRight.png"), new Image("resources/spriteRight.png")};
    	Image[] cursord = {new Image("resources/cursor.png"), new Image("resources/cursor.png")};
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
    	
    	sprite = down; 
    	Csprite = new Animation(cursord, duration, false);
    	enemys = eDown;
    	cursormode = false;
    	
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
        
        cursor = new Characters("cursor", 0, 0, "resources/cursor.png", false, true, 9999, 0, 0, true);
        
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
    	 if (cursormode == false) {
         if (input.isKeyDown(Input.KEY_UP) && movable(2) && currentTurn.getY() != 0 && tileAmt != 0)
         { 
        	 if(currentTurn == turns[0]) {
        		 sprite = up;
        		 sprite.update(delta);
        	 }
             // The lower the delta the slowest the sprite will animate.
             canMove = false;
             currentTurn.setY(currentTurn.getY()-1);
             MyTimerTask timer = new MyTimerTask();
             timer.completeTask(0);
             tileAmt--;
         }
         if (input.isKeyDown(Input.KEY_DOWN) && movable(4) && currentTurn.getY() != 9 && tileAmt != 0)
         {
        	 if(currentTurn == turns[0]) {
                 sprite = down;
                 sprite.update(delta);
        	 }
        	 System.out.println("dsf");
             canMove = false;
             currentTurn.setY(currentTurn.getY()+1);
             MyTimerTask timer = new MyTimerTask();
             timer.completeTask(0);
             tileAmt--;
         }
         if (input.isKeyDown(Input.KEY_LEFT)&& movable(3) && currentTurn.getX() != 0 && tileAmt != 0)
         { 
        	 if(currentTurn == turns[0]) {
        		 sprite = left;
        		 sprite.update(delta);
        	 }
             canMove = false;
             currentTurn.setX(currentTurn.getX()-1);
             MyTimerTask timer = new MyTimerTask();
             timer.completeTask(0);
             tileAmt--;
         }
         if (input.isKeyDown(Input.KEY_RIGHT)&& movable(1) && currentTurn.getX() != 9 && tileAmt != 0)
         {
        	 if(currentTurn == turns[0]) {
        		 sprite = right;
        		 sprite.update(delta);
        	 }
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
         if (input.isKeyDown(Input.KEY_ENTER) && OptionPos == 0 && currentTurn.getDidAttack()==false && canAttack()) {
        	 System.out.println("i attacked");
        	 AttackSelection();
      	     MyTimerTask timer = new MyTimerTask();
      	     timer.completeTask(0);
      	     currentTurn.setDidAttack(true);
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
    	 if (cursormode == true){
    		 if (input.isKeyDown(Input.KEY_UP) && cursor.getY() != 0)
             { 
    			 Csprite.update(delta);
                 // The lower the delta the slowest the sprite will animate.
                 canMove = false;
                 cursor.setY(cursor.getY()-1);
                 MyTimerTask timer = new MyTimerTask();
                 timer.completeTask(0);
             }
             if (input.isKeyDown(Input.KEY_DOWN) && cursor.getY() != 9)
             {
                 Csprite.update(delta);
            	 System.out.println("dsf");
                 canMove = false;
                 cursor.setY(cursor.getY()+1);
                 MyTimerTask timer = new MyTimerTask();
                 timer.completeTask(0);
             }
             if (input.isKeyDown(Input.KEY_LEFT) && cursor.getX() != 0)
             { 
            	 Csprite.update(delta);
                 canMove = false;
                 cursor.setX(cursor.getX()-1);
                 MyTimerTask timer = new MyTimerTask();
                 timer.completeTask(0);
             }
             if (input.isKeyDown(Input.KEY_RIGHT) && cursor.getX() != 9)
             {
            	 Csprite.update(delta);
                 cursor.setX(cursor.getX()+1);
                 canMove = false;
                 MyTimerTask timer = new MyTimerTask();
                 timer.completeTask(0);
<<<<<<< HEAD
             }
=======
             }
             if(input.isKeyDown(Input.KEY_ENTER) && Attackable(currentTurn)) {
            	 System.out.println("hi");
            	 Fighting(currentTurn, grid[cursor.getX()][cursor.getY()].getCharacter());
            	 MyTimerTask timer = new MyTimerTask();
                 timer.completeTask(0);
             }
>>>>>>> branch 'master' of https://github.com/cobyee/Symposium.git
    		 
    	 }
    }

    public void render(GameContainer container, Graphics g) throws SlickException
    {
    	grassMap.render(0,0);
    	sprite.draw(main.getX()*64, main.getY()*64); 
    	button.draw((int)buttonX, (int)buttonY);
    	button2.draw((int)84, (int)buttonY);
    	button3.draw(148, (int)buttonY);
    	button4.draw(212, (int)buttonY);
    	button5.draw(276, (int)buttonY);
    	trueTypeFont.drawString(600.0f, 10.0f, Integer.toString(currentTurn.getHp()), Color.black);
    	currentMoves.drawString(600.0f, 30.0f, Integer.toString(tileAmt), Color.black);
    	enemys.draw(64,128);
    	//pikaTest.draw(main.getX()*64, main.getY()*64);
    	allyTest.draw(test1.getX()*64,test1.getY()*64);
    	Csprite.draw(cursor.getX()*64, cursor.getY()*64);
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
    			if(grid[currentTurn.getX()][characterY - 1].isOccupied() || grid[currentTurn.getX()][characterY + 1].isOccupied() || grid[currentTurn.getX()-1][characterY].isOccupied() || 
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
	public static void AttackSelection() {
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
	}
}


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
	
	private TiledMap grassMap;
	private Animation sprite, up, down, left, right;
	private Animation enemys, eDown;
	private static Image button;
	private static Image button2;
	private static Image button3;
	private static Image button4;
	private static Image button5;
	private float x = 64f, y = 64f;
	private boolean[][] blocked;
	private static boolean canMove = true;
	private int buttonX = 20;
	private int buttonY = 640;
	private static Characters enemy1;
	private Image test;
	
	private static Tile[][] grid = new Tile[10][10];
	private int characterX = 1;
	private int characterY = 1;
	
	private static Image[] shownOptions = new Image[5];
	private static Image[] menuOptions = new Image[5];
	private static Image[] menuOptions2 = new Image[5];
	
	Font font = new Font("Verdana", Font.BOLD, 8);
	TrueTypeFont trueTypeFont;


	public static int OptionPos = 0;
	
	static int ourlife = 10;
	static int ouratk = 1;
	
    public FireEmblem()
    {
        super("Fire Emblem game");
    } 

    public static void main(String[] arguments)
    {	
    	populateGrid();
    	grid[2][2].setBlocked();
    	enemy1 = new Characters("gay", 10, 5, "resources/rpgTile101.png", false, false);
    	grid[1][2].placeCharacter(enemy1);
    	grid[1][2].setBlocked();
    	
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
    	
    	enemy1 = new Characters("gay", 10, 5, "resources/rpgTile101.png", false, false);
    	
    	Image [] movementUp = {new Image("resources/cursor.png"), new Image("resources/cursor.png")};
    	Image [] movementDown = {new Image("resources/cursor.png"), new Image("resources/cursor.png")};
    	Image [] movementLeft = {new Image("resources/cursor.png"), new Image("resources/cursor.png")};
    	Image [] movementRight = {new Image("resources/cursor.png"), new Image("resources/cursor.png")};
    	int [] duration = {300, 300}; 
    	
    	Image [] enemyDown = {new Image(enemy1.getPic()), new Image(enemy1.getPic())};
    	
    	eDown = new Animation(enemyDown, duration, false);
    	
    	up = new Animation(movementUp, duration, false);
    	down = new Animation(movementDown, duration, false);
    	left = new Animation(movementLeft, duration, false);
    	right = new Animation(movementRight, duration, false); 

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

        // render some text to the screen
      
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException
    {
    	  Input input = container.getInput();
          if (input.isKeyDown(Input.KEY_UP) && movable(2) && characterY != 0)
          { 
              sprite = up;
              sprite.update(delta);
              // The lower the delta the slowest the sprite will animate.
              y -= 64f;
              canMove = false;
              characterY-- ;
              MyTimerTask timer = new MyTimerTask();
              timer.completeTask();
          }
          if (input.isKeyDown(Input.KEY_DOWN) && movable(4) && characterY != 9)
          {
              sprite = down;
              sprite.update(delta);
              y += 64f;
              canMove = false;
              characterY++;
              MyTimerTask timer = new MyTimerTask();
              timer.completeTask();
          }
          if (input.isKeyDown(Input.KEY_LEFT)&& movable(3) && characterX != 0)
          { 
              sprite = left;
              sprite.update(delta);
              x -= 64f;
              canMove = false;
              characterX--;
              MyTimerTask timer = new MyTimerTask();
              timer.completeTask();
          }
          if (input.isKeyDown(Input.KEY_RIGHT)&& movable(1) && characterX != 9)
          {
              sprite = right;
              sprite.update(delta);
              x += 64f;
        	  characterX ++;
              canMove = false;
              MyTimerTask timer = new MyTimerTask();
              timer.completeTask();
          }
          if (input.isKeyDown(Input.KEY_A) && canMove) {
        	  OptionLeft();
        	  
        	  
        	  updateButtons();
        	  canMove = false;
        	  MyTimerTask timer = new MyTimerTask();
        	  timer.completeTask();
          }
          if (input.isKeyDown(Input.KEY_D) && canMove) {
        	  OptionRight();
        	  updateButtons();
        	  canMove = false;
        	  MyTimerTask timer = new MyTimerTask();
        	  timer.completeTask();
          }
          if (input.isKeyDown(Input.KEY_ENTER) && OptionPos == 0 && canMove && canAttack()) {
        	  attack(grid[1][2].getCharacter());
        	  System.out.println("i attacked");
        	  System.out.println(grid[1][2].getCharacter().getHp());
        	  canMove = false;
      	      MyTimerTask timer = new MyTimerTask();
      	      timer.completeTask();
          }
          if (input.isKeyDown(Input.KEY_ENTER) && OptionPos == 3 && canMove && grid[characterX][characterY].getCharacter().isAlly() && grid[characterX][characterY].getCharacter().getcanMove()){
        	  
          }
    }

    public void render(GameContainer container, Graphics g) throws SlickException
    {
    	grassMap.render(0,0);
    	sprite.draw((int)x, (int)y); 
    	button.draw((int)buttonX, (int)buttonY);
    	button2.draw((int)84, (int)buttonY);
    	button3.draw(148, (int)buttonY);
    	button4.draw(212, (int)buttonY);
    	button5.draw(276, (int)buttonY);
    	trueTypeFont.drawString(600.0f, 10.0f, "20/20", Color.black);
    	enemys.draw(64,128);

    }
    
    public boolean movable(int direction) {
    	if(canMove) {
    		//1 right, 2 up, 3 left, 4 down
    		if(direction == 1 && characterX != 9) {
    			if(grid[characterX+1][characterY].getBlocked()) {
    				return false;
    			}
    		}
    		if(direction == 2 && characterY != 0) {
    			if(grid[characterX][characterY-1].getBlocked()) {
    				return false;
    			}
    		}
    		if(direction == 3 && characterX != 0) {
    			if(grid[characterX-1][characterY].getBlocked()) {
    				return false;
    			}
    		}
    		if(direction == 4 && characterY != 9) {
    			if(grid[characterX][characterY+1].getBlocked()) {
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
}

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
	private static Image button;
	private static Image button2;
	private static Image button3;
	private static Image button4;
	private static Image button5;
	private float x = 64f, y = 64f;
	private boolean[][] blocked;
	private static final int SIZE = 64; 
	private static boolean canMove = true;
	private int buttonX = 20;
	private int buttonY = 640;
	
	private static Tile[][] grid = new Tile[10][10];
	private int characterX = 1;
	private int characterY = 1;
	private static Tile tree = new Tile(true, null);
	private static Tile open = new Tile(false, new Characters("Joe", 10, 10, "resources/rpgTile066"));
	
	private static Image[] shownOptions = new Image[5];
	private static Image[] menuOptions = new Image[5];
	private static Image[] menuOptions2 = new Image[5];
	
	Font font = new Font("Verdana", Font.BOLD, 8);
	TrueTypeFont trueTypeFont;


	static int OptionPos = 0;
	
	
	
    public FireEmblem()
    {
        super("Fire Emblem game");
    } 

    public static void main(String[] arguments)
    {	
    	populateGrid();
    	grid[2][2].setBlocked();
    	
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
    	
    	Image [] movementUp = {new Image("resources/rpgTile009.png"), new Image("resources/rpgTile020.png")};
    	Image [] movementDown = {new Image("resources/rpgTile013.png"), new Image("resources/rpgTile020.png")};
    	Image [] movementLeft = {new Image("resources/rpgTile025.png"), new Image("resources/rpgTile008.png")};
    	Image [] movementRight = {new Image("resources/rpgTile026.png"), new Image("resources/rpgTile010.png")};
    	int [] duration = {300, 300}; 
    	
    	up = new Animation(movementUp, duration, false);
    	down = new Animation(movementDown, duration, false);
    	left = new Animation(movementLeft, duration, false);
    	right = new Animation(movementRight, duration, false); 

    	sprite = right; 
    	
    	// build a collision map based on tile properties in the TileD map
    	blocked = new boolean[grassMap.getWidth()][grassMap.getHeight()];
    	
    	menuOptions[0] = new Image("resources/rpgTile020.png");
        menuOptions[1] = new Image("resources/rpgTile021.png");
        menuOptions[2] = new Image("resources/rpgTile022.png");
        menuOptions[3] = new Image("resources/rpgTile023.png");
        menuOptions[4] = new Image("resources/rpgTile024.png");
        menuOptions2[0] = new Image("resources/rpgTile025.png");
        menuOptions2[1] = new Image("resources/rpgTile026.png");
        menuOptions2[2] = new Image("resources/rpgTile027.png");
        menuOptions2[3] = new Image("resources/rpgTile028.png");
        menuOptions2[4] = new Image("resources/rpgTile029.png");
        
        shownOptions[0] = new Image("resources/rpgTile020.png");
        shownOptions[1] = new Image("resources/rpgTile021.png");
        shownOptions[2] = new Image("resources/rpgTile022.png");
        shownOptions[3] = new Image("resources/rpgTile023.png");
        shownOptions[4] = new Image("resources/rpgTile024.png");
        
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
    		System.out.println("fagd");
    		System.out.print(menuOptions[OptionPos]);
    	}
    }
    public static void OptionRight() {
    	if(OptionPos != 4) {
    		shownOptions[OptionPos] = menuOptions[OptionPos];
    		OptionPos++;
    		shownOptions[OptionPos] = menuOptions2[OptionPos];
    		System.out.println(shownOptions[0]);
    		System.out.println(shownOptions[1]);
    		System.out.println(shownOptions[2]);
    		System.out.println(shownOptions[3]);
    		System.out.println(shownOptions[4]);
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
}

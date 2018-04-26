package wizard;

import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
/**
 * @author panos
 */

//-Djava.library.path=C:\Users\BT_1N3_27\git\Symposium\NewGame\lib\slick
public class FireEmblem extends BasicGame
{
	
	private TiledMap grassMap;
	private Animation sprite, up, down, left, right;
	private float x = 64f, y = 64f;
	private boolean[][] blocked;
	private static final int SIZE = 64; 
	private boolean canMove = true;
	
	private static Tile[][] grid = new Tile[10][10];
	private int characterX = 1;
	private int characterY = 1;
	private static Tile tree = new Tile(true, false);
	private static Tile open = new Tile(false,false);
	
	
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
            app.setDisplayMode(640, 640, false);
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
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException
    {
    	  Input input = container.getInput();
          if (input.isKeyDown(Input.KEY_UP) && movable(2))
          { 
              sprite = up;
              sprite.update(delta);
              // The lower the delta the slowest the sprite will animate.
              y -= 64f;
              canMove = false;
              characterY-- ;
          }
          else if (input.isKeyDown(Input.KEY_DOWN) && movable(4) && characterY < 10)
          {
              sprite = down;
              sprite.update(delta);
              y += 64f;
              canMove = false;
              characterY++;
          }
          else if (input.isKeyDown(Input.KEY_LEFT)&& movable(3) && characterX > 0)
          {
              sprite = left;
              sprite.update(delta);
              x -= 64f;
              canMove = false;
              characterX--;
          }
          else if (input.isKeyDown(Input.KEY_RIGHT)&& movable(1) && characterX != 9)
          {
              sprite = right;
              sprite.update(delta);
              x += 64f;
        	  characterX ++;
              canMove = false;
          }
          else if (input.isKeyDown(Input.KEY_SPACE) && !canMove) {
        	  sprite = right;
        	  sprite.update(delta);
        	  canMove = true;
          }
    }

    public void render(GameContainer container, Graphics g) throws SlickException
    {
    	grassMap.render(0,0);
    	sprite.draw((int)x, (int)y); 
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
    			grid[i][j] = new Tile(false, false);
    		}
    	}
    }
}

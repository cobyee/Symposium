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
	
	
    public FireEmblem()
    {
        super("Fire Emblem game");
    }

    public static void main(String[] arguments)
    {
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
    	for (int xAxis=0;xAxis
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException
    {
    	  Input input = container.getInput();
          if (input.isKeyDown(Input.KEY_UP))
          {
              sprite = up;
              sprite.update(delta);
              // The lower the delta the slowest the sprite will animate.
              y -= delta * .8f;
          }
          else if (input.isKeyDown(Input.KEY_DOWN))
          {
              sprite = down;
              sprite.update(delta);
              y += delta * .8f;
          }
          else if (input.isKeyDown(Input.KEY_LEFT))
          {
              sprite = left;
              sprite.update(delta);
              x -= delta * .8f;
          }
          else if (input.isKeyDown(Input.KEY_RIGHT))
          {
              sprite = right;
              sprite.update(delta);
              x += delta * .8f;
          }
    }

    public void render(GameContainer container, Graphics g) throws SlickException
    {
    	grassMap.render(0,0);
    	sprite.draw((int)x, (int)y); 
    }
}

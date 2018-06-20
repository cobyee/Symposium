package wizard;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Application extends StateBasedGame {
//asddas
	   // Game state identifiers
    public static final int SPLASHSCREEN = 0;
    public static final int MAINMENU     = 1;
    public static final int LVLSELECTOR  = 2;
    public static final int GAME         = 3;
    
    public static boolean justSwapped = false;

    // Application Properties
    public static final int WIDTH   = 640;
    public static final int HEIGHT  = 480;
    public static final int FPS     = 60;
    public static final double VERSION = 1.0;

    // Class Constructor
    public Application(String appName) {
        super(appName);
    }

    // Initialize your game states (calls init method of each gamestate, and set's the state ID)
    public void initStatesList(GameContainer gc) throws SlickException {
        // The first state added will be the one that is loaded first, when the application is launched
    	this.addState(new MainMenu());
    	this.addState(new SelectionScreen());
    	this.addState(new LevelOne());
    }
    
	
    public static void main(String[] arguments) {
        try
        {
            AppGameContainer app = new AppGameContainer(new Application("Fire Emblem"+VERSION));
            app.setDisplayMode(640, 704, false);
            app.start();
        }
        catch (SlickException e)
        {
            e.printStackTrace();
        }
    }
    
    public static boolean justSwapped() {
    	return justSwapped;
    }
    
    public static void switchScreen() {
    	justSwapped = true;
    	Thread thread = new Thread(){
    		public void run(){
    			try {
					Thread.sleep(2000);
					justSwapped = false;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
    			
    		}
    	};
    	thread.start();
    }

}

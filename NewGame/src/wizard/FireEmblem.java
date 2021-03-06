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
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import sun.java2d.loops.DrawRect;
 /**
 */
//https://www.imagefu.com/create/button

//http://roguebasin.roguelikedevelopment.org/index.php?title=Roguelike_Intelligence
//asd
//-Djava.library.path=C:\Users\BT_1N3_27\git\Symposium\NewGame\lib\slick

//https://mrbubblewand.wordpress.com/page/8/?archives-list=1
public class FireEmblem extends BasicGameState {
	
	private Inventory inventory = new Inventory();
	private ArrayList<Item> items = new ArrayList<Item>();
	
	private static Characters test1;
	private static ArrayList<Characters> turns = new ArrayList<Characters>();
	private int turnLoc = 0;
	private Characters currentTurn;
	private static Characters cursor;
	
	private static int setted;
	private static boolean pickingItem = false;
	private static boolean isHealing = false;
	private static boolean isFireball = false;
	private static boolean isExplosion = false;
	private static boolean isWaterblast = false;
	private static boolean isFinalspark = false;
	private static boolean isThunder = false;
	private static boolean isHealskill = false;
	private int tileAmt = 0;
	private Animation sprite, up, down, left, right, allyTest,Csprite;
	//private Animation h1,h2,h3,h4,h5,h6,h7,h8,h9,h10,h11,h12,h13,h14,h15,h16,h17;
	private Animation healing;
	private Animation fireballani;
	private Animation explosionani;
	private Animation waterblastani;
	private Animation finalsparkani;
	private Animation thunderani;
	private Animation healSkillani;
	private Animation manapothealani;
	
	private Animation enemys, eDown;
	private static Image button;
	private static Image button2;
	private static Image button3;
	private static Image button4;
	private static Image button5;
	private static Image map;
	private static Image itemScreen;
	private static Image shownImage;
	private static boolean canMove = false;
	private static boolean chooseOption = true;
	private static boolean doneHealing = false;
	private static Characters enemy1;
	private static boolean cursormode;
	private static boolean skillmenu;
	private static boolean skillmodeheal;
	private static boolean skillmodedamage;
	private static int damageskill;
	private static int healskill;
	private static int skillmanaused;
	private static boolean keyDown;
	
	private static int targetplaceX;
	private static int targetplaceY;
	
	private static Tile[][] grid = new Tile[10][10];
	
	private static Image[] shownOptions = new Image[5];
	private static Image[] menuOptions = new Image[5];
	private static Image[] menuOptions2 = new Image[5];
	
	private static Image[] skillOptions = new Image[5];
	private static Image[] skillOptions2 = new Image[5];
	
	private static double currentPer;
	
	private static boolean healingStarted;
	
	Font font = new Font("Verdana", Font.BOLD, 8);
	TrueTypeFont trueTypeFont;
	TrueTypeFont currentMoves;
	TrueTypeFont smallPotion;
	private String skillname;


	public static int OptionPos = 0;
	public static int SkillPos = 0;
	public static int ItemPos = 0;
	
	private static Characters main;
	
	
    public FireEmblem() {
    	super();
    } 
    
    private void healingMethod() {
    	isHealing = true;
    	Thread thread = new Thread(){
		    public void run(){
		    	 MyTimerTask timer = new MyTimerTask();
                 timer.completeTask(5);
                 isHealing = false;
                 System.out.println("sdfdfsd");
                 
		    }
	  };
	  thread.start();
	}
    private void FireballMethod() {
    	isFireball = true;
    	Thread thread = new Thread(){
		    public void run(){
		    	 MyTimerTask timer = new MyTimerTask();
                 timer.completeTask(3);
                 isFireball = false;
                 grid[targetplaceX][targetplaceY].getCharacter().setHp(grid[targetplaceX][targetplaceY].getCharacter().getHp()-damageskill);
               	 currentTurn.setMana(currentTurn.getMana() - skillmanaused);
               	updateMap();
		    }
	  };
	  thread.start();
    }
    private void ThunderMethod() {
    	isThunder = true;
    	Thread thread = new Thread(){
		    public void run(){
		    	 MyTimerTask timer = new MyTimerTask();
                 timer.completeTask(6);
                 isThunder = false;
                 grid[targetplaceX][targetplaceY].getCharacter().setHp(grid[targetplaceX][targetplaceY].getCharacter().getHp()-damageskill);
               	 currentTurn.setMana(currentTurn.getMana() - skillmanaused);
               	updateMap();
		    }
	  };
	  thread.start();
    }
    private void FinalsparkMethod() {
    	isFinalspark = true;
    	Thread thread = new Thread(){
		    public void run(){
		    	 MyTimerTask timer = new MyTimerTask();
                 timer.completeTask(6);
                 isFinalspark = false;
                 grid[targetplaceX][targetplaceY].getCharacter().setHp(grid[targetplaceX][targetplaceY].getCharacter().getHp()-damageskill);
               	 currentTurn.setMana(currentTurn.getMana() - skillmanaused);
               	updateMap();
		    }
	  };
	  thread.start();
    }
    private void WaterblastMethod() {
    	isWaterblast = true;
    	Thread thread = new Thread(){
		    public void run(){
		    	 MyTimerTask timer = new MyTimerTask();
                 timer.completeTask(8);
                 isWaterblast = false;
                 grid[targetplaceX][targetplaceY].getCharacter().setHp(grid[targetplaceX][targetplaceY].getCharacter().getHp()-damageskill);
               	 currentTurn.setMana(currentTurn.getMana() - skillmanaused);
               	updateMap();
		    }
	  };
	  thread.start();
    }
    private void ExplosionMethod() {
    	isExplosion = true;
    	Thread thread = new Thread(){
		    public void run(){
		    	 MyTimerTask timer = new MyTimerTask();
                 timer.completeTask(5);
                 isExplosion = false;
                 grid[targetplaceX][targetplaceY].getCharacter().setHp(grid[targetplaceX][targetplaceY].getCharacter().getHp()-damageskill);
               	 currentTurn.setMana(currentTurn.getMana() - skillmanaused);
               	MyTimerTask timerx = new MyTimerTask();
                timerx.completeTask(0);
               	updateMap();
		    }
	  };
	  thread.start();
    }
    private void healSkillMethod() {
    	isHealskill = true;
    	Thread thread = new Thread(){
		    public void run(){
		    	 MyTimerTask timer = new MyTimerTask();
                 timer.completeTask(5);
                 isHealskill = false;
                 grid[targetplaceX][targetplaceY].getCharacter().setHp(grid[targetplaceX][targetplaceY].getCharacter().getHp()+healskill);
               	 currentTurn.setMana(currentTurn.getMana() - skillmanaused);
               	MyTimerTask timerx = new MyTimerTask();
                timerx.completeTask(0);
               	updateMap();
		    }
	  };
	  thread.start();
    	
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
    public static void SkillLeft() {
    	if(SkillPos != 0) {
    		shownOptions[SkillPos] = skillOptions[SkillPos];
    		SkillPos--;
    		shownOptions[SkillPos] = skillOptions2[SkillPos];
    	}
    }
    public static void SkillRight() {
    	if(SkillPos != 4) {
    		shownOptions[SkillPos] = skillOptions[SkillPos];
    		SkillPos++;
    		shownOptions[SkillPos] = skillOptions2[SkillPos];
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
	
	public static void setOptionItem(boolean b) {
		pickingItem = b;
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
		if(turns.size() >0) {
			checkDead();
		}
		for (int i = 0;i< turns.size();i++) {
			if (turns.get(i).getHp() > turns.get(i).getMaxHp()) {
				turns.get(i).setHp(turns.get(i).getMaxHp());
			}
		}
		
	}
	public void checkDead() {
		for (int q = 0; q < turns.size(); q++) {
			if (turns.get(q).getHp()<=0) {
				turns.remove(q);
			}
		}
	}
	public double specificPerc(Characters c) {
		return c.getHp()/c.getMaxHp();
	}
	public double specificPercMana(Characters c) {
		return c.getMana()/c.getMaxMana();
	}
	public void changeOptionSkills() throws SlickException {
		skillmenu = true;
		for(int i=0; i<skillOptions.length-1; i++) {
			skillOptions[i] = new Image("resources/"+currentTurn.getSkill()[i]+".png");
		}
		for(int i=0;i<skillOptions2.length-1;i++) {
			skillOptions2[i] = new Image("resources/"+currentTurn.getSkill()[i]+"selected.png");
		}
		skillOptions[4] = new Image("resources/back.png");
		skillOptions2[4] = new Image("resources/backselected.png");
		updateSkill();
	}
	public void updateSkill() {
		shownOptions[0] = skillOptions[0];
		shownOptions[1] = skillOptions[1];
		shownOptions[2] = skillOptions[2];
		shownOptions[3] = skillOptions[3];
		shownOptions[4] = skillOptions[4];
		shownOptions[SkillPos] = skillOptions2[SkillPos];
	}
	public void updateMenu() {
		shownOptions[0] = menuOptions[0];
		shownOptions[1] = menuOptions[1];
		shownOptions[2] = menuOptions[2];
		shownOptions[3] = menuOptions[3];
		shownOptions[4] = menuOptions[4];
		shownOptions[OptionPos] = menuOptions2[OptionPos];
	}
	public void useSkill(String name) {
		if (name.equals("explosion")) {
			damageskill = 30;
			skillmodedamage = true;
			cursormode = true;
			skillmanaused = 40;
			skillmenu = false;
			cursor.setX(currentTurn.getX());
			cursor.setY(currentTurn.getY());
		}
		if(name.equals("finalspark")) {
			damageskill = 50;
			skillmodedamage = true;
			cursormode = true;
			skillmanaused = 70;
			skillmenu = false;
			cursor.setX(currentTurn.getX());
			cursor.setY(currentTurn.getY());
			MyTimerTask timer = new MyTimerTask();
	        timer.completeTask(0);
		}
		if(name.equals("fireball")) {
			damageskill = 20;
			skillmodedamage = true;
			cursormode = true;
			skillmanaused = 30;
			skillmenu = false;
			cursor.setX(currentTurn.getX());
			cursor.setY(currentTurn.getY());
			System.out.println("dsaf");
			MyTimerTask timer = new MyTimerTask();
	        timer.completeTask(0);
		}
		if(name.equals("heal")) {
			healskill = 30;
			skillmodeheal = true;
			cursormode = true;
			skillmanaused = 50;
			skillmenu = false;
			cursor.setX(currentTurn.getX());
			cursor.setY(currentTurn.getY());
			MyTimerTask timer = new MyTimerTask();
	        timer.completeTask(0);
		}
		if(name.equals("thunder")) {
			damageskill = 10;
			skillmodedamage = true;
			cursormode = true;
			skillmanaused = 15;
			skillmenu = false;
			cursor.setX(currentTurn.getX());
			cursor.setY(currentTurn.getY());
			MyTimerTask timer = new MyTimerTask();
	        timer.completeTask(0);
		}
		if(name.equals("waterblast")) {
			damageskill = 25;
			skillmodedamage = true;
			cursormode = true;
			skillmanaused = 35;
			skillmenu = false;
			cursor.setX(currentTurn.getX());
			cursor.setY(currentTurn.getY());
			MyTimerTask timer = new MyTimerTask();
	        timer.completeTask(0);
		}
		this.skillname = name;
	}
	public boolean checkTargettable() {
		if (Math.abs(cursor.getX()-currentTurn.getX()) < 3) {
			if (Math.abs(cursor.getY()-currentTurn.getY()) < 3) {
				if (grid[cursor.getX()][cursor.getY()].getCharacter() != null) {
					return true;
				}
			}
		}
		return false;
	}
	public static void stopHealingAnimation() {
		isHealing = false;
	}
	public void updateMap() {
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
    	for(int i = 0; i<turns.size(); i++) {
    		grid[turns.get(i).getX()][turns.get(i).getY()].setBlocked();
    		grid[turns.get(i).getX()][turns.get(i).getY()].placeCharacter(turns.get(i));
    	}
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
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
    	enemy1 = new Characters("Enemy", 10, 5, 100, 100, "resources/asdf.png","resources/asdf.png","resources/asdf.png","resources/asdf.png",3, false, false, 0, 1, 2, false,"fireball", "heal", "thunder", "finalspark");
    	grid[1][2].placeCharacter(enemy1);
    	grid[1][2].setBlocked();
    	main = new Characters("Joe", 10, 6, 100, 100, "resources/spriteUp.png","resources/spriteLeft.png", "resources/spriteRight.png", "resources/SpriteFront.png",3, true, false,5,1,1, false,"waterblast", "explosion", "thunder", "finalspark");
    	turns.add(main);
    	grid[1][1].placeCharacter(main);
    	grid[1][1].setBlocked();
    	test1 = new Characters("Ally", 10, 6, 100, 100, "resources/asdf.png","resources/asdf.png","resources/asdf.png","resources/asdf.png",3, true, false,1,4,4,false, "waterblast", "heal", "thunder", "finalspark");
    	turns.add(enemy1);
    	turns.add(test1);
    	grid[4][4].placeCharacter(test1);
    	grid[4][4].setBlocked();
    	keyDown = false;
    	currentTurn = turns.get(turnLoc);
    	
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
    	Image [] cursord = {new Image("resources/cursor.png"), new Image("resources/cursor.png")};
    	Image [] healingPics = {new Image("resources/heal1.png"),new Image("resources/heal2.png"),new Image("resources/heal3.png"),
    			new Image("resources/heal4.png"),new Image("resources/heal5.png"),new Image("resources/heal6.png"),
    			new Image("resources/heal7.png"),new Image("resources/heal8.png"),new Image("resources/heal9.png"),
    			new Image("resources/heal10.png"),new Image("resources/heal11.png"),new Image("resources/heal12.png"),
    			new Image("resources/heal13.png"),new Image("resources/heal14.png"),new Image("resources/heal15.png"),
    			new Image("resources/heal16.png"),new Image("resources/heal17.png")};
    	Image [] test = {new Image("resources/asdf.png"), new Image("resources/asdf.png")};
    	int [] duration = {300,300};
    	int [] healingDuration = {100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100};
    	
    	//Image [] enemyDown = {new Image(enemy1.getPicU()), new Image(enemy1.getPicU())};
    	SpriteSheet fbs = new SpriteSheet("resources/fireballanimate.png", 64,64);
    	SpriteSheet es = new SpriteSheet("resources/explosionanimate.png",64,64);
    	SpriteSheet ws = new SpriteSheet("resources/waterblastanimate.png",64,64);
    	SpriteSheet ts = new SpriteSheet("resources/thunderanimate.png",64,64);
    	SpriteSheet fss = new SpriteSheet("resources/finalsparkanimate.png",64,64);
    	SpriteSheet hs = new SpriteSheet("resources/healskill.png",64,64);
    	SpriteSheet manas = new SpriteSheet("resources/manapotheal.png",64,64);
    	eDown = new Animation(enemyDown, duration, false);
    	
    	up = new Animation(movementUp, duration, false);
    	down = new Animation(movementDown, duration, false);
    	left = new Animation(movementLeft, duration, false);
    	right = new Animation(movementRight, duration, false); 
    	allyTest = new Animation(test, duration, false);
    	healing = new Animation(healingPics,healingDuration,true);
    	fireballani = new Animation(fbs, 100);
    	explosionani = new Animation(es,100);
    	waterblastani = new Animation(ws,200);
    	thunderani = new Animation(ts,100);
    	finalsparkani = new Animation(fss,100);
    	healSkillani = new Animation(hs,100);
    	manapothealani = new Animation(manas, 100);
    	
    	sprite = down; 
    	Csprite = new Animation(cursord, duration, false);
    	enemys = eDown;
    	cursormode = false;
    	skillmenu = false;
    	
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
        
        cursor = new Characters("cursor", 20, 20, 0, 0, "resources/cursor.png","resources/cursor.png","resources/cursor.png","resources/cursor.png", 3, false, true, 9999, 20, 20, true, null, null, null, null);
        
        updateButtons();
        
        font = new Font("Verdana", Font.BOLD, 8);
        trueTypeFont = new TrueTypeFont(font, true);
        currentMoves = new TrueTypeFont(font,true);

        updateHealth();
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		map.draw(0,0);
    	button.draw((int)20, (int)640);
    	button2.draw((int)84, (int)640);
    	button3.draw(148, (int)640);
    	button4.draw(212, (int)640);
    	button5.draw(276, (int)640);
    	trueTypeFont.drawString(600.0f, 10.0f, Double.toString(currentTurn.getHp()), Color.black);
    	currentMoves.drawString(600.0f, 30.0f, Integer.toString(tileAmt), Color.black);
    	
    	
    	if(turns.size()>0) {
    		updateHealth();
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
    			g.setColor(Color.red);
    			g.drawRect(turns.get(i).getX() * 64 + 10, (turns.get(i).getY() * 64)-2, 44, 2);
        		g.fillRect(turns.get(i).getX() * 64 + 10, (turns.get(i).getY() * 64)-2, (int)(specificPerc(turns.get(i))*44), 2);
    		}
    	}
    	for (int l = 0; l<turns.size(); l++) {
    		if(specificPercMana(turns.get(l)) > 0) {
    			g.setColor(Color.blue);
    			g.drawRect(turns.get(l).getX() * 64 + 10, turns.get(l).getY() * 64, 44, 2);
    			g.fillRect(turns.get(l).getX() * 64 + 10, turns.get(l).getY() * 64, (int)(specificPercMana(turns.get(l))*44), 2);
    		}
    	}
    	Csprite.draw(cursor.getX()*64, cursor.getY()*64);
    	if(pickingItem) {
    		itemScreen.draw(45,50);
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
        	shownImage = new Image(items.get(ItemPos).getSource());
    		shownImage.draw(420,120);
	        TrueTypeFont description = new TrueTypeFont(new Font("Verdana", Font.ITALIC , 16),true);
	        description.drawString(415.0f, 300.0f, items.get(ItemPos).getDescription(), Color.black);
    	}
    	if (isHealing) {
    	healing.draw(currentTurn.getX()*64,currentTurn.getY()*64+10);
    	}
    	if (isFireball) {
    	fireballani.draw(targetplaceX*64,targetplaceY*64);
    	}
    	if (isExplosion) {
    	explosionani.draw(targetplaceX*64,targetplaceY*64);
    	}
    	if (isThunder) {
    	thunderani.draw(targetplaceX*64,targetplaceY*64);
    	}
    	if (isWaterblast) {
    	waterblastani.draw(targetplaceX*64,targetplaceY*64);
    	}
    	if (isFinalspark) {
    	finalsparkani.draw(targetplaceX*64,targetplaceY*64);
    	}
    	if (isHealskill) {
    		healSkillani.draw(targetplaceX*64,targetplaceY*64);
    	}
    	
   // 		if(healingStarted) {
   // 			healingStarted = false;
   // 			healingMethod();
   // 		}
   // 		healing.
   // 		if(doneHealing) {
   // 			System.out.println("sdf");

   // 		}
    //	}
	}

	@Override
	public void update(GameContainer container, StateBasedGame arg1, int delta) throws SlickException {
		if(tileAmt > 0) {
   		 chooseOption = false;
   	 }else {
   		 chooseOption = true;
   	 }
   	 Input input = container.getInput();
   	 if(skillmenu) {
   		 if (input.isKeyDown(Input.KEY_A) && chooseOption) {
   			 SkillLeft();
   			 updateSkill();
   			 optionHelper();
   		 }
   		 if (input.isKeyDown(Input.KEY_D) && chooseOption) {
   			 SkillRight();
   			 updateSkill();
   			 optionHelper();
   		 }
   		 if (input.isKeyDown(Input.KEY_ENTER) && SkillPos == 4) {
   			 updateMenu();
   			 optionHelper();
   			 skillmenu = false;
   		 }
   		 if (input.isKeyDown(Input.KEY_ENTER) && SkillPos == 0) {
   			 useSkill(currentTurn.getSkill()[0]);
   		 }
   		 if (input.isKeyDown(Input.KEY_ENTER) && SkillPos == 1) {
   			 useSkill(currentTurn.getSkill()[1]);
   		 }
   		 if (input.isKeyDown(Input.KEY_ENTER) && SkillPos == 2) {
   			 useSkill(currentTurn.getSkill()[2]);
   		 }
   		 if (input.isKeyDown(Input.KEY_ENTER) && SkillPos == 3) {
   			 useSkill(currentTurn.getSkill()[3]);
   		 }
   	 }
   	 else if(!cursormode && !pickingItem) {
   		 if(input.isKeyDown(Input.KEY_W) && movable(2) && currentTurn.getY() != 0 && tileAmt != 0) { 
   			 if(currentTurn == turns.get(0)) {
   				 currentTurn.setFace(0);
   				 sprite.update(delta);
   			 }
   			 // The lower the delta the slowest the sprite will animate.
   			 currentTurn.setY(currentTurn.getY()-1);
   			 moveHelper();
   			 updateMap();
   		 }
   		 if(input.isKeyDown(Input.KEY_S) && movable(4) && currentTurn.getY() != 9 && tileAmt != 0) {
   			 if(currentTurn == turns.get(0)) {
   				 currentTurn.setFace(3);
   				 sprite.update(delta);
   			 }
   			 currentTurn.setY(currentTurn.getY()+1);
   			 moveHelper();
   			 updateMap();
   		 }
   		 if (input.isKeyDown(Input.KEY_A)&& movable(3) && currentTurn.getX() != 0 && tileAmt != 0) { 
   			 if(currentTurn == turns.get(0)) {
   				 currentTurn.setFace(1);
   				 sprite.update(delta);
   			 }
   			 currentTurn.setX(currentTurn.getX()-1);
            	moveHelper();
            	updateMap();
   		 }
   		 if (input.isKeyDown(Input.KEY_D)&& movable(1) && currentTurn.getX() != 9 && tileAmt != 0) {
   			 if(currentTurn == turns.get(0)) {
   				 currentTurn.setFace(2);
   				 sprite.update(delta);
   			 }
   			 currentTurn.setX(currentTurn.getX()+1);
   			 moveHelper();
   			 updateMap();
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
   			 MyTimerTask timer = new MyTimerTask();
   			 timer.completeTask(1);
   			 System.out.println("Opened");
   		 }
   		 if(input.isKeyDown(Input.KEY_ENTER)&& OptionPos == 2) {
   			 SkillPos=0;
   			 changeOptionSkills();
   			 updateButtons();
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
   			 MyTimerTask timer = new MyTimerTask();
   			 timer.completeTask(0);
   		 }
   		 if(input.isKeyDown(Input.KEY_ENTER) && OptionPos == 4 && chooseOption) {
   			 currentTurn.setDidAttack(false);
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
   	 else if (cursormode == true && skillmodedamage == false && skillmodeheal == false){
   		 if (input.isKeyDown(Input.KEY_W) && cursor.getY() != 0) {
                // The lower the delta the slowest the sprite will animate.
                cursor.setY(cursor.getY()-1);
                cursorHelper(delta);
            }
            if (input.isKeyDown(Input.KEY_S) && cursor.getY() != 9) {
                cursor.setY(cursor.getY()+1);
                cursorHelper(delta);
            }
            if (input.isKeyDown(Input.KEY_A) && cursor.getX() != 0) { 
                cursor.setX(cursor.getX()-1);
                cursorHelper(delta);
            }
            if (input.isKeyDown(Input.KEY_D) && cursor.getX() != 9)  {
                cursor.setX(cursor.getX()+1);
                cursorHelper(delta);
            }
            if(input.isKeyDown(Input.KEY_ENTER) && Attackable(currentTurn)) {
           	 Fighting(currentTurn, grid[cursor.getX()][cursor.getY()].getCharacter());
           	 MyTimerTask timer = new MyTimerTask();
                timer.completeTask(0);
                updateHealth();
            }
   	 }
   	 else if (cursormode == true && skillmodedamage == true) {
   		 if (input.isKeyDown(Input.KEY_W) && cursor.getY() != 0) {
                // The lower the delta the slowest the sprite will animate.
                cursor.setY(cursor.getY()-1);
                cursorHelper(delta);
            }
            if (input.isKeyDown(Input.KEY_S) && cursor.getY() != 9) {
                cursor.setY(cursor.getY()+1);
                cursorHelper(delta);
            }
            if (input.isKeyDown(Input.KEY_A) && cursor.getX() != 0) { 
                cursor.setX(cursor.getX()-1);
                cursorHelper(delta);
            }
            if (input.isKeyDown(Input.KEY_D) && cursor.getX() != 9)  {
                cursor.setX(cursor.getX()+1);
                cursorHelper(delta);
            }
            if (input.isKeyDown(Input.KEY_ENTER) && checkTargettable() && grid[cursor.getX()][cursor.getY()].getCharacter().isAlly() == false) {
             targetplaceX = cursor.getX(); 
             targetplaceY = cursor.getY();
             if (skillname.equals("fireball")) {
            	 FireballMethod();
             }
             if (skillname.equals("thunder")) {
            	 ThunderMethod();
             }
             if (skillname.equals("explosion")){
            	 ExplosionMethod();
             }
             if (skillname.equals("waterblast")) {
            	 WaterblastMethod();
             }
             if (skillname.equals("finalspark")) {
            	 FinalsparkMethod();
             }
           	 cursormode = false;
           	 skillmodedamage = false;
           	 updateMenu();
           	 updateButtons();
         
          
           	 cursor.setX(20);
   			 cursor.setY(20);
           	 MyTimerTask timer = new MyTimerTask();
                timer.completeTask(0);
            }
            if (input.isKeyDown(Input.KEY_ESCAPE)) {
           	 cursormode = false;
           	 skillmodedamage = false;
           	 skillmenu = true;
           	 cursor.setX(20);
           	 cursor.setY(20);
            }
   	 }
   	 else if (cursormode == true && skillmodeheal == true) {
   		 if (input.isKeyDown(Input.KEY_W) && cursor.getY() != 0) {
                // The lower the delta the slowest the sprite will animate.
                cursor.setY(cursor.getY()-1);
                cursorHelper(delta);
            }
            if (input.isKeyDown(Input.KEY_S) && cursor.getY() != 9) {
                cursor.setY(cursor.getY()+1);
                cursorHelper(delta);
            }
            if (input.isKeyDown(Input.KEY_A) && cursor.getX() != 0) { 
                cursor.setX(cursor.getX()-1);
                cursorHelper(delta);
            }
            if (input.isKeyDown(Input.KEY_D) && cursor.getX() != 9)  {
                cursor.setX(cursor.getX()+1);
                cursorHelper(delta);
            }
            if (input.isKeyDown(Input.KEY_ENTER) && checkTargettable() && grid[cursor.getX()][cursor.getY()].getCharacter().isAlly() == true) {
            	targetplaceX = cursor.getX(); 
                targetplaceY = cursor.getY();
            	if (skillname.equals("heal")) {
               	 healSkillMethod();
                }
           	 cursormode = false;
           	 skillmodeheal = false;
           	 updateMenu();
           	 updateButtons();
           	 updateHealth();
           	 cursor.setX(20);
   			 cursor.setY(20);
           	 MyTimerTask timer = new MyTimerTask();
                timer.completeTask(0);
                updateMap();
            }
            if (input.isKeyDown(Input.KEY_ESCAPE)) {
           	 cursormode = false;
           	 skillmodeheal = false;
           	 skillmenu = true;
           	 cursor.setX(20);
           	 cursor.setY(20);
            }
   	 }
   	 else if(pickingItem) {
   		 if (input.isKeyDown(Input.KEY_W)) { 
   			 if(ItemPos > 0) {
               	 MyTimerTask timer = new MyTimerTask();
                    timer.completeTask(2);
   				 ItemPos --;
   			 }
            }
            if (input.isKeyDown(Input.KEY_S)) {
           	 if(ItemPos < items.size()-1) {
               	 MyTimerTask timer = new MyTimerTask();
                    timer.completeTask(2);
                    ItemPos ++;
           	 }
            }
            if(input.isKeyDown(Input.KEY_ESCAPE)) {
           	 pickingItem = false;
            }
            if(input.isKeyDown(Input.KEY_ENTER)) {
           	 if(items.get(ItemPos) instanceof HpItem) {
           		 //currentTurn.setHp(currentTurn.getHp() +  items.get(ItemPos).getRestoration());
           		 MyTimerTask timer = new MyTimerTask();
                    timer.completeTask(2);
           		 pickingItem = false;
           		 healingMethod();

           		 
           	 }
           	 System.out.println("I healed");
            }
   	 }
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 3;
	}
}
//asd

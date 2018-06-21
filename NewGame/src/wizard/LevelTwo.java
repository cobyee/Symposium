package wizard;

import java.awt.Button;
import java.awt.Component;
import java.awt.Font;
import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

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
public class LevelTwo extends BasicGameState {
	
	private ArrayList<Item> items = SelectionScreen.getInventory();
	
	private static Characters side, enemy2,enemy3;
	private static ArrayList<Characters> turns = new ArrayList<Characters>();
	private int turnLoc = 0;
	private Characters currentTurn;
	private static Characters cursor;
	
	private Animation a0,a1,a2,a3,a4;
	private Animation[] anis;
	
	private static boolean pickingItem = false;
	private static boolean isHealing = false;
	private static boolean isFireball = false;
	private static boolean isExplosion = false;
	private static boolean isWaterblast = false;
	private static boolean isWind = false;
	private static boolean isThunder = false;
	private static boolean usedItem = false;
	private static boolean isHealskill = false;
	private int tileAmt = 0;
	private Animation sprite, up, down, left, right, allyTest,Csprite;
	private Animation healing;
	private Animation fireballani;
	private Animation explosionani;
	private Animation waterblastani;
	private Animation windani;
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
	private static Image cube;
	private static boolean canMove = false;
	private static boolean chooseOption = true;
	private static boolean doneHealing = false;
	private static boolean usedSkill = false;
	private static boolean moved = false;
	private static Characters enemy1;
	private static boolean cursormode;
	private static boolean skillmenu;
	private static boolean skillmodeheal;
	private static boolean skillmodedamage;
	private static int damageskill;
	private static int healskill;
	private static int skillmanaused;
	
	private static int targetplaceX;
	private static int targetplaceY;
	
	private static Tile[][] grid = new Tile[10][10];
	private static boolean[][] redArea = new boolean[10][10];
	
	private static Image[] shownOptions = new Image[5];
	private static Image[] menuOptions = new Image[5];
	private static Image[] menuOptions2 = new Image[5];
	private static Image[] skillOptions = new Image[5];
	private static Image[] skillOptions2 = new Image[5];
	
	private boolean cantPress;
	
	private static double currentPer;
	
	private static boolean healingStarted;
	
	private boolean dialoguemode;
	private int dial;
	
	private Clip clip;
	
	private boolean startsong;
	private MapTwoSound mapsound;
	
	Font font = new Font("Verdana", Font.BOLD, 8);
	TrueTypeFont trueTypeFont;
	TrueTypeFont currentMoves;
	TrueTypeFont smallPotion;
	private String skillname;

	private Font dfont;

	private TrueTypeFont dialogueFont;

	private boolean alrdywon;

	private Animation df;

	public static int OptionPos = 0;
	public static int SkillPos = 0;
	public static int ItemPos = 0;
	
	private static Characters main;

	public static int YMoves;

	public static int XMoves;
	
	
    public LevelTwo() {
    	super();
    } 
    
    private void healingMethod() {
    	isHealing = true;
    	usedSkill = true;
    	try {
 	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("resources/pot1.wav"));
 	        clip = AudioSystem.getClip();
 	        clip.open(audioInputStream);
 	        clip.start();
 	    } catch(Exception ex) {
 	        System.out.println("Error with playing sound.");
 	        ex.printStackTrace();
 	    }
    	Thread thread = new Thread(){
		    public void run(){
		    	 MyTimerTask timer = new MyTimerTask();
                 timer.completeTask(5);
                 isHealing = false;
                 clip.stop();
		    }
	  };
	  thread.start();
	}
    private void FireballMethod() {
    	isFireball = true;
    	usedSkill = true;
    	try {
 	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("resources/explosions.wav"));
 	        clip = AudioSystem.getClip();
 	        clip.open(audioInputStream);
 	        clip.start();
 	    } catch(Exception ex) {
 	        System.out.println("Error with playing sound.");
 	        ex.printStackTrace();
 	    }
    	Thread thread = new Thread(){
		    public void run(){
		    	 MyTimerTask timer = new MyTimerTask();
                 timer.completeTask(3);
                 isFireball = false;
                 grid[targetplaceX][targetplaceY].getCharacter().setHp(grid[targetplaceX][targetplaceY].getCharacter().getHp()-damageskill);
               	 currentTurn.setMana(currentTurn.getMana() - skillmanaused);
               	updateMap();
               	clip.stop();
		    }
	  };
	  thread.start();
    }
    private void ThunderMethod() {
    	isThunder = true;
    	usedSkill = true;
    	try {
 	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("resources/thunders.wav"));
 	        clip = AudioSystem.getClip();
 	        clip.open(audioInputStream);
 	        clip.start();
 	    } catch(Exception ex) {
 	        System.out.println("Error with playing sound.");
 	        ex.printStackTrace();
 	    }
    	Thread thread = new Thread(){
		    public void run(){
		    	 MyTimerTask timer = new MyTimerTask();
                 timer.completeTask(6);
                 isThunder = false;
                 grid[targetplaceX][targetplaceY].getCharacter().setHp(grid[targetplaceX][targetplaceY].getCharacter().getHp()-damageskill);
               	 currentTurn.setMana(currentTurn.getMana() - skillmanaused);
               	updateMap();
               	clip.stop();
		    }
	  };
	  thread.start();
    }
    private void WindMethod() {
    	isWind = true;
    	usedSkill = true;
    	try {
 	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("resources/winds.wav"));
 	        clip = AudioSystem.getClip();
 	        clip.open(audioInputStream);
 	        clip.start();
 	    } catch(Exception ex) {
 	        System.out.println("Error with playing sound.");
 	        ex.printStackTrace();
 	    }
    	Thread thread = new Thread(){
		    public void run(){
		    	 MyTimerTask timer = new MyTimerTask();
                 timer.completeTask(6);
                 isWind = false;
                 grid[targetplaceX][targetplaceY].getCharacter().setHp(grid[targetplaceX][targetplaceY].getCharacter().getHp()-damageskill);
               	 currentTurn.setMana(currentTurn.getMana() - skillmanaused);
               	updateMap();
               	clip.stop();
		    }
	  };
	  thread.start();
    }
    private void WaterblastMethod() {
    	isWaterblast = true;
    	usedSkill = true;
    	try {
 	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("resources/waterblasts.wav"));
 	        clip = AudioSystem.getClip();
 	        clip.open(audioInputStream);
 	        clip.start();
 	    } catch(Exception ex) {
 	        System.out.println("Error with playing sound.");
 	        ex.printStackTrace();
 	    }
    	Thread thread = new Thread(){
		    public void run(){
		    	 MyTimerTask timer = new MyTimerTask();
                 timer.completeTask(8);
                 isWaterblast = false;
                 grid[targetplaceX][targetplaceY].getCharacter().setHp(grid[targetplaceX][targetplaceY].getCharacter().getHp()-damageskill);
               	 currentTurn.setMana(currentTurn.getMana() - skillmanaused);
               	updateMap();
               	clip.stop();
		    }
	  };
	  thread.start();
    }
    private void ExplosionMethod() {
    	isExplosion = true;
    	usedSkill = true;
    	try {
 	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("resources/explosions.wav"));
 	        clip = AudioSystem.getClip();
 	        clip.open(audioInputStream);
 	        clip.start();
 	    } catch(Exception ex) {
 	        System.out.println("Error with playing sound.");
 	        ex.printStackTrace();
 	    }
    	Thread thread = new Thread(){
		    public void run(){
		    	 MyTimerTask timer = new MyTimerTask();
                 timer.completeTask(7);
                 isExplosion = false;
                 grid[targetplaceX][targetplaceY].getCharacter().setHp(grid[targetplaceX][targetplaceY].getCharacter().getHp()-damageskill);
               	 currentTurn.setMana(currentTurn.getMana() - skillmanaused);
               	MyTimerTask timerx = new MyTimerTask();
                timerx.completeTask(0);
               	updateMap();
               	clip.stop();
		    }
	  };
	  thread.start();
    }
    private void healSkillMethod() {
    	isHealskill = true;
    	usedSkill = true;
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
    			if (grid[currentTurn.getX()][currentTurn.getY() - 1].isOccupied()) {
    				if (grid[currentTurn.getX()][currentTurn.getY() - 1].getCharacter().isAlly() == false) {
    	    			return true;
    	    			}
    			}
    			if (grid[currentTurn.getX()][currentTurn.getY() + 1].isOccupied()) {
    				if (grid[currentTurn.getX()][currentTurn.getY() + 1].getCharacter().isAlly() == false) {
    	    			return true;
    	    			}
    			}
    			if (grid[currentTurn.getX()+1][currentTurn.getY()].isOccupied()) {
    				if (grid[currentTurn.getX()+1][currentTurn.getY()].getCharacter().isAlly() == false) {
    	    			return true;
    	    			}
    			}
    		}
    		if(currentTurn.getX() == 9) {
    			if (grid[currentTurn.getX()][currentTurn.getY() - 1].isOccupied()) {
    				if (grid[currentTurn.getX()][currentTurn.getY() - 1].getCharacter().isAlly() ==false) {
    	    			return true;
    	    			}
    			}
    			if (grid[currentTurn.getX()][currentTurn.getY() + 1].isOccupied()) {
    				if (grid[currentTurn.getX()][currentTurn.getY() + 1].getCharacter().isAlly()==false) {
    	    			return true;
    	    			}
    			}
    			if (grid[currentTurn.getX()-1][currentTurn.getY()].isOccupied()) {
    				if (grid[currentTurn.getX()-1][currentTurn.getY()].getCharacter().isAlly() == false) {
    	    			return true;
    	    			}
    			}
    		}
    		if(currentTurn.getY() == 9) {
    			if (grid[currentTurn.getX()][currentTurn.getY() - 1].isOccupied()) {
    				if (grid[currentTurn.getX()][currentTurn.getY() - 1].getCharacter().isAlly() == false) {
    	    			return true;
    	    			}
    			}
    			if (grid[currentTurn.getX()-1][currentTurn.getY()].isOccupied()) {
    				if (grid[currentTurn.getX()-1][currentTurn.getY()].getCharacter().isAlly()== false) {
    	    			return true;
    	    			}
    			}
    			if (grid[currentTurn.getX()+1][currentTurn.getY()].isOccupied()) {
    				if (grid[currentTurn.getX()+1][currentTurn.getY()].getCharacter().isAlly()==false) {
    	    			return true;
    	    			}
    			}
    		}
    		if(currentTurn.getY() == 0) {
    			if (grid[currentTurn.getX()][currentTurn.getY() + 1].isOccupied()) {
    				if (grid[currentTurn.getX()][currentTurn.getY() + 1].getCharacter().isAlly() == false) {
    	    			return true;
    	    			}
    			}
    			if (grid[currentTurn.getX()-1][currentTurn.getY()].isOccupied()) {
    				if (grid[currentTurn.getX()-1][currentTurn.getY()].getCharacter().isAlly() == false) {
    	    			return true;
    	    			}
    			}
    			if (grid[currentTurn.getX()+1][currentTurn.getY()].isOccupied()) {
    				if (grid[currentTurn.getX()+1][currentTurn.getY()].getCharacter().isAlly()==false) {
    	    			return true;
    	    			}
    			}
    		}
    	} else {
    			if(grid[currentTurn.getX()][currentTurn.getY() - 1].isOccupied()){
    			if (grid[currentTurn.getX()][currentTurn.getY() - 1].getCharacter().isAlly()==false) {
    			return true;
    			}
    			}
    			if(grid[currentTurn.getX()][currentTurn.getY() + 1].isOccupied()) {
    				if (grid[currentTurn.getX()][currentTurn.getY() + 1].getCharacter().isAlly()==false) {
    	    			return true;
    	    			}
    			}
    			if (grid[currentTurn.getX()-1][currentTurn.getY()].isOccupied()) {
    				if (grid[currentTurn.getX()-1][currentTurn.getY()].getCharacter().isAlly()==false) {
    	    			return true;
    	    			}
    			}
    			if (grid[currentTurn.getX()+1][currentTurn.getY()].isOccupied()) {
    				if (grid[currentTurn.getX()+1][currentTurn.getY()].getCharacter().isAlly()==false) {
    	    			return true;
    	    			}
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
		currentPer=currentTurn.getHp()/currentTurn.getMaxHp();
		if(turns.size() >0) {
			checkDead();
		}
		victoryCond();
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
		if (name.equals("explosion") && currentTurn.getMana() >= 40) {
			damageskill = 30;
			skillmodedamage = true;
			cursormode = true;
			skillmanaused = 40;
			skillmenu = false;
			cursor.setX(currentTurn.getX());
			cursor.setY(currentTurn.getY());
		}
		if(name.equals("wind") && currentTurn.getMana() >=  70) {
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
		if(name.equals("fireball") && currentTurn.getMana() >= 30) {
			damageskill = 20;
			skillmodedamage = true;
			cursormode = true;
			skillmanaused = 30;
			skillmenu = false;
			cursor.setX(currentTurn.getX());
			cursor.setY(currentTurn.getY());
			MyTimerTask timer = new MyTimerTask();
	        timer.completeTask(0);
		}
		if(name.equals("heal") && currentTurn.getMana() >= 50) {
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
		if(name.equals("thunder") && currentTurn.getMana() >= 15) {
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
		if(name.equals("waterblast") && currentTurn.getMana() >= 35 ) {
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
		for(int i = 1; i < 8; i++) {
    		grid[9][i].setBlocked();
    	}
		for(int i = 1; i < 6; i++) {
			for(int j = 2; j < 10; j++) {
				grid[i][j].setBlocked();
			}
		}
		for(int i = 3; i < 9; i++) {
			grid[0][i].setBlocked();
		}
		for(int i = 3; i < 8; i++) {
			grid[8][i].setBlocked();
		}
		for(int i = 6; i < 8; i++) {
			grid[7][i].setBlocked();
		}
		for(int i = 6; i < 8; i++) {
			grid[i][9].setBlocked();
		}
    	for(int i = 0; i<turns.size(); i++) {
    		grid[turns.get(i).getX()][turns.get(i).getY()].setBlocked();
    		grid[turns.get(i).getX()][turns.get(i).getY()].placeCharacter(turns.get(i));
    	}
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		cantPress=false;
		anis=new Animation[5];
		anis[0]=a0;
		anis[1]=a1;
		anis[2]=a2;
		anis[3]=a3;
		anis[4]=a4;
		
		alrdywon = false;
		dial = 1;
		dialoguemode = true;
		startsong = false;
		mapsound = new MapTwoSound();
		populateGrid();
		clearRedArea();
    	main = new Characters("Joe", 60, 6, 100, 100,new String[]{"resources/back1w.png","resources/back2w.png","resources/back3w.png"}, new String[]{"resources/left1w.png","resources/left2w.png","resources/left3w.png"},new String[]{"resources/right1w.png","resources/right2w.png","resources/right3w.png"}, new String[]{"resources/front1w.png","resources/front2w.png","resources/front3w.png"},3, true, false,3,1,1, false,"fireball", "explosion", "thunder", "wind");
    	turns.add(main);
    	grid[1][1].placeCharacter(main);
    	grid[1][1].setBlocked();
		enemy1 = new Characters("Enemy", 60, 5, 100, 100, new String[]{"resources/back1w.png","resources/back2w.png","resources/back3w.png"}, new String[]{"resources/left1w.png","resources/left2w.png","resources/left3w.png"},new String[]{"resources/right1w.png","resources/right2w.png","resources/right3w.png"}, new String[]{"resources/front1w.png","resources/front2w.png","resources/front3w.png"},3, false, false, 2, 8, 1, false,"fireball", "heal", "thunder", "wind");
    	grid[8][1].placeCharacter(enemy1);
    	grid[8][1].setBlocked();
    	turns.add(enemy1);
    	enemy2 = new Characters("Enemy2", 60, 6, 100, 100, new String[]{"resources/back1w.png","resources/back2w.png","resources/back3w.png"}, new String[]{"resources/left1w.png","resources/left2w.png","resources/left3w.png"},new String[]{"resources/right1w.png","resources/right2w.png","resources/right3w.png"}, new String[]{"resources/front1w.png","resources/front2w.png","resources/front3w.png"},3, false, false,2,7,5,false, "waterblast", "heal", "thunder", "wind");
    	turns.add(enemy2);
    	grid[7][5].placeCharacter(side);
    	grid[7][5].setBlocked();
    	enemy3 = new Characters("Enemy3", 60,6,100,100,new String[]{"resources/back1w.png","resources/back2w.png","resources/back3w.png"}, new String[]{"resources/left1w.png","resources/left2w.png","resources/left3w.png"},new String[]{"resources/right1w.png","resources/right2w.png","resources/right3w.png"}, new String[]{"resources/front1w.png","resources/front2w.png","resources/front3w.png"},3,false,false,2,9,9,false,"fireball", "explosion", "thunder", "wind");
    	turns.add(enemy3);
    	grid[9][9].placeCharacter(enemy3);
    	grid[9][9].setBlocked();
    	side = new Characters("Jessica", 60,6,100,100,new String[]{"resources/back1w.png","resources/back2w.png","resources/back3w.png"}, new String[]{"resources/left1w.png","resources/left2w.png","resources/left3w.png"},new String[]{"resources/right1w.png","resources/right2w.png","resources/right3w.png"}, new String[]{"resources/front1w.png","resources/front2w.png","resources/front3w.png"},3,true,false,2,3,1,false,"fireball", "explosion", "thunder", "wind");
    	turns.add(side);
    	grid[3][1].placeCharacter(side);
    	grid[3][1].setBlocked();
    	currentTurn = turns.get(turnLoc);
    	
    	for (int i=0;i<turns.size();i++) {
			Image[] asdf = {new Image(turns.get(i).getPicU()[0]), new Image(turns.get(i).getPicU()[1]), new Image(turns.get(i).getPicU()[2])};
			if (turns.get(i).getFace() == 0) {
				Image[] u = {new Image(turns.get(i).getPicU()[0]), new Image(turns.get(i).getPicU()[1]), new Image(turns.get(i).getPicU()[2])};
				asdf = u;
			}
			if (turns.get(i).getFace() == 1) {
				Image[] r = {new Image(turns.get(i).getPicR()[0]), new Image(turns.get(i).getPicR()[1]), new Image(turns.get(i).getPicR()[2])};	
				asdf = r;
			}
			if(turns.get(i).getFace()==2) {
				Image[] l = {new Image(turns.get(i).getPicL()[0]), new Image(turns.get(i).getPicL()[1]), new Image(turns.get(i).getPicL()[2])};
				asdf = l;
			}
			if(turns.get(i).getFace()==3) {
				Image[] fda = {new Image(turns.get(i).getPicD()[0]), new Image(turns.get(i).getPicD()[1]), new Image(turns.get(i).getPicD()[2])};
				asdf = fda;
			}
			int[] duration=new int[]{100,100,100};
			anis[i]= new Animation(asdf,duration,true);
		}
    	
    	//grassMap = new TiledMap("resources/map1.tmx");
    	map = new Image("resources/FE Map 2.png");
    	
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
    	
    //	Image [] enemyDown = {new Image(enemy1.getPicU()), new Image(enemy1.getPicU())};
    	SpriteSheet fbs = new SpriteSheet("resources/fireballanimate.png", 64,64);
    	SpriteSheet es = new SpriteSheet("resources/explosionanimate.png",64,64);
    	SpriteSheet ws = new SpriteSheet("resources/waterblastanimate.png",64,64);
    	SpriteSheet ts = new SpriteSheet("resources/thunderanimate.png",64,64);
    	SpriteSheet fss = new SpriteSheet("resources/windanimate.png",64,64);
    	SpriteSheet hs = new SpriteSheet("resources/healskill.png",64,64);
    	SpriteSheet manas = new SpriteSheet("resources/manapotheal.png",64,64);
    	
    	//eDown = new Animation(enemyDown, duration, false);
    	
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
    	windani = new Animation(fss,100);
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
        
        cursor = new Characters("cursor", 20, 20, 0, 0, new String[]{"resources/cursor.png","resources/cursor.png","resources/cursor.png"},new String[]{"resources/cursor.png","resources/cursor.png","resources/cursor.png"},new String[]{"resources/cursor.png","resources/cursor.png","resources/cursor.png"},new String[]{"resources/cursor.png","resources/cursor.png","resources/cursor.png"}, 3, false, true, 9999, 20, 20, true, null, null, null, null);
        
        updateButtons();
        
        font = new Font("Verdana", Font.BOLD, 8);
        dfont = new Font("Constantia", Font.BOLD, 16);
        trueTypeFont = new TrueTypeFont(font, true);
        currentMoves = new TrueTypeFont(font,true);
        dialogueFont = new TrueTypeFont(dfont, true);

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
    	
/**
    	int x = 360;
    	for(int i = 0; i < 4; i ++) {
    		if(turnLoc+i >= turns.size()) {
    			Image r = new Image(turns.get((turnLoc+i)%turns.size()).getPicD());
        		r.draw(x,640);
    		} else {
    			Image r = new Image(turns.get(turnLoc+i).getPicD());
        		r.draw(x,640);
    		}
    		x+=64;
    	}
    	**/
    	
    	if(turns.size()>0) {
    		updateHealth();
    		for (int j=0; j<turns.size();j++) {
    			if (j==turnLoc) {
    			anis[j].draw((turns.get(j).getX()*64)+XMoves, (turns.get(j).getY()*64)+YMoves);
    			}
    			else {
    				anis[j].draw((turns.get(j).getX()*64), (turns.get(j).getY()*64));
    			}
    		}
    	} else {
    		System.out.println("game over");
    	}
    	for(int i = 0; i < turns.size(); i++) {
    		if (i==turnLoc) {
    		if(specificPerc(turns.get(i)) >0) {
    			g.setColor(Color.red);
    			g.drawRect((turns.get(i).getX() * 64 + 10)+XMoves, ((turns.get(i).getY() * 64)-2)+YMoves, 44, 2);
        		g.fillRect((turns.get(i).getX() * 64 + 10)+XMoves, ((turns.get(i).getY() * 64)-2)+YMoves, (int)(specificPerc(turns.get(i))*44), 2);
    		}
    		}else {
    			if(specificPerc(turns.get(i)) >0) {
        			g.setColor(Color.red);
        			g.drawRect(turns.get(i).getX() * 64 + 10, (turns.get(i).getY() * 64)-2, 44, 2);
            		g.fillRect(turns.get(i).getX() * 64 + 10, (turns.get(i).getY() * 64)-2, (int)(specificPerc(turns.get(i))*44), 2);
        		}
    		}
    	}
    	for (int l = 0; l<turns.size(); l++) {
    		if (l==turnLoc) {
    			g.setColor(Color.blue);
        		g.drawRect((turns.get(l).getX() * 64 + 10)+XMoves, (turns.get(l).getY() * 64)+YMoves, 44, 2);
        		if(specificPercMana(turns.get(l)) > 0) {
        			g.fillRect((turns.get(l).getX() * 64 + 10)+XMoves, (turns.get(l).getY() * 64)+YMoves, (int)(specificPercMana(turns.get(l))*44), 2);
        		}
    		}else {
    			g.setColor(Color.blue);
        		g.drawRect(turns.get(l).getX() * 64 + 10, turns.get(l).getY() * 64, 44, 2);
        		if(specificPercMana(turns.get(l)) > 0) {
        			g.fillRect(turns.get(l).getX() * 64 + 10, turns.get(l).getY() * 64, (int)(specificPercMana(turns.get(l))*44), 2);
        		}
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
	        description.drawString(400.0f, 300.0f, items.get(ItemPos).getDescription(), Color.black);
    	}
    	if (isHealing) {
    		if(items.get(ItemPos) instanceof HpItem) {
    			healing.draw(currentTurn.getX()*64,currentTurn.getY()*64+10);
    		}
    		if(items.get(ItemPos) instanceof ManaItem) {
    			manapothealani.draw(currentTurn.getX()*64,currentTurn.getY()*64);
    		}
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
    	if (isWind) {
    		windani.draw(targetplaceX*64,targetplaceY*64);
    	}
    	if (isHealskill) {
    		healSkillani.draw(targetplaceX*64,targetplaceY*64);
    	}
    	if(skillmodeheal || skillmodedamage) {
    		for(int i = 0; i < 10; i++) {
    			for(int j = 0; j < 10; j++) {
    				if(redArea[i][j]) {
    					cube = new Image("resources/redcube.png");
    					cube.draw(i*64,j*64);
    				}
    			}
    		}
    	}
    	if (dialoguemode) {
    		if (dial == 1) {
    		g.drawRect(0, 600, 640, 104);
    		g.fillRect(0, 600, 640, 104);
    		dialogueFont.drawString(30.0f, 640.0f,  "Jessica : Agh!!", Color.white);
    		}
    		if (dial == 2) {
    			g.drawRect(0, 600, 640, 104);
        		g.fillRect(0, 600, 640, 104);
        		dialogueFont.drawString(30.0f, 640.0f,  "Bandit 1 : Hahaha! Its three on one, just give it up!", Color.white);
    		}
    		if (dial == 3) {
    			g.drawRect(0, 600, 640, 104);
        		g.fillRect(0, 600, 640, 104);
        		dialogueFont.drawString(30.0f, 640.0f,  "Bandit 2 : Hehe its a good thing were so far away from the nearest town!", Color.white);
    		}
    		if (dial == 4) {
    			g.drawRect(0, 600, 640, 104);
        		g.fillRect(0, 600, 640, 104);
        		dialogueFont.drawString(30.0f, 640.0f,  "Joe : Stop right there, bandits.", Color.white);
    		}
    		if (dial == 5) {
    			g.drawRect(0, 600, 640, 104);
        		g.fillRect(0, 600, 640, 104);
        		dialogueFont.drawString(30.0f, 640.0f,  "Joe : Put all your money down and leave right now, and I may spare your lives.", Color.white);
    		}
    		if (dial == 6) {
    			g.drawRect(0, 600, 640, 104);
        		g.fillRect(0, 600, 640, 104);
        		dialogueFont.drawString(30.0f, 640.0f,  "Bandit 1 : Pfft, you delusional, kid? It's still 3 on 2.", Color.white);
    		}
    		if (dial == 7) {
    			g.drawRect(0, 600, 640, 104);
        		g.fillRect(0, 600, 640, 104);
        		dialogueFont.drawString(30.0f, 640.0f,  "Joe : *sigh* Don't say I didn't warn you.", Color.white);
    		}
    		if (dial == 8) {
    			g.drawRect(0, 600, 640, 104);
        		g.fillRect(0, 600, 640, 104);
        		dialogueFont.drawString(30.0f, 640.0f,  "(Jessica has joined your party)", Color.white);
    		}
    		if (dial == 9) {
    			g.drawRect(0, 600, 640, 104);
        		g.fillRect(0, 600, 640, 104);
        		dialogueFont.drawString(30.0f, 640.0f,  "Jessica : Whew, thank you very much for your help!", Color.white);
    		}
    		if (dial == 10) {
    			g.drawRect(0, 600, 640, 104);
        		g.fillRect(0, 600, 640, 104);
        		dialogueFont.drawString(30.0f, 640.0f,  "Joe : No need for thanks, I just wanted their money.", Color.white);
    		}
    		if (dial == 11) {
    			g.drawRect(0, 600, 640, 104);
        		g.fillRect(0, 600, 640, 104);
        		dialogueFont.drawString(30.0f, 640.0f,  "Jessica : Say, I've no clue where this is. Can I follow you out of here?", Color.white);
    		}
    		if (dial == 12) {
    			g.drawRect(0, 600, 640, 104);
        		g.fillRect(0, 600, 640, 104);
        		dialogueFont.drawString(30.0f, 640.0f,  "Joe : Sure, but you'd better prepare yourself for battles.", Color.white);
        		dialogueFont.drawString(67.0f, 660.0f,  "This place is infested with low lifes.", Color.white);
    		}
    	}
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
		if (cantPress==false) {
		if (startsong == false) {			
			mapsound.playSound();
			startsong = true;
			System.out.println("bcv");
		}
		if (dialoguemode == true) {
			Input inputx = container.getInput();
			if (inputx.isKeyDown(Input.KEY_ENTER) && dial == 12) {
				MyTimerTask timer = new MyTimerTask();
				timer.completeTask(0);
				wizard.SelectionScreen.addLevel();
				mapsound.stopSound();
				wizard.SelectionScreen.queueMusic();
				sbg.enterState(2);
			}
			if (inputx.isKeyDown(Input.KEY_ENTER) && dial == 11) {
				MyTimerTask timer = new MyTimerTask();
				timer.completeTask(0);
				dial++;
			}
			if (inputx.isKeyDown(Input.KEY_ENTER) && dial == 10) {
				MyTimerTask timer = new MyTimerTask();
				timer.completeTask(0);
				dial++;
			}
			if (inputx.isKeyDown(Input.KEY_ENTER) && dial == 9) {
				MyTimerTask timer = new MyTimerTask();
				timer.completeTask(0);
				dial++;
			}
			if (inputx.isKeyDown(Input.KEY_ENTER) && dial == 8) {
				MyTimerTask timer = new MyTimerTask();
				timer.completeTask(0);
				dialoguemode = false;
			}
			if (inputx.isKeyDown(Input.KEY_ENTER) && dial == 7) {
				MyTimerTask timer = new MyTimerTask();
				timer.completeTask(0);
				dial++;
			}
			if (inputx.isKeyDown(Input.KEY_ENTER) && dial == 6) {
				MyTimerTask timer = new MyTimerTask();
				timer.completeTask(0);
				dial++;
			}
			if (inputx.isKeyDown(Input.KEY_ENTER) && dial == 5) {
				MyTimerTask timer = new MyTimerTask();
				timer.completeTask(0);
				dial++;
			}
			if (inputx.isKeyDown(Input.KEY_ENTER) && dial == 4) {
				MyTimerTask timer = new MyTimerTask();
				timer.completeTask(0);
				dial++;
			}
			if (inputx.isKeyDown(Input.KEY_ENTER) && dial == 3) {
				MyTimerTask timer = new MyTimerTask();
				timer.completeTask(0);
				dial++;
			}
			if (inputx.isKeyDown(Input.KEY_ENTER) && dial == 2) {
				MyTimerTask timer = new MyTimerTask();
				timer.completeTask(0);
				dial++;
			}
			if (inputx.isKeyDown(Input.KEY_ENTER) && dial == 1) {
				MyTimerTask timer = new MyTimerTask();
				timer.completeTask(0);
				dial++;
			}
			
		}else {
		if (currentTurn.isAlly() == false){
			if(searchingTarget()[0] == -1 && searchingTarget()[1]==-1) {
				turnLoc++;
				if(turnLoc > turns.size()-1) {
	   				 turnLoc = 0;
	   			 }
	   			 currentTurn = turns.get(turnLoc);
			}
			else {
				System.out.println(currentTurn.getX() + " bnvz " + currentTurn.getY());
				System.out.println(searchingTarget()[0] +" czx "+ searchingTarget()[1]);
				checkClose(searchingTarget());
			}
		}
		if(!Application.justSwapped()) {
			if(currentTurn.isAlly() == false){
				if(searchingTarget()[0] == -1 && searchingTarget()[1]==-1) {
					turnLoc++;
					if(turnLoc > turns.size()-1) {
						turnLoc = 0;
					}
	   			 	currentTurn = turns.get(turnLoc);
				} else {
					System.out.println(currentTurn.getX() + " bnvz " + currentTurn.getY());
					System.out.println(searchingTarget()[0] +" czx "+ searchingTarget()[1]);
					checkClose(searchingTarget());
				}
			} else {
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
						cantPress=true;
							currentTurn.setFace(0);
							sprite.update(delta);
							Thread thread = new Thread(){
							    public void run(){
							    	MyTimerTask timer = new MyTimerTask();
							        timer.completeTask(16);
							        currentTurn.setY(currentTurn.getY()-1);
							        cantPress = false;
							    }
						  };
						  thread.start();
						moveHelper();
						for (int i=0;i<turns.size();i++) {
			    			Image[] asdf = {new Image(turns.get(i).getPicU()[0]), new Image(turns.get(i).getPicU()[1]), new Image(turns.get(i).getPicU()[2])};
			    			if (turns.get(i).getFace() == 0) {
			    				Image[] u = {new Image(turns.get(i).getPicU()[0]), new Image(turns.get(i).getPicU()[1]), new Image(turns.get(i).getPicU()[2])};
			    				asdf = u;
			    			}
			    			if (turns.get(i).getFace() == 1) {
			    				Image[] r = {new Image(turns.get(i).getPicR()[0]), new Image(turns.get(i).getPicR()[1]), new Image(turns.get(i).getPicR()[2])};	
			    				asdf = r;
			    			}
			    			if(turns.get(i).getFace()==2) {
			    				Image[] l = {new Image(turns.get(i).getPicL()[0]), new Image(turns.get(i).getPicL()[1]), new Image(turns.get(i).getPicL()[2])};
			    				asdf = l;
			    			}
			    			if(turns.get(i).getFace()==3) {
			    				Image[] fda = {new Image(turns.get(i).getPicD()[0]), new Image(turns.get(i).getPicD()[1]), new Image(turns.get(i).getPicD()[2])};
			    				asdf = fda;
			    			}
			    			int[] duration=new int[]{100,100,100};
			    			anis[i]= new Animation(asdf,duration,true);
						}
						updateMap();
						
					}
					if(input.isKeyDown(Input.KEY_S) && movable(4) && currentTurn.getY() != 9 && tileAmt != 0) {
						cantPress=true;
							currentTurn.setFace(3);
							sprite.update(delta);
							Thread thread = new Thread(){
							    public void run(){
							    	MyTimerTask timer = new MyTimerTask();
							        timer.completeTask(14);
							        currentTurn.setY(currentTurn.getY()+1);
							        cantPress = false;
							    }
						  };
						  thread.start();
						moveHelper();
						for (int i=0;i<turns.size();i++) {
			    			Image[] asdf = {new Image(turns.get(i).getPicU()[0]), new Image(turns.get(i).getPicU()[1]), new Image(turns.get(i).getPicU()[2])};
			    			if (turns.get(i).getFace() == 0) {
			    				Image[] u = {new Image(turns.get(i).getPicU()[0]), new Image(turns.get(i).getPicU()[1]), new Image(turns.get(i).getPicU()[2])};
			    				asdf = u;
			    			}
			    			if (turns.get(i).getFace() == 1) {
			    				Image[] r = {new Image(turns.get(i).getPicR()[0]), new Image(turns.get(i).getPicR()[1]), new Image(turns.get(i).getPicR()[2])};	
			    				asdf = r;
			    			}
			    			if(turns.get(i).getFace()==2) {
			    				Image[] l = {new Image(turns.get(i).getPicL()[0]), new Image(turns.get(i).getPicL()[1]), new Image(turns.get(i).getPicL()[2])};
			    				asdf = l;
			    			}
			    			if(turns.get(i).getFace()==3) {
			    				Image[] fda = {new Image(turns.get(i).getPicD()[0]), new Image(turns.get(i).getPicD()[1]), new Image(turns.get(i).getPicD()[2])};
			    				asdf = fda;
			    			}
			    			int[] duration=new int[]{100,100,100};
			    			anis[i]= new Animation(asdf,duration,true);
						}
						updateMap();
					}
					if (input.isKeyDown(Input.KEY_A)&& movable(3) && currentTurn.getX() != 0 && tileAmt != 0) { 
						cantPress=true;
							currentTurn.setFace(1);
							sprite.update(delta);
							Thread thread = new Thread(){
							    public void run(){
							    	MyTimerTask timer = new MyTimerTask();
							        timer.completeTask(15);
							        currentTurn.setX(currentTurn.getX()-1);
							        cantPress = false;
							    }
						  };
						  thread.start();
						moveHelper();
						for (int i=0;i<turns.size();i++) {
			    			Image[] asdf = {new Image(turns.get(i).getPicU()[0]), new Image(turns.get(i).getPicU()[1]), new Image(turns.get(i).getPicU()[2])};
			    			if (turns.get(i).getFace() == 0) {
			    				Image[] u = {new Image(turns.get(i).getPicU()[0]), new Image(turns.get(i).getPicU()[1]), new Image(turns.get(i).getPicU()[2])};
			    				asdf = u;
			    			}
			    			if (turns.get(i).getFace() == 1) {
			    				Image[] r = {new Image(turns.get(i).getPicR()[0]), new Image(turns.get(i).getPicR()[1]), new Image(turns.get(i).getPicR()[2])};	
			    				asdf = r;
			    			}
			    			if(turns.get(i).getFace()==2) {
			    				Image[] l = {new Image(turns.get(i).getPicL()[0]), new Image(turns.get(i).getPicL()[1]), new Image(turns.get(i).getPicL()[2])};
			    				asdf = l;
			    			}
			    			if(turns.get(i).getFace()==3) {
			    				Image[] fda = {new Image(turns.get(i).getPicD()[0]), new Image(turns.get(i).getPicD()[1]), new Image(turns.get(i).getPicD()[2])};
			    				asdf = fda;
			    			}
			    			int[] duration=new int[]{100,100,100};
			    			anis[i]= new Animation(asdf,duration,true);
						}
						updateMap();
					}
					if (input.isKeyDown(Input.KEY_D)&& movable(1) && currentTurn.getX() != 9 && tileAmt != 0) {
						cantPress=true;
							currentTurn.setFace(2);
							sprite.update(delta);
							Thread thread = new Thread(){
							    public void run(){
							    	MyTimerTask timer = new MyTimerTask();
							        timer.completeTask(13);
									currentTurn.setX(currentTurn.getX()+1);
									cantPress = false;
							    }
						  };
						  thread.start();
						moveHelper();
						for (int i=0;i<turns.size();i++) {
			    			Image[] asdf = {new Image(turns.get(i).getPicU()[0]), new Image(turns.get(i).getPicU()[1]), new Image(turns.get(i).getPicU()[2])};
			    			if (turns.get(i).getFace() == 0) {
			    				Image[] u = {new Image(turns.get(i).getPicU()[0]), new Image(turns.get(i).getPicU()[1]), new Image(turns.get(i).getPicU()[2])};
			    				asdf = u;
			    			}
			    			if (turns.get(i).getFace() == 1) {
			    				Image[] r = {new Image(turns.get(i).getPicR()[0]), new Image(turns.get(i).getPicR()[1]), new Image(turns.get(i).getPicR()[2])};	
			    				asdf = r;
			    			}
			    			if(turns.get(i).getFace()==2) {
			    				Image[] l = {new Image(turns.get(i).getPicL()[0]), new Image(turns.get(i).getPicL()[1]), new Image(turns.get(i).getPicL()[2])};
			    				asdf = l;
			    			}
			    			if(turns.get(i).getFace()==3) {
			    				Image[] fda = {new Image(turns.get(i).getPicD()[0]), new Image(turns.get(i).getPicD()[1]), new Image(turns.get(i).getPicD()[2])};
			    				asdf = fda;
			    			}
			    			int[] duration=new int[]{100,100,100};
			    			anis[i]= new Animation(asdf,duration,true);
						}
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
						AttackSelection(currentTurn);
						MyTimerTask timer = new MyTimerTask();
						timer.completeTask(0);
						currentTurn.setDidAttack(true);
						updateHealth();
					}
					if(input.isKeyDown(Input.KEY_ENTER) && OptionPos == 1 && !usedItem) {
						pickingItem = true;
						MyTimerTask timer = new MyTimerTask();
						timer.completeTask(1);
					}
					if(input.isKeyDown(Input.KEY_ENTER)&& OptionPos == 2 && !usedSkill) {
						SkillPos=0;
						changeOptionSkills();
						updateButtons();
						MyTimerTask timer = new MyTimerTask();
						timer.completeTask(0);
					}
					if (input.isKeyDown(Input.KEY_ENTER) && OptionPos == 3 && !moved){
						tileAmt = currentTurn.getDistance();
						moved = true;
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
						usedItem = false;
						usedSkill = false;
						moved = false;
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
					settingRedArea();
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
						if (skillname.equals("wind")) {
							WindMethod();
						}
						cursormode = false;
						skillmodedamage = false;
						updateMenu();
						updateButtons();
						cursor.setX(20);
						cursor.setY(20);
						MyTimerTask timer = new MyTimerTask();
						timer.completeTask(0);
						clearRedArea();
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
					settingRedArea();
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
						clearRedArea();
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
					if(input.isKeyDown(Input.KEY_ENTER) && items.get(ItemPos).getAmt() != 0) {
						if(items.get(ItemPos) instanceof HpItem) {
							currentTurn.setHp(currentTurn.getHp() + ((HpItem) items.get(ItemPos)).getRestoration());
							if(currentTurn.getMaxHp() < currentTurn.getHp()) {
								currentTurn.setHp(currentTurn.getMaxHp());
							}
							items.get(ItemPos).setAmt(items.get(ItemPos).getAmt()-1);
							MyTimerTask timer = new MyTimerTask();
							timer.completeTask(2);
							pickingItem = false;
							healingMethod();
						}
						if(items.get(ItemPos) instanceof ManaItem) {
							currentTurn.setMana(currentTurn.getMana() + ((ManaItem) items.get(ItemPos)).getRestoration());
							if(currentTurn.getMaxMana() < currentTurn.getMana()) {
								currentTurn.setMana(currentTurn.getMaxMana());
							}
							items.get(ItemPos).setAmt(items.get(ItemPos).getAmt()-1);
							MyTimerTask timer = new MyTimerTask();
							timer.completeTask(2);
							pickingItem = false;
							healingMethod();
						}
						usedItem = true;
					}
				}
			}
		}
		}
		}else {
			
		}
		updateMap();
	}
	
	public int[] searchingTarget() {
		int[] hahaxd =	new int[2];
		hahaxd[0] = -1;
		hahaxd[1] = -1;
		for (int j = currentTurn.getX()-currentTurn.getDistance(); j<currentTurn.getX()+currentTurn.getDistance()+1; j++) {
			for (int i = currentTurn.getY()-currentTurn.getDistance(); i< currentTurn.getY()+currentTurn.getDistance()+1; i++) {
				if (j >= 0 && j < 10 && i >= 0 && i<10) {
				if (grid[j][i].isOccupied()) {
					if (grid[j][i].getCharacter().isAlly()) {
						hahaxd[0] = j;
						hahaxd[1] = i;
						return hahaxd;
					}
				}
			}
		}}
		System.out.println("no");
		return hahaxd;
	}
	public void checkClose(int[] target) {
		System.out.println("zxcv");
		System.out.println(target[0]+ " and " + target[1]);
		if (target[0] == currentTurn.getX()-1 && target[1] == currentTurn.getY()) {
			Attackuh(target);
			turnLoc++;
			if(turnLoc > turns.size()-1) {
   				 turnLoc = 0;
   			 }
   			 currentTurn = turns.get(turnLoc);
		}
		else if (target[0] == currentTurn.getX()+1 && target[1] == currentTurn.getY()) {
			Attackuh(target);
			turnLoc++;
			if(turnLoc > turns.size()-1) {
   				 turnLoc = 0;
   			 }
   			 currentTurn = turns.get(turnLoc);
		}
		else if (target[0] == currentTurn.getX() && target[1] == currentTurn.getY()-1) {
			Attackuh(target);
			turnLoc++;
			if(turnLoc > turns.size()-1) {
   				 turnLoc = 0;
   			 }
   			 currentTurn = turns.get(turnLoc);
		}
		else if (target[0] == currentTurn.getX() && target[1] == currentTurn.getY()+1) {
			Attackuh(target);
			turnLoc++;
			if(turnLoc > turns.size()-1) {
   				 turnLoc = 0;
   			 }
   			 currentTurn = turns.get(turnLoc);
		}
		else {
			moveTowardsTarget(target);
		}
	}
	public void Attackuh(int[] target) {
		currentTurn.setHp(currentTurn.getHp()-grid[target[0]][target[1]].getCharacter().getAtk());
		grid[target[0]][target[1]].getCharacter().setHp(grid[target[0]][target[1]].getCharacter().getHp()-currentTurn.getAtk());
		System.out.println("attacked");
	}
	public void moveTowardsTarget(int[] target) {
		if (target[0] < currentTurn.getX() && (grid[currentTurn.getX()-1][currentTurn.getY()].getBlocked() == false)) {
			currentTurn.setX(currentTurn.getX()-1);
			checkClose(target);
			return;
		}
		if (target[0] > currentTurn.getX() && (grid[currentTurn.getX()+1][currentTurn.getY()].getBlocked() == false)) {
			currentTurn.setX(currentTurn.getX()+1);
			checkClose(target);
			return;
		}
		if (target[1] < currentTurn.getY() && (grid[currentTurn.getX()][currentTurn.getY()-1].getBlocked() == false)) {
			currentTurn.setY(currentTurn.getY()-1);
			checkClose(target);
			return;
		}
		if (target[1] > currentTurn.getY() && (grid[currentTurn.getX()][currentTurn.getY()+1].getBlocked() == false)) {
			currentTurn.setY(currentTurn.getY()+1);
			checkClose(target);
			return;
		}
		turnLoc++;
		if(turnLoc > turns.size()-1) {
				 turnLoc = 0;
			 }
			 currentTurn = turns.get(turnLoc);
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 5;
	}
	
	public void settingRedArea() {
    	for(int i = currentTurn.getX()-2; i < currentTurn.getX()+3;i++) {
    		for(int j = currentTurn.getY()-2;j<currentTurn.getY()+3;j++) {
    			if(i > -1 && j > -1 && i < 10 && j < 10) {
    				if(grid[i][j].isOccupied()) {
        				redArea[i][j] = true;
    				} else {
    					if(!grid[i][j].getBlocked()) {
    						redArea[i][j] = true;
    					}
    				}
    			}
    		}
    	}
	}
	private void clearRedArea() {
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				redArea[i][j] = false;
			}
		}
	}
	public void victoryCond() {
		for (int i=0;i<turns.size();i++) {
			if (turns.get(i).isAlly() == false) {
				return;
			}
		}
		if (alrdywon == false) {
		Victory();
		alrdywon = true;
		}
	}
	public void Victory() {
		dialoguemode = true;
		dial = 9;
	}
	
}
package wizard;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class IntroSound {
	private Thread thread;
	boolean vc = true;
	
	public IntroSound() {
		
	}
	 public static void main(String[] args) 
	    {
	        
	    }
	 
	public void playSound() {
		thread = new Thread(){
	    public void run(){
	    	 try {
	 	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("resources/DwarvenMines.wav"));
	 	        Clip clip = AudioSystem.getClip();
	 	        clip.open(audioInputStream);
	 	        clip.start();
	 	        while(vc == true) {
	 	        clip.loop(Clip.LOOP_CONTINUOUSLY);
	 	        }
	 	        clip.stop();
	 	    } catch(Exception ex) {
	 	        System.out.println("Error with playing sound.");
	 	        ex.printStackTrace();
	 	    }
	    }
		};
		thread.start();
	}
	public void stopSound() {
		vc = false;
	}
	public void startSound() {
		vc = true;
	}
}

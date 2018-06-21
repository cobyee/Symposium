package wizard;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class MapTwoSound {
	private Thread thread;
	boolean vc = true;
	
	public MapTwoSound() {
		
	}
	 public static void main(String[] args) 
	    {
	        
	    }
	 
	public void playSound() {
		thread = new Thread(){
	    public void run(){
	    	 try {
	 	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("resources/AwayTeam.wav"));
	 	        Clip clip = AudioSystem.getClip();
	 	        clip.open(audioInputStream);
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
}
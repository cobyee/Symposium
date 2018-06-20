package wizard;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MyTimerTask extends TimerTask {

    @Override
    public void run() {
        System.out.println("Timer task started at:"+new Date());
        //completeTask();
        System.out.println("Timer task finished at:"+new Date());
    }

    void completeTask(int n) {
    	if(n == 0) {
    		try {
    			LevelOne.setMovable(true);
    			Thread.sleep(400);
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
    	}
    	if(n == 1) {
    		try {
    			LevelOne.setOptionMovable(true);
    			Thread.sleep(400);
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
    	}
    	if(n == 2) {
    		try {
    			LevelOne.setOptionItem(true);
    			Thread.sleep(100);
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
    	}
    	if(n==3) {
    		try {
    			Thread.sleep(1700);
    			LevelOne.stopHealingAnimation();
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
    	}
    	if(n==4) {
    		try {
    			MainMenu.setMovable();
    			Thread.sleep(200);
    		} catch(InterruptedException e) {
    			e.printStackTrace();
    		}
    	}
    	if (n==5) {
    		try {
    			Thread.sleep(1700);
    		} catch(InterruptedException e) {
    			e.printStackTrace();
    		}
    	}
    	if (n==6) {
    		try {
    			Thread.sleep(2500);
    		} catch(InterruptedException e) {
    			e.printStackTrace();
    		}
    	}
    	if (n==7) {
    		try {
    			Thread.sleep(3700);
    		} catch(InterruptedException e) {
    			e.printStackTrace();
    		}
    	}
    	if (n==8) {
    		try {
    			Thread.sleep(2300);
    		} catch(InterruptedException e) {
    			e.printStackTrace();
    		}
    	}
    
    }
    
    public static void main(String args[]){
        TimerTask timerTask = new MyTimerTask();
        //running timer task as daemon thread
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
        System.out.println("TimerTask started");
        //cancel after sometime
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timer.cancel();
        System.out.println("TimerTask cancelled");
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
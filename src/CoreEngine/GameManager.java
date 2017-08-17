package CoreEngine;

import static Display.Display.getGamePanel;
import static Display.Display.getPlayer;
import static Display.Display.isGameRunning;
import java.awt.event.KeyEvent;
import java.util.HashSet;

public class GameManager implements Runnable, CoreInterface {
    
    @Override
    public void run() {
        while(isGameRunning) {
            //manage the keys currently pressed
            manageKeys();
            getGamePanel().repaintGame();
            
            try {
                Thread.sleep(MAIN_SLEEP_TIME);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void manageKeys() {
        HashSet<Integer> currentKeys = KeyboardController.getActiveKeys();
        
        // manage possible actions
        if(currentKeys.contains(KeyEvent.VK_RIGHT)) {
            //move right
            getPlayer().move(KeyEvent.VK_RIGHT);
            //System.out.println("move(KeyEvent.VK_RIGHT);");
        } else if (currentKeys.contains(KeyEvent.VK_LEFT)) {
            //move left
            getPlayer().move(KeyEvent.VK_LEFT);
            //System.out.println("move(KeyEvent.VK_LEFT);");
        } else if (currentKeys.isEmpty()) {
            //stands still
            getPlayer().stop();
        }
    }
}

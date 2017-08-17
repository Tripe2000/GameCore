package CoreEngine;

import java.awt.event.KeyEvent;
import java.util.HashSet;

public class GameManager implements Runnable {
    
    @Override
    public void run() {
        while(gameIsRunning) {
            //manage the keys currently pressed
            manageKeys();
            gamePanel.repaintGame();
            
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
            player.move(KeyEvent.VK_RIGHT);
        } else if (currentKeys.contains(KeyEvent.VK_LEFT)) {
            //move left
            player.move(KeyEvent.VK_LEFT);
        } else if (currentKeys.isEmpty()) {
            //stands still
            player.stop();
        }
    }
}

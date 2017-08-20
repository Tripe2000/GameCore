/**
 *Date: 17/08/2017
 *Author: Whaleballs
 *File Name: GameManager.java
 *Project Name: GameCore
 */

package CoreEngine;

public class GameManager implements CoreInterface {
    
    protected static EntitiesPanel entitiesPanel = new EntitiesPanel();
    protected static BackgroundPanel backgroundPanel = new BackgroundPanel();
    
    public static boolean isGameRunning = false;
    
    public static void main(String[] args) {      
        isGameRunning = true;
        Display frame = new Display(GAME_NAME);
        frame.setVisible(true);
        entitiesPanel.requestFocusInWindow();
        Thread mainThread = new Thread(new PlayerManager());
        mainThread.start();
    }
}

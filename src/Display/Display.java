package Display;

import CoreEngine.GameManager;
import CoreEngine.Player;
import javax.swing.*;

public class Display extends JFrame {
    
    public final static String GAME_NAME = "Game Name";
    public final static int WIDTH  = 1280;
    public final static int HEIGHT = 720;
    public static GamePanel gamePanel = new GamePanel();
    public static boolean isGameRunning = true;
    public static Player player = new Player();
    
    public Display(String name) {
        super(name);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        getContentPane().add(gamePanel);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        System.out.println("Panel is Displayed");
        
    }
    
    public static void main(String[] args) {        
        Display frame = new Display(GAME_NAME);
        frame.setVisible(true);
        gamePanel.requestFocusInWindow();
        
        Thread mainThread = new Thread(new GameManager());
        mainThread.start();
    }
    
    public static GamePanel getGamePanel() {
        return gamePanel;
    }
    
    public static Player getPlayer() {
        return player;
    }
}

/**
 *Date: 19/08/2017
 *Author: Whaleballs
 *File Name: GamePanel.java
 *Project Name: GameCore
 */

package Main;

import GameState.GameStateManager;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements MainInterface, Runnable, KeyListener {
    //game thread
    private Thread thread;
    private boolean gameRunning = false;
    private int FPS = 60;
    private long targetTime = 1000/FPS;
    
    //image
    private BufferedImage image;
    private Graphics2D g;
    
    //game state manager
    private GameStateManager gameStateManager;
    
    public GamePanel() {
        super();
        setPreferredSize(new Dimension(MainInterface.WIDTH * SCALE, MainInterface.HEIGHT * SCALE));
        setFocusable(true);
        requestFocus();
    }
    
    private void initialize() {
        image = new BufferedImage(MainInterface.WIDTH, MainInterface.HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();
        gameRunning = true;
        gameStateManager = new GameStateManager();
    }
    
    @Override
    public void run() {
        initialize();
        long start;
        long elapsed;
        long wait;
        while(gameRunning) {
            start = System.nanoTime();
            
            update();
            draw();
            drawToScreen();
            
            elapsed = System.nanoTime() - start;
            wait = targetTime - elapsed/1000000;
            if(wait < 0) {
                wait = 15;
            }
            try {
                Thread.sleep(wait);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    private void update(){
        gameStateManager.update();
        KeyboardManager.update();
    }
    
    private void draw(){
        gameStateManager.draw(g);
    }
    
    private void drawToScreen(){
        Graphics g2 = getGraphics();
        g2.drawImage(image, 0, 0, MainInterface.WIDTH * SCALE, MainInterface.HEIGHT * SCALE, null);
        g2.dispose();
    }
    
    @Override
    public void keyPressed(KeyEvent key) {
        KeyboardManager.setKey(key.getKeyCode(), true);
    }
    
    @Override
    public void keyReleased(KeyEvent key) {
        KeyboardManager.setKey(key.getKeyCode(), false);
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }
    
    @Override
    public void addNotify() {
        super.addNotify();
        if(thread == null) {
            thread = new Thread(this);
            addKeyListener(this);
            thread.start();
        }
    }
}

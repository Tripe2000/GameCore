package CoreEngine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;

public class KeyboardController implements KeyListener {
    
    private static HashSet<Integer> activeKeys;
    
    public KeyboardController() {
        //System.out.println("new KeyboardController();");
        activeKeys = new HashSet<Integer>();
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        //System.out.println("keyPressed();");
        activeKeys.add(e.getKeyCode());
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        //System.out.println("keyReleased();");
        activeKeys.remove(e.getKeyCode());
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        //System.out.println("keyTyped();");
    }
    
    public static HashSet<Integer> getActiveKeys() {
        //System.out.println("getActiveKeys();");
        return activeKeys;
    }
}

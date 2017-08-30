/**
 *Date: 17/08/2017
 *Author: Whaleballs
 *File Name: KeyboardController.java
 *Project Name: GameCore
 */

package Main;

import java.awt.event.KeyEvent;

public class KeyboardManager {
    
    public static final int NUM_KEYS = 16;
	
	public static boolean keyState[] = new boolean[NUM_KEYS];
	public static boolean prevKeyState[] = new boolean[NUM_KEYS];
	
	public static final int UP = 0;
	public static final int LEFT = 1;
	public static final int DOWN = 2;
	public static final int RIGHT = 3;
	public static final int BUTTON1 = 4;
	public static final int BUTTON2 = 5;
	public static final int BUTTON3 = 6;
	public static final int BUTTON4 = 7;
	public static final int ENTER = 8;
	public static final int ESCAPE = 9;
        public static final int B = 10;
    
    public static void setKey(int keyCode, boolean pressed){
        switch(keyCode) {
            case KeyEvent.VK_UP:
                keyState[UP] = pressed;
                break;
            case KeyEvent.VK_LEFT:
                keyState[LEFT] = pressed;
                break;
            case KeyEvent.VK_DOWN:
                keyState[DOWN] = pressed;
                break;
            case KeyEvent.VK_RIGHT:
                keyState[RIGHT] = pressed;
                break;
            case KeyEvent.VK_Q:
                keyState[BUTTON1] = pressed;
                break;
            case KeyEvent.VK_W:
                keyState[BUTTON2] = pressed;
                break;
            case KeyEvent.VK_E:
                keyState[BUTTON3] = pressed;
                break;
            case KeyEvent.VK_R:
                keyState[BUTTON4] = pressed;
                break;
            case KeyEvent.VK_ENTER:
                keyState[ENTER] = pressed;
                break;
            case KeyEvent.VK_ESCAPE:
                keyState[ESCAPE] = pressed;
                break;
            case KeyEvent.VK_B:
                keyState[B] = pressed;
                break;
            default: break;
        }
    }
    
    public static void update() {
        for(int i = 0; i < NUM_KEYS; i++) {
            prevKeyState[i] = keyState[i];
        }
    }
    
    public static boolean isPressed(int i) {
        return keyState[i] && !prevKeyState[i];
    }
    
    public static boolean anyKeyPressed() {
        for(int i = 0; i < NUM_KEYS; i++) {
            if(keyState[i]) return true;
        }
        return false;
    }
}
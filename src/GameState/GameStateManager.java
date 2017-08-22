/**
 *Date: 19/08/2017
 *Author: Whaleballs
 *File Name: GameStateManager.java
 *Project Name: GameCore
 */

package GameState;

import java.util.ArrayList;

public class GameStateManager {
    public static final int MENUSTATE = 0;
    public static final int IN_WORLD_STATE = 1;
    
    private ArrayList<GameState> gameStates;
    private int currentState;
    
    public GameStateManager() {
        gameStates = new ArrayList<GameState>();
        currentState = MENUSTATE;
        gameStates.add(new MenuState(this));
        gameStates.add(new InWorldState(this));
    }
    
    public void setState(int state) {
        currentState = state;
        gameStates.get(currentState).initialize();
    }
    
    public void update() {
        gameStates.get(currentState).update();
    }
    
    public void draw(java.awt.Graphics2D g) {
        gameStates.get(currentState).draw(g);
    }
    
    public void keyPressed(int key) {
        gameStates.get(currentState).keyPressed(key);
    }
    
    public void keyReleased(int key) {
        gameStates.get(currentState).keyReleased(key);
    }
}

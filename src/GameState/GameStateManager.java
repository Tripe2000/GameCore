/**
 *Date: 19/08/2017
 *Author: Whaleballs
 *File Name: GameStateManager.java
 *Project Name: GameCore
 */

package GameState;

import Entity.Player;
import GameManager.GameManager;
import java.util.ArrayList;

public class GameStateManager {
    
    public static final int MENUSTATE = 0;
    public static final int IN_WORLD_STATE = 1;
    
    private ArrayList<GameState> gameStates;
    private int currentState;
    
    private boolean paused;
    private PauseState pauseState;
    
    public GameStateManager() {
        gameStates = new ArrayList<GameState>();
        currentState = MENUSTATE;
        gameStates.add(new MenuState(this));                                    //gotta add these in order, else it fucks the getState method
        gameStates.add(new InWorldState(this));
        
        pauseState = new PauseState(this);
        paused = false;
    }
    
    public void setState(int state) {
        currentState = state;
        gameStates.get(currentState).initialize();
    }
    
    public void update() {
        if(paused) {
            pauseState.update();
            return;
        }
        gameStates.get(currentState).update();
    }
    
    public void draw(java.awt.Graphics2D g) {
        if(paused) {
            pauseState.draw(g);
            return;
        }
        gameStates.get(currentState).draw(g);
    }
    
    public void setPaused(boolean b) { paused = b; }
}

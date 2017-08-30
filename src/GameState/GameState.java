/**
 *Date: 19/08/2017
 *Author: Whaleballs
 *File Name: GameState.java
 *Project Name: GameCore
 */

package GameState;

import Main.MainInterface;

public abstract class GameState implements MainInterface {
    
    protected GameStateManager gameStateManager;
    
    public GameState(GameStateManager gsm) {
        gameStateManager = gsm;
    }
    
    public abstract void initialize();
    public abstract void update();
    public abstract void draw(java.awt.Graphics2D g);
    public abstract void handleInput();
}

/**
 *Date: 19/08/2017
 *Author: Whaleballs
 *File Name: GameState.java
 *Project Name: GameCore
 */

package GameState;

import Engine.MainInterface;

public abstract class GameState implements MainInterface {
    
    protected GameStateManager gameStateManager;
    
    public abstract void initialize();
    public abstract void update();
    public abstract void draw(java.awt.Graphics2D g);
    public abstract void keyPressed(int key);
    public abstract void keyReleased(int key);
    
}

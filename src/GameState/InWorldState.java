/**
 *Date: 21/08/2017
 *Author: Whaleballs
 *File Name: InWorldState.java
 *Project Name: GameCore
 */

package GameState;

import Engine.MainInterface;
import TileMap.TileMap;
import java.awt.Color;
import java.awt.Graphics2D;

public class InWorldState extends GameState {
    
    private TileMap tileMap;
    
    public InWorldState(GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;
        initialize();
    }
    
    @Override
    public void initialize() {
        tileMap = new TileMap(30);
        tileMap.loadTiles("/Tilesets/grasstileset.gif");
        tileMap.loadMap("/Maps/level1-1.map");
        tileMap.setPosition(0, 0);
    }
    
    @Override
    public void update() {
    }
    
    @Override
    public void draw(Graphics2D g) {
        
        //clear screen
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, MainInterface.WIDTH, MainInterface.HEIGHT);
        
        //draw tilemap
        tileMap.draw(g);
    }
    
    @Override
    public void keyPressed(int key) {
    }
    
    @Override
    public void keyReleased(int key) {
    }
}

/**
 *Date: 21/08/2017
 *Author: Whaleballs
 *File Name: InWorldState.java
 *Project Name: GameCore
 */

package GameState;

import Engine.MainInterface;
import static Engine.MainInterface.MENU_BACKGROUND;
import TileMap.Background;
import TileMap.TileMap;
import java.awt.Color;
import java.awt.Graphics2D;

public class InWorldState extends GameState {
    
    private TileMap tileMap;
    private Background background;
    
    public InWorldState(GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;
        initialize();
    }
    
    @Override
    public void initialize() {
        tileMap = new TileMap(TILE_SIZE);
        tileMap.loadTiles("/Tilesets/grasstileset.gif");
        tileMap.loadMap("/Maps/level1-1.map");
        tileMap.setPosition(0, 0);
        background = new Background(MENU_BACKGROUND, 1);
        background.setVector(0.5, 0);
    }
    
    @Override
    public void update() {
        background.update();
    }
    
    @Override
    public void draw(Graphics2D g) {
        
        //clear screen
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, MainInterface.WIDTH, MainInterface.HEIGHT);
        background.draw(g);
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

/**
 *Date: 21/08/2017
 *Author: Whaleballs
 *File Name: InWorldState.java
 *Project Name: GameCore
 */

package GameState;

import Engine.MainInterface;
import static Engine.MainInterface.MENU_BACKGROUND;
import Entity.Player;
import TileMap.Background;
import TileMap.TileMap;
import com.sun.glass.events.KeyEvent;
import java.awt.Color;
import java.awt.Graphics2D;

public class InWorldState extends GameState {
    
    private TileMap tileMap;
    private Background background;
    
    private Player player;
    
    public InWorldState(GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;
        initialize();
    }
    
    @Override
    public void initialize() {
        tileMap = new TileMap(TILE_SIZE);
        tileMap.loadTiles("/Tilesets/grasstileset.gif");
        tileMap.loadMap("/Maps/level1-1-test.map");
        tileMap.setPosition(0, 0);
        background = new Background(GRASS_BACKGROUND, 0.1);
        background.setVector(0.5, 0);
        
        player = new Player(tileMap);
        player.setPosition(100, 300);
    }
    
    @Override
    public void update() {
        //background.update();
        player.update();
        tileMap.setPosition(
                MainInterface.WIDTH / 2 - player.getX(),
                MainInterface.HEIGHT / 2 - player.getY()
        );
    }
    
    @Override
    public void draw(Graphics2D g) {
        
        //draw background
        background.draw(g);
        
        //draw tilemap
        tileMap.draw(g);
        
        //draw player
        player.draw(g);
    }
    
    @Override
    public void keyPressed(int key) {
        if(key == KeyEvent.VK_LEFT) { player.setLeft(true); }
        if(key == KeyEvent.VK_RIGHT) { player.setRight(true); }
        if(key == KeyEvent.VK_UP) { player.setUp(true); }
        if(key == KeyEvent.VK_DOWN) { player.setDown(true); }
        if(key == KeyEvent.VK_W) { player.setJumping(true); }
        if(key == KeyEvent.VK_E) { player.setGliding(true); }
        if(key == KeyEvent.VK_R) { player.setScratching(); }
        if(key == KeyEvent.VK_F) { player.setFiring(); }
    }
    
    @Override
    public void keyReleased(int key) {
        if(key == KeyEvent.VK_LEFT) { player.setLeft(false); }
        if(key == KeyEvent.VK_RIGHT) { player.setRight(false); }
        if(key == KeyEvent.VK_UP) { player.setUp(false); }
        if(key == KeyEvent.VK_DOWN) { player.setDown(false); }
        if(key == KeyEvent.VK_W) { player.setJumping(false); }
        if(key == KeyEvent.VK_E) { player.setGliding(false); }
    }
}

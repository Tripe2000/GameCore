/**
 *Date: 21/08/2017
 *Author: Whaleballs
 *File Name: InWorldState.java
 *Project Name: GameCore
 */

package GameState;

import Main.KeyboardManager;
import Main.MainInterface;
import Entity.Player;
import TileMap.Background;
import TileMap.TileMap;
import java.awt.Graphics2D;

public class InWorldState extends GameState {
    
    private TileMap tileMap;
    private Background background;
    
    private Player player;
    
    public InWorldState(GameStateManager gsm) {
        super(gsm);
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
        handleInput();
        
        player.update();
        tileMap.setPosition(
                MainInterface.WIDTH / 2 - player.getX(),
                MainInterface.HEIGHT / 2 - player.getY()
        );
        
        //set background
        background.setPosition(tileMap.getX(), tileMap.getY());
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
    public void handleInput() {
        if(KeyboardManager.isPressed(KeyboardManager.ESCAPE)) { gameStateManager.setPaused(true); }
        player.setLeft(KeyboardManager.keyState[KeyboardManager.LEFT]);
        player.setRight(KeyboardManager.keyState[KeyboardManager.RIGHT]);
        player.setUp(KeyboardManager.keyState[KeyboardManager.UP]);
        player.setDown(KeyboardManager.keyState[KeyboardManager.DOWN]);
        player.setJumping(KeyboardManager.keyState[KeyboardManager.BUTTON1]);
        player.setGliding(KeyboardManager.keyState[KeyboardManager.BUTTON2]);
        if(KeyboardManager.isPressed(KeyboardManager.BUTTON3)) { player.setUsingTool(); }
        if(KeyboardManager.isPressed(KeyboardManager.BUTTON4)) { player.setFiring(); }
        if(KeyboardManager.isPressed(KeyboardManager.B)) { player.setLaser(); }
    }
}

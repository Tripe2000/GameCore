/**
 *Date: 21/08/2017
 *Author: Whaleballs
 *File Name: InWorldState.java
 *Project Name: GameCore
 */

package GameState;

import Entity.Enemies.BadDude;
import Entity.Enemy;
import Entity.HUD;
import Entity.Inventory;
import Main.KeyboardManager;
import Main.MainInterface;
import Entity.Player;
import TileMap.Background;
import TileMap.TileMap;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class InWorldState extends GameState {
    
    private TileMap tileMap;
    private Background background;
    
    private Player player;
    private ArrayList<Enemy> enemies;
    
    private HUD hud;
    
    private boolean openInventory;
    private Inventory inventory;
    
    public InWorldState(GameStateManager gsm) {
        super(gsm);
        initialize();
    }
    
    @Override
    public void initialize() {
        tileMap = new TileMap(TILE_SIZE);
        tileMap.loadTiles(TILE_SET);
        tileMap.loadMap(TILE_MAP);
        tileMap.setPosition(0, 0);
        
        background = new Background(GRASS_BACKGROUND, 0.1);
        background.setVector(0.5, 0);
        
        player = new Player(tileMap);
        player.setPosition(200, 200);
        
        enemies = new ArrayList<Enemy>();
        BadDude bd = new BadDude(tileMap);
        bd.setPosition(100, 100);
        enemies.add(bd);
        
        hud = new HUD(player);
        
        openInventory = false;
        inventory = new Inventory(player);
    }
    
    @Override
    public void update() {        
        handleInput();
        
        //set background
        background.setPosition(tileMap.getX(), tileMap.getY());
        
        if(openInventory) {
            if(inventory.update()) { openInventory = false; }
            return;
        }
        
        player.update();
        tileMap.setPosition(
                MainInterface.WIDTH / 2 - player.getX(),
                MainInterface.HEIGHT / 2 - player.getY()
        );
        
        //update all enemies
        for(int i = 0; i < enemies.size(); i++) {
            enemies.get(i).update();
        }
    }
    
    @Override
    public void draw(Graphics2D g) {
        
        //draw background
        background.draw(g);
        
        //draw tilemap
        tileMap.draw(g);
        
        //draw player
        player.draw(g);
        
        //draw enemies
        for(int i = 0; i < enemies.size(); i++) {
            enemies.get(i).draw(g);
        }
        
        //draw HUD
        hud.draw(g);
        
        //draw inventory
        if(openInventory) { inventory.draw(g); }
    }
    
    @Override
    public void handleInput() {
        if(KeyboardManager.isPressed(KeyboardManager.ESCAPE)) { gameStateManager.setPaused(true); }
        if(KeyboardManager.isPressed(KeyboardManager.I)) {
            if(openInventory) { openInventory = false; inventory.openInventory(true); }
            else { openInventory = true; inventory.openInventory(false); }
        }
        if(openInventory) { return; }
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

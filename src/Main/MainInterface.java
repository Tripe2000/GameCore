/**
 *Date: 19/08/2017
 *Author: Whaleballs
 *File Name: MainInterface.java
 *Project Name: GameCore
 */

package Main;

public interface MainInterface {
    String GAME_NAME = "Game Name";
    int WIDTH = 640;  //1280;
    int HEIGHT = 360;  //720;
    int SCALE = 2;
    
    int TILE_SIZE = 30;
    
    String TILE_SET = "/Tilesets/tileSet.png";//"/Tilesets/grasstileset.gif";
    String TILE_MAP = "/Maps/tileMap.csv";//"/Maps/level1-1-test.map";
    String MENU_BACKGROUND = "/Backgrounds/menuBackground.gif";
    String GRASS_BACKGROUND = "/Backgrounds/grassbackground1.gif";
    String PLAYER_SPRITESHEET = "/Sprites/Player/player_sprites.png";
    String INVENTORY_SPRITESHEET = "/Inventory/inventory.png";
    String FIREBALL_SPRITESHEET = "/Sprites/Player/fireball.gif";
    String SWORD_SPRITESHEET = "/Sprites/Player/sword_sprites.png";
    String LASERBEAM_SPRITESHEET = "/Sprites/Player/laserbeam_sprites.png";
    String BAD_DUDE_SPRITESHEET = "/Sprites/Enemies/slugger.gif";
    String HUD_IMAGE = "/HUD/hud.gif";
}

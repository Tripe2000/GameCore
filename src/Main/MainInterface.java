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
    
    String MENU_BACKGROUND = "/Backgrounds/menuBackground.gif";
    String GRASS_BACKGROUND = "/Backgrounds/grassbackground1.gif";
    String PLAYER_SPRITES = "/Sprites/Player/player_sprites.png";
    String FIREBALL_SPRITE_SHEET = "/Sprites/Player/fireball.gif";
    String SWORD = "/Sprites/Player/sword_sprites.png";
    String LASERBEAM_SPRITESHEET = "/Sprites/Player/laserbeam_sprites.png";
    String BAD_DUDE_SPRITESHEET = "/Sprites/Enemies/slugger.gif";
    String HUD_IMAGE = "/HUD/hud.gif";
}

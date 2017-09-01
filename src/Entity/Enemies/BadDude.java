/**
 *Date: 21/08/2017
 *Author: Whaleballs
 *File Name: BadDude.java
 *Project Name: GameCore
 */

package Entity.Enemies;

import Entity.Animation;
import Entity.Enemy;
import static Main.MainInterface.BAD_DUDE_SPRITESHEET;
import TileMap.TileMap;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class BadDude extends Enemy {
    
    private BufferedImage[] sprites;
    
    public BadDude(TileMap tm) {
        super(tm);
        
        moveSpeed = 0.3;
        maxSpeed = 0.3;
        fallSpeed = 0.2;
        maxFallSpeed = 10.0;
        
        width = 30;
        height = 30;
        cWidth = 20;
        cHeight = 20;
        
        health = maxHealth = 2;
        damage = 1;
        
        //load sprites
        try {
            
            BufferedImage spriteSheet = ImageIO.read(getClass().getResourceAsStream(BAD_DUDE_SPRITESHEET));
            
            sprites = new BufferedImage[3];
            for(int i = 0; i < sprites.length; i++) {
                sprites[i] = spriteSheet.getSubimage(
                        i * width,
                        0,
                        width,
                        height
                );
            }
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        animation = new Animation();
        animation.setFrames(sprites);
        animation.setDelay(300);
        
        right = true;
        facingRight = true;
    }
    
    private void getNextPosition() {
        
        //movement
        if(left) {
            dx -= moveSpeed;
            if(dx < -maxSpeed) { dx = -maxSpeed; }
        } else if(right) {
            dx += moveSpeed;
            if(dx > maxSpeed) { dx = maxSpeed; }
        }
        
        //falling
        if(falling) {
            dy += fallSpeed;
        }
    }
    
    public void update() {
        
        //update position
        getNextPosition();
        checkTileMapCollision();
        setPosition(xTemp, yTemp);
        
        //check flinching
        if(flinching) {
            long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
            if(elapsed > 400) { flinching = false; }
        }
        
        //if it hits a wall, go other direction
        if(right && dx == 0) {
            right = false;
            left = true;
            facingRight = false;
        } else if(left && dx == 0) {
            left = false;
            right = true;
            facingRight = true;
        }
        
        //updpate animation
        animation.update();
    }
    
    public void draw(Graphics2D g) {
        
        if(notOnScreen()) { return; }
        
        setMapPosition();
        
        super.draw(g);
    }
}

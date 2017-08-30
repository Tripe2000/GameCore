/**
 *Date: 21/08/2017
 *Author: Whaleballs
 *File Name: LaserBeam.java
 *Project Name: GameCore
 */

package Entity;

import static Main.MainInterface.LASERBEAM_SPRITESHEET;
import TileMap.TileMap;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class LaserBeam extends WorldObject {
    
    private BufferedImage[] sprites;
    private BufferedImage[] hitSprites;
    
    private boolean hit = false;
    private boolean remove = false;
    
    public LaserBeam(TileMap tm) {
        super(tm);
        
        moveSpeed = 3.8;
        dx = moveSpeed;
        
        width = 30;
        height = 30;
        cWidth = 14;
        cHeight = 14;
        
        //load sprites
        try {
            
            BufferedImage spriteSheet = ImageIO.read(getClass().getResourceAsStream(LASERBEAM_SPRITESHEET));
            
            sprites = new BufferedImage[1];
            hitSprites = new BufferedImage[1];
            
            sprites[0] = spriteSheet.getSubimage(0, 0, width, height);
            hitSprites[0] = spriteSheet.getSubimage(0, height, width, height);
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        animation = new Animation();
        animation.setFrames(sprites);
        animation.setDelay(-1);
    }
    
    public void setHit() {
        if(hit) return;
        hit = true;
        animation.setFrames(hitSprites);
        animation.setDelay(70);
        dx = 0;
    }
    
    public boolean shouldRemove() { return remove; }
    
    public void shootLaser(boolean facingRight) { 
        if(facingRight) { dx = moveSpeed; }
        else { dx = -moveSpeed; }
        
        remove = false;
        hit = false;
        animation.setFrames(sprites);
        animation.setDelay(100);
        
        calculateDistanceTravel();
    }
    
    private void calculateDistanceTravel() {
    }
    
    public void update() {
        checkTileMapCollision();
        setPosition(xTemp, yTemp);
        
        if(dx == 0 && !hit) setHit();
        
        animation.update();
        if(hit) remove = true;
    }
    
    public void draw(Graphics2D g) {
        setMapPosition();
        
        if(facingRight) {
            g.drawImage(
                animation.getImage(),
                (int)(x + xMap - width / 2) + 30,
                (int)(y + yMap - height / 2),
                null
            );
        } else {
            g.drawImage(
                animation.getImage(),
                (int)(x + xMap - width / 2 + width) - 30,
                (int)(y + yMap - height / 2),
                -width,
                height,
                null
            );
        }
    }
}

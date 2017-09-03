/**
 *Date: 21/08/2017
 *Author: Whaleballs
 *File Name: Sword.java
 *Project Name: GameCore
 */

package Entity.Item;

import Entity.Animation;
import Entity.WorldObject;
import Main.MainInterface;
import static Main.MainInterface.SWORD_ICON;
import TileMap.TileMap;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Sword extends WorldItem {
    
    //animation stuff
    private ArrayList<BufferedImage[]> sprites;
    private final int[] numberFrames = {                                        //keeps track of how many frames each animation has
        4, 6, 1, 1, 1, 2, 5
    };
    
    //animation actions
    private static final int IDLE = 0;
    private static final int WALKING = 1;
    private static final int JUMPING = 2;
    private static final int FALLING = 3;
    private static final int GLIDING = 4;
    private static final int RANGE_ATTACK = 5;
    private static final int USE_TOOL = 6;
    private static final int LASER_ATTACK = 7;
    
    public Sword(TileMap tm) {
        super(tm);
        
        width = 30;
        height = 30;
        
        try {
            
            BufferedImage spriteSheet = ImageIO.read(getClass().getResourceAsStream(MainInterface.SWORD_SPRITESHEET));
            sprites = new ArrayList<BufferedImage[]>();
            
            for(int i = 0; i < numberFrames.length; i++) {
                BufferedImage[] bi = new BufferedImage[numberFrames[i]];
                for(int j = 0; j < bi.length; j++) {
                    bi[j] = spriteSheet.getSubimage(j * width, i * height, width, height);
                }
                sprites.add(bi);
            }
            
            icon = ImageIO.read(getClass().getResourceAsStream(SWORD_ICON));
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        animation = new Animation();
        currentAction = IDLE;
        animation.setFrames(sprites.get(IDLE));
        animation.setDelay(400);
    }
    
    private void getNextPosition() {
        //falling
        if(falling) {
            dy += fallSpeed;
        }
    }
    
    public void update(int currentAction) {
        if(inWorld) {
            getNextPosition();
            checkTileMapCollision();
            setPosition(xTemp, yTemp);
        }
        
        if(currentAction == LASER_ATTACK) { this.currentAction = currentAction; return; }
        if(this.currentAction != currentAction) {
            this.currentAction = currentAction;
            animation.setFrames(sprites.get(currentAction));
            if(currentAction == JUMPING || currentAction == FALLING || currentAction == GLIDING) { animation.setDelay(-1); }
            else if(currentAction == USE_TOOL){ animation.setDelay(50); }
            else if(currentAction == RANGE_ATTACK) { animation.setDelay(100); }
            else if(currentAction == WALKING) { animation.setDelay(40); }
            else if(currentAction == IDLE) { animation.setDelay(400); }
            width = 30;
        }
        animation.update();
    }
    
    public void drawEquipped(Graphics2D g, boolean facingRight) {
        if(currentAction == LASER_ATTACK) { return; }
        setMapPosition();
        if(currentAction == USE_TOOL && animation.hasPlayedOnce()) { animation.setFrame(sprites.get(USE_TOOL).length - 1); }
        if(facingRight) {
            g.drawImage(
                animation.getImage(),
                (int)(x + xMap - width / 2),
                (int)(y + yMap - height / 2),
                null
            );
        } else {
            g.drawImage(
                animation.getImage(),
                (int)(x + xMap - width / 2 + width),
                (int)(y + yMap - height / 2),
                -width,
                height,
                null
            );
        }
    }
    
    @Override
    public void drawInventory(Graphics2D g, int position, int x, int y) {
        int h = position / 6;
        h = 34 * h + y + 22;
        
        int w = position % 6;
        w = 34 * w + x + 22;
        
        System.out.println(position);
        System.out.println("w" + w);
        System.out.println("h" + h);
        
        g.drawImage(icon, w, h, null);
    }
}

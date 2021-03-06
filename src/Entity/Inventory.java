/**
 *Date: 21/08/2017
 *Author: Whaleballs
 *File Name: InventoryManager.java
 *Project Name: GameCore
 */

package Entity;

import Entity.Item.Sword;
import Entity.Item.WorldItem;
import Main.KeyboardManager;
import static Main.MainInterface.HEIGHT;
import static Main.MainInterface.INVENTORY_SPRITESHEET;
import static Main.MainInterface.WIDTH;
import TileMap.TileMap;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Inventory {
    
    private int freeSpace;
    private int maxSpace;
    private WorldItem[] inventory;
    
    private boolean closed;
    
    private BufferedImage[] sprites;
    private int width;
    private int height;
    private Animation animation;
    private int x;
    private int y;
    
    public Inventory(Player player) {
        width = 320;
        height = 180;
        x = (WIDTH - width) / 2;
        y = (HEIGHT - height) / 2;
        
        maxSpace = freeSpace = 12;
        inventory = new WorldItem[12];
        
        try {
            
            BufferedImage spriteSheet = ImageIO.read(getClass().getResourceAsStream(INVENTORY_SPRITESHEET));
            
            sprites = new BufferedImage[4];
            
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
        animation.setDelay(400);
    }
    
    public void addItem(WorldItem item) {
        for(int i = 0; i < inventory.length; i++) {
            if(inventory[i] == null) {
                inventory[i] = item;
                return;
            }
        }
    }
    
    public void removeItem(WorldItem item) {
    }
    
    public void isEquipped(WorldItem item) {
    }
    
    public void openInventory(boolean b) { closed = b; }
    
    public boolean update() {
        animation.update();
        
        return closed;
    }
    
    public void draw(Graphics2D g) {
        
        g.drawImage(animation.getImage(), x, y, null);
        
        for(int i = 0; i < inventory.length; i++) {
            if(inventory[i] != null) {
                inventory[i].drawInventory(g, i, x, y);
            }
        }
    }
}

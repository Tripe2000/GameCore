/**
 *Date: 21/08/2017
 *Author: Whaleballs
 *File Name: WorldItem.java
 *Project Name: GameCore
 */

package Entity.Item;

import Entity.WorldObject;
import TileMap.TileMap;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public abstract class WorldItem extends WorldObject {
    
    protected boolean inWorld;
    
    protected BufferedImage icon;
    
    public WorldItem(TileMap tm) {
        super(tm);
    }
    
    public abstract void drawInventory(Graphics2D g, int position, int x, int y);
}

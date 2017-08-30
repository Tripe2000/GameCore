/**
 *Date: 21/08/2017
 *Author: Whaleballs
 *File Name: WorldObject.java
 *Project Name: GameCore
 */

package Entity;

import Main.MainInterface;
import TileMap.Tile;
import TileMap.TileMap;
import java.awt.Rectangle;

public abstract class WorldObject {
    
    //tile stuff
    protected TileMap tileMap;
    protected int tileSize;
    protected double xMap;
    protected double yMap;
    
    //position and vector
    protected double x;
    protected double y;
    protected double dx;
    protected double dy;
    
    //dimension
    protected int width;
    protected int height;
    
    //collision box
    protected int cWidth;
    protected int cHeight;
    
    //collision variables
    protected int currentRow;
    protected int currentColumn;
    protected double xDestination;
    protected double yDestination;
    protected double xTemp;
    protected double yTemp;
    protected boolean topLeft;
    protected boolean topRight;
    protected boolean bottomLeft;
    protected boolean bottomRight;
    
    //animation
    protected Animation animation;
    protected int currentAction;
    protected int previousAction;
    protected boolean facingRight;                                              //to know weather we need to flip the sprite or not
    
    //movement
    protected boolean left;
    protected boolean right;
    protected boolean up;
    protected boolean down;
    protected boolean jumping;
    protected boolean falling;
    
    //movement attributes
    protected double moveSpeed;
    protected double maxSpeed;
    protected double stopSpeed;
    protected double fallSpeed;
    protected double maxFallSpeed;
    protected double jumpStart;
    protected double stopJumpSpeed;
    
    //constructor
    public WorldObject(TileMap tm) {
        tileMap = tm;
        tileSize = tm.getTileSize();
    }
    
    //check if collision box intersects
    public boolean intersects(WorldObject entity) {
        Rectangle r1 = getCollisionRectangle();
        Rectangle r2 = entity.getCollisionRectangle();
        return r1.intersects(r2);
    }
    
    public Rectangle getCollisionRectangle() {
        return new Rectangle((int)x - cWidth, (int)y - cHeight, cWidth, cHeight);
    }
    
    public void calculateCorners(double x, double y) {
        int leftTile = (int)(x - cWidth / 2) / tileSize;
        int rightTile = (int)(x + cWidth / 2 - 1) / tileSize;
        int topTile = (int)(y - cHeight / 2) / tileSize;
        int bottomTile = (int)(y + cHeight / 2 - 1) / tileSize;
        
        if( topTile < 0 ||
            bottomTile >= tileMap.getNumberRows() ||
            leftTile < 0 ||
            rightTile >= tileMap.getNumberColumns()) {
                topLeft = topRight = bottomLeft = bottomRight = false;
                return;
        }
        
        int topLeftTile = tileMap.getType(topTile, leftTile);
        int topRightTile = tileMap.getType(topTile, rightTile);
        int bottomLeftTile = tileMap.getType(bottomTile, leftTile);
        int bottomRightTile = tileMap.getType(bottomTile, rightTile);
        
        topLeft = topLeftTile == Tile.BLOCKED;
        topRight = topRightTile == Tile.BLOCKED;
        bottomLeft = bottomLeftTile == Tile.BLOCKED;
        bottomRight = bottomRightTile == Tile.BLOCKED;
    }
    
    public void checkTileMapCollision() {
        currentColumn = (int)x / tileSize;
        currentRow = (int)y / tileSize;
        
        xDestination = x + dx;
        yDestination = y + dy;
        
        xTemp = x;
        yTemp = y;
        
        calculateCorners(x, yDestination);
        if(dy < 0) {
            if(topLeft || topRight) {
                dy = 0;
                yTemp = currentRow * tileSize + cHeight / 2;
            } else {
                yTemp += dy;
            }
        }
        if(dy > 0) {
            if(bottomLeft || bottomRight) {
                dy = 0;
                falling = false;
                yTemp = (currentRow + 1) * tileSize - cHeight / 2;
            } else {
                yTemp += dy;
            }
        }
        
        calculateCorners(xDestination, y);
        if(dx < 0) {
            if(topLeft || bottomLeft) {
                dx = 0;
                xTemp = currentColumn * tileSize + cWidth / 2;
            } else {
                xTemp += dx;
            }
        }
        
        if(dx > 0) {
            if(topRight || bottomRight) {
                dx = 0;
                xTemp = (currentColumn + 1) * tileSize - cWidth / 2;
            } else {
                xTemp += dx;
            }
        }
        
        if(!falling) {
            calculateCorners(x, yDestination + 1);
            if(!bottomLeft && !bottomRight) {
                falling = true;
            }
        }
    }
    
    public int getX() { return (int)x; }
    public int getY() { return (int)y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public int getCollisionWidth() { return cWidth; }
    public int getCollisionHeight() { return cHeight; }
    
    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public void setVector(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }
    
    public void setMapPosition() {
        xMap = tileMap.getX();
        yMap = tileMap.getY();
    }
    
    public void setLeft(boolean b) { left = b; }
    public void setRight(boolean b) { right = b; }
    public void setUp(boolean b) { up = b; }
    public void setDown(boolean b) { down = b; }
    public void setJumping(boolean b) { jumping = b; }
    
    public boolean notOnScreen() {                                              //if the object is beyond the right side or the left sidwe of the screen, returns true
        return x + xMap + width < 0 ||
                x + xMap - width > MainInterface.WIDTH ||
                y + yMap + height < 0 ||
                y + yMap - height > MainInterface.HEIGHT;
    }
}

/**
 *Date: 21/08/2017
 *Author: Whaleballs
 *File Name: TileMap.java
 *Project Name: GameCore
 */

package TileMap;

import Engine.GamePanel;
import Engine.MainInterface;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;

public class TileMap implements MainInterface {
    
    //position
    private double x;
    private double y;
    
    //bounds
    private int xMin;
    private int yMin;
    private int xMax;
    private int yMax;
    
    private double tween;
    
    //map
    private int[][] map;
    private int tileSize;
    private int numberRows;
    private int numberColumns;
    private int width;
    private int height;
    
    //tileset
    private BufferedImage tileSet;
    private int numberTilesAcross;
    private Tile[][] tiles;
    
    //drawing
    private int rowOffset;
    private int columnOffset;
    private int numberRowsToDraw;
    private int numberColumnsToDraw;
    
    public TileMap(int tileSize) {
        this.tileSize = tileSize;
        numberRowsToDraw = MainInterface.HEIGHT / tileSize + 2;
        numberColumnsToDraw = MainInterface.WIDTH / tileSize + 2;
        tween = 0.07;
    }
    
    public void loadTiles(String s) {
        try {
            
            tileSet = ImageIO.read(getClass().getResourceAsStream(s));
            
            numberTilesAcross = tileSet.getWidth() / tileSize;
            tiles = new Tile[2][numberTilesAcross];
            
            BufferedImage subImage;
            for(int column = 0; column < numberTilesAcross; column++) {
                
                subImage = tileSet.getSubimage(column * tileSize, 0, tileSize, tileSize);
                tiles[0][column] = new Tile(subImage, Tile.NORMAL);
                
                subImage = tileSet.getSubimage(column * tileSize, tileSize, tileSize, tileSize);
                tiles[1][column] = new Tile(subImage, Tile.BLOCKED);
            }
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void loadMap(String s) {
        try {
            
            InputStream in = getClass().getResourceAsStream(s);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            
            numberColumns = Integer.parseInt(br.readLine());
            numberRows = Integer.parseInt(br.readLine());
            map = new int[numberRows][numberColumns];
            width = numberColumns * tileSize;
            height = numberRows * tileSize;
            
            String delimiters = "\\s+";
            for(int row = 0; row < numberRows; row++) {
                
                String line = br.readLine();
                String[] tokens = line.split(delimiters);
                for(int column = 0; column < numberColumns; column++) {
                    map[row][column] = Integer.parseInt(tokens[column]);
                }
                
            }
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public int getTileSize() { return tileSize; }
    public int getX() { return (int)x; }
    public int getY() { return (int)y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    
    public int getTypee(int row, int column) {
        int rc = map[row][column];
        int r = rc / numberTilesAcross;
        int c = rc % numberTilesAcross;
        return tiles[r][c].getType();
    }
    
    public void setPosition(double x, double y) {
        this.x += (x - this.x) * tween;
        this.y += (y - this.y) * tween;
        
        fixBounds();
        
        columnOffset = (int)-this.x / tileSize;
        rowOffset = (int)-this.y / tileSize;
    }
    
    private void fixBounds() {
        if(x < xMin) x = xMin;
        if(y < yMin) y = yMin;
        if(x > xMax) x = xMax;
        if(y > yMax) y = yMax;
    }
    
    public void draw(Graphics2D g) {
        for(int row = rowOffset; row < rowOffset + numberRowsToDraw; row++) {
            
            if(row >= numberRows) break;
            
            for(int column = columnOffset; column < columnOffset + numberColumnsToDraw; column++) {
                
                if(column >= numberColumns) break;
                if(map[row][column] == 0) continue;
                
                int rc = map[row][column];
                int r = rc / numberTilesAcross;
                int c = rc % numberTilesAcross;
                
                g.drawImage(tiles[r][c].getImage(), (int)x + column * tileSize, (int)y + row * tileSize, null);
            }
        }
    }
}

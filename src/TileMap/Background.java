/**
 *Date: 19/08/2017
 *Author: Whaleballs
 *File Name: Background.java
 *Project Name: GameCore
 */

package TileMap;

import Main.MainInterface;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Background {
    
    private BufferedImage image;
    
    private double x;
    private double y;
    private double dx;
    private double dy;
    
    private  double moveScale;
    
    public Background(String s, double ms) {
        try {
            image = ImageIO.read(getClass().getResourceAsStream(s));
            moveScale = ms;
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void setPosition(double x, double y) {
        this.x = (x * moveScale) % MainInterface.WIDTH;
        this.y = (y * moveScale) % MainInterface.HEIGHT;
    }
    
    public void setVector(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }
    
    public void update() {
        x += dx;
        y += dy;
    }
    
    public void draw(Graphics2D g) {        
        g.drawImage(image, (int)x, (int)y, null);
        
        if(x < 0) {
            g.drawImage(image, (int)x + MainInterface.WIDTH, (int)y, null);
        } else if(x > 0) {
            g.drawImage(image, (int)x - MainInterface.WIDTH, (int)y, null);
        }
        
        if((x * moveScale) % MainInterface.WIDTH != 0) {
            setPosition(x, y);
        }
    }
}

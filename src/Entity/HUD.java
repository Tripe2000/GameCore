/**
 *Date: 21/08/2017
 *Author: Whaleballs
 *File Name: HUD.java
 *Project Name: GameCore
 */

package Entity;

import static Main.MainInterface.HUD_IMAGE;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class HUD {
    
    private Player player;
    
    private BufferedImage image;
    private Font font;
    
    public HUD(Player p) {
        player = p;
        
        try {
            
            image = ImageIO.read(getClass().getResourceAsStream(HUD_IMAGE));
            font = new Font("Arial", Font.PLAIN, 14);
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void draw(Graphics2D g) {
        g.drawImage(image, 0, 10, null);
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString(player.getHealth() + "/" + player.getMaxHealth(), 20, 25);
        g.drawString(player.getFire() / 100 + "/" + player.getMaxFire() / 100, 20, 45);
    }
}

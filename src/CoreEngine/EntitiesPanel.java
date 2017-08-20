/**
 *Date: 17/08/2017
 *Author: Whaleballs
 *File Name: EntitiesPanel.java
 *Project Name: GameCore
 */

package CoreEngine;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class EntitiesPanel extends PanelManager implements CoreInterface {
    
    protected int playerWidth;
    protected int playerHeight;
    protected BufferedImage[][] playerSprites;
    
    public EntitiesPanel() {
        setSize(CoreInterface.WIDTH, CoreInterface.HEIGHT);
        addKeyListener(new KeyboardController());                               //Adds key listeners
        setOpaque(false);                                                       //Sets panel transparent
        setFocusable(true);
        
        loadInformation();
    }
    
    protected void loadInformation() {    
        playerSprites = new BufferedImage[TOTAL_PLAYER_SPRITES][];
        
        playerSprites[IDLE_LEFT_SET] = new BufferedImage[1];
        playerSprites[IDLE_RIGHT_SET] = new BufferedImage[1];
        playerSprites[SPRINT_LEFT_SET] = new BufferedImage[6];
        playerSprites[SPRINT_RIGHT_SET] = new BufferedImage[6];
        
        try {
            playerSprites[IDLE_LEFT_SET][0] = ImageIO.read(new File(IDLE_LEFT));
            
            playerSprites[IDLE_RIGHT_SET][0] = ImageIO.read(new File(IDLE_RIGHT));
            
            playerSprites[SPRINT_LEFT_SET][0] = ImageIO.read(new File(SPRINT_LEFT[0]));
            playerSprites[SPRINT_LEFT_SET][1] = ImageIO.read(new File(SPRINT_LEFT[1]));
            playerSprites[SPRINT_LEFT_SET][2] = ImageIO.read(new File(SPRINT_LEFT[2]));
            playerSprites[SPRINT_LEFT_SET][3] = ImageIO.read(new File(SPRINT_LEFT[3]));
            playerSprites[SPRINT_LEFT_SET][4] = ImageIO.read(new File(SPRINT_LEFT[4]));
            playerSprites[SPRINT_LEFT_SET][5] = ImageIO.read(new File(SPRINT_LEFT[5]));
            
            playerSprites[SPRINT_RIGHT_SET][0] = ImageIO.read(new File(SPRINT_RIGHT[0]));
            playerSprites[SPRINT_RIGHT_SET][1] = ImageIO.read(new File(SPRINT_RIGHT[1]));
            playerSprites[SPRINT_RIGHT_SET][2] = ImageIO.read(new File(SPRINT_RIGHT[2]));
            playerSprites[SPRINT_RIGHT_SET][3] = ImageIO.read(new File(SPRINT_RIGHT[3]));
            playerSprites[SPRINT_RIGHT_SET][4] = ImageIO.read(new File(SPRINT_RIGHT[4]));
            playerSprites[SPRINT_RIGHT_SET][5] = ImageIO.read(new File(SPRINT_RIGHT[5]));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public void repaintPlayer(int currentX, int currentY, int frameSet, int frameNumber) {
        Graphics g = this.getGraphics();
        g.clearRect(currentX, currentY, playerSprites[frameSet][frameNumber].getWidth(), playerSprites[frameSet][frameNumber].getHeight());
        g.drawImage(playerSprites[frameSet][frameNumber], currentX, currentY, this);
        g.dispose();
        System.out.println("frameSet=" + frameSet);
        System.out.println("frameNumber=" + frameNumber);
    }
}

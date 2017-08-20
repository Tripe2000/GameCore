/**
 *Date: 17/08/2017
 *Author: Whaleballs
 *File Name: Display.java
 *Project Name: GameCore
 */

package CoreEngine;

import CoreEngine.PlayerManager;
import javax.swing.*;

public class Display extends JFrame implements CoreInterface {
    
    protected JLayeredPane layeredPane;
    
    public Display(String name) {
        super(name);
        setSize(CoreInterface.WIDTH, CoreInterface.HEIGHT);
        setResizable(false);
        
        layeredPane = getLayeredPane();
        layeredPane.add(GameManager.backgroundPanel, new Integer(0));
        layeredPane.add(GameManager.entitiesPanel, new Integer(100));
        
        /*getContentPane().add(GameManager.backgroundPanel);
        getContentPane().add(GameManager.entitiesPanel);*/
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
    }
}

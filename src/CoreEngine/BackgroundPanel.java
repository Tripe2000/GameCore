/**
 *Date: 17/08/2017
 *Author: Whaleballs
 *File Name: BackgroundPanel.java
 *Project Name: GameCore
 */

package CoreEngine;

import java.awt.Color;

public class BackgroundPanel extends PanelManager {
    
    public BackgroundPanel() {
        setSize(CoreInterface.WIDTH, CoreInterface.HEIGHT);
        setBackground(Color.blue);
    }
}

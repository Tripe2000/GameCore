package Display;

import CoreEngine.KeyboardController;
import java.awt.BorderLayout;
import javax.swing.*;

public class GamePanel extends JPanel {
    
    public GamePanel() {
        super(new BorderLayout());
        System.out.println("new GamePanel();");
        
        addKeyListener(new KeyboardController());
        setFocusable(true);
    }
    
    public void repaintGame() {
    }
}

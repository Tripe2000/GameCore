/**
 *Date: 21/08/2017
 *Author: Whaleballs
 *File Name: PauseState.java
 *Project Name: GameCore
 */

package GameState;

import Main.KeyboardManager;
import Main.MainInterface;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

public class PauseState extends GameState {
	
    private Font titleFont;
    private Font optionsFont;
    private Color titleColor;
    private Color optionsColor;
    private Color selectedColor;
    private Color bgColor;
    
    private boolean isBackgroundSet;
    
    private int currentChoice = 0;
    
    private String[] options = {
        "Resume",
        "Restart",
        "Main Menu",
        "Quit"
    };
	
    public PauseState(GameStateManager gsm) {
        super(gsm);
        initialize();		
    }
	
    public void initialize() {
        titleFont = new Font("Century Gothic", Font.PLAIN, 14);
        optionsFont = new Font("Century Gothic", Font.PLAIN, 11);
        
        titleColor = optionsColor = new Color(255, 255, 255);
        selectedColor = new Color(50, 50, 255);
        bgColor = new Color(128, 128, 128, 200);
        
        isBackgroundSet = false;
    }
    
    public void update() {
        handleInput();
    }
    
    public void draw(Graphics2D g) {
        if(!isBackgroundSet) {
            isBackgroundSet = true;
            g.setColor(bgColor);
            g.fillRect(0, 0, MainInterface.WIDTH, MainInterface.HEIGHT);
            g.setColor(titleColor);
            g.setFont(titleFont);
            FontMetrics fontMetrics = g.getFontMetrics();
            g.drawString("Game Paused", (MainInterface.WIDTH - fontMetrics.stringWidth("Game Paused")) / 2, (int)(MainInterface.HEIGHT * 0.25));
        }
        
        for(int i = 0; i < options.length; i++) {
            if(i == currentChoice) {
                g.setColor(selectedColor);
            } else { g.setColor(optionsColor); }
            g.setFont(optionsFont);
            FontMetrics fontMetrics = g.getFontMetrics();
            g.drawString(options[i], (MainInterface.WIDTH - fontMetrics.stringWidth(options[i])) / 2, (int)(MainInterface.HEIGHT * 0.4) + i * 15);
        }
    }
    
    private void select() {
        switch(currentChoice) {
            case 0:
                isBackgroundSet = false;
                gameStateManager.setPaused(false);
                break;
            case 1:
                isBackgroundSet = false;
                gameStateManager.setState(GameStateManager.IN_WORLD_STATE);
                gameStateManager.setPaused(false);
                break;
            case 2:
                isBackgroundSet = false;
                gameStateManager.setState(GameStateManager.MENUSTATE);
                gameStateManager.setPaused(false);
                break;
            case 3:
                System.exit(0);
                break;
            default:
                break;
        }
    }
    
    public void handleInput() {
        if(KeyboardManager.isPressed(KeyboardManager.ESCAPE)) {
            isBackgroundSet = false;
            gameStateManager.setPaused(false);
        }
        if(KeyboardManager.isPressed(KeyboardManager.ENTER)) { select(); }
        if(KeyboardManager.isPressed(KeyboardManager.UP)) {
            currentChoice--;
            if(currentChoice == -1) { currentChoice = options.length - 1; }
        }
        if(KeyboardManager.isPressed(KeyboardManager.DOWN)) {
            currentChoice++;
            if(currentChoice == options.length) { currentChoice = 0; }
        }
    }
}

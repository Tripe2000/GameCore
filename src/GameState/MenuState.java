/**
 *Date: 19/08/2017
 *Author: Whaleballs
 *File Name: MenuState.java
 *Project Name: GameCore
 */

package GameState;

import Main.KeyboardManager;
import Main.MainInterface;
import TileMap.Background;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

public class MenuState extends GameState {
    
    private Background background;
    
    private int currentChoice = 0;
    private String[] options = {
        "Start",
        "Resume",
        "Quit"
    };
    
    private Color titleColor;
    private Font titleFont;
    
    private Font font;
    
    public MenuState(GameStateManager gsm) {
        super(gsm);
        try {
            background = new Background(MENU_BACKGROUND, 1);
            background.setVector(0.5, 0);
            
            titleColor = new Color(128, 0, 0);
            titleFont = new Font("Century Gothic", Font.PLAIN, 28);
            
            font = new Font("Arial", Font.PLAIN, 12);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void initialize() {
    }
    
    @Override
    public void update() {
        background.update();
        handleInput();
    }
    
    @Override
    public void draw(Graphics2D g) {
        //draw background
        background.draw(g);
        
        //draw title
        g.setColor(titleColor);
        g.setFont(titleFont);
        FontMetrics fontMetrics = g.getFontMetrics();
        g.drawString(GAME_NAME, (MainInterface.WIDTH - fontMetrics.stringWidth(GAME_NAME))/2, MainInterface.HEIGHT/2 - 70);
        
        //draw menu options
        g.setFont(font);
        fontMetrics = g.getFontMetrics();
        for(int i = 0; i < options.length; i++) {
            if(i == currentChoice) {
                g.setColor(Color.WHITE);
            } else {
                g.setColor(Color.RED);
            }
            g.drawString(options[i], (MainInterface.WIDTH - fontMetrics.stringWidth(options[i]))/2, MainInterface.HEIGHT/2 + i * 15);
        }
    }
    
    @Override
    public void handleInput() {
        if(KeyboardManager.isPressed(KeyboardManager.ENTER)) { select(); }
        if(KeyboardManager.isPressed(KeyboardManager.UP)) {
            currentChoice--;
            if(currentChoice == -1) {
                currentChoice = options.length - 1;
            }
        }
        if(KeyboardManager.isPressed(KeyboardManager.DOWN)) {
            currentChoice++;
            if(currentChoice == options.length) {
                currentChoice = 0;
            }
        }
    }
    
    private void select() {
        switch(currentChoice) {
            case 0:
                gameStateManager.setState(GameStateManager.IN_WORLD_STATE);
                break;
            case 1:
                //resume
                break;
            case 2:
                System.exit(0);
                break;
            default:
                break;
        }
    }
}

/**
 *Date: 19/08/2017
 *Author: Whaleballs
 *File Name: GameFrame.java
 *Project Name: GameCore
 */

package Main;

import javax.swing.JFrame;

public class GameFrame implements MainInterface {
    public static void main(String[] args) {
        JFrame window = new JFrame(GAME_NAME);
        window.setContentPane(new GamePanel());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.pack();
        window.setVisible(true);
    }
}

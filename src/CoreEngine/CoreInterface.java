/**
 *Date: 17/08/2017
 *Author: Whaleballs
 *File Name: CoreInterface.java
 *Project Name: GameCore
 */

package CoreEngine;

public interface CoreInterface {
    String GAME_NAME = "Game Name";                                             //Game name
    int WIDTH  = 1280;                                                          //Game frame width
    int HEIGHT = 720;                                                           //Game frame height
    
    int MAIN_SLEEP_TIME = 100;                                                  //GameManager's thread sleep time
    int MOVE_COUNTER_THRESH = 2;                                                //Move counter before animation switch
    int DISPLACEMENT = 10;                                                      //Player displacement on move
    int TOTAL_FRAMES_IN_SPRINT_ANIMATION = 6;                                   //total amount of frames in sprinting animation
    int JUMP_COUNTER_THRESH = 20;
    
    //sprites locations
    int TOTAL_PLAYER_SPRITES = 4;
    /*int AMOUNT_IDLE_FRAMES = 1;
    int AMOUNT_SPRINT_FRAMES = 6;*/
    int IDLE_LEFT_SET = 0;
    int IDLE_RIGHT_SET = 1;
    int SPRINT_LEFT_SET = 2;
    int SPRINT_RIGHT_SET = 3;
    int JUMPING_FRAME = 5;
    String IDLE_LEFT = "C:\\Users\\Whaleballs\\Documents\\NetBeansProjects\\GameCore\\resources\\stopLeft.png";
    String IDLE_RIGHT = "C:\\Users\\Whaleballs\\Documents\\NetBeansProjects\\GameCore\\resources\\stopRight.png";
    String[] SPRINT_LEFT = {
        "C:\\Users\\Whaleballs\\Documents\\NetBeansProjects\\GameCore\\resources\\spriteLeft_0.png",
        "C:\\Users\\Whaleballs\\Documents\\NetBeansProjects\\GameCore\\resources\\spriteLeft_1.png",
        "C:\\Users\\Whaleballs\\Documents\\NetBeansProjects\\GameCore\\resources\\spriteLeft_2.png",
        "C:\\Users\\Whaleballs\\Documents\\NetBeansProjects\\GameCore\\resources\\spriteLeft_3.png",
        "C:\\Users\\Whaleballs\\Documents\\NetBeansProjects\\GameCore\\resources\\spriteLeft_4.png",
        "C:\\Users\\Whaleballs\\Documents\\NetBeansProjects\\GameCore\\resources\\spriteLeft_5.png"
    };
    String[] SPRINT_RIGHT = {
        "C:\\Users\\Whaleballs\\Documents\\NetBeansProjects\\GameCore\\resources\\spriteRight_0.png",
        "C:\\Users\\Whaleballs\\Documents\\NetBeansProjects\\GameCore\\resources\\spriteRight_1.png",
        "C:\\Users\\Whaleballs\\Documents\\NetBeansProjects\\GameCore\\resources\\spriteRight_2.png",
        "C:\\Users\\Whaleballs\\Documents\\NetBeansProjects\\GameCore\\resources\\spriteRight_3.png",
        "C:\\Users\\Whaleballs\\Documents\\NetBeansProjects\\GameCore\\resources\\spriteRight_4.png",
        "C:\\Users\\Whaleballs\\Documents\\NetBeansProjects\\GameCore\\resources\\spriteRight_5.png"
    };
}

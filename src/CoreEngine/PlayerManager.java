/**
 *Date: 17/08/2017
 *Author: Whaleballs
 *File Name: PlayerManager.java
 *Project Name: GameCore
 */

package CoreEngine;

import java.awt.event.KeyEvent;
import java.util.HashSet;

public class PlayerManager implements Runnable, CoreInterface {
    
    protected static PlayerManager playerManager = new PlayerManager();
    
    protected EntitiesPanel entitiesPanel;
    protected int currentX;                                                     //Current X value of players position
    protected int currentY;                                                     //Current Y value of players position
    protected String currentFrame;                                              //The frame currently in use
    protected int currentFrameNumber;                                           //The frame number currently in use (for the array of the animation)
    protected int lastDirection;                                                //Last direction player was facing
    protected int moveCounter;                                                  //Counter used to change animation frames
    protected BoundingBox boundingBox;                                          //Players bounding box
    
    public PlayerManager() {
        entitiesPanel = GameManager.entitiesPanel;
        currentX = 0;
        currentY = 50;
        currentFrame = STAND_STILL_RIGHT;
        currentFrameNumber = 0;
        lastDirection = KeyEvent.VK_RIGHT;
        moveCounter = 0;
        boundingBox = new BoundingBox();
    }
    
    public PlayerManager(int startingX, int startingY, String startingFrame, 
            int startingFrameNumber, int preStartingDirection) {
        currentX = startingX;
        currentY = startingY;
        currentFrame = startingFrame;
        currentFrameNumber = startingFrameNumber;
        lastDirection = preStartingDirection;
        
        entitiesPanel = GameManager.entitiesPanel;
        moveCounter = 0;
        boundingBox = new BoundingBox();
    }
    
    //-----------------------------------------------------------------------------------------------------------------------------------
                                                                                //TOOL METHODS
    
    @Override
    public void run() {                                                         //OVERRIDE OF RUN METHOD FOR PLAYER MANAGER THREAD
        while(GameManager.isGameRunning) {
            //manage the keys currently pressed
            manageKeys();
            entitiesPanel.repaintPlayer();
            
            try {
                Thread.sleep(MAIN_SLEEP_TIME);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    protected void manageKeys() {                                               //MANAGE KEYS METHOD
        HashSet<Integer> currentKeys = KeyboardController.getActiveKeys();
        
        if(currentKeys.contains(KeyEvent.VK_RIGHT)) {
            playerManager.sprint(KeyEvent.VK_RIGHT);
        } else if (currentKeys.contains(KeyEvent.VK_LEFT)) {
            playerManager.sprint(KeyEvent.VK_LEFT);
        } else if (currentKeys.isEmpty()) {
            playerManager.standStill();
        }
    }
    
    //-----------------------------------------------------------------------------------------------------------------------------------
                                                                                //PLAYER MOVEMENT METHODS
    
    public void sprint(int direction) {                                         //MOVE METHOD
        switch(direction) {
            case KeyEvent.VK_LEFT:
                currentX = currentX - DISPLACEMENT;                             //update the character's position
                boundingBox.setLocation(currentX, currentY);                    //update the characters bounding box position
                setFrameNumber();                                               //change the current frame in animation
                currentFrame = SPRINT_LEFT[currentFrameNumber];                    //change the current frame in animation
                lastDirection = KeyEvent.VK_LEFT;                               //set the left direction as the last one
                break;
            case KeyEvent.VK_RIGHT:
                currentX = currentX + DISPLACEMENT;                             //update the character's position
                boundingBox.setLocation(currentX, currentY);                    //update the characters bounding box position
                setFrameNumber();                                               //change the current frame in animation
                currentFrame = SPRINT_RIGHT[currentFrameNumber];                   //change the current frame in animation
                lastDirection = KeyEvent.VK_RIGHT;                              //set the left direction as the last one
                break;
            default:
                break;
        }
        moveCounter++;
    }
    
    public void standStill() {                                                  //STOP METHOD
        switch(lastDirection) {                                                 //changes animation depending where he was running to
            case KeyEvent.VK_LEFT:
                currentFrame = STAND_STILL_LEFT;
                break;
            case KeyEvent.VK_RIGHT:
                currentFrame = STAND_STILL_RIGHT;
                break;
            default:
                break;
        }
    }
    
    //sets the current frame when the player is moving - we have a total of 5 frames for 
    //each run direction. The variable moveCounter is incremented each time the gameManager
    //calls the move function on the Boy. So according to moveCounter we can choose the current
    //frame. The frame changes every MOVE_COUNTER_THRESH increments of the moveCounter variable.
    //In this case MOVE_COUNTER_THRESH is set to 5. The use of "6" instead of a variable is temporary
    //because I still don't know how many frames will be used in the final animation
    private void setFrameNumber() {
        currentFrameNumber = moveCounter/MOVE_COUNTER_THRESH;
        currentFrameNumber %= TOTAL_FRAMES_IN_SPRINT_ANIMATION;
        
        if(moveCounter > MOVE_COUNTER_THRESH * TOTAL_FRAMES_IN_SPRINT_ANIMATION) {
            moveCounter = 0;
        }
    }
}

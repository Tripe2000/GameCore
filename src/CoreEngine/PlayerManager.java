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
    protected int frameSet;                                                  //The frame currently in use
    protected int frameNumber;                                                  //The frame number currently in use (for the array of the animation)
    protected int lastDirection;                                                //Last direction player was facing
    protected int moveCounter;                                                  //Counter used to change animation frames
    protected BoundingBox boundingBox;                                          //Players bounding box
    protected boolean jumping = false;
    protected int jumpCount = 0;
    
    public PlayerManager() {
        entitiesPanel = GameManager.entitiesPanel;
        currentX = 0;
        currentY = 450;
        frameSet = IDLE_RIGHT_SET;
        frameNumber = 0;
        lastDirection = KeyEvent.VK_RIGHT;
        moveCounter = 0;
        boundingBox = new BoundingBox();
    }
    
    public PlayerManager(int startingX, int startingY, String startingFrame, 
            int startingFrameNumber, int preStartingDirection) {
        currentX = startingX;
        currentY = startingY;
        frameSet = IDLE_RIGHT_SET;
        frameNumber = 0;
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
            checkJumpState();
            //manage the keys currently pressed
            manageKeys();
            entitiesPanel.repaintPlayer(currentX, currentY, frameSet, frameNumber);
            
            try {
                Thread.sleep(MAIN_SLEEP_TIME);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    protected void manageKeys() {                                               //MANAGE KEYS METHOD
        HashSet<Integer> currentKeys = KeyboardController.getActiveKeys();
        
        if(currentKeys.contains(KeyEvent.VK_SPACE)) {
            if(!getJumping()) {
                jump();
            }
        }
        
        if(currentKeys.contains(KeyEvent.VK_RIGHT)) {
            sprint(KeyEvent.VK_RIGHT);
        } else if (currentKeys.contains(KeyEvent.VK_LEFT)) {
            sprint(KeyEvent.VK_LEFT);
        } else if (currentKeys.isEmpty() && !getJumping()) {
            standStill();
        }
    }
    
    //-----------------------------------------------------------------------------------------------------------------------------------
                                                                                //PLAYER MOVEMENT METHODS
    
    protected void sprint(int direction) {                                         //MOVE METHOD
        switch(direction) {
            case KeyEvent.VK_LEFT:
                currentX = currentX - DISPLACEMENT;                             //update the character's position
                boundingBox.setLocation(currentX, currentY);                    //update the characters bounding box position
                setFrameNumber();                                               //change the current frame in animation
                frameSet = SPRINT_LEFT_SET;                                     //change the current frame in animation
                lastDirection = KeyEvent.VK_LEFT;                               //set the left direction as the last one
                break;
            case KeyEvent.VK_RIGHT:
                currentX = currentX + DISPLACEMENT;                             //update the character's position
                boundingBox.setLocation(currentX, currentY);                    //update the characters bounding box position
                setFrameNumber();                                               //change the current frame in animation
                frameSet = SPRINT_RIGHT_SET;                                //change the current frame in animation
                lastDirection = KeyEvent.VK_RIGHT;                              //set the left direction as the last one
                break;
            default:
                break;
        }
        moveCounter++;
    }
    
    protected void standStill() {                                                  //STOP METHOD
        frameNumber = 0;
        switch(lastDirection) {                                                 //changes animation depending where he was running to
            case KeyEvent.VK_LEFT:
                frameSet = IDLE_LEFT_SET;
                break;
            case KeyEvent.VK_RIGHT:
                frameSet = IDLE_RIGHT_SET;
                break;
            default:
                break;
        }
    }
    
    protected void jump() {
        jumping = true;
        
        jumpCount = 0;
        
        frameNumber = JUMPING_FRAME;
        
        if(lastDirection == KeyEvent.VK_RIGHT) {
            frameSet = SPRINT_RIGHT_SET;
        } else {
            frameSet = SPRINT_LEFT_SET;
        }
    }
    
    protected void checkJumpState() {
        if(jumping) {
            if(jumpCount < JUMP_COUNTER_THRESH) {
                if(lastDirection == KeyEvent.VK_RIGHT) {
                    currentX += DISPLACEMENT / 2;
                } else {
                    currentX -= DISPLACEMENT / 2;
                }
                currentY -= DISPLACEMENT;
                boundingBox.setLocation(currentX, currentY);
            } else {
                if(lastDirection == KeyEvent.VK_RIGHT) {
                    currentX += DISPLACEMENT / 2;
                } else {
                    currentX -= DISPLACEMENT / 2;
                }
                currentY += DISPLACEMENT;
                boundingBox.setLocation(currentX, currentY);
            }
            jumpCount++;
            
            if(jumpCount >= JUMP_COUNTER_THRESH * 2) {
                jumping = false;
                jumpCount = 0;
            }
        }
    }
    
    protected boolean getJumping() {
        return jumping;
    }
    
    //sets the current frame when the player is moving - we have a total of 5 frames for 
    //each run direction. The variable moveCounter is incremented each time the gameManager
    //calls the move function on the Boy. So according to moveCounter we can choose the current
    //frame. The frame changes every MOVE_COUNTER_THRESH increments of the moveCounter variable.
    //In this case MOVE_COUNTER_THRESH is set to 5. The use of "6" instead of a variable is temporary
    //because I still don't know how many frames will be used in the final animation
    private void setFrameNumber() {
        frameNumber = moveCounter/MOVE_COUNTER_THRESH;
        frameNumber %= TOTAL_FRAMES_IN_SPRINT_ANIMATION;
        
        if(moveCounter > MOVE_COUNTER_THRESH * TOTAL_FRAMES_IN_SPRINT_ANIMATION) {
            moveCounter = 0;
        }
    }
    
    private void check() {
        System.out.println("jumping" + jumping);
        //System.out.println("jumpCount" + jumpCount);
        //System.out.println("X" + currentX);
        //System.out.println("Y" + currentY);
    }
}

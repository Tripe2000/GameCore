package CoreEngine;

import java.awt.event.KeyEvent;

public class Player {
    
    public void move(int direction) {
        switch(direction) {
            case KeyEvent.VK_LEFT:
                //update the character's position
                currentX = currentX - DISPLACEMENT;
                
                //update the characters bounding box position
                boundingBox.setLocation(currentX, currentY);
                
                //change the current frame in animation
                setFrameNumber();
                currentFrame = run_L[currentFrameNumber];
                
                //set the left direction as the last one
                last_direction = KeyEvent.VK_LEFT;
                break;
                
            case KeyEvent.VK_RIGHT:
                //update the character's position
                currentX = currentX + DISPLACEMENT;
                
                //update the characters bounding box position
                boundingBox.setLocation(currentX, currentY);
                
                //change the current frame in animation
                setFrameNumber();
                currentFrame = run_R[currentFrameNumber];
                
                //set the left direction as the last one
                last_direction = KeyEvent.VK_RIGHT;
                break;
                
            default:
                break;
        }
        
        moveCounter++;
    }
    
    //sets the current frame when the boy is moving - we have a total of 5 frames for 
    //each run direction. The variable moveCounter is incremented each time the gameManager
    //calls the move function on the Boy. So according to moveCounter we can choose the current
    //frame. The frame changes every MOVE_COUNTER_THRESH increments of the moveCounter variable.
    //In this case MOVE_COUNTER_THRESH is set to 5. The use of "6" instead of a variable is temporary
    //because I still don't know how many frames will be used in the final animation
    private void setFrameNumber() {
        currentFrameNumber = moveCounter/MOVE_COUNTER_THRESH;
        currentFrameNumber %= 6;
        
        if(moveCounter > MOVE_COUNTER_THRESH * 6) {
            moveCounter = 0;
        }
    }
}

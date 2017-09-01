/**
 *Date: 21/08/2017
 *Author: Whaleballs
 *File Name: Player.java
 *Project Name: GameCore
 */

package Entity;

import Entity.Item.Sword;
import TileMap.TileMap;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import static Main.MainInterface.PLAYER_SPRITESHEET;

public class Player extends WorldObject {
    
    private Sword sword;
    
    //player stuff
    private int health;
    private int maxHealth;
    private int fire;
    private int maxFire;
    private boolean dead;
    private boolean flinching;
    private long flinchTimer;
    
    //laser
    private boolean laserShooting;
    private boolean finishedChargingLaser;
    private int laserDamage;
    private LaserBeam laserBeam;
    
    //fireball
    private boolean firing;
    private int fireCost;
    private int fireBallDamage;
    private ArrayList<FireBall> fireBalls;
    
    //use tool
    private boolean usingTool;
    private int scratchDamage;
    private int scratchRange;
    
    //gliding
    private boolean gliding;
    
    //animation stuff
    private ArrayList<BufferedImage[]> sprites;
    private final int[] numberFrames = {                                        //keeps track of how many frames each animation has
        4, 6, 1, 1, 1, 2, 5, 4
    };
    
    //animation actions
    private static final int IDLE = 0;
    private static final int WALKING = 1;
    private static final int JUMPING = 2;
    private static final int FALLING = 3;
    private static final int GLIDING = 4;
    private static final int RANGE_ATTACK = 5;
    private static final int USE_TOOL = 6;
    private static final int LASER_ATTACK = 7;
    
    public Player(TileMap tm) {
        super(tm);
        
        sword = new Sword(tm);
        
        width = 30;
        height = 30;
        
        cWidth = 20;
        cHeight = 20;
        
        moveSpeed = 0.3;
        maxSpeed = 1.6;
        stopSpeed = 0.4;
        fallSpeed = 0.15;
        maxFallSpeed = 4.0;
        jumpStart = -4.8;
        stopJumpSpeed = 0.3;
        
        facingRight = true;
        
        health = maxHealth = 5;
        fire = maxFire = 2500;
        
        laserDamage = 10;
        laserBeam = new LaserBeam(tileMap);
        
        fireCost = 200;
        fireBallDamage = 5;
        fireBalls = new ArrayList<FireBall>();
        
        scratchDamage = 8;
        scratchRange = 40;
        
        //load sprites
        try {
            
            BufferedImage spriteSheet = ImageIO.read(getClass().getResourceAsStream(PLAYER_SPRITESHEET)
            );
            
            sprites = new ArrayList<BufferedImage[]>();
            
            for(int i = 0; i < numberFrames.length; i++) {
                BufferedImage[] bi = new BufferedImage[numberFrames[i]];
                
                for(int j = 0; j < numberFrames[i]; j++) {
                    bi[j] = spriteSheet.getSubimage(
                        j * width,
                        i * height, 
                        width,
                        height
                    );
                }
                
                sprites.add(bi);
            }
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        animation = new Animation();
        currentAction = IDLE;
        animation.setFrames(sprites.get(IDLE));
        animation.setDelay(400);
    }
    
    public int getHealth() { return health; }
    public int getMaxHealth() { return maxHealth; }
    public int getFire() { return fire; }
    public int getMaxFire() { return maxFire; }
    
    public void setLaser() { 
        if(jumping || falling) { laserShooting = false; }
        else { laserShooting = true; finishedChargingLaser = false; }
    }
    public void setFiring() { firing = true; }
    public void setUsingTool() { usingTool = true; }
    public void setGliding(boolean b) { gliding = b; }
    
    private void getNextPosition() {
        
        //movement
        if(left) {
            dx -= moveSpeed;
            if(dx < -maxSpeed) { dx = -maxSpeed; }
        } else if(right) {
            dx += moveSpeed;
            if(dx > maxSpeed) { dx = maxSpeed; }
        } else {
            if(dx > 0) {
                dx -= stopSpeed;
                if(dx < 0) {
                    dx = 0;
                }
            } else if(dx < 0) {
                dx += stopSpeed;
                if(dx > 0) {
                    dx = 0;
                }
            }
        }
        
        //cannot move while attacking unless in air
        if((currentAction == LASER_ATTACK) && !(jumping || falling)) {
            dx = 0;
        }
        
        //jumping
        if(jumping && !falling) {
            dy = jumpStart;
            falling = true;
        }
        
        //falling
        if(falling) {
            if(dy > 0 && gliding) { dy += fallSpeed * 0.1; }
            else { dy += fallSpeed; }
            
            if(dy > 0) { jumping = false; }
            if(dy < 0 && !jumping) { dy += stopJumpSpeed; }
            
            if(dy > maxFallSpeed) { dy = maxFallSpeed; }
        }
        
    }
    
    public void update() {        
        ///update position
        getNextPosition();
        checkTileMapCollision();
        setPosition(xTemp, yTemp);
        
        //check attack has stoppped
        if(currentAction == USE_TOOL) {
            if(animation.hasPlayedOnce()) { usingTool = false; }
        }
        if(currentAction == RANGE_ATTACK) {
            if(animation.hasPlayedOnce()) { firing = false; }
        }
        if(currentAction == LASER_ATTACK) {
            if(laserBeam.shouldRemove()) { laserShooting = false; }
        }
        
        //laser attack
        if(laserShooting && currentAction != LASER_ATTACK) {
            laserBeam.setPosition(x, y);
            laserBeam.shootLaser(facingRight);
        }
        
        //update laser attack
        if(currentAction == LASER_ATTACK && animation.hasPlayedOnce()) { finishedChargingLaser = true; }
        if(finishedChargingLaser) { laserBeam.update(); }
        
        //fireball attack
        fire += 1;
        if(fire > maxFire) fire = maxFire;
        if(firing && currentAction != RANGE_ATTACK) {
            if(fire > fireCost) {
                fire -= fireCost;
                FireBall fb = new FireBall(tileMap, facingRight);
                fb.setPosition(x, y);
                fireBalls.add(fb);
            }
        }
        
        //update fireballs
        for(int i = 0; i < fireBalls.size(); i++) {
            fireBalls.get(i).update();
            if(fireBalls.get(i).shouldRemove()) {
                fireBalls.remove(i);
                i--;
            }
        }
        
        //set animation
        if(usingTool) {
            if(currentAction != USE_TOOL) {
                currentAction = USE_TOOL;
                animation.setFrames(sprites.get(USE_TOOL));
                animation.setDelay(50);
                width = 30;
            }
        } else if(laserShooting) {
            if(currentAction != LASER_ATTACK) {
                currentAction = LASER_ATTACK;
                animation.setFrames(sprites.get(LASER_ATTACK));
                animation.setDelay(100);
                width = 30;
            }
        } else if(firing) {
            if(currentAction != RANGE_ATTACK) {
                currentAction = RANGE_ATTACK;
                animation.setFrames(sprites.get(RANGE_ATTACK));
                animation.setDelay(100);
                width = 30;
            }
        } else if(dy > 0) {
            if(gliding) {
                if(currentAction != GLIDING) {
                    currentAction = GLIDING;
                    animation.setFrames(sprites.get(GLIDING));
                    animation.setDelay(-1);
                    width = 30;
                }
            } else if(currentAction != FALLING) {
                currentAction = FALLING;
                animation.setFrames(sprites.get(FALLING));
                animation.setDelay(-1);
                width = 30;
            }
        } else if(dy < 0) {
            if(currentAction != JUMPING) {
                currentAction = JUMPING;
                animation.setFrames(sprites.get(JUMPING));
                animation.setDelay(-1);
                width = 30;
            }
        } else if(left || right) {
            if(currentAction != WALKING) {
                currentAction = WALKING;
                animation.setFrames(sprites.get(WALKING));
                animation.setDelay(40);
                width = 30;
            }
        } else {
            if(currentAction != IDLE) {
                currentAction = IDLE;
                animation.setFrames(sprites.get(IDLE));
                animation.setDelay(400);
                width = 30;
            }
        }
        
        animation.update();
        
        //set direction
        if(currentAction != USE_TOOL && currentAction != RANGE_ATTACK && currentAction != LASER_ATTACK) {
            if(right) facingRight = true;
            if(left) facingRight = false;
        }
        
        //update sword
        sword.update(currentAction);
    }
    
    public void draw(Graphics2D g) {
        setMapPosition();
        
        if(currentAction == USE_TOOL && animation.hasPlayedOnce()) { animation.setFrame(sprites.get(USE_TOOL).length - 1); }
        if(currentAction == LASER_ATTACK && animation.hasPlayedOnce()) { animation.setFrame(sprites.get(IDLE).length - 1); }
        
        //draw laser
        if(laserShooting && finishedChargingLaser) { laserBeam.draw(g); }
        
        //draw fireballs
        for(int i = 0; i < fireBalls.size(); i++) {
            fireBalls.get(i).draw(g);
        }
        
        //draw player
        if(flinching) {
            long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
            if(elapsed / 100 % 2 == 0) {
                return;
            }
        }
        
        super.draw(g);
        
        //draw sword
        sword.setPosition(x, y);
        sword.draw(g, facingRight);
    }
}

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.Image;

public class Player extends drawableObject{
	
	private double xVelocity;
	private double yVelocity;
    private int lives;
    private int healthPips;
    private int shotSpeed;
    private int shotSize;
    private int playerSpeed;
    private long invulnLength;
    private boolean invincible;
    private Rectangle playerHitbox;
	private Animation playerAnimation;
	private ShootingStrategy shotType;
	AnimationHandler animationHandler = new AnimationHandler();
	KeyboardInput input;

    
    public Player() throws SlickException
    {
        xVelocity = 0;
        yVelocity = 0;
        lives = 3;
        healthPips = 3;
        shotSpeed = 5;
        playerSpeed = 1;
        invincible = false;
        invulnLength = 1000;
        input = new KeyboardInput();
        playerAnimation = animationHandler.getShipAnimation();
    		playerHitbox = new Rectangle(this.getXPos(),this.getYPos(), this.getWidth(), this.getHeight());
    		shotType = new singleShot();
    	
    }

    //Getter Methods
    public boolean isInvincible() 
    {
		return invincible;
	}
    
    
    public Rectangle getHitBox()
    {
    		return playerHitbox;
    }
    
    public int getLives()
    {
    		return lives;
    }
    
    public int getHealthPips()
    {
    		return healthPips;
    }
    
    public int getEmptyLives()
    {
    		return 3 - lives;
    }
    
    public long getInvulnLength()
    {
    		return invulnLength;
    }
    
    public int getShotSpeed()
    {
    		return shotSpeed;
    }
    
    public int getShotSize()
    {
    		return shotSize;
    }
    
    
    public int getWidth() 
    {	
    		return playerAnimation.getWidth();
	}
    
    public int getHeight() 
    {	
    		return playerAnimation.getHeight();
	}
    
    public Animation getAnimation()
    {
    		return playerAnimation;
    }
    
    public int getSpeed()
    {
    		return playerSpeed;
    }
    
    public KeyboardInput getInput()
    {
    		return input;
    }
    
    public ShootingStrategy getShotType()
    {
    		return shotType;
    }
    
    //Setter Methods
    public void setInvincible(boolean invuln) 
	{
		invincible = invuln;
	}
    
    public void setLives(int d)
    {
    		if (d < lives && invincible == true);
    		else
    		lives = d;
    }
    
    public void setInvulnLength(Long time)
    {
    		invulnLength = time;
    }
    
    public void setShotSpeed(int myShotSpeed)
    {
    		shotSpeed = myShotSpeed;
    }
    
    public void setShotSize(int myShotSize)
    {
    		shotSize = myShotSize;
    }
    
    public void setShotType(ShootingStrategy type) 
    {
    		shotType = type;
    }
    
    public void setSpeed(int myPlayerSpeed)
    {
    		playerSpeed = myPlayerSpeed;
    }
    
    public void setHealthPips(int myHealthPips)
    {
    		healthPips = myHealthPips;
    }
    
    public void incrementVelocity()
	{
    		if(input.getConstraint() < 20)
    		{
    			input.incrementConstraint();
    		}
	}
    
    public void decrementShotInterval()
    {
    	
    		input.decrementInterval(200);
    }
    

	public void draw(Graphics pen) 
	{
		playerAnimation.draw(this.getXPos(), this.getYPos());
	}
	
	public void move()
    {
    		super.move();
    		playerHitbox.setX(this.getXPos());
    		playerHitbox.setY(this.getYPos());
    		
    }//Moves the block according to the x and y velocity
    
	
	public ArrayList<Projectile> shootUp(ArrayList<Projectile> shots) 
	{
		return shotType.upShots(this);
	}

	public ArrayList<Projectile> shootDown(ArrayList<Projectile> shots) 
	{
		return shotType.downShots(this);
		
	}

	public ArrayList<Projectile> shootLeft(ArrayList<Projectile> shots) 
	{
		return shotType.leftShots(this);
		
	}

	public ArrayList<Projectile> shootRight(ArrayList<Projectile> shots) 
	{
		return shotType.rightShots(this);
		
	}




	


	
    
};
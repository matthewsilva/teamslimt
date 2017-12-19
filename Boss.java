import java.awt.Graphics;
import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

public class Boss extends Player {

	private int xPosition;
    private int yPosition;
	private double xVelocity;
	private double yVelocity;
	private Rectangle bossHitbox;
	private Animation bossAnimation;
	private SpriteSheet bossSheet;
	private BossStrategy phase;
	private ShootingStrategy shotType;
	AnimationHandler animationHandler = new AnimationHandler();
	private int health;
	private int bossTimer;
	private int fireTimer;
	
	
	public Boss() throws SlickException
    {
		xVelocity = 0;
        yVelocity = 0;
        health = 10;
        bossAnimation = animationHandler.getBossAnimation();
        phase = new MoveAndShoot();
        fireTimer = 5;
        bossHitbox = new Rectangle(this.getXPos(),this.getYPos(), this.getWidth(), this.getHeight());
    		//bossHitbox = new Rectangle(this.getXPos(),this.getYPos(), this.getWidth(), this.getHeight());
    	
    }

	public void moveBoss() {
		phase.useMovement(this);
	}
	
	public void move()
    {
    		super.move();
    		xPosition += xVelocity;
    		yPosition += yVelocity;
    		bossHitbox.setX(this.getXPos());
    		bossHitbox.setY(this.getYPos());
    		
    }
	
	public void shootBoss(ArrayList<Projectile> shots) {
		phase.useWeapon(this, shots);
	}
	
	public void checkBossPhase() {
		phase.changeStrategy(this);
	}
	
	public void setStrategy(BossStrategy newPhase) {
		phase = newPhase;
	}
	
	// Used for the constructors in order to keep the values in the specified bounds
    public double constrain(double num, double lowBound, double highBound)
    {
		if(num < lowBound)
		{
	            num = lowBound;
		}
	    else if(num > highBound)
	    {
	    		num = highBound;
	    }
		
	   return num;
    }
    
    
    //Getter methods
    public int getXPos()
    {
        return xPosition;
    }//returns xPositon
	
    
    public int getYPos()
    {
        return yPosition;
    }//Returns the yPositon
	
    
    public double getXVel()
    {
        return xVelocity;
    }//Returns the xVelocity
	
    
    public double getYVel()
    {
        return yVelocity;
    }//Returns the yVelocity;
	
    
    public int getWidth() 
    {	
    		return 20; //bossAnimation.getWidth();
	}
       

    public int getHeight() 
    {	
    		return 20;//bossAnimation.getHeight();
	}
    
    public Animation getAnimation()
    {
    		return bossAnimation;
    }
    
    public Rectangle getHitBox()
    {
    		return bossHitbox;
    }
    
    public void setXVelocity(double xVel)
    {
        xVelocity = xVel;
    }
    
    public void setYVelocity(double yVel)
    {
        yVelocity = yVel;
    }
    
   
    public void moveTo(int absX, int absY)
    {
    		xPosition = absX;
        yPosition = absY;
    }//Moves the block to a certain position
	
    
    public void moveBy(int dX, int dY)
    {
    	bossHitbox.setX(this.getXPos());
		bossHitbox.setY(this.getYPos());
    		xPosition += dX;
    		yPosition += dY;
    }// Moves the block by the change in x and y
    
	
   

	public void draw(Graphics pen) 
	{
		bossAnimation.draw(this.getXPos(), this.getYPos());
	}

	
	/*
    public int getWidth() 
    {	
    		return shotAnimation.getWidth();
	}
    
    public int getHeight() 
    {	
    		return shotAnimation.getHeight();
	}
    
    public Animation getAnimation()
    {
    		return shotAnimation;
    }
	*/
    boolean checkWalls()
    {
        int panelRight = 800; // gets max right X
        int panelBottom = 800;  // gets max bottom Y

        	int rightX = this.getXPos() + this.getWidth();
        	int leftX = this.getXPos();
        	int topY = this.getYPos();
        	int bottomY = this.getYPos() + this.getHeight();
        	
        	// Check if the object hits the RIGHT of the panel
        	if(rightX > panelRight)
        	{
        		return true;
        		
        	// Check if the object hits the LEFT of the panel
        	}else if(leftX < 0)
        	{
        		return true;
        		
        	// Check if the object hits the TOP of the panel
        	}else if(topY < 0 )
        	{
        		return true;
        		
        	// Check if the object hits the BOTTOM of the panel
        	}else if(bottomY > panelBottom)
        	{
        		return true;
        	}
    return false;
}
}

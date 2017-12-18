import java.awt.Graphics;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Projectile extends drawableObject
{
	
	private int xPosition;
    private int yPosition;
	private double xVelocity;
	private double yVelocity;
	private Animation shotAnimation;
	private SpriteSheet shotSheet;
	private Rectangle shotHitbox;

	public Projectile(Player player) throws SlickException 
	{
		xPosition = player.getXPos();
		yPosition = player.getYPos();
		xVelocity = player.getShotSpeed();
		yVelocity = player.getShotSpeed();
		shotSheet = new SpriteSheet("data/projectile.png", 16, 16);
		shotAnimation = new Animation(shotSheet, 100);
		shotHitbox = new Rectangle(this.getXPos(),this.getYPos(), this.getWidth(), this.getHeight());
	}
	
	public Projectile(int myxVelocity, int myyVelocity) throws SlickException 
	{
		xVelocity = myxVelocity;
		yVelocity = myyVelocity;
		shotHitbox = new Rectangle(this.getXPos(),this.getYPos(), this.getWidth(), this.getHeight());
	}
	
	public Projectile(int x, int y, double dx, double dy) throws SlickException {
		// IMPORTANT KEEP JUST IN CASE
		//super(x, y, dx, dy);
		xPosition = x;
        yPosition = y;
        xVelocity = constrain(dy, -10, 10);
        yVelocity = constrain(dx, -10, 10);
		shotSheet = new SpriteSheet("data/ship.png", 32, 32);
		shotAnimation = new Animation(shotSheet, 100);
		shotHitbox = new Rectangle(this.getXPos(),this.getYPos(), this.getWidth(), this.getHeight());
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
    
    public Rectangle getHitBox()
    {
    		return shotHitbox;
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
    	shotHitbox.setX(this.getXPos());
		shotHitbox.setY(this.getYPos());
    		xPosition += dX;
    		yPosition += dY;
    }// Moves the block by the change in x and y
    
	
    public void move()
    {
    		xPosition += xVelocity;
    		yPosition += yVelocity;
    		shotHitbox.setX(this.getXPos());
    		shotHitbox.setY(this.getYPos());
    }//Moves the block according to the x and y velocity
    


	public void draw(Graphics pen) 
	{
		shotAnimation.draw(this.getXPos(), this.getYPos());
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
        	
    }//end checkPosition
    /*
    public void move()
    {
    		super.move();
    		shotHitbox.setX(this.getXPos());
    		shotHitbox.setY(this.getYPos());
    		
    }//Moves the block according to the x and y velocity
    
    */
	
	
	
	
	
};
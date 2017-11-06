import java.awt.Graphics;
import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

public class drawableObject {
	
	private int xPosition;
    private int yPosition;
	private double xVelocity;
	private double yVelocity;
	private Animation myAnimation;
	private SpriteSheet mySpriteSheet;
	private Rectangle objectHitbox;


    
    public drawableObject() throws SlickException
    {
    		Random generator = new Random();
        xPosition = generator.nextInt(100);
        yPosition = generator.nextInt(100);
        xVelocity = Math.random()*-10;
        yVelocity = Math.random()*-10; 
        
    }

	
	public drawableObject(int myXPos, int myYPos, double myXVel, double myYVel) throws SlickException
	{
		xPosition = myXPos;
        yPosition = myYPos;
        xVelocity = constrain(myYVel, -10, 10);
        yVelocity = constrain(myXVel, -10, 10);
        
	}
	
	public drawableObject(int myxVelocity, int myyVelocity) throws SlickException 
	{
		xVelocity = myxVelocity;
		yVelocity = myyVelocity;
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
    		return myAnimation.getWidth();
	}
    
    public int getHeight() 
    {	
    		return myAnimation.getHeight();
	}
    
    public Animation getAnimation()
    {
    		return myAnimation;
    }
    
    public Rectangle getHitBox()
    {
    		return objectHitbox;
    }
    
    public void setXVelocity(double xVel)
    {
        xVelocity = xVel;
    }
    
    public void setYVelocity(double yVel)
    {
        yVelocity = yVel;
    }
    
    public void setVelocity(double xVel, double yVel)
	{
		xVelocity = xVel;
		yVelocity = yVel;
	}
      
	
    public void moveTo(int xPos, int yPos)
    {
    		xPosition = xPos;
        yPosition = yPos;
    }//Moves the block to a certain position
	
    
    public void moveBy(int dX, int dY)
    {
    		xPosition += dX;
    		yPosition += dY;
    }// Moves the block by the change in x and y
    
	
    public void move()
    {
    		xPosition += xVelocity;
    		yPosition += yVelocity;
    }//Moves the block according to the x and y velocity
    
    
    void checkPosition()
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
        		this.setVelocity(-.25, this.getYVel());
        		
        	// Check if the object hits the LEFT of the panel
        	}else if(leftX < 0)
        	{
        		this.setVelocity(.25, this.getYVel());
        		
        	// Check if the object hits the TOP of the panel
        	}else if(topY < 0 )
        	{
        		this.setVelocity(this.getXVel(), .25);
        		
        	// Check if the object hits the BOTTOM of the panel
        	}else if(bottomY > panelBottom)
        	{
        		this.setVelocity(this.getXVel(), -.25);
        	}
        	
    }//end checkPosition
    
    
    public boolean intersects(drawableObject object)
    {
    		return (this.getHitBox().intersects(object.getHitBox()));
    }


	public void draw(Graphics pen) 
	{
		myAnimation.draw(this.getXPos(), this.getYPos());
	}



	
    
};
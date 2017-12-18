import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;


public class Alien extends drawableObject 
{
	private int xPosition;
    private int yPosition;
    private int type;
	private double xVelocity;
	private double yVelocity;
	private Animation alienAnimation;
	private SpriteSheet alienSheet;
	private Rectangle alienHitbox;
	AnimationHandler animationHandler = new AnimationHandler();


    
    public Alien() throws SlickException
    {
    		Random generator = new Random();
        xPosition = generator.nextInt(800);
        yPosition = generator.nextInt(800);
        xVelocity = generator.nextInt(5);
        yVelocity = generator.nextInt(5); 
        type = generator.nextInt(4);
        alienAnimation = animationHandler.getRandomAlienAnimation(type);
		alienHitbox = new Rectangle(this.getXPos(),this.getYPos(), this.getWidth(), this.getHeight());

    }
    public Alien(int myType) throws SlickException
    {
    		Random generator = new Random();
        xPosition = generator.nextInt(800);
        yPosition = generator.nextInt(800);
        xVelocity = generator.nextInt(5);
        yVelocity = generator.nextInt(5); 
        type = generator.nextInt(4);
        alienAnimation = animationHandler.getRandomAlienAnimation(type);
		alienHitbox = new Rectangle(this.getXPos(),this.getYPos(), this.getWidth(), this.getHeight());

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
    		return alienAnimation.getWidth();
	}
       

    public int getHeight() 
    {	
    		return alienAnimation.getHeight();
	}
    
    public int getType() 
    {	
    		return type;
	}
    
    public Animation getAnimation()
    {
    		return alienAnimation;
    }
    
    public Rectangle getHitBox()
    {
    		return alienHitbox;
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
    		alienHitbox.setX(this.getXPos());
		alienHitbox.setY(this.getYPos());
    		xPosition += dX;
    		yPosition += dY;
    }// Moves the block by the change in x and y
    
	
    public void move()
    {
    		xPosition += xVelocity;
    		yPosition += yVelocity;
    		alienHitbox.setX(this.getXPos());
    		alienHitbox.setY(this.getYPos());
    }//Moves the block according to the x and y velocity
    
    public void setAlienVelocity(double xVel, double yVel)
	{
		xVelocity = xVel;
		yVelocity = yVel;
	}
    
    void checkAlienPosition()
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
        		this.setAlienVelocity(-4.0, this.getYVel());
        		
        	// Check if the object hits the LEFT of the panel
        	}else if(leftX < 0)
        	{
        		this.setAlienVelocity(4.0, this.getYVel());
        		
        	// Check if the object hits the TOP of the panel
        	}else if(topY < 0 )
        	{
        		this.setAlienVelocity(this.getXVel(), 4.0);
        		
        	// Check if the object hits the BOTTOM of the panel
        	}else if(bottomY > panelBottom)
        	{
        		this.setAlienVelocity(this.getXVel(), -4.0);
        	}
        	
    }//end checkPosition
    
    


	public void draw(Graphics pen) 
	{
		alienAnimation.draw(this.getXPos(), this.getYPos());
	}



	
    
};
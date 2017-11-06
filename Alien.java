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
	private double xVelocity;
	private double yVelocity;
	private Animation alienAnimation;
	private SpriteSheet alienSheet;
	private Rectangle alienHitbox;


    
    public Alien() throws SlickException
    {
    		Random generator = new Random();
        xPosition = generator.nextInt(800);
        yPosition = generator.nextInt(800);
        xVelocity = generator.nextInt(5);
        yVelocity = generator.nextInt(5); 
        alienSheet = new SpriteSheet("data/alien_spritesheet.png", 32, 32);
        alienAnimation = new Animation(alienSheet, 100);
		alienHitbox = new Rectangle(this.getXPos(),this.getYPos(), this.getWidth(), this.getHeight());

    }

	
	public Alien(int myXPos, int myYPos, double myXVel, double myYVel) throws SlickException
	{
		xPosition = myXPos;
        yPosition = myYPos;
        xVelocity = constrain(myYVel, -10, 10);
        yVelocity = constrain(myXVel, -10, 10);
		alienSheet = new SpriteSheet("data/alien_spritesheet.png", 32, 32);
		alienAnimation = new Animation(alienSheet, 100);
		
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
    		xPosition += dX;
    		yPosition += dY;
    }// Moves the block by the change in x and y
    
	
    public void move()
    {
    		xPosition += xVelocity;
    		yPosition += yVelocity;
    		alienHitbox = new Rectangle(this.getXPos(),this.getYPos(), this.getWidth(), this.getHeight());	
    }//Moves the block according to the x and y velocity
    
    


	public void draw(Graphics pen) 
	{
		alienAnimation.draw(this.getXPos(), this.getYPos());
	}



	
    
};
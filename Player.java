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
    private int shotSpeed;
    private int playerSpeed;
    private boolean invincible;
    private Rectangle playerHitbox;
	private Animation playerAnimation;
	private SpriteSheet playerSheet;


    
    public Player() throws SlickException
    {
        xVelocity = 0;
        yVelocity = 0;
        lives = 3;
        shotSpeed = 5;
        playerSpeed = 1;
        invincible = false;
        playerSheet = new SpriteSheet("data/ship.png", 72, 72);
        playerAnimation = new Animation(playerSheet, 200);
    		playerHitbox = new Rectangle(this.getXPos(),this.getYPos(), this.getWidth(), this.getHeight());
    		
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
    
    public int getShotSpeed()
    {
    		return shotSpeed;
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
    
    //Setter Methods
    public void setInvincible(boolean invuln) 
	{
		invincible = invuln;
	}
    
    public void setLives(int myLives)
    {
    		lives = myLives;
    }
    
    public void setShotSpeed(int myShotSpeed)
    {
    		shotSpeed = myShotSpeed;
    }
    
    public void setSpeed(int myPlayerSpeed)
    {
    		playerSpeed = myPlayerSpeed;
    }
    

	public void draw(Graphics pen) 
	{
		playerAnimation.draw(this.getXPos(), this.getYPos());
	}

	public void shootUp(ArrayList<Projectile> shots) 
	{
		
	}

	public void shootDown(ArrayList<drawableObject> shots) 
	{
		// TODO Auto-generated method stub
		
	}

	public void shootLeft(ArrayList<drawableObject> shots) 
	{
		// TODO Auto-generated method stub
		
	}

	public void shootRight(ArrayList<drawableObject> shots) 
	{
		// TODO Auto-generated method stub
		
	}

	public String getlivesString() 
	{
		String str = "";
		for (int i = 0; i < this.getLives(); i++)
			str = str + " I ";
		
		return str;
	}




	


	
    
};
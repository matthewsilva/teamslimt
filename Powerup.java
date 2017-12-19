import java.awt.Shape;
import java.util.Random;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;


public class Powerup extends drawableObject
{
	
	private int xPosition;
	private int yPosition;
	private int type;
	private Animation animation;
	private Rectangle powerupHitbox;
	AnimationHandler animationHandler = new AnimationHandler();
	

	public Powerup() throws SlickException 
	{
		Random generator = new Random();
        xPosition = generator.nextInt(800);
        yPosition = generator.nextInt(800); 
        type = generator.nextInt(6);
        animation = animationHandler.getRandomPowerupAnimation(type);
        powerupHitbox = new Rectangle(this.getXPos(), this.getYPos(), this.getWidth(), this.getHeight());
	}
	
	public Powerup(int myType) throws SlickException 
	{
		Random generator = new Random();
        xPosition = generator.nextInt(800);
        yPosition = generator.nextInt(800);
        type = myType;
        animation = animationHandler.getRandomPowerupAnimation(type);
        powerupHitbox = new Rectangle(this.getXPos(), this.getYPos(), this.getWidth(), this.getHeight());

	}
	
	
	public void powerupFunction(Player myPlayer)
	{
		switch(type) {
		case 0: //Health
				myPlayer.setLives(myPlayer.getLives() + 1);
				break;
		case 1: //Healthup
				myPlayer.setHealthPips(myPlayer.getHealthPips() + 1);
				myPlayer.setLives(myPlayer.getLives() + 1);
				break;
		case 2: //Double shot
				myPlayer.setShotType(new DoubleShot());
				break;
		case 3: //Invincibility
				myPlayer.setInvincible(true);
				myPlayer.setInvulnLength((long) 5000);
				break;
		case 4: //Speedup
				myPlayer.incrementVelocity();
				break;
		case 5: //ShotSpeedUp
				myPlayer.decrementShotInterval();
				break;
		}
	}
	
	public boolean intersectsPlayer(Player myPlayer)
    {
    		return (this.powerupHitbox.intersects(myPlayer.getHitBox()));
    }
	
	
	public Animation getAnimation()
	{
		return animation;
	}
	
    public int getType() 
    {	
    		return type;
	}
	
	public Rectangle getHitbox()
	{
		return this.powerupHitbox;
	}
	
	
	public int getWidth() 
    {	
    		return animation.getWidth();
	}
    
    public int getHeight() 
    {	
    		return animation.getHeight();
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
    
    public void moveOffScreen()
    {
    		this.powerupHitbox.setX(2000);
    		this.powerupHitbox.setY(2000);
    		this.xPosition = 2000;
    		this.yPosition = 2000;
    }
    
    
	
}
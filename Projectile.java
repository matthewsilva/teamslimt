import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

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
		shotSheet = new SpriteSheet("data/scrap_spritesheet.png", 32, 32);
		shotAnimation = new Animation(shotSheet, 100);
	}
	
	public Projectile(int myxVelocity, int myyVelocity) throws SlickException 
	{
		xVelocity = myxVelocity;
		yVelocity = myyVelocity;
	}
	
	
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
	
	
	
	
	
	
};

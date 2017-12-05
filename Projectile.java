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
		shotSheet = new SpriteSheet("data/ship.png", 32, 32);
		shotAnimation = new Animation(shotSheet, 100);
	}
	
	public Projectile(int myxVelocity, int myyVelocity) throws SlickException 
	{
		xVelocity = myxVelocity;
		yVelocity = myyVelocity;
	}
	
	public Projectile(int x, int y, double dx, double dy) throws SlickException {
		super(x, y, dx, dy);
		shotSheet = new SpriteSheet("data/ship.png", 32, 32);
		shotAnimation = new Animation(shotSheet, 100);
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
    
	
	
	
	
	
};

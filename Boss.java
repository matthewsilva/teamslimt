import java.awt.Graphics;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

public class Boss extends Player {

	private Rectangle bossHitbox;
	private Animation bossAnimation;
	private SpriteSheet bossSheet;
	private ShootingStrategy shotType;
	
	public Boss() throws SlickException
    {
		super();
        bossSheet = new SpriteSheet("data/ship.png", 72, 72);
        bossAnimation = new Animation(bossSheet, 200);
        bossHitbox = new Rectangle(this.getXPos(),this.getYPos(), this.getWidth(), this.getHeight());
    }

	public void draw(Graphics pen) 
	{
		bossAnimation.draw(this.getXPos(), this.getYPos());
	}
	public Rectangle getHitBox()
    {
    		return bossHitbox;
    }
	public void controlBoss(int bossTimer) {
		
	}
}
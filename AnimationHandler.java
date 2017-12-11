import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class AnimationHandler 
{
	
	
	private SpriteSheet mobSheet;
	private SpriteSheet bossSheet;
	private SpriteSheet shipSheet;
	private SpriteSheet powerupSheet;
	private SpriteSheet projectileSheet;
	private SpriteSheet healthSheet;
	private Animation purpleAnimation;
	private Animation redAnimation;
	private Animation greenAnimation;
	private Animation pinkAnimation;
	private Animation bossAnimation;
	private Animation shipAnimation;
	private Animation healthAnimation;
	private Animation healthUpAnimation;
	private Animation doubleshotAnimation;
	private Animation invincibilityAnimation;
	private Animation speedupAnimation;
	private Animation armorAnimation;
	private Animation damageupAnimation;
	private Animation projectileAnimation;
	private Animation shipHealthAnimation;
	private Animation shipEmptyHealthAnimation;
	
	public AnimationHandler() throws SlickException 
	{
				//SpriteSheet Instantiation
				mobSheet = new SpriteSheet("data/mobSpriteSheet.png", 38, 38);
				bossSheet = new SpriteSheet("data/Bosssmol.png", 118, 92); 
				shipSheet = new SpriteSheet("data/shiptrans.png",32, 35);
				powerupSheet = new SpriteSheet("data/powerups.png", 16, 16);
				projectileSheet = new SpriteSheet("data/projectile.png", 16, 16);
				healthSheet = new SpriteSheet("data/health.png", 32, 35);
				
				//Animation instantiation
				purpleAnimation = new Animation();
				redAnimation = new Animation();
				greenAnimation = new Animation();
				pinkAnimation = new Animation();
				bossAnimation = new Animation();	
				shipAnimation = new Animation();
				healthAnimation = new Animation();
				healthUpAnimation = new Animation();
				doubleshotAnimation = new Animation();
				invincibilityAnimation = new Animation();
				speedupAnimation = new Animation();
				armorAnimation = new Animation();
				damageupAnimation = new Animation();
				projectileAnimation = new Animation();
				shipHealthAnimation = new Animation();
				shipEmptyHealthAnimation = new Animation();
				

				//MobAddFrameLoop guy
				for (int i = 0; i < 4; i++)
				{
					purpleAnimation.addFrame(mobSheet.getSprite(0, i), 100);
					redAnimation.addFrame(mobSheet.getSprite(1, i), 100);
					greenAnimation.addFrame(mobSheet.getSprite(2, i), 100);
					pinkAnimation.addFrame(mobSheet.getSprite(3, i), 100);
				}
				
				//PowerupAddFrameLoop guy
				for (int i = 0; i < 6; i++)
				{
					healthAnimation.addFrame(powerupSheet.getSprite(i, 0), 100);
					healthUpAnimation.addFrame(powerupSheet.getSprite(i, 1), 100);
					doubleshotAnimation.addFrame(powerupSheet.getSprite(i, 2), 100);
					invincibilityAnimation.addFrame(powerupSheet.getSprite(i, 3), 100);
					speedupAnimation.addFrame(powerupSheet.getSprite(i, 4), 100);
					armorAnimation.addFrame(powerupSheet.getSprite(i, 5), 100);
					damageupAnimation.addFrame(powerupSheet.getSprite(i, 2), 100);

				}
			
				//BossAddFrameLoop guy	
				for (int i = 0; i < 6; i++)
				{
					bossAnimation.addFrame(bossSheet.getSprite(0, i), 100);
				}
				
				
				//ShipAddFrameLoop guy
				for (int i = 0; i < 4; i++)
				{
					shipAnimation.addFrame(shipSheet.getSprite(0, i), 100);
				}
				
				
	
				projectileAnimation.addFrame(projectileSheet.getSprite(0, 0), 100);
				projectileAnimation.addFrame(projectileSheet.getSprite(0, 1), 100);
				
				shipHealthAnimation.addFrame(healthSheet.getSprite(0,0), 100);
				shipEmptyHealthAnimation.addFrame(healthSheet.getSprite(2,0), 100);

				
	}
	
	
	//Getters for mobs
	public Animation getPurpleAnimation()
	{
		return purpleAnimation;
	}
	
	public Animation getRedAnimation()
	{
		return redAnimation;
	}
	
	public Animation getGreenAnimation()
	{
		return greenAnimation;
	}
	
	public Animation getPinkAnimation()
	{
		return pinkAnimation;
	}
	
	
	//Getters for power ups
	public Animation getHealthAnimation()
	{
		return healthAnimation;
	}
	
	public Animation getHealthUpAnimation()
	{
		return healthUpAnimation;
	}
	
	public Animation getDoubleshotAnimation()
	{
		return doubleshotAnimation;
	}
	
	public Animation getInvincibilityAnimation()
	{
		return invincibilityAnimation;
	}
	
	public Animation getSpeedupAnimation() 
	{
		return speedupAnimation;
	}
	
	public Animation getArmorAnimation()
	{
		return armorAnimation;
	}
	
	public Animation getDamageupAnimation()
	{
		return damageupAnimation;
	}
	
	
	//Getter for ship
	public Animation getShipAnimation()
	{
		return shipAnimation;
	}
	
	//Getter for Projectile
	public Animation getProjectileAnimation()
	{
		return projectileAnimation;
	}
	
	//Getters for shipHealth
	public Animation getShipHealthAnimation()
	{
		return shipHealthAnimation;
	}
	
	public Animation getShipEmptyHealthAnimation()
	{
		return shipEmptyHealthAnimation;
	}
	
	
	
	//Getter for boss
	public Animation getBossAnimation()
	{
		return this.bossAnimation;
	}
	
	
	
	
	
	
	
}
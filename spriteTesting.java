import java.awt.Font;
import java.util.ArrayList;
import java.util.Random;


import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.CrossStateTransition;
import org.newdawn.slick.state.transition.EmptyTransition;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.SpriteSheet;

public class spriteTesting extends BasicGameState{
	
	
	public static final int ID = 4; //State ID
	private StateBasedGame game;
	
	private AnimationHandler animation;
	
	


	
	
	KeyboardInput input;


	
	

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException 
	{
		this.game = game;
		
		animation = new AnimationHandler();
		
		Powerup powerup = new Powerup(0);
		System.out.println(powerup.getHitBox());
		
		
		
		/*//SpriteSheet Instantiation
		mobSheet = new SpriteSheet("data/mobSpriteSheet.png", 38, 38);
		bossSheet = new SpriteSheet("data/Bosssmol.png", 118, 92); 
		shipSheet = new SpriteSheet("data/shiptrans.png",32, 35);
		powerupSheet = new SpriteSheet("data/powerups.png", 16, 16);
		
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
		bossAnimation = new Animation();	
		for (int i = 0; i < 6; i++)
		{
			bossAnimation.addFrame(bossSheet.getSprite(0, i), 100);
		}
		
		
		//ShipAddFrameLoop guy
		for (int i = 0; i < 4; i++)
		{
			shipAnimation.addFrame(shipSheet.getSprite(0, i), 100);
		}
		*/
		
		
		


	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException 
	{
		Powerup power = new Powerup(0);
		Alien alien = new Alien(0);
		
		//Single power up looping
		for(int i = 0; i < 18; i++) 
	    {
	    		
	    		for (int j = 0; j < 20; j++)
	    		{
	    			power.getAnimation().draw(j * 60, i * 55);
	    			System.out.println(power.getHitbox());
	    		}
	    }
	    
	    
		
		/*All power up looping
		for(int i = 0; i < 18; i++) 
	    {
	    		Random rand = new Random();
	    		
	    		for (int j = 0; j < 20; j++)
	    		{
		    		int value = rand.nextInt(7);
	    			if (value == 1)
	    			{
	    				animation.getHealthAnimation().draw(j * 50, i * 50);
	    			} else if (value == 2) {
	    				animation.getHealthUpAnimation().draw(j * 50, i * 50);
	    			} else if (value == 3) {
	    				animation.getDoubleshotAnimation().draw(j * 50, i * 50);
	    			} else if (value == 4) {
	    				animation.getInvincibilityAnimation().draw(j * 50, i * 50);
	    			} else if (value == 5) {
	    				animation.getSpeedupAnimation().draw(j * 50, i * 50);
	    			} else if (value == 6) {
	    				animation.getArmorAnimation().draw(j * 50, i * 50);
	    			} else if (value == 7) {
	    				animation.getDamageupAnimation().draw(j * 50, i * 50);
	    			} 
	    		}
	    };
	    */
	       
		
		/*Boss looping
		for(int i = 0; i < 10; i++)
		{
			for(int j = 0; j < 6; j++)
			{
				animation.getShipEmptyHealthAnimation().draw(j*135, i*90);
			}
		}
		*/
		
			
		/*Mob Looping
	    for(int i = 0; i < 18; i++) 
	    {
	    		Random rand = new Random();
	    		
	    		for (int j = 0; j < 20; j++)
	    		{
		    		int value = rand.nextInt(4);
	    			if (value == 1)
	    			{
	    				animation.getPurpleAnimation().draw(j * 50, i * 50);
	    			} else if (value == 2) {
	    				animation.getRedAnimation().draw(j * 50, i * 50);
	    			} else if (value == 3) {
	    				animation.getGreenAnimation().draw(j * 50, i * 50);
	    			} else if (value == 4) {
	    				animation.getPinkAnimation().draw(j * 50, i * 50);
	    			}
	    		}
	    };
	    */
	    
	    
	    
	    
	    
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException 
	{
	}

	@Override
	public int getID() 
	{
		// TODO Auto-generated method stub
		return ID;
	}

	

	
};
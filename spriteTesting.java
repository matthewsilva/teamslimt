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
	
	private SpriteSheet alienSheet;
	private Animation alienAnimation;
	
	
	KeyboardInput input;


	
	

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException 
	{
		this.game = game;
		
		//Alien Animation
		alienSheet = new SpriteSheet("data/alien_spritesheet.png", 32, 32);
		alienAnimation = new Animation(alienSheet, 100);
				
		

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException 
	{
 	    
		g.setColor(Color.green);
		
	    for(int i = 0; i < 18; i++) 
	    {
	    		alienAnimation.draw(200, 200);
	    		for (int j = 0; j < 20; j++)
	    		{
	    			alienAnimation.draw(j * 50, i * 50);
	    		}
	    };
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException 
	{
		alienAnimation.update(delta);
	}

	@Override
	public int getID() 
	{
		// TODO Auto-generated method stub
		return ID;
	}

	
};
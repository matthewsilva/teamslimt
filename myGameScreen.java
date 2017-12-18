import java.awt.Font;
import java.util.ArrayList;
import java.util.Random;


import org.newdawn.slick.Animation;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.CrossStateTransition;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Shape;


public class myGameScreen extends BasicGameState 
{
	//Data fields
	public static final int ID = 2; //State ID
	private StateBasedGame game;

	private final int powerupSpawnTime = 2000;
	private final int alienSpawnTime = 500;
	private final int frameTime = 40;
	
	private ArrayList<Alien> aliens= new ArrayList();
	private ArrayList<Projectile> shots = new ArrayList();
	private ArrayList<Powerup> powerups = new ArrayList();
	
	private int invulnTime;
	private Player player;
	private Boss brain;
	private int enemyCap = 10;
	long movementTime;
	long overallTime;
	long powerupTime;
	long invulnLength;
	private int score;
	KeyboardInput input;
	
	private AnimationHandler animationHandler;
	


@Override
public void init(GameContainer container, StateBasedGame game) throws SlickException 
{
	//Game initialization
	this.game = game;
	player = new Player();
	player.moveTo(400, 400);
	player.setXVelocity(0.0);
	player.setYVelocity(0.0);
	animationHandler = new AnimationHandler();
	score = 0;
	movementTime = 0;
	overallTime = 0;
	powerupTime = 0;
	invulnTime = 0;
	invulnLength = 0;
	
}

@Override
public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException 
{
	//Draws the player's ship
	animationHandler.getShipAnimation().draw(player.getXPos(),player.getYPos());
   	
	//Draws the aliens
   	for (Alien alien : aliens) 
   	{
   		animationHandler.getRandomAlienAnimation(alien.getType()).draw(alien.getXPos(), alien.getYPos());
   	}
   	
   	//Draws the powerups
   	for (Powerup powerup : powerups) 
   	{
   		animationHandler.getRandomPowerupAnimation(powerup.getType()).draw(powerup.getXPos(), powerup.getYPos());
   	}
   	
   	//Draws the projectiles
   	for (Projectile shot : shots) 
   	{
   		animationHandler.getProjectileAnimation().draw(shot.getXPos(),shot.getYPos());
   	}   
   	
   	//Draws the player's current Health
   	g.setColor(Color.white);
   	g.drawString("Health: ", 5, 40);
   	drawLives();
   	
   	//Draws the players stats
   	g.drawString("Invincible: " + player.isInvincible(), 5, 60);
   	g.drawString("Top Speed: " + player.getInput().getConstraint(), 5, 80);
   	g.drawString("Shot Interval: " + player.getInput().getShotInterval(), 5, 100);
   	//Draws the score
   	g.drawString("Score: " + score, 700, 40);
   	
   	if(player.getLives() == 0)
   	{
   		g.clear();
   		g.drawString("GAME OVER", 350, 350);
   		g.drawString("Press esc to return to the main menu.", 250, 700);
   	}
   	
   	   	
}




@Override
public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

	//Keeps track of various timers via incrementing by delta 
	movementTime += delta;
	overallTime += delta;
	powerupTime += delta;
	invulnLength = player.getInvulnLength();
	input = player.getInput();
	
	input.readInput(container, delta, player, shots);
	
	//General game updates during gameplay
	if (movementTime >= frameTime) 
	{
		player.move();
		player.checkPosition();
		updateObjectMovement(aliens, shots);
		checkCollisions(aliens, powerups, player);
		shotCollide(aliens, shots);
		if(brain != null)
		{
			brain.move();
			brain.getAnimation().draw(400,400);
			controlBoss(brain);
		}
		movementTime = 0;
	}
		
	//Alien spawn
	if (overallTime >= alienSpawnTime && aliens.size() < enemyCap)
	{		
		aliens.add(new Alien());
		overallTime = 0;
	} 
		
	//Powerup spawn
	if (powerupTime >= powerupSpawnTime)
	{
		powerups.add(new Powerup());
		powerupTime = 0;
	}
	
	//Keeps track of how long the player has been invincible
	if (player.isInvincible()) 
	{
		invulnTime += delta;
	}
	
	//Resets invincibility
	if (invulnTime > invulnLength) 
	{
		player.setInvincible(false);
		player.setInvulnLength((long) 1000);
		invulnTime = 0;
	}
	
	//Spawn new boss
	if (score > 10) {
		brain = new Boss();
	}
	
	
}

private void controlBoss(Boss boss) 
{
	
	
}

//Updates the positions of the drawableObjects
public void updateObjectMovement (ArrayList<Alien> aliens, ArrayList<Projectile> shots)
{
		//Moves aliens
		for (Alien alien : aliens)
		{
			alien.checkAlienPosition();
			alien.move();
		}
		
		//Moves projectiles
		for (Projectile shot : shots)
		{
			shot.checkPosition();
			shot.move();
		}
		
}

//Checks for player collisions with the aliens and powerups
public void checkCollisions(ArrayList<Alien> aliens, ArrayList<Powerup> powerups, Player myPlayer)
{
	//Checks for alien collisions
	for (Alien alien : aliens)
	{
		if (alien.intersects(myPlayer) && !myPlayer.isInvincible())
		{
			myPlayer.setLives(myPlayer.getLives() - 1);
			myPlayer.setInvincible(true);
		}
	}
	
	//Checks for powerup collisions
	for (Powerup powerup : powerups)
	{
		if(powerup.intersectsPlayer(myPlayer))
		{
			powerup.powerupFunction(myPlayer);
			powerup.moveOffScreen();
		}
	}
	
}

//Checks if the aliens have been hit
public void shotCollide(ArrayList<Alien> aliens, ArrayList<Projectile> shots) {
	for (int i = 0; i < aliens.size(); i++)
	{
		for(int j = 0; j < shots.size(); j++) {
			try {
				if (aliens.get(i) != null && shots.get(i) != null) {
					if (aliens.get(i).intersects(shots.get(j)))
					{
						aliens.remove(i);
						shots.remove(j);
						score++;
						return;
					}
				}
			} catch (Exception e){
			e.printStackTrace();
			
			}
		}
	}
}


//Draws the players current lives
public void drawLives()
{
	//Draws full heart pips
	for(int i = 0; i < player.getLives() && i < player.getHealthPips(); i++)
   	{  	
   		
   		animationHandler.getShipHealthAnimation().draw(70 + (30 * i), 25);  
   		
   	}
	//Draws empty heart pips
   	for(int i = player.getHealthPips(); (i > player.getLives() && i > 0); i--)
   	{
   		
   		animationHandler.getShipEmptyHealthAnimation().draw(40 + (30 * i), 25);
   		
   	}
	
}

//Allows for changing between states
public void keyReleased(int key, char c) {
    
    if (key == Input.KEY_ESCAPE) {
       GameState target = game.getState(TitleScreen.ID);
       
       final long start = System.currentTimeMillis();
       CrossStateTransition t = new CrossStateTransition(target) 
       {            
          public boolean isComplete() 
          {
             return (System.currentTimeMillis() - start) > 2000;
          }

          public void init(GameState firstState, GameState secondState) {
          }
       };
       
       game.enterState(TitleScreen.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
    
 }
}

@Override
public int getID() 
{	
	return ID;
}//Returns ID of state


	
}
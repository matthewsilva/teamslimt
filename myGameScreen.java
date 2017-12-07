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
	
	public static final int ID = 2; //State ID
	
	
	private Shape testShape;
	private final static int rightEdge = 800;
	private final static int bottomEdge = 800;
	private final int alienSpawnTime = 1000;
	private final int powerTime = 2000;
	private final int frameTime = 40;
	private StateBasedGame game;
	
	private int invulnTime;
	private final int invulnLength = 1000;
	private float shotSize;
	private Player player;
	private ArrayList<Alien> aliens= new ArrayList();
	private Boss brain;
	private ArrayList<Projectile> shots = new ArrayList();
	long movementTime;
	long overallTime;
	long overallPowerTime;
	private int score;
	KeyboardInput input;
	


@Override
public void init(GameContainer container, StateBasedGame game) throws SlickException 
{
	this.game = game;
	movementTime = 0;
	overallTime = 0;
	overallPowerTime = 0;
	invulnTime = 0;
	shotSize = 20;
	player = new Player();
	player.moveTo(400, 400);
	input = new KeyboardInput();
	
}

@Override
public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException 
{
	
	
	player.getAnimation().draw(player.getXPos(),player.getYPos());
   	
   	for (Alien alien : aliens) 
   	{
   		alien.getAnimation().draw(alien.getXPos(), alien.getYPos());
   	}
   	
   	for (Projectile shot : shots) 
   	{
   		shot.getAnimation().draw(shot.getXPos(),shot.getYPos());
   	}
   	
   	g.setColor(Color.white);
   	g.drawString("Lives: " + player.getlivesString(), 5, 40);
   	g.drawString("Score: " + score, 5, 60);
   	
   }




@Override
public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

	movementTime += delta;
	overallTime += delta;
	overallPowerTime += delta;
	
	input.readInput(container, delta, player, shots);
	
	if (movementTime >= frameTime) 
	{
		player.move();
		player.checkPosition();
		if(brain != null) brain.move();
		if(brain != null) controlBoss(brain);
		updateShots(shots);
		updateAliens(aliens);
		alienCollide(aliens, player);
		shotCollide(aliens, shots);
		
		movementTime = 0;
	}
	if (overallTime >= alienSpawnTime)
	{		
		aliens.add(new Alien());
		overallTime = 0;
	}
	if (invulnTime > invulnLength) {
		invulnTime = 0;
		player.setInvincible(false);
	}
	if (player.isInvincible()) {
		invulnTime += delta;
	}
	
	if (score > 50) {
		brain = new Boss();
	}
	
	
	
	
	//checkPlayerCollisions(mobList, powerList, delta);
	//checkShotCollisions(shotList, mobList);
	
}

private void controlBoss(Boss boss) {
	
	
}

private void updateShots(ArrayList<Projectile> shots) {
	for (int i = 0; i < shots.size(); i++)
	{
		if (shots.get(i).checkWalls()) shots.remove(i);
		else shots.get(i).move();
	}
}

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


public void updateAliens (ArrayList<Alien> aliens)
{
		for (Alien alien : aliens)
		{
			alien.checkPosition();
			alien.move();
		}
}

public void alienCollide(ArrayList<Alien> aliens, Player myPlayer)
{
	for (Alien alien : aliens)
	{
		if (alien.intersects(myPlayer))
		{
			myPlayer.setLives(myPlayer.getLives() - 1);
			System.out.println("collision");
			myPlayer.setInvincible(true);
		}
	}
}

public void shotCollide(ArrayList<Alien> aliens, ArrayList<Projectile> shots) {
	for (int i = 0; i < aliens.size(); i++)
	{
		for(int j = 0; j < shots.size(); j++) {
			if (aliens.get(i) != null && shots.get(i) != null) {
				
			
				if (aliens.get(i).intersects(shots.get(j)))
				{
					aliens.remove(i);
					shots.remove(j);
			
				}
			}
		}
	}
}


@Override
public int getID() 
{	
	return ID;
}//Returns ID of state


/*
public void moveShots(ArrayList<Mob> shots) {
	int shtsze = shots.size();
	for (int i = 0; i < shtsze; i++) {
		shots.get(i).move();
		if (shots.get(i).getX() > rightEdge + 100 || shots.get(i).getX() < -100) {
			shots.remove(i);
		}
		else if (shots.get(i).getY() > bottomEdge + 100 || shots.get(i).getY() < -100) {
			shots.remove(i);
		}
		shtsze = shots.size();
		
	}
}
*/

/*
public void checkPlayerCollisions(ArrayList<Mob> roids, ArrayList<Mob> powerups, int delta) 
{
	
	if (invulnTime > invulnLength) 
	{
		invulnTime = 0;
		player.setInvincible(false);
	}
	
	else if (player.isInvincible()) 
	{
		invulnTime += delta;
	}
		
	else 
	{
		for (Alien alien : aliens) 
		{
    			if(alien.getHitBox().intersects(player.getHitBox())) 
	    		{
	    			player.setLives(player.getLives()-1);
	    			player.setInvincible(true);
	    		}
	    	}
	}
	
}
*/	


/*
public void checkShotCollisions(ArrayList<Mob> shots, ArrayList<Mob> roids) {
	for (Mob shot : shots) {
		for (int i = 0; i < roids.size(); i++) {
			if(shot.getShape().intersects(roids.get(i).getShape())) {
    			roids.remove(i);
    			score ++;
    			System.out.println("Shot Collision");
    		}
		}
		
	}	
	}
*/

	
}
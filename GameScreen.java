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


public class GameScreen extends BasicGameState 
{
	
	public static final int ID = 2; //State ID

	
	
	private Shape testShape;
	private final static int rightEdge = 500;
	private final static int bottomEdge = 400;
	private final int roidSpawnTime = 1000;
	private final int powerTime = 2000;
	private final int frameTime = 40;
	private StateBasedGame game;
	
	private int invulnTime;
	private final int invulnLength = 1000;
	private float shotSize;
	private Mob player;
	private ArrayList<Mob> mobList = new ArrayList();
	private ArrayList<Mob> shotList = new ArrayList();
	private ArrayList<Mob> powerList = new ArrayList();
	private Mob roid1;
	long movementTime;
	long overallTime;
	long overallPowerTime;
	private int lives;
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
	player = new Mob();
	player.setShotSize(20);
	player.setShotSpeed(1);
	player.setDx(0);
	player.setDy(0);
	input = new KeyboardInput();
	lives = 3;
}
@Override
public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException 
{
	
	g.setColor(Color.green);
   	g.draw(player.getShape());
   	
   	if(player.isInvincible())
   		g.drawString(lives + " Lives Left: INVINCIBILITY ACTIVATED",
   				player.getShape().getCenterX(),
   				player.getShape().getCenterY());
   	g.setColor(Color.white);
   	for (Mob roid : mobList) {
   		g.draw(roid.getShape());
   	}
   	g.setColor(Color.blue);
   	for (Mob shot : shotList) {
   		g.draw(shot.getShape());
   	}
   	
   	for (Mob power : powerList) {
		g.draw(power.getShape());
		System.out.print("rendered powerups");
	}
   	
   	
// 	g.drawString("Frame Updated\n Spawn Timer :" + overallTime
//   			, 40, 50);
//   	g.drawString("Frame Updated\n Move Timer :" + movementTime
//   			, 40, 100);
   	g.setColor(Color.white);
   	g.drawString("Lives: " + livesString(), (int) (rightEdge*0.1), (int) (bottomEdge*0.1));
   	g.drawString("Score: " + score, (int) (rightEdge*0.1), (int) (bottomEdge*0.15));
   	
   }




@Override
public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

	movementTime += delta;
	overallTime += delta;
	overallPowerTime += delta;
	input.readInput(container, delta, player, shotList);
	if (movementTime >= frameTime) {
		movePlayer();
		moveRoids(mobList);
		moveShots(shotList);
		movementTime = 0;
	}
	if (overallTime >= roidSpawnTime)
	{
		mobList.add(randomRoid());
		overallTime = 0;
		System.out.println("Asteroid Spawned");
	}
	
	if (overallPowerTime >= powerTime)
	{
		powerList.add(randomPower());
		overallPowerTime = 0;
		System.out.println("Asteroid Spawned");
	}
	
	
	
	checkPlayerCollisions(mobList, powerList, delta);
	checkShotCollisions(shotList, mobList);
	
}

public void keyReleased(int key, char c) {
    
    if (key == Input.KEY_ESCAPE) {
       GameState target = game.getState(TitleScreen.ID);
       
       final long start = System.currentTimeMillis();
       CrossStateTransition t = new CrossStateTransition(target) {            
          public boolean isComplete() {
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

public String livesString() 
{
	String str = "";
	for (int i = 0; i < lives; i++)
		str = str + " I ";
	return str;
}

public void movePlayer() {
	if (player.getX() > rightEdge + 100 || player.getX() < -100) {
		player.sideCollide();
	}
	if (player.getY() > bottomEdge + 100 || player.getY() < -100) {
		player.topBotCollide();
	}
	player.move();	
}


public void moveRoids(ArrayList<Mob> roids) {
	for ( Mob roid : roids ) {
		if (roid.getX() > rightEdge + 100 || roid.getX() < -100) {
			roid.sideCollide();
		}
		if (roid.getY() > bottomEdge + 100 || roid.getY() < -100) {
			roid.topBotCollide();
		}
		roid.move();
	}
}

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

public void checkPlayerCollisions(ArrayList<Mob> roids, ArrayList<Mob> powerups, int delta) {
	
	if (invulnTime > invulnLength) {
		invulnTime = 0;
		player.setInvincible(false);
	}
	if (player.isInvincible()) {
		invulnTime += delta;
	}
	else {
		for (Mob mob : roids) {
    		if(mob.getShape().intersects(player.getShape())) {
    			lives--;
    			player.setInvincible(true);
    		}
    	}
	}
	for (int i = 0; i < powerups.size(); i++) {
		if(player.getShape().intersects(powerups.get(i).getShape())) {
			powerups.get(i).powerup(player);
			powerups.remove(i);
		}
	}
	
}


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


public Mob randomRoid() 
{
	float x = (float) (Math.floor(Math.random()*bottomEdge));
	float y =  (float) (Math.floor(Math.random()*rightEdge));
	float radius =  (float) (Math.floor(Math.random()*25) + 5);
	float dx = (float) (Math.floor(Math.random()*2)*2-1);
	float dy = (float) (Math.floor(Math.random()*2)*2-1);
	float speed =  (float) (Math.random()*3);
	return new Mob(x, y, radius, radius, dx, dy, speed);
}

public Mob randomPower() {
	float x = (float) (Math.floor(Math.random()*250) + 100);
	float y =  (float) (Math.floor(Math.random()*200) + 100);
	float radius =  (float) (Math.floor(Math.random()*25) + 5);
	Mob power = new Mob(x, y, radius, radius);
	power.setPowerup(1);
	return power;
}

public Mob topShot() 
{
	float x = player.getShape().getCenterX();
	float y =  shotSize + 5 + player.getShape().getMinY();
	float radius = shotSize;
	float dx = 0;
	float dy = -1;
	float speed =  (float) (Math.random()*3);
	return new Mob(x, y, radius, radius, dx, dy, speed);
}

	
}
/*  
* To change this template, choose Tools | Templates  
* and open the template in the editor.  
*/  

 
import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
/**
 * @author panos
 */
import org.newdawn.slick.tiled.TiledMap;

public class AsteroidsGame extends BasicGame
{
	private TiledMap grassMap;
	private Shape testShape;
	private final static int rightEdge = 500;
	private final static int bottomEdge = 400;
	private final int roidSpawnTime = 1000;
	private final int frameTime = 40;
	private int invulnTime;
	private final int invulnLength = 1000;
	private float shotSize;
	private Mob player;
	private ArrayList<Mob> mobList = new ArrayList();
	private ArrayList<Mob> shotList = new ArrayList();
	private Mob roid1;
	long movementTime;
	long overallTime;
	private int lives;
	KeyboardInput input;
	
	public AsteroidsGame()
    {
        super("Meteorite Game");
    }
 
    public static void main(String[] arguments)
    {
        try
        {
        	
        	
            AppGameContainer app = new AppGameContainer(new AsteroidsGame());
            app.setDisplayMode(rightEdge, bottomEdge, false);
            app.start();
        }
        catch (SlickException e)
        {
            e.printStackTrace();
        }
    }
 
    @Override
    public void init(GameContainer container) throws SlickException
    {
    	movementTime = 0;
    	overallTime = 0;
    	invulnTime = 0;
    	shotSize = 20;
    	player = new Mob();
    	input = new KeyboardInput();
    	lives = 3;
    }
 
    @Override
    public void update(GameContainer container, int delta) throws SlickException
    {
    	movementTime += delta;
    	overallTime += delta;
    	input.readInput(container, delta, player);
    	if (movementTime >= frameTime) {
    		moveRoids(mobList);
    		moveShots(shotList);
    		movementTime = 0;
    	}
    	if (overallTime >= roidSpawnTime)
    	{
    		mobList.add(randomRoid());
    		overallTime = 0;
    		System.out.println("Asteroid Spawned");
    		//TEST TEST TEST
    		shotList.add(topShot());
    	}
    	
    	checkPlayerCollisions(mobList, delta);
    	checkShotCollisions(shotList, mobList);
    	
    }
 
    public void render(GameContainer container, Graphics g) throws SlickException
    {
    	g.draw(player.userShape());
    	if(player.isInvincible())
    		g.drawString(lives + " Lives Left: INVINCIBILITY ACTIVATED",
    				player.userShape().getCenterX(),
    				player.userShape().getCenterY());
    	for (Mob roid : mobList) {
    		g.draw(roid.userShape());
    	}
    	for (Mob shot : shotList) {
    		g.draw(shot.userShape());
    	}
    	
    	
    	g.drawString("Frame Updated\n Spawn Timer :" + overallTime
    			, 40, 50);
    	g.drawString("Frame Updated\n Move Timer :" + movementTime
    			, 40, 100);
    	g.drawString("Lives: " + livesString(), (int) (rightEdge*0.1), (int) (bottomEdge*0.1));
    	
    }
    
    public String livesString() {
    	String str = "";
    	for (int i = 0; i < lives; i++)
    		str = str + " I ";
    	return str;
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
    	for ( Mob shot: shots ) {
    		shot.move();
    	}
    }
    
    public void checkPlayerCollisions(ArrayList<Mob> roids, int delta) {
    	
    	if (invulnTime > invulnLength) {
    		invulnTime = 0;
    		player.setInvincible(false);
    	}
    	if (player.isInvincible()) {
    		invulnTime += delta;
    	}
    	else {
    		for (Mob mob : roids) {
        		if(mob.userShape().intersects(player.userShape())) {
        			lives--;
        			player.setInvincible(true);
        		}
        	}
    	}
    	
    }
    
    public void checkShotCollisions(ArrayList<Mob> shots, ArrayList<Mob> roids) {
    	for (Mob shot : shots) {
    		for (Mob roid : roids) {
    			if(shot.userShape().intersects(roid.userShape())) {
        			roid = null;
        		}
    		}
    		
    	}	
    	}
    	
    
    
    
    public Mob randomRoid() {
    	float x = (float) (Math.floor(Math.random()*250) + 100);
		float y =  (float) (Math.floor(Math.random()*200) + 100);
		float radius =  (float) (Math.floor(Math.random()*25) + 5);
		float dx = (float) (Math.floor(Math.random()*2)*2-1);
		float dy = (float) (Math.floor(Math.random()*2)*2-1);
		float speed =  (float) (Math.random()*3);
    	return new Mob(x, y, radius, radius, dx, dy, speed);
    }
    
    public Mob topShot() {
    	float x = player.userShape().getCenterX();
		float y =  shotSize + 5 + player.userShape().getMinY();
		float radius = shotSize;
		float dx = 0;
		float dy = -1;
		float speed =  (float) (Math.random()*3);
    	return new Mob(x, y, radius, radius, dx, dy, speed);
    }
    
    
    /*
    public boolean playerCollision() {
    	
    }
    */
}

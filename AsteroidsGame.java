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
    	player.setShotSize(20);
    	player.setShotSpeed(1);
    	player.setDx(0);
    	player.setDy(0);
    	input = new KeyboardInput();
    	lives = 3;
    }
 
    @Override
    public void update(GameContainer container, int delta) throws SlickException
    {
    	movementTime += delta;
    	overallTime += delta;
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
    	
    	checkPlayerCollisions(mobList, delta);
    	checkShotCollisions(shotList, mobList);
    	
    }
 
    public void render(GameContainer container, Graphics g) throws SlickException
    {
    	g.draw(player.getShape());
    	if(player.isInvincible())
    		g.drawString(lives + " Lives Left: INVINCIBILITY ACTIVATED",
    				player.getShape().getCenterX(),
    				player.getShape().getCenterY());
    	for (Mob roid : mobList) {
    		g.draw(roid.getShape());
    	}
    	for (Mob shot : shotList) {
    		g.draw(shot.getShape());
    	}
    	
    	
    	g.drawString("Frame Updated\n Spawn Timer :" + overallTime
    			, 40, 50);
    	g.drawString("Frame Updated\n Move Timer :" + movementTime
    			, 40, 100);
    	g.drawString("Lives: " + livesString(), (int) (rightEdge*0.1), (int) (bottomEdge*0.1));
    	
    }
    
    private String livesString() {
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
    // FIX WITH getShape.getMaxX for each edge (should be 4 collision functions with max dimension condition for each
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
        		if(mob.getShape().intersects(player.getShape())) {
        			lives--;
        			player.setInvincible(true);
        		}
        	}
    	}
    	
    }
    
    public void checkShotCollisions(ArrayList<Mob> shots, ArrayList<Mob> roids) {
    	for (Mob shot : shots) {
    		for (int i = 0; i < roids.size(); i++) {
    			if(shot.getShape().intersects(roids.get(i).getShape())) {
        			roids.remove(i);
        			System.out.println("Shot Collision");
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
    
    
    
    /*
    public boolean playerCollision() {
    	
    }
    */
}

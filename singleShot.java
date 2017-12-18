/**
 * ISSUE: BLEEP BLOOP
 * 
 * I had to instantiate Projectiles using (x, y, dy, dx), while the proper way is (x, y, dx, dy).
 * Need to investigate.
 * 
 */

import java.util.ArrayList;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

public class singleShot implements ShootingStrategy {

	
	public ArrayList<Projectile> upShots(Player player) 
	{
		ArrayList<Projectile> shots = new ArrayList<Projectile>();
		float diameter = player.getShotSize();
		int x = player.getXPos();
		int y = (int) (player.getYPos() - 0.5f*diameter);
		double dx = (player.getXVel());
		double dy = (player.getYVel() - player.getShotSpeed());
		float speed = player.getSpeed();
		Projectile shot1 = null;
		try {
			shot1 = new Projectile(x, y, dy, dx);
			shot1.setYVelocity(shot1.constrain(shot1.getYVel(), -15, -5));
			
			//System.out.println("Actual dy = " + shot1.getYVel());
			//System.out.println("x: " + x + " y: " + y + " dx: " + dx + " dy: " + dy);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		shots.add(shot1);
		return shots;
	}
	
	public ArrayList<Projectile> downShots(Player player) 
	{
		ArrayList<Projectile> shots = new ArrayList<Projectile>();
		float diameter = player.getShotSize();
		int x = player.getXPos();
		int y = (int) (player.getYPos() - 0.5f*diameter);
		double dx = (player.getXVel());
		double dy = (player.getYVel() + player.getShotSpeed());
		float speed = player.getSpeed();
		Projectile shot1 = null;
		try {
			shot1 = new Projectile(x, y, dy, dx);
			shot1.setYVelocity(shot1.constrain(shot1.getYVel(), 5, 15));
			
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		shots.add(shot1);
		return shots;
	}
	
	public ArrayList<Projectile> leftShots(Player player) 
	{
		ArrayList<Projectile> shots = new ArrayList<Projectile>();
		float diameter = player.getShotSize();
		int x = player.getXPos();
		int y = (int) (player.getYPos() - 0.5*diameter);
		double dx = (player.getXVel() - player.getShotSpeed());
		double dy = (player.getYVel());
		float speed = player.getSpeed();
		Projectile shot1 = null;
		try {
			shot1 = new Projectile(x, y, dy, dx);
			shot1.setXVelocity(shot1.constrain(shot1.getXVel(), -15, -5));
			
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		shots.add(shot1);
		return shots;
	}
	public ArrayList<Projectile> rightShots(Player player) {
		ArrayList<Projectile> shots = new ArrayList<Projectile>();
		float diameter = player.getShotSize();
		int x = player.getXPos();
		int y = (int) (player.getYPos() - 0.5*diameter);
		double dx = (player.getXVel() + player.getShotSpeed());
		double dy = (player.getYVel());
		float speed = player.getSpeed();
		Projectile shot1 = null;
		try {
			shot1 = new Projectile(x, y, dy, dx);
			shot1.setXVelocity(shot1.constrain(shot1.getXVel(), 5, 15));
			
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		shots.add(shot1);
		return shots;
	}

}
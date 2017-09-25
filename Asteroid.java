import java.util.Random;

import org.newdawn.slick.geom.Rectangle;

public class Asteroid extends Mob {
	private int speed;
	private int dx;
	private int dy;
	
	public Asteroid(int x1, int y1, int x2, int y2, int speed)
	{
		super(x1, y1, x2, y2, speed);
		dx = (int) (Math.floor(Math.random()*2)*2-1);
		dy = (int) (Math.floor(Math.random()*2)*2-1);
		
		
	}
	
	public void sideCollide() {
		dx*=-1;
		dy*=((Math.random()*0.20)+1);
	}
	
	public void topBotCollide() {
		dy*=-1;
		dx*=((Math.random()*0.20)+1);
	}
}

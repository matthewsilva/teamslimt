





import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Mob
{

	private Shape mob;
	private static final float BOUNCEFACTOR = 1;
	private float x = 34, y = 34;
	private float dx, dy;
    private float speed = 1;
    private boolean invincible = false;
	
	public Mob()
	{
		mob = new Rectangle(30, 40, 60, 70);
	}
	
	public Mob(float x1, float y1, float x2, float y2)
	{
		mob = new Rectangle(x1, y1, x2, x2);
	}
	
	public Mob(float x1, float y1, float x2, float y2, float speed)
	{
		mob = new Rectangle(x1, y1, x2, x2);
		this.speed = speed;
	}
	
	public Mob(float x1, float y1, float x2, float y2, float dx, float dy, float speed)
	{
		mob = new Rectangle(x1, y1, x2, x2);
		this.dx = dx;
		this.dy = dy;
		this.speed = speed;
		System.out.println(BOUNCEFACTOR);
	}
	
	
	
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public void setVelocity() {
		dx = (float) (Math.floor(Math.random()*2)*2-1);
		dy = (float) (Math.floor(Math.random()*2)*2-1);
	}
	
	public void setSpeed(float givenSpeed) {
		speed = givenSpeed;
	}
	
	
	public void move() {
		x += dx*speed;
		mob.setX(x);
		y += dy*speed;
		mob.setY(y);
	}
	
	public void move(float moveX, float moveY) {
		x += moveX*speed;
		mob.setX(x);
		y += moveY*speed;
		mob.setY(y);
	}
	
	public void sideCollide() {
		dx*=-1;
		dy*=(Math.floor(Math.random()*2)*2-1)*((Math.random()*BOUNCEFACTOR)+1);
	}
	
	public void topBotCollide() {
		dy*=-1;
		dx*=(Math.floor(Math.random()*2)*2-1)*((Math.random()*BOUNCEFACTOR)+1);
	}
	
	public boolean isInvincible() {
		return invincible;
	}
	
	public void setInvincible(boolean invuln) {
		invincible = invuln;
	}
	
	public Shape userShape() {
		return mob;
	}
}
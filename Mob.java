





import java.util.ArrayList;

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
	private float x, y;
	private float dx, dy;
    private float speed = 1;
    private float shotSize;
    private float shotSpeed;
    private final static float SHOTCONSTANT = 5;
    private boolean invincible = false;
	
	public Mob()
	{
		mob = new Rectangle(30, 40, 60, 70);
	}
	
	public Mob(float x1, float y1, float x2, float y2)
	{
		x = x1;
		y = y1;
		mob = new Rectangle(x1, y1, x2, x2);
	}
	
	public Mob(float x1, float y1, float x2, float y2, float speed)
	{
		x = x1;
		y = y1;
		mob = new Rectangle(x1, y1, x2, x2);
		this.speed = speed;
	}
	
	public Mob(float x1, float y1, float x2, float y2, float dx, float dy, float speed)
	{
		x = x1;
		y = y1;
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
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setDx(float dx) {
		this.dx = dx;
	}
	
	public void setDy(float dy) {
		this.dy = dy;
	}
	
	public float getDx() {
		return dx;
	}
	
	public float getDy() {
		return dy;
	}
	
	public void setVelocity() {
		dx = (float) (Math.floor(Math.random()*2)*2-1);
		dy = (float) (Math.floor(Math.random()*2)*2-1);
	}
	
	public void setSpeed(float givenSpeed) {
		speed = givenSpeed;
	}
	
	public float getSpeed() {
		return speed;
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
	
	public void setShotSize(float size) {
		shotSize = size;
	}
	
	public void setShotSpeed(float speed) {
		shotSpeed = speed;
	}
	
	public void shootUp(ArrayList<Mob> shots) {
		float diameter = shotSize;
		float x = mob.getCenterX() - 0.5f*diameter;
		float y = mob.getMinY() - shotSize;
		float dx = this.dx;
		float dy = (this.dy - SHOTCONSTANT*shotSpeed);
		float speed = this.speed;
		shots.add(new Mob(x, y, diameter, diameter, dx, dy, speed));
		
	}
	
	public void shootDown(ArrayList<Mob> shots) {
		float diameter = shotSize;
		float x = mob.getCenterX() - 0.5f*diameter;
		float y = mob.getMaxY();
		float dx = this.dx;
		float dy = (this.dy + SHOTCONSTANT*shotSpeed);
		float speed =  this.speed;
		shots.add(new Mob(x, y, diameter, diameter, dx, dy, speed));
	}
	
	public void shootLeft(ArrayList<Mob> shots) {
		float diameter = shotSize;
		float x = mob.getMinX() - shotSize;
		float y = mob.getCenterY() - 0.5f*diameter;
		float dx = (this.dx - SHOTCONSTANT*shotSpeed);
		float dy = this.dy;
		float speed =  this.speed;
		shots.add(new Mob(x, y, diameter, diameter, dx, dy, speed));
	}
	
	public void shootRight(ArrayList<Mob> shots) {
		float diameter = shotSize;
		float x = mob.getMaxX();
		float y = mob.getCenterY() - 0.5f*diameter;
		float dx = (this.dx + SHOTCONSTANT*shotSpeed);
		float dy = this.dy;
		float speed = this.speed;
		shots.add(new Mob(x, y, diameter, diameter, dx, dy, speed));
	}
 	
	public Shape getShape() {
		return mob;
	}
}
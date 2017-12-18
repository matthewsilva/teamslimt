import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

public class KeyboardInput 
{
	
	private double velocityConstraint;
	private int shotTime = 0;
	private int shotInterval = 1000;
	
	public KeyboardInput() 
	{
		velocityConstraint = 10;
	}

	public KeyboardInput(double constraint) 
	{
		velocityConstraint = constraint;
	}
	
	
	public double getConstraint()
	{
		return velocityConstraint;
	}
	
	public int getShotInterval()
	{
		return shotInterval;
	}
	
	public void decrementInterval(int myInterval)
	{
		shotInterval -= myInterval;
	}
	
	public void incrementConstraint()
	{
		velocityConstraint += 2;
	}
	
	public void readInput(GameContainer container, int delta, Player player, ArrayList<Projectile> shots) {
		Input input = container.getInput();
		shotTime += delta;

		if(shotTime >= shotInterval)
		{
			if (input.isKeyPressed(Input.KEY_UP))
			{
				shots.addAll(player.shootUp(shots));
				shotTime = 0;
			}
			else if (input.isKeyPressed(Input.KEY_DOWN))
			{
				shots.addAll(player.shootDown(shots));
				shotTime = 0;
			}
			else if (input.isKeyPressed(Input.KEY_LEFT))
			{
				shots.addAll(player.shootLeft(shots));
				shotTime = 0;
			}
			else if (input.isKeyPressed(Input.KEY_RIGHT))
			{
				shots.addAll(player.shootRight(shots));
				shotTime = 0;
			}
			
		}
		
    	if (input.isKeyDown(Input.KEY_W))
    	{
    		player.setYVelocity(player.constrain(player.getYVel() - 0.05, -velocityConstraint, velocityConstraint));
    	}
    	else if (input.isKeyDown(Input.KEY_S))
    	{
    		player.setYVelocity(player.constrain(player.getYVel() + 0.05, -velocityConstraint, velocityConstraint));
    	}
    	if (input.isKeyDown(Input.KEY_A))
    	{
        player.setXVelocity(player.constrain(player.getXVel() - 0.05, -velocityConstraint, velocityConstraint));
    	}
    	else if (input.isKeyDown(Input.KEY_D))
    	{
         player.setXVelocity(player.constrain(player.getXVel() + 0.05, -velocityConstraint, velocityConstraint));
    		//player.move(delta * 0.1f, 0);
    	}
    	else if (input.isKeyDown(Input.KEY_E))
    	{
    		player.setVelocity(0, 0);
    		
	}
    	else if (input.isKeyDown(Input.KEY_M)) {
    		player.setShotType(new DoubleShot());
    	}
}
}
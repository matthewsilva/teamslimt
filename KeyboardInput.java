import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

public class KeyboardInput 
{
	
	private double velocityConstraint;

	public KeyboardInput() 
	{
		velocityConstraint = 20;
	}
	public void readInput(GameContainer container, int delta, Player player) {
		Input input = container.getInput();
		
		/*
		if (input.isKeyPressed(Input.KEY_UP))
		{
			player.shootUp(shots);
		}
		else if (input.isKeyPressed(Input.KEY_DOWN))
		{
			player.shootDown(shots);
		}
		else if (input.isKeyPressed(Input.KEY_LEFT))
		{
			player.shootLeft(shots);
		}
		else if (input.isKeyPressed(Input.KEY_RIGHT))
		{
			player.shootRight(shots);
		}
		*/
		
    	if (input.isKeyDown(Input.KEY_W))
    	{
    		player.setYVelocity(player.constrain(player.getYVel() - 0.05, -velocityConstraint, velocityConstraint));
    	}
    	else if (input.isKeyDown(Input.KEY_S))
    	{
    		player.setYVelocity(player.constrain(player.getYVel() + 0.05, -velocityConstraint, velocityConstraint));
    	}
    	else if (input.isKeyDown(Input.KEY_A))
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
}
}

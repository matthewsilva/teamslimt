import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

public class KeyboardInput {

	public KeyboardInput() {
		
	}
	public void readInput(GameContainer container, int delta, Mob player, ArrayList<Mob> shots) {
		Input input = container.getInput();
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
		
    	if (input.isKeyDown(Input.KEY_W))
    	{
    		player.setDy((float) (player.getDy() - 0.05));
    	}
    	else if (input.isKeyDown(Input.KEY_S))
    	{
    		player.setDy((float) (player.getDy() + 0.05));
    	}
    	else if (input.isKeyDown(Input.KEY_A))
    	{
    		player.setDx((float) (player.getDx() - 0.05));
    	}
    	else if (input.isKeyDown(Input.KEY_D))
    	{
    		player.setDx((float) (player.getDx() + 0.05));
    		//player.move(delta * 0.1f, 0);
    	}
    	
	}
}

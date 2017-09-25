import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

public class KeyboardInput {

	public KeyboardInput() {
		
	}
	public void readInput(GameContainer container, int delta, Mob player) {
		Input input = container.getInput();
    	if (input.isKeyDown(Input.KEY_UP))
    	{
    	    player.move(0, -delta * 0.1f);
    	}
    	else if (input.isKeyDown(Input.KEY_DOWN))
    	{
    		player.move(0, delta * 0.1f);
    	}
    	else if (input.isKeyDown(Input.KEY_LEFT))
    	{
    		
    		player.move(-delta * 0.1f, 0);
    	}
    	else if (input.isKeyDown(Input.KEY_RIGHT))
    	{
    		player.move(delta * 0.1f, 0);
    	}
	}
}

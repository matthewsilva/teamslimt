import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;


public class AsteroidRework extends StateBasedGame
{
	
	KeyboardInput input;

	public AsteroidRework(String AsteroidRework) 
	{
		super("AsteroidRework");
	}

	
	public void initStatesList(GameContainer container) throws SlickException 
	{
		addState (new TitleScreen());
		addState (new myGameScreen());
		addState (new instructionScreen());
		addState (new spriteTesting());
		
	}
	
	public static void main(String[] arguments)
    {
        try
        {    	
            AppGameContainer app = new AppGameContainer(new AsteroidRework("Test"));
            app.setDisplayMode(800, 800, false);
            app.setAlwaysRender(true);
            app.start();
        }
        catch (SlickException e)
        {
            e.printStackTrace();
        }
    }
	

}

import java.awt.Font;

import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.CrossStateTransition;
import org.newdawn.slick.state.transition.EmptyTransition;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;


public class TitleScreen extends BasicGameState {
	
	
public static final int ID = 1; //State ID

private Font font; //Font

private StateBasedGame game;//Name of game


@Override
public void init(GameContainer container, StateBasedGame game) throws SlickException 
{
	this.game = game;
	
}
@Override
public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException 
{
    g.setColor(Color.cyan);
    g.drawString("This is the Title Screen!", 200, 50);
    g.drawString("Press the corresponding keys to view the other screens", 200, 100);
    g.drawString("2. Start Game", 200, 200);
    g.drawString("3. Instructions", 200, 400);
    g.drawString("4. Sprite Testing", 200, 600);

}
@Override
public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
	// TODO Auto-generated method stub
	
}

public void keyReleased(int key, char c) {
    
    if (key == Input.KEY_2) {
    	
       GameState target = game.getState(myGameScreen.ID);
       
       final long start = System.currentTimeMillis();
       CrossStateTransition t = new CrossStateTransition(target) {            
          public boolean isComplete() {
             return (System.currentTimeMillis() - start) > 2000;
          }

          public void init(GameState firstState, GameState secondState) {
          }
       };
       
       game.enterState(myGameScreen.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
    }
    
    if (key == Input.KEY_3) {
    		
    		GameState target = game.getState(TitleScreen.ID);
        
        final long start = System.currentTimeMillis();
        CrossStateTransition t = new CrossStateTransition(target) {            
           public boolean isComplete() {
              return (System.currentTimeMillis() - start) > 2000;
           }

           public void init(GameState firstState, GameState secondState) {
           }
        };
       game.enterState(instructionScreen.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
    }
    if (key == Input.KEY_4) {
		
		GameState target = game.getState(spriteTesting.ID);
    
		final long start = System.currentTimeMillis();
		CrossStateTransition t = new CrossStateTransition(target) {            
       public boolean isComplete() {
          return (System.currentTimeMillis() - start) > 2000;
       }

       public void init(GameState firstState, GameState secondState) {
       }
    };
   game.enterState(spriteTesting.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
}
 }


@Override
public int getID() 
{
	
	return ID;
}//Returns ID of state
	
}
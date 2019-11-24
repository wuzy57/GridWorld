import info.gridworld.actor.Actor; 
import info.gridworld.actor.Rock; 
import info.gridworld.actor.Critter; 
import java.util.ArrayList;
public class RockHound extends Critter
{
	//The processActors method in the Critter class eats (that is, removes) actors that are not rocks or critters. This behavior is either inherited or overridden in subclasses.
	//这里把原有的processActors更改为吃石头
  public void processActors(ArrayList<Actor> actors)
  {
    for (Actor a : actors)
    {
    	if (a instanceof Rock) 
    	{
    		a.removeSelfFromGrid();
    	}
    }
  }
}
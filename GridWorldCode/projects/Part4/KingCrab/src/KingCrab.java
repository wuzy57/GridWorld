

import info.gridworld.actor.Actor; 
import info.gridworld.grid.Location;
import java.awt.Color; 
import java.util.ArrayList;

public class KingCrab extends CrabCritter
{
	public KingCrab()
  	{
		setColor(Color.RED); 
	}
	//KingCrab和Actor之间的距离判定
	public int distanceFrom(Location loc1, Location loc2)
  	{
		int x1 = loc1.getRow();
		int y1 = loc1.getCol();
		int x2 = loc2.getRow();
		int y2 = loc2.getCol();
		double dist = Math.sqrt((x1 - x2)*(x1 - x2) + (y1 - y2)*(y1 - y2)) + .5; 
		return (int)Math.floor(dist);
  	}
	
	//尝试移动Actor
	private boolean moveOneMoreAway(Actor a)
	{
		ArrayList<Location> locs = getGrid().getEmptyAdjacentLocations(a.getLocation());
		for(Location tempLoc:locs)
		{
			if(distanceFrom(getLocation(), tempLoc) > 1)
		  	{
				a.moveTo(tempLoc);
				return true;
		  	}
		}
		return false;
	}
	//处理Actor
	public void processActors(ArrayList<Actor> actors)
	{
		for (Actor a : actors)
		{
			if (!moveOneMoreAway(a))
			{
				a.removeSelfFromGrid(); 
			}
		}
	}
}

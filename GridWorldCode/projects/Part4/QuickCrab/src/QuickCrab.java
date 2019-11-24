

import info.gridworld.actor.Actor; 
import info.gridworld.grid.Grid; 

import info.gridworld.grid.Location;
import main.CrabCritter;

import java.awt.Color; 
import java.util.ArrayList;

public class QuickCrab extends CrabCritter
{
  public QuickCrab()
  {
	  setColor(Color.GRAY); 
  }
  
  public ArrayList<Location> getMoveLocations()
  {
	  ArrayList<Location> loca = new ArrayList<Location>();
	  //查看左右是否为空
	  addIfGoodTwoAwayMove(loca,getDirection() + Location.LEFT); 
	  addIfGoodTwoAwayMove(loca,getDirection() + Location.RIGHT);
	  //如果都不为空，则像CrabCritter一样移动
	  if (loca.size() == 0)
	  {
		  return super.getMoveLocations();
	  }
	  return loca;
  }

  private void addIfGoodTwoAwayMove(ArrayList<Location> locs,int dir)
  {
	  Grid<Actor> g = getGrid();
	  Location loc = getLocation();
	  Location transLoc = loc.getAdjacentLocation(dir);
	  if(g.isValid(transLoc) && g.get(transLoc) == null) {
		  Location loc2 = transLoc.getAdjacentLocation(dir); 
		  //如果位置可行且为空，则添加到ArrayList
		  if(g.isValid(loc2) && g.get(loc2)== null)
		  {
			  locs.add(loc2); 
		  }
		  }
  }
}

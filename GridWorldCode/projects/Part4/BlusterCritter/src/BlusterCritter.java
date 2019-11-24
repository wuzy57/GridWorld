

import info.gridworld.actor.Actor; 

import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import java.util.ArrayList; 
import java.awt.Color;

public class BlusterCritter extends Critter
{
  private int courageFactor;
  //传入参数c，表示如果少于c的critter，则BlusterCritter的颜色会变得更亮。反之变暗。
  public BlusterCritter(int c)
  {
	  super();
	  courageFactor = c;
  }
  
  
  public ArrayList<Actor> getActors()
  {
    ArrayList<Actor> actors = new ArrayList<Actor>();
    Location loc = getLocation();
    //遍历周围的四个格子
    for(int r = loc.getRow() - 2; r <= loc.getRow()+2;r++)
    	for(int c = loc.getCol() - 2; c <= loc.getCol()+2;c++)
    	{
    		Location searchLoc = new Location(r,c); 
    		if(getGrid().isValid(searchLoc))
    		{
    			Actor a = getGrid().get(searchLoc); 
    			if(a != null && a != this)
    				actors.add(a); }
    	}
    return actors;
  }

  public void processActors(ArrayList<Actor> actors)
  {
	  int count = 0;
	  for(Actor a: actors)
		  if(a instanceof Critter)
			  count++;
	  if(count < courageFactor)
		  lighten();
	  else
    	darken(); 
    }
  //变暗的实现
  private void darken()
  {
	  Color c = getColor();
	  int red = c.getRed();
	  int green = c.getGreen(); int blue = c.getBlue();
	  if(red > 0) red--; 
	  if(green > 0) green--; 
	  if(blue > 0) blue--;
	  setColor(new Color(red, green, blue));
  }
  //变亮的实现
  private void lighten()
  {
	  Color c = getColor();
	  int red = c.getRed();
	  int green = c.getGreen(); int blue = c.getBlue();
	  if(red < 255) red++;
	  if(green < 255) green++;
	  if(blue < 255) blue++;
	  setColor(new Color(red, green, blue));
  }
}

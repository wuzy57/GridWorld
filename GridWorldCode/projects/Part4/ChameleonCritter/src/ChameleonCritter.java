

import java.util.ArrayList;
import java.awt.Color;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;


public class ChameleonCritter extends Critter
{	
	private static final double DARKENING_FACTOR = 0.05; 
    
	public ChameleonCritter() {

	}
	//处理Actor
    public void processActors(ArrayList<Actor> actors)
    {
        int n = actors.size();
        //要处理的actor列表为空，则ChameleonCritter的颜色将变暗
        if (n == 0) {
        	darken();
        	return;
        }
        
        int r = (int) (Math.random() * n);
        //选择要处理的Actor，让颜色相同
        Actor other = actors.get(r);
        setColor(other.getColor());
    }
    
    //移动
    public void makeMove(Location loc)
    {
        setDirection(getLocation().getDirectionToward(loc));
        super.makeMove(loc);
    }
    //颜色变暗的实现
    public void darken() {
    	Color c = getColor();
 		int red = (int) (c.getRed() * (1 - DARKENING_FACTOR));
 		int green = (int) (c.getGreen() * (1 - DARKENING_FACTOR));
 		int blue = (int) (c.getBlue() * (1 - DARKENING_FACTOR));
 		//每次变暗5%
 		setColor(new Color(red, green, blue)); 
    }
}

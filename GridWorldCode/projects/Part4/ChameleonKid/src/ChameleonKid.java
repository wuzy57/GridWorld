


import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Grid;

import java.util.ArrayList;
import java.awt.Color;

/**
 * A <code>ChameleonKid</code> takes on the color of neighboring actors immediately in front 
 * or behind as it moves through the grid. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public final class ChameleonKid extends ChameleonCritter
{	 
    public ChameleonKid() {

    }
    public ArrayList<Actor> getActors() {
        ArrayList<Actor> actors = new ArrayList<Actor>();//存储Actor
        ArrayList<Location> locs = new ArrayList<Location>();//存储loc

      //获取Grid中的Actor
        Grid<Actor> gr = getGrid();
        Location loc = getLocation();

        // 添加前方的loc
        Location neighborLoc = loc.getAdjacentLocation(getDirection());
        if (gr.isValid(neighborLoc)) {
            locs.add(neighborLoc);
        }
        //添加后方的loc
        neighborLoc = loc.getAdjacentLocation(getDirection() + Location.HALF_CIRCLE);
        if (gr.isValid(neighborLoc)) {
            locs.add(neighborLoc);
        }
        
        //查找locs里面有没有Actor
        for (Location l: locs) {
            Actor a = getGrid().get(l);
            if (a != null) {
                actors.add(a);
            }
        }
        return actors;
    }
}

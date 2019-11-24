

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Actor;

import java.awt.Color;

/**
 * This class runs a world that contains Jumper. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public final class JumperRunner
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        Jumper alice = new Jumper();
        world.add(new Location(5, 6), new Rock());
        world.add(new Location(4, 6), new Flower());
        world.add(new Location(6, 6), alice);
        world.show();
    }
}

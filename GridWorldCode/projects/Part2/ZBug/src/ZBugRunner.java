

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

public class ZBugRunner {
	public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        ZBug bob = new ZBug(4);
        world.add(new Location(3, 3), bob);
        world.show();
    }
}

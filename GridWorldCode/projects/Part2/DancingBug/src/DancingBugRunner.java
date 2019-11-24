


import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

public class DancingBugRunner {
	public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        int[] dancing= {1,1,1,1};
        DancingBug bob = new DancingBug(dancing);
        world.add(new Location(3, 3), bob);
        world.show();
    }
}

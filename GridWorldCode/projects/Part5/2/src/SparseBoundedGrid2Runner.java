import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;
/**
 * This class runs a world with additional grid choices.
 */
public final class SparseBoundedGrid2Runner
{
    private SparseBoundedGrid2Runner() {}
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        // 添加SparseBoundedGrid2到World
        world.addGridClass("SparseBoundedGrid2");
        world.add(new Location(2, 2), new Critter());
        world.show();
    }
}
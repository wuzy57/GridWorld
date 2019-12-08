import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.actor.Critter;

public final class SparseBoundedGridRunner
{
    private SparseBoundedGridRunner() {}
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        // 添加SparseBoundedGrid到world
        world.addGridClass("SparseBoundedGrid");
        world.add(new Location(2, 2), new Critter());
        world.show();
    }
}

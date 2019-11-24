package main;

import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug; 
import info.gridworld.actor.Rock; 

public class BlusterCritterRunner
{
	public static void main(String[] args)
	{
		ActorWorld world = new ActorWorld();
		//添加Jumper、Rock、Bug
		world.add(new BlusterCritter(0));
		world.add(new Rock());
		world.add(new Bug());
		world.add(new Actor());
		//显示
		world.show();
	}
}

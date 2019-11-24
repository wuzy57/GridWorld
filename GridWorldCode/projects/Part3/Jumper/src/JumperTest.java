

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Actor;

public class JumperTest {

	private Jumper alice = new Jumper();
	private Jumper bob = new Jumper();

	/*
   *Test for the method turn().
   *We expected the jumper turn right for 90 degrees after a turn operation.
   * 
   */
	@Test
	public void testTurn() {
		ActorWorld world = new ActorWorld();
		world.add(new Location(6, 6), alice);
		int direction = (alice.getDirection() + Location.RIGHT)%360;
		alice.turn();
		assertEquals(alice.getDirection(), direction);
	}

	/*
   *Test for the method jump(). (Base on canJump() condition)
   *We expected the jumper jump two cells after a jump() operation.
   * 
   */
	@Test
	public void testJump() {
		ActorWorld world = new ActorWorld();
		world.add(new Location(6, 6), alice);
		alice.jump();
		assertEquals(new Location(4, 6), alice.getLocation());
	}

	
	/*
   *Test for the method canJump().
   *We expected the jumper jump two cells for a jump() operation.
   *More details we test in testAct() method.
   *Here we test a simple one.
   */

	@Test
	public void testCanJump() {
		ActorWorld world = new ActorWorld();
		
		/*case1:
		 *next location is a Rock, next two cells location is empty.
		 *we expected canJump() is true.
		 */
		world.add(new Location(6, 1), alice);
		world.add(new Location(5, 1), new Rock());
		assertEquals(true, alice.canJump());
		/*case2:
		 *next location is Rock. next two cells location is flower. 
		 *we expected canJump() is true.
		 */
		alice.moveTo(new Location(6, 2));
		world.add(new Location(5, 2), new Rock());
		world.add(new Location(4, 2), new Flower());
		assertEquals(true, alice.canJump());
		/*case2:
		 *next location is Rock. next two cells location is Rock. 
		 *we expected canJump() is false.
		 */
		alice.moveTo(new Location(6, 3));
		world.add(new Location(5, 3), new Rock());
		world.add(new Location(4, 3), new Rock());
		assertEquals(false, alice.canJump());
	}


  /*
   *Test for the method act().
   *According to the five given problem on vmatrix, we have five cases to test.
   *
   */

  /*case1:
	 *What will a jumper do if the location in front of it is empty,
	 *But the location two cells in front contains a flower or a rock?
	 *Answer:
	 *we expected it jump for the flower case and turn for the rock one
	 */
  @Test
  public void testActOne() {
  	ActorWorld world = new ActorWorld();
   	world.add(new Location(3, 1), alice);
   	world.add(new Location(6, 1), bob);
   	world.add(new Location(1, 1), new Rock());
   	world.add(new Location(4, 1), new Flower());
   	int direction = (alice.getDirection() + Location.RIGHT)%360;
   	alice.act();
   	bob.act();
   	assertEquals(direction, alice.getDirection());
   	assertEquals(new Location(4, 1), bob.getLocation());
  } 

  /*case2:
   *What will a jumper do if the location two cells in front of the jumper is out of the grid?
   *ANSWER: we expected the jumper will turn right for 90 degrees.
   */
  @Test
  public void testActTwo() {
  	ActorWorld world = new ActorWorld();
  	world.add(new Location(1, 1), alice);
  	int direction = (alice.getDirection() + Location.RIGHT)%360;
  	alice.act();
  	assertEquals(direction, alice.getDirection());
  }

  /* case3:
   * What will a jumper do if it is facing an edge of the grid?
   * ANSWER: we expected the jumper will turn right for 90 degrees.
   */
  @Test
  public void testActThree() {
  	ActorWorld world = new ActorWorld();
  	world.add(new Location(0, 1), alice);
  	int direction = (alice.getDirection() + Location.RIGHT)%360;
  	alice.act();
  	assertEquals(direction, alice.getDirection());
  }

  /* case4:
   * What will a jumper do if another actor (not a flower or a rock) is in the cell that is two cells in front of the jumper?
   * ANSWER: we expected the jumper turn right for 90 degrees whatever this another actor is an Actor or jumper.
   */
  @Test
  public void testActFour() {
  	ActorWorld world = new ActorWorld();
  	world.add(new Location(6, 6), alice);
  	world.add(new Location(2, 0), bob);
  	world.add(new Location(4, 6), new Actor());
  	world.add(new Location(0, 0), new Jumper());
  	int direction = (alice.getDirection() + Location.RIGHT)%360;
  	int directionB = (bob.getDirection() + Location.RIGHT)%360;
  	alice.act();
  	bob.act();
  	assertEquals(direction, alice.getDirection());
  	assertEquals(directionB, bob.getDirection());	
  }

  /* case5: 
   * What will a jumper do if it encounters another jumper in its path?
   * ANSWER: 
   * if they jump for the same location: the first jumper who can jump, and the other one turn right.
   * if they just jump by, not for the same location, they can both jump to their own locations. 
   */
  @Test
  public void testActFive(){
  	ActorWorld world = new ActorWorld();
  	
  	//jump for same location:
  	world.add(new Location(6, 4), alice);
  	world.add(new Location(3, 4), bob);
  	bob.setDirection(Location.SOUTH);
  	alice.act();
  	bob.act();
  	assertEquals(new Location(4, 4), alice.getLocation());
  	assertEquals(new Location(5, 4), bob.getLocation());

  	//jump by
  	bob.moveTo(new Location(0, 4));
  	int direction = (alice.getDirection() + Location.RIGHT)%360;
  	bob.act();
  	alice.act();
  	assertEquals(new Location(2, 4), bob.getLocation());
  	assertEquals(direction, alice.getDirection());
  }

}

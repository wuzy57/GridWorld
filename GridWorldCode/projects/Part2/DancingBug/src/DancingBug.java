

import info.gridworld.actor.Bug;
public class DancingBug extends Bug{
	private int steps;
    private int []turnArr;
    private int turnLength;
    
    /**
     * Constructs a box bug that traces a square of a given side length
     * @param length the side length
     */
    public DancingBug (int [] turns)
    {
        steps = 0;
        turnArr=turns;
        turnLength=turns.length;
    }

    /**
     * Moves to the next location of the square.
     */
    public void act()
    {
    	if(steps==turnLength)
    		steps=0;
    	for(int i=0;i<turnArr[steps];i++)
    		turn();
    	steps++;
    	move();
    }
}
